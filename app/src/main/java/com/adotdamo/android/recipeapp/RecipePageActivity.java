package com.adotdamo.android.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 * Created by karloandrada on 6/16/16.
 */
public class RecipePageActivity extends SingleFragmentActivity {
    public static Intent newIntent(Context context, Uri recipePageUri)
    {
        Intent i = new Intent(context, RecipePageActivity.class);
        i.setData(recipePageUri);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        return RecipePageFragment.newInstance(getIntent().getData());
    }
}
