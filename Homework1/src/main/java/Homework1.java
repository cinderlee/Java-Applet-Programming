/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cindylee
 */
public class Homework1 {
    public static void main(String[] args) {
        Recipe cookie = new Recipe();

        cookie.addIngredient(new Ingredient(1.0, "salted butter"));
        cookie.addIngredient(new Ingredient(1.0, "white sugar"));
        cookie.addIngredient(new Ingredient(1.0, "light brown sugar"));
        cookie.addIngredient(new Ingredient(2.0, "pure vanilla extract"));
        cookie.addIngredient(new Ingredient(2.0, "large eggs"));
        cookie.addIngredient(new Ingredient(3.0, "flour"));
        cookie.addIngredient(new Ingredient(1.0, "baking soda"));
        cookie.addIngredient(new Ingredient(0.5, "baking powder"));
        cookie.addIngredient(new Ingredient(1.0, "sea salt"));
        cookie.addIngredient(new Ingredient(2.0, "chocolate chips"));
        
        cookie.addStep("Preheat oven to 375 degrees F. Line a baking pan with parchment paper and set aside.");
        cookie.addStep("In a separate bowl mix flour, baking soda, salt, baking powder. Set aside.");
        cookie.addStep("Cream together butter and sugars until combined.");
        cookie.addStep("Beat in eggs and vanilla until fluffy.");
        cookie.addStep("Mix in the dry ingredients until combined.");
        cookie.addStep("Add 12 oz package of chocolate chips and mix well.");
        cookie.addStep("Roll 2-3 TBS of dough at a time into balls and place them evenly spaced on your prepared cookie sheets!");
        cookie.addStep("Bake in preheated oven for approximately 8-10 minutes. Take them out when they are just BARELY starting to turn brown.");
        cookie.addStep("Let them sit on the baking pan for 2 minutes before removing to cooling rack.");
        
        System.out.println("Cookie Recipe");
        System.out.println(cookie);
        
        
        Recipe tea = new Recipe(2, 3);
        tea.addIngredient(new Ingredient(1.0, "tea bag"));
        tea.addIngredient(new Ingredient(2.0, "water"));
        
        tea.addStep("Boil the water");
        tea.addStep("Place tea bag into a cup");
        tea.addStep("Pour water inside the cup");
        System.out.println("Tea Recipe");
        System.out.println(tea);
    }
}

class Ingredient{
    double measure;
    String item;
    
    Ingredient(double measurement, String itemName){
        measure = measurement;
        item = itemName;
    }
    
    @Override
    public String toString() {
        return measure + " " + item;
    }
}

class Recipe {
    Ingredient[] ingredients;
    String[] steps;
    int ingredientCounter;
    int stepCounter; 
    
    Recipe(){
        ingredients = new Ingredient[20];
        steps = new String[20];
        ingredientCounter = 0;
        stepCounter = 0;
    }
    
    Recipe(int ingredientsNum, int stepsNum){
        ingredients = new Ingredient[ingredientsNum];
        steps = new String[stepsNum];
        ingredientCounter = 0;
        stepCounter = 0;
    }
    
    void addIngredient(Ingredient newIngredient){
        if (ingredientCounter == ingredients.length){
            System.out.println("You ran out of space to add ingredients.");
        }
        else {
            ingredients[ingredientCounter] = newIngredient;
            ingredientCounter++;
        }
    }
    
    void addStep(String newStep){
        if (stepCounter == steps.length){
            System.out.println("You ran out of space to add steps.");
        }
        else {
            steps[stepCounter] = newStep;
            stepCounter++;
        }
    }
    
    @Override
    public String toString() {
        String s = "";
        for (int itemCount = 0; itemCount < ingredientCounter; itemCount++){
            s += ingredients[itemCount] + "\r\n";
        }
        s += "\r\n\r\n";
        for (int moveCount = 0; moveCount < stepCounter; moveCount++){
            s += steps[moveCount] + "\r\n";
        }
        return s;
    }
}