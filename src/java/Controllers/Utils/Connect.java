package Controllers.Utils;

import CheckerERROR.Alert;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
    public static Connection getConnection(){

        Connection conn;
        try {
            //?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/course_work?serverTimezone=UTC",
                    "root", "123456");
            return conn;
        } catch (Exception e){
            System.err.println("::  Error:" + e.getMessage());
            return null;
        }
    }
    public static void executeQuery(String query, String ButName) {
        Connection conn = getConnection();
        Statement st;
        try {
            assert conn != null;
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (SQLException ex) {
            ex.printStackTrace();
            Alert.showAlert("Connect","(SQL) Invalid value|| Неправлиьное занчение(я) в поле(ях) для действия(кнопки) : <"
                    + ButName + "> " + "\n<:| " + ex + "  |:> ");
        }catch (Exception ex){
            ex.printStackTrace();
            Alert.showAlert("Connect","(NOSQL) Invalid value|| Неправлиьное занчение(я) в поле(ях) для действия(кнопки) : <"
                    + ButName + "> " + "\n<:| " + ex + "  |:> ");
        }

    }
}
