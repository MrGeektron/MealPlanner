import java.util.ArrayList;
import java.util.Random;

public class RecipeSelector {

    private final ArrayList<Recipe> fastingRecipes;
    private final ArrayList<Recipe> fastFreeRecipes;
    private int fastingTotalWeight;
    private int fastFreeTotalWeight;
    public RecipeSelector(ArrayList<Recipe> fastingRecipes, ArrayList<Recipe> fastFreeRecipes) {
        this.fastingRecipes = fastingRecipes;
        this.fastFreeRecipes = fastFreeRecipes;
        fastingTotalWeight = 0;
        fastFreeTotalWeight = 0;
        for(Recipe recipe: this.fastingRecipes){
            fastingTotalWeight += recipe.getWeight();
        }
        for(Recipe recipe: this.fastFreeRecipes){
            fastFreeTotalWeight += recipe.getWeight();
        }
    }

    /**
     * Randomly select a recipe from the fast-friendly or fast-free recipes based on the recipes current weights.
     * @param meals Number of meals to provide recipes for.
     * @param fasting Whether the meals are selected from the fast-friendly or fast-free recipes.
     * @return A list of the recipes selected randomly by weight.
     */
    public ArrayList<Recipe> getRecipes(int meals, boolean fasting) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        Random random = new Random();
        if(fasting) {
            while(meals > 0) {
                int randIndex = random.nextInt(fastingTotalWeight);
                Recipe newRecipe = selectWeightedRecipe(this.fastingRecipes, randIndex);
                recipes.add(newRecipe);
                fastingTotalWeight -= 5;
                meals -= newRecipe.getServings();
            }
        } else {
            while(meals > 0) {
                int randIndex = random.nextInt(fastFreeTotalWeight);
                Recipe newRecipe = selectWeightedRecipe(this.fastFreeRecipes, randIndex);
                recipes.add(newRecipe);
                fastFreeTotalWeight -= 5;
                meals -= newRecipe.getServings();
            }

        }
        return recipes;
    }

    /**
     * Resets the weights of the provided recipes to their default and updates the total weight to the default based on the number of recipes..
     * @param recipes A list of recipes to reset the weights to default.
     */
    private void resetWeights(ArrayList<Recipe> recipes) {
        for(Recipe recipe : recipes) {
            recipe.resetWeight();
        }
        if(recipes.get(0).isFastFriendly()) {
            fastingTotalWeight = recipes.size() * 100;
        }
        else {
            fastFreeTotalWeight = recipes.size() * 100;
        }
    }

    /**
     * Increments through a list of recipes until a random recipe is selected based on weight.
     * @param recipes The list of recipes to traverse through.
     * @param weight The total weight to check against when traversing recipes.
     * @return A recipe that matches the provided weight.
     */
    private Recipe selectWeightedRecipe(ArrayList<Recipe> recipes, int weight) {
        for(Recipe recipe : recipes) {
            weight -= recipe.getWeight();
            if(weight <= 0) {
                recipe.decrementWeight();
                if(recipe.getWeight() <= 0) {
                    resetWeights(recipes);
                }
                return recipe;
            }
        }
        return null;
    }

}
