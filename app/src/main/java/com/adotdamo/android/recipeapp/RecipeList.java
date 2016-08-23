package com.adotdamo.android.recipeapp;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class RecipeList {

    @SerializedName("recipe")
    @Expose
    private FoodRecipe foodRecipe;

    /**
     * @return The foodRecipe
     */
    public FoodRecipe getFoodRecipe() {
        return foodRecipe;
    }

    /**
     * @param foodRecipe The FoodRecipe
     */
    public void setFoodRecipe(FoodRecipe foodRecipe) {
        this.foodRecipe = foodRecipe;
    }
}