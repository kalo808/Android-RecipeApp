package com.adotdamo.android.recipeapp;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class FoodRecipe {

    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("f2f_url")
    @Expose
    private String f2fUrl;
    @SerializedName("ingredients")
    @Expose
    private List<String> ingredients = new ArrayList<String>();
    @SerializedName("source_url")
    @Expose
    private String sourceUrl;
    @SerializedName("recipe_id")
    @Expose
    private String recipeId;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("social_rank")
    @Expose
    private Double socialRank;
    @SerializedName("publisher_url")
    @Expose
    private String publisherUrl;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     *
     * @return
     * The publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     *
     * @param publisher
     * The publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     *
     * @return
     * The f2fUrl
     */
    public String getF2fUrl() {
        return f2fUrl;
    }

    /**
     *
     * @param f2fUrl
     * The f2f_url
     */
    public void setF2fUrl(String f2fUrl) {
        this.f2fUrl = f2fUrl;
    }

    /**
     *
     * @return
     * The ingredients
     */
    public List<String> getIngredients() {
        return ingredients;
    }

    /**
     *
     * @param ingredients
     * The ingredients
     */
    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     *
     * @return
     * The sourceUrl
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     *
     * @param sourceUrl
     * The source_url
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    /**
     *
     * @return
     * The recipeId
     */
    public String getRecipeId() {
        return recipeId;
    }

    /**
     *
     * @param recipeId
     * The recipe_id
     */
    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    /**
     *
     * @return
     * The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     *
     * @param imageUrl
     * The image_url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     *
     * @return
     * The socialRank
     */
    public Double getSocialRank() {
        return socialRank;
    }

    /**
     *
     * @param socialRank
     * The social_rank
     */
    public void setSocialRank(Double socialRank) {
        this.socialRank = socialRank;
    }

    /**
     *
     * @return
     * The publisherUrl
     */
    public String getPublisherUrl() {
        return publisherUrl;
    }

    /**
     *
     * @param publisherUrl
     * The publisher_url
     */
    public void setPublisherUrl(String publisherUrl) {
        this.publisherUrl = publisherUrl;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}