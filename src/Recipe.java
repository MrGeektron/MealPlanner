public class Recipe {

    private final boolean fastFriendly;
    private final int servings;
    private final String recipeName;
    private int weight;

    public Recipe(String recipeName, boolean fastFriendly, int servings, int weight) {
        this.recipeName = recipeName;
        this.fastFriendly = fastFriendly;
        this.servings = servings;
        this.weight = weight;
    }

    /**
     * Resets the recipes weight to the default
     */
    public void resetWeight() {
        weight = 100;
    }

    /**
     * Returns the tracked weight for the recipe.
     * @return Current weight of the recipe.
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * Decrements the weight by one step for the recipe.
     */
    public void decrementWeight() {
        this.weight -= 5;
    }

    /**
     * Returns the number of servings in the recipe.
     * @return Number of servings.
     */
    public int getServings() {
        return this.servings;
    }

    /**
     * Returns true if recipe is fast friendly.
     * @return True if recipe is fast-friendly, false otherwise.
     */
    public boolean isFastFriendly() {
        return this.fastFriendly;
    }

    @Override
    public String toString() {
        return recipeName + " " + servings + "\n";
    }

    /**
     * Returns a string containing the details of the recipe.
     * @return String with recipe data.
     */
    public String exportRecipe() {
        return recipeName + ", " + fastFriendly + ", " + servings + ", " + weight;
    }

}
