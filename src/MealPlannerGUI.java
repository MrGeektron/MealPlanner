import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class MealPlannerGUI implements ActionListener {

    private JLabel fastMealsDisplayLabel;
    private JLabel fastFreeMealsDisplayLabel;
    private int fastMeals;
    private int fastFreeMeals;
    private JTextField fastMealsInputField;
    private JTextField fastFreeMealsInputField;
    private final RecipeManager recipeManager;
    private JButton generateRecipesButton;

    public MealPlannerGUI(RecipeManager recipeManager) {
        this.recipeManager = recipeManager;
        createMealInputWindow();
    }

    private void createMealInputWindow() {
        JFrame mealSelector = new JFrame();
        mealSelector.setSize(500,250);
        mealSelector.setLayout(new BorderLayout());
        mealSelector.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel fastMealsText = new JLabel("Enter the number of fasting meals");
        JLabel fastFreeMealsText = new JLabel("Enter the number of fast-free meals");
        fastMealsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        fastFreeMealsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        initializeFastMealsInput();
        JButton enterButton = new JButton("Enter");
        enterButton.setActionCommand("Enter");
        enterButton.addActionListener(this);
        enterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        initializeGenerateRecipesButton();
        JPanel textInputPanel = new JPanel();
        textInputPanel.setLayout(new BoxLayout(textInputPanel, BoxLayout.PAGE_AXIS));
        textInputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textInputPanel.add(fastMealsText);
        textInputPanel.add(Box.createRigidArea(new Dimension(0,5)));
        textInputPanel.add(fastMealsInputField);
        textInputPanel.add(Box.createRigidArea(new Dimension(0,5)));
        textInputPanel.add(fastFreeMealsText);
        textInputPanel.add(Box.createRigidArea(new Dimension(0,5)));
        textInputPanel.add(fastFreeMealsInputField);
        textInputPanel.add(Box.createRigidArea(new Dimension(0,5)));
        textInputPanel.add(enterButton);
        textInputPanel.add(Box.createRigidArea(new Dimension(0,5)));
        textInputPanel.add(fastMealsDisplayLabel);
        textInputPanel.add(Box.createRigidArea(new Dimension(0,5)));
        textInputPanel.add(fastFreeMealsDisplayLabel);
        textInputPanel.add(Box.createRigidArea(new Dimension(0,5)));
        textInputPanel.add(generateRecipesButton);
        mealSelector.add(textInputPanel, BorderLayout.CENTER);
        textInputPanel.setVisible(true);
        mealSelector.setVisible(true);
    }

    private void initializeGenerateRecipesButton() {
        this.generateRecipesButton = new JButton("Generate Recipes");
        generateRecipesButton.setActionCommand("Generate Recipes");
        generateRecipesButton.addActionListener(this);
        generateRecipesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateRecipesButton.setEnabled(false);
    }

    public void initializeFastMealsInput() {
        this.fastMealsInputField = new JTextField();
        this.fastMealsInputField.setHorizontalAlignment(SwingConstants.CENTER);
        this.fastMealsInputField.setMaximumSize(new Dimension(100, 100));
        this.fastFreeMealsInputField = new JTextField();
        this.fastFreeMealsInputField.setHorizontalAlignment(SwingConstants.CENTER);
        this.fastFreeMealsInputField.setMaximumSize(new Dimension(100, 100));
        this.fastMealsDisplayLabel = new JLabel();
        this.fastFreeMealsDisplayLabel = new JLabel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Enter")) {
            try {
            this.fastMeals = Integer.parseInt(fastMealsInputField.getText());
            fastMealsDisplayLabel.setText("Fast Meals: " + fastMealsInputField.getText());
            fastMealsInputField.setEnabled(false);
            this.generateRecipesButton.setEnabled(true);
            } catch (NumberFormatException exception) {
                //TODO: Display Error Message
            }
            try {
                this.fastFreeMeals = Integer.parseInt(fastFreeMealsInputField.getText());
                fastFreeMealsDisplayLabel.setText("Fast-free Meals: " + fastFreeMealsInputField.getText());
                fastFreeMealsInputField.setEnabled(false);
                this.generateRecipesButton.setEnabled(true);
            } catch (NumberFormatException exception) {
                //TODO: Display Error Message
            }
        }
        if(e.getActionCommand().equals("Generate Recipes")) {
            DisplayRecipes displayRecipes = new DisplayRecipes(recipeManager.selectedRecipes(fastMeals, fastFreeMeals));
        }
    }
}
