package gubenia;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

/**
 * GUI Application Form Using GUI Components
 * CMIS242 Project 2
 * @author Steve Gubenia
 */
public class JobApplication extends JFrame {
    // Create labels and text fields for applicants name
    private JLabel jlbFirstName = new JLabel ("First Name:");
    private JLabel jlbLastName = new JLabel ("Last Name:");
    private JTextField jtfFirstName = new JTextField(10);
    private JTextField jtfLastName = new JTextField(10);
    
    // Create radio buttons and label for gender
    private JLabel jlbGender = new JLabel("Gender:");
    private JRadioButton jrbMale = new JRadioButton("Male");
    private JRadioButton jrbFemale = new JRadioButton("Female");
    
    //Create Text Area to display applicant information
    JTextArea jtaInfo = new JTextArea(6,30);
    
    // Create Text Field to display applicant information
    
    JTextField jtfSalary = new JTextField("50,000");
    
    //Create a slider to set salary
    private JSlider jsldSalary = new JSlider(JSlider.HORIZONTAL);
    
    //Create Button to sumbit information
    JButton jbtSubmit = new JButton("Submit");
    
    public JobApplication(){
    
    //Create panel for slider and salary textfield
    JPanel jpSalary = new JPanel(new GridLayout(2,1));
    jpSalary.add(jtfSalary);
    jtfSalary.setEditable(false);
    jpSalary.add(jsldSalary);
    jpSalary.setBorder(new TitledBorder("Desired Salary"));
    add(jpSalary,BorderLayout.SOUTH);
    
    // Set properties for slider
    jsldSalary.setMaximum(100);
    jsldSalary.setPaintLabels(true);
    jsldSalary.setPaintTicks(true);
    jsldSalary.setMajorTickSpacing(10);
    jsldSalary.setMinorTickSpacing(1);
    jsldSalary.setPaintTrack(true);
    
    
    // Create a panel to hold radio buttons
    JPanel jpRadioButtons = new JPanel();
    jpRadioButtons.add(jlbGender);
    jpRadioButtons.add(jrbMale);
    jpRadioButtons.add(jrbFemale);
    
    //Create Button group
    ButtonGroup gender = new ButtonGroup();
    gender.add(jrbMale);
    gender.add(jrbFemale);
    
    // Set initial value to Male
    jrbMale.setSelected(true);
        
    // Create Combo Box for applicants age
    List<Integer> age = new ArrayList<>();
        for (int i = 15; i <= 100; ++i) {
        age.add(i);
        }
    final JComboBox jcbAge = new JComboBox(age.toArray());
    jcbAge.setSelectedItem(36);
    
    // Create a panel to hold combo box
    JPanel jpAge = new JPanel();
    jpAge.add(new JLabel("Age:"));
    jpAge.add(jcbAge);
    
    // Create panel to hold Name Labels and Fields
    JPanel jpName = new JPanel();
    jpName.setLayout(new GridLayout(2,2));
    jpName.add(jlbFirstName);
    jpName.add(jtfFirstName);
    jpName.add(jlbLastName);
    jpName.add(jtfLastName);
    
    // Create a panel to hold name Gender, and Ate
    JPanel jpNameAgeGender = new JPanel();
    jpNameAgeGender.setLayout(new GridLayout(3,1));
    jpNameAgeGender.add(jpName);
    jpNameAgeGender.add(jpRadioButtons);
    jpNameAgeGender.add(jpAge);
    
    // Create Panel to hold Text Area
    JPanel jpTextArea = new JPanel();
    jpTextArea.add(jtaInfo);
    jtaInfo.setEditable(false);
    
    // Create Panel for place holder
    JPanel jpBlank = new JPanel();
    
    //Create Panel for button
    JPanel jpButton = new JPanel();
    jpButton.setLayout(new BorderLayout());
    jpButton.add(jbtSubmit, BorderLayout.WEST);
    add(jpButton, BorderLayout.SOUTH);
    
    // Create Panel to hold all components
    JPanel jpMain = new JPanel();
    jpMain.setLayout(new GridLayout(2,2));
    jpMain.add(jpNameAgeGender);
    jpMain.add(jpTextArea);
    jpMain.add(jpSalary);
    jpMain.add(jpBlank);
    jpMain.setBorder(new TitledBorder("Appliccant Information"));
    add(jpMain, BorderLayout.CENTER);
    
    // Register listender for ComboBox
    jcbAge.addItemListener(new ItemListener(){
      @Override
      public void itemStateChanged(ItemEvent e){
        jcbAge.getSelectedIndex();
      }
    });
    
    // Register listender for JSlider
    jsldSalary.addChangeListener(new ChangeListener(){
      @Override
        public void stateChanged(ChangeEvent e){
      int value = jsldSalary.getValue()* 1000;
      jtfSalary.setText(Integer.toString(value));
      }
      });
    
    // Register listeners for JButton
    jbtSubmit.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        jtaInfo.setText("First Name: " + jtfFirstName.getText() + "\n"
                + "Last Name: " + jtfLastName.getText() + "\n"
                + "Gender: " + setGender() + "\n" + "Age: " +
                jcbAge.getSelectedIndex() + "\n" + "Desired Salary: "
                + jtfSalary.getText());
      }
    });
    

    }
    
    private String setGender(){
       //Determing Gender Selected
        String gender;
        if (jrbFemale.isSelected()){
        gender = "Female";
        }
        else
            gender = "Male";
        return gender;
            }
   

    public static void main(String[] args) {
        JobApplication frame = new JobApplication();
        frame.pack();
        frame.setTitle("Job Application");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }

}
