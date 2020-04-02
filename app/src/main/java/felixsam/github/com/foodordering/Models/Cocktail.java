package felixsam.github.com.foodordering.Models;

public class Cocktail {
    private String cocktail_name;
    private Integer cocktail_ID;
    private String glass_name;
    private String image_url;

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

}
