/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import database.StudentDbUtils;
import domain.Student;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ashmeet
 */
public class StudentController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtRoll;
    @FXML
    private DatePicker dateDob;
    @FXML
    private RadioButton rdMale;
    @FXML
    private RadioButton rdFemale;
    @FXML
    private ComboBox<String> cmbRoles;
    @FXML
    private TextField txtUsename;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn<Student, Integer> colId;
    @FXML
    private TableColumn<Student, String> colName;
    @FXML
    private TableColumn<Student, String> colAddress;
    @FXML
    private TableColumn<Student, Integer> colRoll;
    @FXML
    private TableColumn<Student, String> colDob;
    @FXML
    private TableColumn<Student, String> colUsername;
    @FXML
    private TableColumn<Student, String> colPassword;
    @FXML
    private TableColumn<Student, String> colRoles;
    @FXML
    private TableColumn<Student, String> colGender;
    @FXML
    private TableView<Student> tblStudent;

    /**
     * Initializes the controller class.
     */
    ToggleGroup radioToggle;
    ObservableList listOfStudents;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioToggle = new ToggleGroup();
        rdMale.setToggleGroup(radioToggle);
        rdFemale.setToggleGroup(radioToggle);

        cmbRoles.getItems().addAll("Admin", "User");
        
        getAllData();
    }

    @FXML
    private void handleClearBtnAction(ActionEvent event) {
    }

    @FXML
    private void handleBtnSaveAction(ActionEvent event) {
        Student student = new Student();
        student.setName(txtName.getText());
        student.setAddress(txtAddress.getText());
        student.setRoll(Integer.parseInt(txtRoll.getText()));
        RadioButton rbGender = (RadioButton) radioToggle.getSelectedToggle();
        student.setGender(rbGender.getText());
        student.setDateOfBirth(dateDob.getEditor().getText());
        student.setRole(cmbRoles.getSelectionModel().getSelectedItem());
        student.setUsername(txtUsename.getText());
        student.setPassword(pwdPassword.getText());

        //Creating StudentDbUtils object for createStudent(Student) function call
        StudentDbUtils studentDbUtils = new StudentDbUtils();
        if (studentDbUtils.createStudent(student)) {
            clearFields();
            
            //Clears current table Data
            tblStudent.setItems(null);
            //set the data into table
            getAllData();
            
            System.out.println("Student Created Successfully");
        } else {
            System.out.println("Cannot Created Student");
        }

    }

    @FXML
    private void handleBtnUpdateAction(ActionEvent event) {
    }

    @FXML
    private void handleBtnDeleteAction(ActionEvent event) {
    }

    public void clearFields() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtRoll.clear();
        txtUsename.clear();
        pwdPassword.clear();
        dateDob.getEditor().setText("");

    }

    public void getAllData() {
        StudentDbUtils studentDbUtils = new StudentDbUtils();
        //Stores ObservableList of students from fetchData()
        listOfStudents = studentDbUtils.fetchData();
        if (listOfStudents != null) {
            
            //Helps to set the data according to their field
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colRoll.setCellValueFactory(new PropertyValueFactory<>("roll"));
            colDob.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
            colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colRoles.setCellValueFactory(new PropertyValueFactory<>("role"));
            colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            
            //Sets the list of Student into table view
            tblStudent.setItems(listOfStudents);

        } else {
            System.out.println("Cannot Read Data from Database");
        }
    }

}
