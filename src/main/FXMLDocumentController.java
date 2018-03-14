/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import common.CommonFunction;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;




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
    
    CommonFunction common=new CommonFunction();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleLoginBtnAction(ActionEvent event) throws IOException {
        common.nextStage("/student/Student.fxml", "Admin Window", true);
    }
    
}
