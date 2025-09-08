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

    /**
     * Loads Recipes from the "Recipes" file and stores them for later querying.
     */
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

    /**
     * Prompts user for input and prints a list of randomly selected weighted recipes.
     */
    public void receiveInput() {
        totalFastMeals = receiveMeals("Fast Meals");
        totalFastFreeMeals = receiveMeals("Fast Free Meals");
        printSelectedRecipes();
        MealPlannerGUI UI = new MealPlannerGUI();
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
     * Writes recipes changes to the "Recipes" file.
     */
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

    /**
     * Prints the prompt for user to provide number of fast or fast-free meals.
     * @param mealString A description of the meal to be added at the end of the prompt.
     * @return The number of meals for the given prompt.
     */
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
