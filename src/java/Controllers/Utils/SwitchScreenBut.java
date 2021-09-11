package Controllers.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchScreenBut {

    public static void SSB(ActionEvent event, String addr, Parent table) throws IOException {
        System.out.println("Ты перешел на вкладку: " + addr);

        Scene tableViewScene = new Scene(table);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
       // Stage.setTitle("Магазин у Ашота");
    }
}
