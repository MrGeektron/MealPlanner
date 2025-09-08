import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeManager {
    int totalFastMeals;
    int totalFastFreeMeals;
    ArrayList<Recipe> fastingRecipes;
    ArrayList<Recipe> fastFreeRecipes;

    public RecipeManager() {
        this.totalFastMeals = 0;
        this.totalFastFreeMeals = 0;
        fastingRecipes = new ArrayList<>();
        fastFreeRecipes = new ArrayList<>();
    }

    public void loadRecipes() {
        try {
            File recipeFile = new File("src/Recipes");
            Scanner recipeReader = new Scanner(recipeFile);
            while (recipeReader.hasNextLine()) {
                String recipeInfo = recipeReader.nextLine();
                String [] recipes = recipeInfo.split(", ");
                Recipe recipe = new Recipe(recipes[0], Boolean.parseBoolean(recipes[1]), Integer.parseInt(recipes[2]), Integer.parseInt(recipes[3]));
                if(recipe.isFastFriendly()) {
                    this.fastingRecipes.add(recipe);
                }
                else {
                    this.fastFreeRecipes.add(recipe);
                }

            }
        } catch(FileNotFoundException error) {
            System.err.println("File Not Found.");
            error.printStackTrace();
        }
    }

    public void receiveInput() {
        totalFastMeals = receiveMeals("Fast Meals");
        totalFastFreeMeals = receiveMeals("Fast Free Meals");
        printSelectedRecipes();
        writeToFile();
    }

    public void printSelectedRecipes() {
        RecipeSelector recipeSelector = new RecipeSelector(fastingRecipes, fastFreeRecipes);
        System.out.println(recipeSelector.getRecipes(totalFastMeals, true));
        System.out.println(recipeSelector.getRecipes(totalFastFreeMeals, false));
    }

    private void writeToFile() {
        try {
            File recipesFile = new File("src/Recipes");
            FileWriter recipeWriter = new FileWriter(recipesFile);
            for(Recipe recipe : fastFreeRecipes) {
                recipeWriter.write(recipe.exportRecipe() + "\n");
            }
            for(Recipe recipe : fastingRecipes) {
                recipeWriter.write(recipe.exportRecipe() + "\n");
            }
            recipeWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred.");
        }
    }

    public int receiveMeals(String mealString) {
        Scanner userInput = new Scanner(System.in);
        int days;
        System.out.println("Provide the number of " + mealString + ":");
        while(!userInput.hasNextInt()) {
            userInput.nextLine();
            System.out.println("You entered an invalid input.");
            System.out.println("Provide the number of " + mealString + ":");
        }
        days = userInput.nextInt();
        userInput.nextLine();
        return days;
    }

}
