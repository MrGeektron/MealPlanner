import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AddRecipeGUI  implements ActionListener {
    private final JFrame AddRecipeGUIFrame;
    private final String fileName;
    private final JTextField recipeName;
    private final JTextField recipeServings;
    private final JCheckBox fastFreeCheckBox;
    public AddRecipeGUI(String fileName) {
        this.fileName = fileName;
        AddRecipeGUIFrame = new JFrame();
        AddRecipeGUIFrame.setLayout(new BorderLayout());
        AddRecipeGUIFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel recipeNameLabel = new JLabel("Recipe Name");
        JLabel recipeServingsLabel = new JLabel("Number of Servings");
        recipeNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipeServingsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipeName = new JTextField();
        recipeName.setMaximumSize(new Dimension(100, 20));
        recipeServings = new JTextField();
        recipeServings.setMaximumSize(new Dimension(100, 20));
        fastFreeCheckBox = new JCheckBox("Fast-Friendly?");
        fastFreeCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton enterRecipeButton = new JButton("Enter");
        JButton cancelButton = new JButton("Cancel");
        enterRecipeButton.addActionListener(this);
        cancelButton.addActionListener(this);
        enterRecipeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel recipeInputPanel = new JPanel();
        //recipeInputPanel.setBackground(Color.blue);
        recipeInputPanel.setLayout(new BoxLayout(recipeInputPanel, BoxLayout.PAGE_AXIS));
        recipeInputPanel.add(recipeNameLabel);
        recipeInputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        recipeInputPanel.add(recipeName);
        recipeInputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        recipeInputPanel.add(recipeServingsLabel);
        recipeInputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        recipeInputPanel.add(recipeServings);
        recipeInputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        recipeInputPanel.add(fastFreeCheckBox);
        recipeInputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        recipeInputPanel.add(enterRecipeButton);
        recipeInputPanel.add(cancelButton);
        JPanel buttonPanel = new JPanel();
        //buttonPanel.setBackground(Color.yellow);
        buttonPanel.add(enterRecipeButton);
        buttonPanel.add(cancelButton);
        recipeInputPanel.add(buttonPanel);
        JPanel titlePanel = new JPanel();
        //titlePanel.setBackground(Color.red);
        titlePanel.add(new JLabel("<html><b>Add a Recipe</b></html>"));
        AddRecipeGUIFrame.add(titlePanel, BorderLayout.PAGE_START);
        AddRecipeGUIFrame.add(recipeInputPanel, BorderLayout.CENTER);
        AddRecipeGUIFrame.pack();
        AddRecipeGUIFrame.setVisible(true);
    }

    private void writeToFile(Recipe recipe) {
        try {
            File recipesFile = new File(fileName);
            FileWriter recipeWriter = new FileWriter(recipesFile, true);
            recipeWriter.write(recipe.exportRecipe() + "\n");
            recipeWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred.");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Enter")) {
            try {
                String recipeName = this.recipeName.getText();
                int servings = Integer.parseInt(this.recipeServings.getText());
                boolean fastFriendly = this.fastFreeCheckBox.isSelected();
                writeToFile(new Recipe(recipeName, fastFriendly, servings, 100));
                AddRecipeGUIFrame.dispose();
            }
            catch (NumberFormatException exception) {
                //TODO: Display Error Message
            }
        }
        else if(e.getActionCommand().equals("Cancel")) {
            AddRecipeGUIFrame.dispose();
        }
    }
}
