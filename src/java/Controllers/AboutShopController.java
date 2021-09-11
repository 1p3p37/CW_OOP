/*package Controllers;

import Models.AboutShop;
import CheckerERROR.Alert;
import Controllers.Utils.SwitchScreenBut;
import Controllers.Utils.TableUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import Controllers.Utils.Connect;
import org.apache.logging.log4j.LogManager;


public class AboutShopController implements Initializable  {



    @FXML
    private TextField ASidTF;
    @FXML
    private TextField ASbossnameTF;
    @FXML
    private TextField ASspecializationTF;
    @FXML
    private TextField ASaddressTF;
    @FXML
    private TableView<AboutShop> AboutShopTable;
    @FXML
    private TableColumn<AboutShop, Integer> ASIDCol;
    @FXML
    private TableColumn <AboutShop, String> ASbossnameCol;
    @FXML
    private TableColumn<AboutShop, String> ASspecializationCol;
    @FXML
    private TableColumn<AboutShop, String> ASaddressCol;
    @FXML
    private Button ASaddBTN;
    @FXML
    private Button ASupdateBTN;
    @FXML
    private Button ASdeleteBTN;
    @FXML
    private Button ASreloadBTN;
    @FXML
    private Button ASaboutBTN;

    //обработка событий
    @FXML
    private void handleButtonAction(ActionEvent event) {

        System.out.print ("Ты нажал на: ");
        if (event.getSource() == ASaddBTN) {
            insertRecord();
            System.out.println("Добавить.");
        } else if (event.getSource() == ASupdateBTN) {
            updateRecord();
            System.out.println("Редактировать.");
        } else if (event.getSource() == ASdeleteBTN) {
            System.out.println("Удалить.");
            deleteButton();
        } else if (event.getSource() == ASreloadBTN) {
            System.out.println("Обновить таблицу");
            showProduct();
        } else if (event.getSource() == ASaboutBTN) {
            System.out.println("О программе");
            AboutProgram();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        //TODO
        showProduct();
        TableUtils.installCopyPasteHandler(AboutShopTable);
    }


    public  ObservableList<AboutShop> getAboutShopList(){
        ObservableList<AboutShop> aboutShopsList = FXCollections.observableArrayList();
        Connection conn = Connect.getConnection();
        String query = "SELECT * FROM about_shop";
        Statement st;
        ResultSet rs;
        try{
            assert conn != null;
            st = conn.createStatement();
            rs = st.executeQuery(query);
            AboutShop a_shop;
            while(rs.next()){
                a_shop = new AboutShop(rs.getInt("idAbout_Shop"), rs.getString("bossName"), rs.getString("specialization"), rs.getString("address") );
                aboutShopsList.add(a_shop);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            LogManager.getLogger(AboutShopController.class).error(ex);
        }
        return aboutShopsList;
    }
    public void showProduct(){
        ObservableList<AboutShop> list = getAboutShopList();

        ASIDCol.setCellValueFactory(new PropertyValueFactory <AboutShop, Integer> ("idAboutShop"));
        ASbossnameCol.setCellValueFactory(new PropertyValueFactory <AboutShop, String> ("bossName"));
        ASspecializationCol.setCellValueFactory(new PropertyValueFactory <AboutShop, String> ("specialization"));
        ASaddressCol.setCellValueFactory(new PropertyValueFactory <AboutShop, String> ("address"));
        AboutShopTable.setItems(list);
    }

    private void insertRecord(){
        if (textF()) {
            String query = "INSERT INTO about_shop VALUES (" + ASidTF.getText() + ",'" + ASbossnameTF.getText() + "','" +
                    ASspecializationTF.getText() + "','" + ASaddressTF.getText() + "')";
            Connect.executeQuery(query, "Добавить");
            showProduct();
        }
    }
    private void updateRecord(){
        if (textF()) {
            String query = "Update about_shop SET BossName = '" + ASbossnameTF.getText() + "', Specialization = '" + ASspecializationTF.getText() + "', Address = '" +
                    ASaddressTF.getText() + "' WHERE idAbout_shop = " + ASidTF.getText() + "";
            Connect.executeQuery(query, "Редактировать");
            showProduct();
        }
    }
    private void deleteButton(){
        if (textFid()) {
            String query = "DELETE FROM about_shop WHERE idAbout_Shop =" + ASidTF.getText() + "";
            Connect.executeQuery(query, "Удалить" );
            showProduct();
        }
    }


    //проверка нажатий
    public boolean textF() {
        if  (ASidTF.getText().isEmpty()|| ASbossnameTF.getText().isEmpty()|| ASspecializationTF.getText().isEmpty()|| ASaddressTF.getText().isEmpty()) {
            Alert.showAlert("AS","Ошибка ввода: вы оставили пустое занчение");
            return false;
        }
        int id = Integer.parseInt(ASidTF.getText());
        String Bname = ASbossnameTF.getText();
        String spec = ASspecializationTF.getText();
        String addr = ASaddressTF.getText();
        System.out.println("Были введены: ID: {" +  id + "} Имя взладельца: {" + Bname +"} Специальзация : {"
                + spec + "} Адресс : {" +  addr + "}");
        if (id <= 0 || id >= 3) {
            Alert.showAlert("AS","Ошибка ввода ID магазина, пожалуйста выберете значение от 1 до 3");
            return false;
        } if (Bname.length() <= 5 || Bname.length() >= 256) {
            Alert.showAlert("AS","Ошибка ввода ФИО владельца магазина, пожалуйста введите ФИО длиной от 5 до 128 символов");
            return false;
        } if (spec.length() <= 8 || spec.length() >= 256) {
            Alert.showAlert("AS","Ошибка ввода специализации, ппожалуйста выберете значение от 1 до 256");
            return false;
        } if (addr.length() <= 18 || addr.length() >= 500) {
            Alert.showAlert("AS","Ошибка ввода адресса, ппожалуйста выберете значение от 18 до 256");
            return false;
        } return true;
    }

    public boolean textFid(){
        if (ASidTF.getText().isEmpty()){
            Alert.showAlert("AS","Ошибка ввода ID, пожалуйста не оставляете поле пустым");
            return false;
        }
        int test = Integer.parseInt(ASidTF.getText());
        if (test > 0 && test <= 1000){
            return true;
        } else {
            Alert.showAlert("AS","Ошибка ввода ID магазина, пожалуйста выберете значение от 1 до 1000");
            return false;
        }
    }

    //О программе
    public void AboutProgram(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("О программе");
        alert.setContentText("Developed by Denisov M.M.  group 9306" +
                "\nSaint Petersburg Electrotechnical University 'LETI'");
        alert.setGraphic(new ImageView(new Image("FXMLdata/cat_and_booble.jpg")));
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    /**
     * Переход на другую страницу
     */

