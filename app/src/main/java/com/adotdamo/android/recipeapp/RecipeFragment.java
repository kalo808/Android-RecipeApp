package com.adotdamo.android.recipeapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by karloandrada on 6/9/16.
 */
public class RecipeFragment extends Fragment {

    private RecyclerView mRecipeRecyclerView;
    private List<Recipe> mRecipes;
    private RecipeAdapter mRecipeAdapter;
    private String url = "http://food2fork.com/api/";
    private ArrayList mRecipeids = new ArrayList<String>();

    private static final String TAG = "RECIPE_FRAGMENT";


    public static RecipeFragment newInstance()
    {

        return new RecipeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recipe, container,false);
        updateItems();
        mRecipeRecyclerView = (RecyclerView) v.findViewById(R.id.recipe_recycler_view);
        mRecipeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_recipe, menu);

        final MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                QueryPreferences.setStoredQuery(getActivity(),query);
                updateItems();
                return true;
            }



            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = QueryPreferences.getStoredQuery(getActivity());
                searchView.setQuery(query,false);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchView.clearFocus();
                QueryPreferences.setStoredQuery(getActivity(),null);
                updateItems();
                return true;
            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_clear:
                QueryPreferences.setStoredQuery(getActivity(), null);
                updateItems();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class RecipeHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private Recipe mRecipe;
        private ImageView mImageView;
        private TextView mRecipeTitleTextView;

        public RecipeHolder(View itemView)
        {
            super(itemView);

            mRecipeTitleTextView = (TextView) itemView.findViewById(R.id.recipe_image_title);

           mImageView = (ImageView) itemView.findViewById(R.id.fragment_recipe_image_view);
            mImageView.setOnClickListener(this);
        }

        public void bindRecipe(Recipe recipe)
        {
            mRecipeTitleTextView.setText(recipe.getTitle());
            Picasso.with(getContext()).load(recipe.getImageUrl()).placeholder(R.drawable.loading).into(mImageView);
            mRecipe = recipe;
        }

        @Override
        public void onClick(View v) {

            mImageView = (ImageView)v.findViewById(R.id.fragment_recipe_image_view);

            Intent intent = IngredientPagerActivity.newIntent(getActivity(),mRecipeids,mRecipe.getRecipeId());
            startActivity(intent);




        }
    }

    private class RecipeAdapter extends RecyclerView.Adapter<RecipeHolder> {
        private List<Recipe> mRecipes;



        public RecipeAdapter(List<Recipe> recipes)
        {
            mRecipes = recipes;


            for(int i = 0; i < mRecipes.size(); i++)
            {
                mRecipeids.add(recipes.get(i).getRecipeId());
            }
        }


        @Override
        public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.recipe_image,parent,false);
            return  new RecipeHolder(view);
        }

        @Override
        public void onBindViewHolder(RecipeHolder holder, int position) {
            Recipe recipe = mRecipes.get(position);
            holder.bindRecipe(recipe);
        }

        @Override
        public int getItemCount() {
            return mRecipes.size();
        }
    }

    private void FetchRecipes(String query)
    {
        String apikey = "205e28b75af6e499cbbf537382678b4f";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(url).build();



        RecipeSearchInterface searchInt = restAdapter.create(RecipeSearchInterface.class);

        if(query == null) {
            searchInt.getTopChoices(apikey, new Callback<RecipeSearch>() {


                @Override
                public void success(RecipeSearch recipeSearch, Response response) {
                    mRecipeAdapter = new RecipeAdapter(recipeSearch.getRecipes());
                    mRecipeAdapter.notifyDataSetChanged();
                    mRecipeRecyclerView.setAdapter(mRecipeAdapter);

                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }else {
            searchInt.getSearch(apikey, query, new Callback<RecipeSearch>() {
                @Override
                public void success(RecipeSearch recipeSearch, Response response) {
                    mRecipeAdapter = new RecipeAdapter(recipeSearch.getRecipes());
                    mRecipeAdapter.notifyDataSetChanged();
                    mRecipeRecyclerView.setAdapter(mRecipeAdapter);

                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }

    }

    public void updateItems()
    {
        String Query = QueryPreferences.getStoredQuery(getActivity());
        FetchRecipes(Query);
    }
}
