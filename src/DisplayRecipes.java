import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayRecipes {

    public DisplayRecipes(ArrayList<Recipe> recipes) {
        JFrame recipeDisplay = new JFrame();
        JPanel recipePanel = new JPanel();
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.PAGE_AXIS));
        recipeDisplay.setVisible(true);
        recipeDisplay.setSize(500,250);
        JLabel label = new JLabel(buildRecipeList(recipes));
        recipeDisplay.add(recipePanel);
        recipeDisplay.add(label);
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
