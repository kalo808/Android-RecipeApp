package com.adotdamo.android.recipeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by karloandrada on 6/13/16.
 */
public class IngredientFragment extends Fragment {

    private static final String TAG = "IngredientFragment";
    private static final String ARG_RECIPE_ID = "recipe_id";


    private TextView mRecipeid_Textview;
    private TextView mAuthorTextview;
    private ImageView mRecipeImageview;
    private RecyclerView mIngredientRecyclerview;
    private IngredientAdapter mIngredientAdapter;
    private String pageUrl;
    private Button mRecipePageButton;
    private Button mRecipeShareButton;
    private String ingredientmessage;


    private final String url = "http://food2fork.com/api/";

    public static IngredientFragment newInstance(String recipeId)
    {
        Bundle args = new Bundle();
        args.putString(ARG_RECIPE_ID, recipeId);

        IngredientFragment fragment = new IngredientFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fetchRecipe();
        View v = inflater.inflate(R.layout.fragment_ingredient, container,false);
        mIngredientRecyclerview = (RecyclerView)v.findViewById(R.id.ingredient_recylcler_view);
        mIngredientRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecipeid_Textview = (TextView)v.findViewById(R.id.recipe_title_textview);
        mRecipeImageview = (ImageView)v.findViewById(R.id.fragment_ingredient_imagview);
        mAuthorTextview = (TextView)v.findViewById(R.id.ingredient_author_text_view);
        mRecipePageButton = (Button)v.findViewById(R.id.fragment_recipe_web_view_button);
        mRecipeShareButton = (Button)v.findViewById(R.id.fragment_recipe_share_button);


        mRecipePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = RecipePageActivity.newIntent(getActivity(),Uri.parse(pageUrl));
                startActivity(i);
            }
        });

        mRecipeShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,ingredientmessage);
                i= Intent.createChooser(i,getString(R.string.send_message));
                startActivity(i);
            }
        });

        return v;
    }

    private class IngredientHolder extends RecyclerView.ViewHolder
    {

        private TextView mIngredientTextView;
        public IngredientHolder(View ItemView)
        {
            super(ItemView);

            mIngredientTextView = (TextView) ItemView.findViewById(R.id.ingredient_text_view);
        }

        public void bindIngredient(String ingredient)
        {
            mIngredientTextView.setText(ingredient);
        }
    }

    private class IngredientAdapter extends RecyclerView.Adapter<IngredientHolder>
    {
        FoodRecipe mFoodRecipe;

        public IngredientAdapter(FoodRecipe foodRecipe)
        {
            pageUrl =foodRecipe.getSourceUrl();
            mFoodRecipe = foodRecipe;
            ingredientmessage = getMessage(foodRecipe);
        }

        @Override
        public IngredientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
           View view = inflater.inflate(R.layout.ingredient_textview,parent,false);
            return new IngredientHolder(view);
    }

        @Override
        public void onBindViewHolder(IngredientHolder holder, int position) {
            String ingredient = mFoodRecipe.getIngredients().get(position);
            holder.bindIngredient(ingredient);
        }

        @Override
        public int getItemCount() {
            return mFoodRecipe.getIngredients().size();
        }
    }

    private void fetchRecipe()
    {
        String apikey = "205e28b75af6e499cbbf537382678b4f";
        final String recipeId = getArguments().getString(ARG_RECIPE_ID);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(url).build();

        RecipeSearchInterface searchIng = restAdapter.create(RecipeSearchInterface.class);

        searchIng.getRecipe(apikey, recipeId, new Callback<RecipeList>() {

            @Override
            public void success(RecipeList recipeList, Response response) {
                mRecipeid_Textview.setText(recipeList.getFoodRecipe().getTitle());
                mAuthorTextview.setText(recipeList.getFoodRecipe().getPublisher());
                mIngredientAdapter = new IngredientAdapter(recipeList.getFoodRecipe());
                mIngredientAdapter.notifyDataSetChanged();
                mIngredientRecyclerview.setAdapter(mIngredientAdapter);
                Picasso.with(getContext()).load(recipeList.getFoodRecipe().getImageUrl()).placeholder(R.drawable.loading).into(mRecipeImageview);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private String getMessage(FoodRecipe foodRecipe)
    {
        String ingredientsstring = "";

        String title = getString(R.string.recipe_title) +foodRecipe.getTitle().toUpperCase() + System.lineSeparator();

        String message = getString(R.string.recipe_message) + System.lineSeparator();

        for(int i = 0;i < foodRecipe.getIngredients().size();i++)
        {
            ingredientsstring = ingredientsstring + foodRecipe.getIngredients().get(i) + System.lineSeparator();
        }



        String report = title + message +  ingredientsstring;
        return report;
    }
}
