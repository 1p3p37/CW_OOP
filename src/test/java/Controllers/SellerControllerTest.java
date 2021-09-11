package Controllers;

import CheckerERROR.Alert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SellerControllerTest {

    public boolean testTextF(String Tid, String Tname, String Tsalary) {
        if  (Tid.isEmpty()|| Tname.isEmpty()|| Tsalary.isEmpty()) {
            return false;
        }
        int id = Integer.parseInt(Tid);
        String name = Tname;
        float salary = Float.parseFloat(Tsalary);
        if (id <= 0 || id >= 1000) {
            return false;
        } if (name.length() <= 10 || name.length() >= 256) {
            return false;
        } if (salary < 11000.0f || salary >= 130000.0f) {
            return false;
        } return true;
    }

    public boolean testTextFid(String Tid){
        if (Tid.isEmpty()){
            return false;
        }
        int test = Integer.parseInt(Tid);
        if (test > 0 && test <= 100 && (Tid)!=""){
            return true;
        } else {
            return false;
        }
    }

    @Test
    void textF() {
        String id = "1";
        String name = "kora rora test test test";
        String salary = "12311.0";
        boolean testTextF = testTextF(id,name,salary);
        boolean expected = true;// значение которое должно вернуть правильно раблотающая ф-я
        assertEquals(expected,testTextF);

    }

    @Test
    void textFid() {
        String id = "-1";
        boolean testTextF = testTextFid(id);
        boolean expected = false;// значение которое должно вернуть правильно раблотающая ф-я
        assertEquals(expected,testTextF);

    }
}