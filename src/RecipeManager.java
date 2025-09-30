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
    String fileName;

    public RecipeManager() {
        this.totalFastMeals = 0;
        this.totalFastFreeMeals = 0;
        fastingRecipes = new ArrayList<>();
        fastFreeRecipes = new ArrayList<>();
        this.fileName = "src/Recipes";
    }

    /**
     * Loads Recipes from the "Recipes" file and stores them for later querying.
     */
    public void loadRecipes() {
        try {
            File recipeFile = new File(fileName);
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

    /**
     * Prompts user for input and prints a list of randomly selected weighted recipes.
     */
    public void receiveInput() {
        MealPlannerGUI UI = new MealPlannerGUI(this);
        printSelectedRecipes();
        writeToFile();
    }

    /**
     * Prints randomly selected weighted recipes.
     */
    public void printSelectedRecipes() {
        RecipeSelector recipeSelector = new RecipeSelector(fastingRecipes, fastFreeRecipes);
        System.out.println(recipeSelector.getRecipes(totalFastMeals, true));
        System.out.println(recipeSelector.getRecipes(totalFastFreeMeals, false));
    }

    /**
     * Generates randomly selected recipes and returns the recipes.
     * @param totalFastMeals The number of servings of fast meals to generate
     * @param totalFastFreeMeals The number of servings of fast-free meals to generate
     * @return A String with the fast and fast-free meals
     */
    public ArrayList<Recipe> selectedRecipes(int totalFastMeals, int totalFastFreeMeals) {
        RecipeSelector recipeSelector = new RecipeSelector(fastingRecipes, fastFreeRecipes);
        ArrayList<Recipe> recipes = recipeSelector.getRecipes(totalFastMeals, true);
        recipes.addAll(recipeSelector.getRecipes(totalFastFreeMeals, false));
        writeToFile();
        return recipes;
    }

    public ArrayList<Recipe> previewRecipes (int totalFastMeals, int totalFastFreeMeals) {
        return null;
    }

    /**
     * Writes recipes changes to the "Recipes" file.
     */
    private void writeToFile() {
        try {
            File recipesFile = new File(fileName);
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
}
