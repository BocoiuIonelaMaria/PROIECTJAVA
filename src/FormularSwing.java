import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class FormularSwing extends JFrame {
    private JTextField brandTextField;
    private JTextField sizeTextField;
    private JTextField materialTextField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JTextField styleTextField;

    public FormularSwing() {
        setTitle("Shoe Form with Swing");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create UI elements
        brandTextField = new JTextField(20);
        sizeTextField = new JTextField(20);
        materialTextField = new JTextField(20);
        maleRadioButton = new JRadioButton("Barbati");
        femaleRadioButton = new JRadioButton("Femei");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        styleTextField = new JTextField(20);

        JButton saveButton = new JButton("Salveaza");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToJSON();
            }
        });

        JButton cancelButton = new JButton("Anuleaza");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add elements to panel with FlowLayout
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.add(new JLabel("Brand:"));
        panel.add(brandTextField);
        panel.add(new JLabel("Marimea:"));
        panel.add(sizeTextField);
        panel.add(new JLabel("Materialul Pantofului:"));
        panel.add(materialTextField);
        panel.add(new JLabel("Categorie:"));
        panel.add(maleRadioButton);
        panel.add(femaleRadioButton);
        panel.add(saveButton);
        panel.add(cancelButton);

        // Add panel to the main window
        add(panel);

        // Set the appearance of radio buttons
        UIManager.put("RadioButton.focus", UIManager.get("CheckBox.focus"));

        setVisible(true);
    }

    private void saveToJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Brand", brandTextField.getText());
        jsonObject.put("Size", sizeTextField.getText());
        jsonObject.put("Material", materialTextField.getText());
        jsonObject.put("Gender", maleRadioButton.isSelected() ? "Barbati" : "Femei");


        try (FileWriter fileWriter = new FileWriter("shoe_data.json", true)) {
            fileWriter.write(jsonObject.toJSONString() + "\n");
            JOptionPane.showMessageDialog(this, "Data saved successfully to JSON file!", "Save Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data to JSON file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularSwing();
            }
        });
    }
}
