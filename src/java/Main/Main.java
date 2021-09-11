package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;


public class Main extends Application {

   private static Logger logger = LogManager.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLdata/product.fxml"));
        primaryStage.setTitle("Магазин - Admin");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) throws FileNotFoundException {
        logger.info("Program was begin");
        launch(args);

    }
}



//System.setOut(new PrintStream(new FileOutputStream("C:\\Users\\nejda\\IdeaProjects\\Бэкап рабочего проекта до добавления мавена\\CourseWorkTry7\\ConsoleLog\\output.txt")));
// ^ это было на 30 строке
