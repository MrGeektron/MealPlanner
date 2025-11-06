import java.awt.Component;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DisplayRecipes {

    MealPlannerGUI mealPlannerGUI;
    public DisplayRecipes(ArrayList<Recipe> recipes, MealPlannerGUI mealPlannerGUI) {
        this.mealPlannerGUI = mealPlannerGUI;
        JFrame recipeDisplay = new JFrame();
        JPanel recipePanel = new JPanel();
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.PAGE_AXIS));
        recipeDisplay.setVisible(true);
        recipeDisplay.setSize(500,250);
        JTextArea recipeList = new JTextArea(buildRecipeList(recipes));
        recipeList.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipePanel.add(recipeList);
        JButton regenerateRecipesButton = new JButton("Regenerate Recipes");
        regenerateRecipesButton.setActionCommand("Regenerate Recipes");
        regenerateRecipesButton.addActionListener(mealPlannerGUI);
        regenerateRecipesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipePanel.add(regenerateRecipesButton);
        recipeDisplay.add(recipePanel);
    }
    private String buildRecipeList(ArrayList<Recipe> recipes) {
        StringBuilder recipelist = new StringBuilder();
        recipelist.append("<html>Selected Recipes<ul>");
        for(Recipe recipe : recipes) {
            recipelist.append("<li>").append(recipe);
        }
        recipelist.append("</ul></html>");
        return recipelist.toString();
    }

}