/*
    @FXML
    private void ASproductBTN(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../FXMLdata/product.fxml"));
        SwitchScreenBut.SSB(event, "product", tableViewParent);
    }
    @FXML
    private void ASsellerBTN(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../FXMLdata/seller.fxml"));
        SwitchScreenBut.SSB(event, "seller", tableViewParent);
    }
    @FXML
    private void ASaboutshopBTN(){}

}*/
package Controllers;

import Models.AboutShop;
import CheckerERROR.Alert;
import Controllers.Utils.SwitchScreenBut;
import Controllers.Utils.TableUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import Controllers.Utils.Connect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AboutShopController implements Initializable  {


    @FXML
    private TextField ASbossnameTF;
    @FXML
    private TextField ASspecializationTF;
    @FXML
    private TextField ASaddressTF;
    @FXML
    private TableView<AboutShop> AboutShopTable;
    @FXML
    private TableColumn<AboutShop, Integer> ASIDCol;
    @FXML
    private TableColumn <AboutShop, String> ASbossnameCol;
    @FXML
    private TableColumn<AboutShop, String> ASspecializationCol;
    @FXML
    private TableColumn<AboutShop, String> ASaddressCol;
    @FXML
    private Button ASaddBTN;
    @FXML
    private Button ASupdateBTN;
    @FXML
    private Button ASdeleteBTN;
    @FXML
    private Button ASreloadBTN;
    @FXML
    private Button ASaboutBTN;

    private static Logger logger = LogManager.getLogger(ProductController.class);


    //обработка событий
    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == ASaddBTN) {
            logger.info("Ты нажал на: Добавить");
            insertRecord();
        } else if (event.getSource() == ASupdateBTN) {
            logger.info("Ты нажал на: Редактировать");
            updateRecord();
        } else if (event.getSource() == ASdeleteBTN) {
            logger.info("Ты нажал на: Удалить");
            deleteButton();
        } else if (event.getSource() == ASreloadBTN) {
            logger.info("Ты нажал на: Обновть таблицу");
            showProduct();
        } else if (event.getSource() == ASaboutBTN) {
            logger.info("Ты нажал на: О программе");
            AboutProgram();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        //TODO
        showProduct();
        TableUtils.installCopyPasteHandler(AboutShopTable);
    }


    public  ObservableList<AboutShop> getAboutShopList(){
        ObservableList<AboutShop> aboutShopsList = FXCollections.observableArrayList();
        Connection conn = Connect.getConnection();
        String query = "SELECT * FROM about_shop";
        Statement st;
        ResultSet rs;
        try{
            assert conn != null;
            st = conn.createStatement();
            rs = st.executeQuery(query);
            AboutShop a_shop;
            while(rs.next()){
                a_shop = new AboutShop(rs.getInt("idAbout_Shop"), rs.getString("bossName"), rs.getString("specialization"), rs.getString("address") );
                aboutShopsList.add(a_shop);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            LogManager.getLogger(AboutShopController.class).error(ex);
        }
        return aboutShopsList;
    }
    public void showProduct(){
        ObservableList<AboutShop> list = getAboutShopList();

        ASbossnameCol.setCellValueFactory(new PropertyValueFactory <AboutShop, String> ("bossName"));
        ASspecializationCol.setCellValueFactory(new PropertyValueFactory <AboutShop, String> ("specialization"));
        ASaddressCol.setCellValueFactory(new PropertyValueFactory <AboutShop, String> ("address"));
        AboutShopTable.setItems(list);
    }

    private void insertRecord(){
        if (textF()) {
            String id = "1";
            String query = "INSERT INTO about_shop VALUES (" + id + ",'" + ASbossnameTF.getText() + "','" +
                    ASspecializationTF.getText() + "','" + ASaddressTF.getText() + "')";
            Connect.executeQuery(query, "Добавить");
            showProduct();
        }
    }
    private void updateRecord(){
        if (textF()) {
            String id = "1";
            String query = "Update about_shop SET BossName = '" + ASbossnameTF.getText() + "', Specialization = '" + ASspecializationTF.getText() + "', Address = '" +
                    ASaddressTF.getText() + "' WHERE idAbout_shop = " + id + "";
            Connect.executeQuery(query, "Редактировать");
            showProduct();
        }
    }
    private void deleteButton(){
        String id = "1";
        String query = "DELETE FROM about_shop WHERE idAbout_Shop =" + id + "";
        Connect.executeQuery(query, "Удалить" );
        showProduct();
    }


    //проверка нажатий
    public boolean textF() {
        if  ( ASspecializationTF.getText().isEmpty()|| ASaddressTF.getText().isEmpty()) {
            Alert.showAlert("AS","Ошибка ввода: вы оставили пустое занчение");
            return false;
        }
        String Bname = ASbossnameTF.getText();
        String spec = ASspecializationTF.getText();
        String addr = ASaddressTF.getText();
        System.out.println("Были введены: Имя взладельца: {" + Bname +"} Специальзация : {"
                + spec + "} Адресс : {" +  addr + "}");
        if (Bname.length() <= 5 || Bname.length() >= 256) {
            Alert.showAlert("AS","Ошибка ввода ФИО владельца магазина, пожалуйста введите ФИО длиной от 5 до 128 символов");
            return false;
        } if (spec.length() <= 8 || spec.length() >= 256) {
            Alert.showAlert("AS","Ошибка ввода специализации, ппожалуйста выберете значение от 1 до 256");
            return false;
        } if (addr.length() <= 18 || addr.length() >= 500) {
            Alert.showAlert("AS","Ошибка ввода адресса, ппожалуйста выберете значение от 18 до 256");
            return false;
        } return true;
    }


    //О программе
    public void AboutProgram(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("О программе");
        alert.setContentText("Developed by Denisov M.M.  group 9306" +
                "\nSaint Petersburg Electrotechnical University 'LETI'");
        alert.setGraphic(new ImageView(new Image("FXMLdata/cat_and_booble.jpg")));
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    /**
     * Переход на другую страницу
     */


    @FXML
    private void ASproductBTN(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../FXMLdata/product.fxml"));
        SwitchScreenBut.SSB(event, "product", tableViewParent);
    }
    @FXML
    private void ASsellerBTN(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../FXMLdata/seller.fxml"));
        SwitchScreenBut.SSB(event, "seller", tableViewParent);
    }
    @FXML
    private void ASaboutshopBTN(){}

}
