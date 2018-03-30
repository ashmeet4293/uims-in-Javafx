package common;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class CommonFunction {

    public void nextStage(String path, String title, Boolean resizable) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(view);
        
        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle(title);
        window.setResizable(resizable);
        window.show();

    }
    
     public void alertMessage(String type, String title, String headerText, String content) {
        Alert alert = new Alert(Alert.AlertType.valueOf(type));
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        alert.showAndWait();

    }

}
