package Controllers;

import Controllers.Utils.TableUtils;

import Models.Product;
import CheckerERROR.Alert;
import Controllers.Utils.Connect;
import Controllers.Utils.SwitchScreenBut;
import Controllers.Utils.ExportDataToPdf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ProductController extends Thread  implements Initializable {

    @FXML
    private TextField PRidTF;
    @FXML
    private TextField PRnameTF;
    @FXML
    private TextField PRpriceTF;
    @FXML
    private TextField PRnumberTF;
    @FXML
    private TableView<Product> ProductTable;
    @FXML
    private TableColumn<Product, Integer> PRIDCol;
    @FXML
    private TableColumn <Product, String> PRnameCol;
    @FXML
    private TableColumn<Product, Float> PRpriceCol;
    @FXML
    private TableColumn<Product, Integer> PRnumberCol;
    @FXML
    private Button PRaddBTN;
    @FXML
    private Button PRupdateBTN;
    @FXML
    private Button PRdeleteBTN;
    @FXML
    private Button PRreloadBTN;
    @FXML
    private Button PRgetpdfBTN;

    private static Logger logger = LogManager.getLogger(ProductController.class);


    //обработка событий
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {

        if (event.getSource() == PRaddBTN) {
            logger.info("Ты нажал на: Добавить");
            insertRecord();
        } else if (event.getSource() == PRupdateBTN) {
            logger.info("Ты нажал на: Редактировать");
            updateRecord();
        } else if (event.getSource() == PRdeleteBTN) {
            logger.info("Ты нажал на: Удалить");
            deleteButton();
        } else if (event.getSource() == PRreloadBTN){
            logger.info("Ты нажал на: Обновить таблицу");
            showProduct();
        }else if(event.getSource() == PRgetpdfBTN) {
            logger.info("Ты нажал на: Получить отчет ПДФ на рабочий стол");
            new ExportDataToPdf().run();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        //TODO
        showProduct();
        TableUtils.installCopyPasteHandler(ProductTable);
    }


    public  ObservableList<Product> getProductList(){
        ObservableList<Product> productList = FXCollections.observableArrayList();
        Connection conn = Connect.getConnection();
        String query = "SELECT * FROM product";
        Statement st;
        ResultSet rs;
        try{
            assert conn != null;
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Product product;
            while(rs.next()){
                product = new Product(rs.getInt("idProduct"), rs.getString("name"), rs.getFloat("price"), rs.getInt("number") );
                productList.add(product);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            LogManager.getLogger(ProductController.class).error(ex);
        }
        return productList;
    }


    public void showProduct(){
        ObservableList<Product> list = getProductList();

        PRIDCol.setCellValueFactory(new PropertyValueFactory <Product, Integer> ("idProduct"));
        PRnameCol.setCellValueFactory(new PropertyValueFactory <Product, String> ("name"));
        PRpriceCol.setCellValueFactory(new PropertyValueFactory <Product, Float> ("price"));
        PRnumberCol.setCellValueFactory(new PropertyValueFactory <Product, Integer> ("number"));
        ProductTable.setItems(list);
    }



    private void insertRecord(){
        if (textF()) {
            String query = "INSERT INTO product VALUES (" + PRidTF.getText() + ",'" + PRnameTF.getText() + "','" +
                    PRpriceTF.getText() + "'," + PRnumberTF.getText() + ")";
            Connect.executeQuery(query, "Добавить");
            showProduct();
        }
    }
    private void updateRecord(){
        if (textF()) {
            String query = "Update product SET name = '" + PRnameTF.getText() + "', price = '" + PRpriceTF.getText() + "', number = " +
                    PRnumberTF.getText() + " WHERE idProduct = " + PRidTF.getText() + "";
            Connect.executeQuery(query, "Редактировать");
            showProduct();
        };
    }
    private void deleteButton(){
        if (textFid()) {
            String query = "DELETE FROM product WHERE idProduct =" + PRidTF.getText() + "";
            Connect.executeQuery(query, "Удалить" );
            showProduct();
        }
    }


    //проверка нажатий
    public boolean textF() {
        if  (PRidTF.getText().isEmpty()|| PRnameTF.getText().isEmpty()|| PRpriceTF.getText().isEmpty()|| PRnumberTF.getText().isEmpty()) {
            Alert.showAlert("PR","Ошибка ввода: вы оставили пустое занчение");
            return false;
        }
        int id = Integer.parseInt(PRidTF.getText());
        String name = PRnameTF.getText();
        float price = Float.parseFloat(PRpriceTF.getText());
        int num = Integer.parseInt(PRnumberTF.getText());
        System.out.println("Были введены: ID: {" + id + "} название товара: {" + name + "} цена: {" + price
                + "} количество: {" + num + "}" );
        if (id <= 0 || id >= 1000) {
            Alert.showAlert("PR","Ошибка ввода ID, пожалуйста выберете значение от 1 до 1000");
            return false;
        } if (name.length() <= 2 || name.length() >= 128) {
            Alert.showAlert("PR","Ошибка ввода Названия, пожалуйста введите название длиной от 2 до 128 символов");
            return false;
        } if (price < 0.99f || price >= 50000.0f) {
            Alert.showAlert("PR","Ошибка ввода цены, ппожалуйста выберете значение от 1 до 50000");
            return false;
        } if (num <= 0 || num >= 2048) {
            Alert.showAlert("PR","Ошибка ввода количества, ппожалуйста выберете значение от 0 до 2048");
            return false;
        } return true;
    }

    public boolean textFid(){
        if (PRidTF.getText().isEmpty()){
            Alert.showAlert("PR","Ошибка ввода ID, пожалуйста не оставляете поле пустым");
            return false;
        }
        int test = Integer.parseInt(PRidTF.getText());
        if (test > 0 && test <= 1000){
            return true;
        } else {
            Alert.showAlert("PR","Ошибка ввода ID, пожалуйста выберете значение от 1 до 1000");
            return false;
        }
    }

    /**
     * Переход на другую страницу
     */

    @FXML
    private void ACTproductBTN(){}
    @FXML
    private void ACTsellerBTN(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../FXMLdata/seller.fxml"));
        SwitchScreenBut.SSB(event, "seller", tableViewParent);
    }
    @FXML
    private void ACTaboutshopBTN(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../FXMLdata/about_shop.fxml"));
        SwitchScreenBut.SSB(event, "about_shop", tableViewParent);
    }

}
