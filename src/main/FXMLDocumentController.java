/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import common.CommonFunction;
import database.StudentDbUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ashmeet
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label pwdPassword;
    @FXML
    private Button btnLogin;

    CommonFunction common = new CommonFunction();
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    StudentDbUtils studentDbUtils = new StudentDbUtils();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLoginBtnAction(ActionEvent event) throws IOException, SQLException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        if (studentDbUtils.loginAction(username, password)) {
            
            common.nextStage("/student/Student.fxml", "Admin Window", true);
            Stage current = (Stage) pwdPassword.getScene().getWindow();
            current.hide();
        } else {
            System.out.println("Invalid username or password");
        }
    }

}
