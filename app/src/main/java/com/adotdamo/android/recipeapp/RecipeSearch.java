package com.adotdamo.android.recipeapp;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class RecipeSearch {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("recipes")
    @Expose
    private List<Recipe> recipes = new ArrayList<Recipe>();

    /**
     *
     * @return
     * The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     *
     * @return
     * The recipes
     */
    public List<Recipe> getRecipes() {
        return recipes;
    }

    /**
     *
     * @param recipes
     * The recipes
     */
    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
