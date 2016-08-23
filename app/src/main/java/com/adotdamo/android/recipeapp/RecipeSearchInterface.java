package com.adotdamo.android.recipeapp;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by karloandrada on 6/9/16.
 */
public interface RecipeSearchInterface {

    @GET("/search")
    public void getTopChoices(@Query("key") String apikey, Callback<RecipeSearch> response);

    @GET("/search")
    public void getSearch(@Query("key") String apikey, @Query("q") String query,Callback<RecipeSearch> response);


    @GET("/get")
    public void getRecipe(@Query("key") String apikey, @Query("rId") String ingredientId,Callback<RecipeList> response);

}
