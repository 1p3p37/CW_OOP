package Controllers;

import CheckerERROR.Alert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    private boolean testTextF(String id, String name, String price, String num){
        if  (id.isEmpty()|| name.isEmpty()|| price.isEmpty()|| num.isEmpty()) {
            return false;
        }
        int Tid = Integer.parseInt(id);
        String Tname = name;
        float Tprice = Float.parseFloat(price);
        int Tnum = Integer.parseInt(num);
        if (Tid <= 0 || Tid >= 1000) {
            return false;
        } if (name.length() <= 2 || name.length() >= 128) {
            return false;
        } if (Tprice < 0.99f || Tprice >= 50000.0f) {
            return false;
        } if (Tnum <= 0 || Tnum >= 2048) {
            return false;
        } return true;
    }

    private boolean testTextFid(String id){
        if (id.isEmpty()){
            return false;
        }
        int test = Integer.parseInt(id);
        if (test > 0 && test <= 1000){
            return true;
        } else {
            return false;
        }
    }
    @Test
    void textF() {
        String id = "1";
        String name = "";
        String price = "11.0";
        String numbers = "12";
        boolean testTextF = testTextF(id,name,price,numbers);
        boolean expected = false;// значение которое должно вернуть правильно раблотающая ф-я
        assertEquals(expected,testTextF);


    }

    @Test
    void textFid() {
        String id = "99";
        boolean testTextFid = testTextFid(id);
        boolean expected = true;// значение которое должно вернуть правильно раблотающая ф-я
        assertEquals(expected,testTextFid);

    }
}