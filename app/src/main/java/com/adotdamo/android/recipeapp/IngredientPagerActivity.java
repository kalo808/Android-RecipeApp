package com.adotdamo.android.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by karloandrada on 6/15/16.
 */
public class IngredientPagerActivity extends AppCompatActivity {

    private static String EXTRA_IDS_ARRAY_LIST = "com.adotdamo.android.recipeapp.recipe_ids";
    private static String EXTRA_RECIPE_ID = "com.adotdamo.android.recipeapp.recipe_id";

    private ViewPager mViewPager;
    private ArrayList mRecipes = new ArrayList<String>();

    public static Intent newIntent(Context packageContext, ArrayList<String> recipeiIds,String recipeId)
    {


        Intent intent = new Intent(packageContext,IngredientPagerActivity.class);
        intent.putExtra(EXTRA_RECIPE_ID,recipeId);
        intent.putStringArrayListExtra(EXTRA_IDS_ARRAY_LIST,recipeiIds);


        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_pager);
        mRecipes = getIntent().getStringArrayListExtra(EXTRA_IDS_ARRAY_LIST);
        String recipeId = getIntent().getStringExtra(EXTRA_RECIPE_ID);
        mViewPager = (ViewPager) findViewById(R.id.activity_ingredient_pager_view_pager);

        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                String RecipeId =(String) mRecipes.get(position);
                return IngredientFragment.newInstance(RecipeId);
            }

            @Override
            public int getCount() {
              return  mRecipes.size();
            }
        });

        for (int i = 0; i < mRecipes.size();i++)
        {
            if (mRecipes.get(i).equals(recipeId))
            {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }


}
