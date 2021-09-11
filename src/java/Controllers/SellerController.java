package Controllers;

import Models.Seller;
import CheckerERROR.Alert;
import Controllers.Utils.Connect;
import Controllers.Utils.SwitchScreenBut;
import Controllers.Utils.TableUtils;

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

public class SellerController implements Initializable  {



    @FXML
    private TextField SidTF;
    @FXML
    private TextField SnameTF;
    @FXML
    private TextField SsalaryTF;
    @FXML
    private TableView<Seller> SellerTable;
    @FXML
    private TableColumn<Seller, Integer> SIDCol;
    @FXML
    public TableColumn<Seller, String>  SnameCol;
    @FXML
    private TableColumn <Seller, Float> SsalaryCol;
    @FXML
    private Button SaddBTN;
    @FXML
    private Button SupdateBTN;
    @FXML
    private Button SdeleteBTN;
    @FXML
    private Button SreloadBTN;

    private static Logger logger = LogManager.getLogger(ProductController.class);

    //обработка событий
    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == SaddBTN) {
            logger.info("Ты нажал на: Добавить");
            insertRecord();
        } else if (event.getSource() == SupdateBTN) {
            logger.info("Ты нажал на: Редактировать");
            updateRecord();
        } else if (event.getSource() == SdeleteBTN) {
            logger.info("Ты нажал на: Удалить");
            deleteButton();
        } else if (event.getSource() == SreloadBTN){
            logger.info("Ты нажал на: Обновить таблицу");
            showSeller();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        //TODO
        showSeller();
        TableUtils.installCopyPasteHandler(SellerTable);
    }


    public  ObservableList<Seller> getSellerList(){
        ObservableList<Seller> sellerList = FXCollections.observableArrayList();
        Connection conn = Connect.getConnection();
        String query = "SELECT * FROM seller";
        Statement st;
        ResultSet rs;
        try{
            assert conn != null;
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Seller seller;
            while(rs.next()){
                seller = new Seller(rs.getInt("idSeller"), rs.getString("name"), rs.getFloat("salary") );
                sellerList.add(seller);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            LogManager.getLogger(SellerController.class).error(ex);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return sellerList;
    }
    public void showSeller(){
        ObservableList<Seller> list = getSellerList();

        SIDCol.setCellValueFactory(new PropertyValueFactory <Seller, Integer> ("idSeller"));
        SnameCol.setCellValueFactory(new PropertyValueFactory <Seller, String> ("name"));
        SsalaryCol.setCellValueFactory(new PropertyValueFactory <Seller, Float> ("salary"));
        SellerTable.setItems(list);
    }


    private void insertRecord(){
        if (textF()) {
            String query = "INSERT INTO seller VALUES (" + SidTF.getText() + ",'" + SnameTF.getText() + "','" +
                    SsalaryTF.getText() +  "')";
            Connect.executeQuery(query, "Добавить");
            showSeller();
        }
    }
    private void updateRecord(){
        if (textF()) {
            String query = "Update seller SET name = '" + SnameTF.getText() + "', salary = " + SsalaryTF.getText() +
                    " WHERE idSeller = " + SidTF.getText() + "";
            Connect.executeQuery(query, "Редактировать");
            showSeller();
        }
    }
    private void deleteButton(){
        if (textFid()) {
            String query = "DELETE FROM seller WHERE idSeller =" + SidTF.getText() + "";
            Connect.executeQuery(query, "Удалить" );
            showSeller();
        }
    }


    //проверка нажатий
    public boolean textF() {
        if  (SidTF.getText().isEmpty()|| SnameTF.getText().isEmpty()|| SsalaryTF.getText().isEmpty()) {
            Alert.showAlert("S","Ошибка ввода: вы оставили пустое занчение");
            return false;
        }
        int id = Integer.parseInt(SidTF.getText());
        String name = SnameTF.getText();
        float salary = Float.parseFloat(SsalaryTF.getText());
        System.out.println("Ошибки " + id + name + salary   );
        if (id <= 0 || id >= 1000) {
            Alert.showAlert("S","Ошибка ввода ID, пожалуйста выберете значение от 1 до 1000");
            return false;
        } if (name.length() <= 10 || name.length() >= 256) {
            Alert.showAlert("S","Ошибка ввода Имени, пожалуйста введите название длиной от 10 до 128 символов");
            return false;
        } if (salary < 11000.0f || salary >= 130000.0f) {
            Alert.showAlert("S","Ошибка ввода зарплаты, ппожалуйста выберете значение от 11000 до 130000");
            return false;
        } return true;
    }
    public boolean textFid(){
        if (SidTF.getText().isEmpty()){
            Alert.showAlert("S","Ошибка ввода ID, пожалуйста не оставляете поле пустым");
            return false;
        }
        int test = Integer.parseInt(SidTF.getText());
        if (test > 0 && test <= 100 && (SidTF.getText())!=""){
            return true;
        } else {
            Alert.showAlert("S","Ошибка ввода ID, пожалуйста выберете значение от 1 до 100");
            return false;
        }
    }

    /**
     * Переход на другую страницу
     */

    @FXML
    private void ACTproductBTN(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../FXMLdata/product.fxml"));
        SwitchScreenBut.SSB(event, "product", tableViewParent);
    }
    @FXML
    private void ACTaboutshopBTN(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../FXMLdata/about_shop.fxml"));
            SwitchScreenBut.SSB(event, "about_shop", tableViewParent);
    }
    @FXML
    private void ACTsellerBTN(){}


}
