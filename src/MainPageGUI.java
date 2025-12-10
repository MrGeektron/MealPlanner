import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainPageGUI implements ActionListener {

    private final RecipeManager recipeManager;
    private final String recipeFile;

    public MainPageGUI() {
        recipeFile = "src/Recipes";
        recipeManager = new RecipeManager(recipeFile);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        JButton addButton = new JButton("Add Recipe");
        addButton.setActionCommand("Add Recipe");
        addButton.addActionListener(this);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton generateRecipesButton = new JButton("Generate Recipes");
        generateRecipesButton.setActionCommand("Generate Recipes");
        generateRecipesButton.addActionListener(this);
        generateRecipesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(frame.getHeight() / 3, 50, 100, 50));
        menuPanel.add(addButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(generateRecipesButton);
        frame.add(menuPanel, BorderLayout.CENTER);
        menuPanel.setVisible(true);
        frame.setVisible(true);
    }

    private void generateRecipes() {
     recipeManager.loadRecipes();
     recipeManager.receiveInput();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Generate Recipes")) {
         generateRecipes();
        }
        else if(e.getActionCommand().equals("Add Recipe")) {
        new AddRecipeGUI(recipeFile);
        }
    }
}
