package CheckerERROR;
import Controllers.ProductController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Alert {
    private static Logger logger = LogManager.getLogger(Alert.class);
        public static void showAlert(String ClassName,String error) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            logger.warn(" Called from: {" + ClassName + "} " + error);
            alert.setTitle("Error");
            alert.setContentText(error);
            alert.setGraphic(new ImageView(new Image("CheckerERROR/Error.jpg")));
            alert.setHeaderText(null);
            alert.showAndWait();
        }

}
