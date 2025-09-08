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

    public void resetWeight() {
        weight = 100;
    }

    public int getWeight() {
        return this.weight;
    }

    public void decrementWeight() {
        this.weight -= 5;
    }

    public int getServings() {
        return this.servings;
    }

    public boolean isFastFriendly() {
        return this.fastFriendly;
    }

    @Override
    public String toString() {
        return recipeName + " " + servings + "\n";
    }

    public String exportRecipe() {
        return recipeName + ", " + fastFriendly + ", " + servings + ", " + weight;
    }

}
