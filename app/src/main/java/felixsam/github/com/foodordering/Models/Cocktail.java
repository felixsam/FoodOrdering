package felixsam.github.com.foodordering.Models;

import java.util.ArrayList;

public class Cocktail {
    private String cocktail_name;
    private Integer cocktail_ID;
    private String glass_name;
    private String image_url;
    private String instructions;
    private ArrayList<String> ingredients;
    private String category;
    private String alcoholic;

    public Cocktail(String name, Integer ID, String glass){
        cocktail_name = name;
        cocktail_ID = ID;
        glass_name = glass;
    }
    public String getCocktail_name() {
        return cocktail_name;
    }

    public void setCocktail_name(String cocktail_name) {
        this.cocktail_name = cocktail_name;
    }

    public Integer getCocktail_ID() {
        return cocktail_ID;
    }

    public void setCocktail_ID(Integer cocktail_ID) {
        this.cocktail_ID = cocktail_ID;
    }

    public String getGlass_name() {
        return glass_name;
    }

    public void setGlass_name(String glass_name) {
        this.glass_name = glass_name;
    }


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(String alcoholic) {
        this.alcoholic = alcoholic;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getIngredients_single_string(){
        String result = "";
        ArrayList<String> items =  getIngredients();

        for (String ingredient : items){
            if (ingredient != "null"){
                result  = result + "\n" + ingredient;
            }
        }

        return result;
    }
}
