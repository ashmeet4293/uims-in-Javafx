/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import common.CommonFunction;
import database.StudentDbUtils;
import domain.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    CommonFunction commonFunction=new CommonFunction();
    StudentDbUtils studentDbUtils = new StudentDbUtils();

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
        clearFields();
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
        student.setId(Integer.parseInt(txtId.getText()));

        //Creating StudentDbUtils object for createStudent(Student) function call
        StudentDbUtils studentDbUtils = new StudentDbUtils();
        if (studentDbUtils.updateStudent(student)) {
            clearFields();

            //Clears current table Data
            tblStudent.setItems(null);
            //set the data into table
            getAllData();
            AlertBox("INFORMATION", "UPDATE Success", "Success Fully Update", "You data has be successfull updated");

        } else {
            AlertBox("ERROR", "UPDATE Failed", "Cannot Update Student Data", "Update DAta failed");
        }

    }

    @FXML
    private void handleBtnDeleteAction(ActionEvent event) {
        if (studentDbUtils.deleteStudent(Integer.parseInt(txtId.getText()))) {
            clearFields();
            tblStudent.setItems(null);
            getAllData();
            AlertBox("INFORMATION", "Delete Success", "Delete successful", "You data has be successfull deleted");

        } else {
            AlertBox("ERROR", "Delete failed", "Cannot Delete Data", "You data has not deleted");

        }

    }

    @FXML
    private void handleTableRowMouseClickedAction(MouseEvent event) {
        showSelectedDataFromTable();
    }

    @FXML
    private void handleTableKeyReleasedAction(KeyEvent event) {
        showSelectedDataFromTable();

    }

    @FXML
    private void handleTableKeyPressedAction(KeyEvent event) {
//        showSelectedDataFromTable();
    }

    @FXML
    private void handleMenuLogoutAction(ActionEvent event) throws IOException {
        commonFunction.nextStage("/main/FXMLDocument.fxml", "Login Window", Boolean.FALSE);
         Stage current = (Stage) pwdPassword.getScene().getWindow();
        current.hide();

    }

    public void clearFields() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtRoll.clear();
        txtUsename.clear();
        pwdPassword.clear();
        dateDob.getEditor().setText("");
        rdMale.setSelected(false);
        rdFemale.setSelected(false);
        cmbRoles.setValue(null);

    }

    private void AlertBox(String type, String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.valueOf(type));
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();

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

    public void showSelectedDataFromTable() {
        clearFields();
        Student student = (Student) tblStudent.getSelectionModel().getSelectedItem();
        txtId.setText("" + student.getId());
        txtName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtRoll.setText("" + student.getRoll());
        txtUsename.setText(student.getUsername());
        pwdPassword.setText(student.getPassword());
        dateDob.getEditor().setText(student.getDateOfBirth());
        cmbRoles.setValue(student.getRole());
        String gender = student.getGender();
        if (gender != null) {
            if (gender.equalsIgnoreCase("male")) {
                rdMale.setSelected(true);
            } else if (gender.equalsIgnoreCase("female")) {
                rdFemale.setSelected(true);
            }
        } else {
            rdMale.setSelected(false);
            rdFemale.setSelected(false);

        }
    }

}
