package android.example.final_project;

public class Game{
    public String name;
    public String description;
    public String image;
    public String ingredient;
    public String Date;
    public String Developer;
    public String Publisher;
    public String procedure;
    public Game(String name, String description, String image, String ingredients, String procedure,String Date,String Developer, String Publisher) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.ingredient = ingredients;
        this.Date=Date;
        this.Developer=Developer;
        this.Publisher=Publisher;
        this.procedure = procedure;
    }
}
