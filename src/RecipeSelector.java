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

    public ArrayList<Recipe> getRecipes(int days, boolean fasting) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        Random random = new Random();
        if(fasting) {
            while(days > 0) {
                int randIndex = random.nextInt(fastingTotalWeight);
                Recipe newRecipe = incrementWeightedRecipe(this.fastingRecipes, randIndex);
                recipes.add(newRecipe);
                fastingTotalWeight -= 5;
                days -= newRecipe.getServings();
            }
        } else {
            while(days > 0) {
                int randIndex = random.nextInt(fastFreeTotalWeight);
                Recipe newRecipe = incrementWeightedRecipe(this.fastFreeRecipes, randIndex);
                recipes.add(newRecipe);
                fastFreeTotalWeight -= 5;
                days -= newRecipe.getServings();
            }

        }
        return recipes;
    }

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

    private Recipe incrementWeightedRecipe(ArrayList<Recipe> recipes, int index) {
        for(Recipe recipe : recipes) {
            index -= recipe.getWeight();
            if(index <= 0) {
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
