package com.example.foodtip.Model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class Recepta {

    private final String id;
    private String description;
    private String title;
    private ArrayList<SliderData> images;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;
    private ArrayList<String> likes;
    private Map<String, Comentari> comentaris;


    public Recepta(String description, String title, ArrayList<SliderData> images, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, ArrayList<String> likes, Map<String, Comentari> comentaris) {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.description = description;
        this.title = title;
        this.images = images;
        this.ingredients = ingredients;
        this.steps = steps;
        this.likes = likes;
        this.comentaris = comentaris;
    }

    public Recepta(String id, String description, String title, ArrayList<SliderData> images, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, ArrayList<String> likes, Map<String, Comentari> comentaris) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.images = images;
        this.ingredients = ingredients;
        this.steps = steps;
        this.likes = likes;
        this.comentaris = comentaris;
    }

    //Getter i Setter
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<SliderData> getImages() {
        return images;
    }

    public void setImages(ArrayList<SliderData> images) {
        this.images = images;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public ArrayList<String> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<String> likes) {
        this.likes = likes;
    }

    public Map<String, Comentari> getComentaris() {
        return comentaris;
    }

    public void setComentaris(Map<String, Comentari> comentaris) {
        this.comentaris = comentaris;
    }
}
