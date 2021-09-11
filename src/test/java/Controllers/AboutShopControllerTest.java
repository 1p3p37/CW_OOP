package Controllers;

import CheckerERROR.Alert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AboutShopControllerTest {

    private boolean testTextF(String Tid, String Tbossname, String  Tspecialization, String Taddress) {
        if  (Tid.isEmpty()|| Tbossname.isEmpty()|| Tspecialization.isEmpty()|| Taddress.isEmpty()) {
            return false;
        }
        int id = Integer.parseInt(Tid);
        String Bname = Tbossname;
        String spec = Tspecialization;
        String addr = Taddress;
        if (id <= 0 || id >= 3) {
            return false;
        } if (Bname.length() <= 5 || Bname.length() >= 256) {
            return false;
        } if (spec.length() <= 8 || spec.length() >= 256) {
            return false;
        } if (addr.length() <= 18 || addr.length() >= 500) {
            return false;
        } return true;
    }

    public boolean testTextFid(String id){
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
        String bossname = "AMOGUS SUS";
        String specialization = "Game store";
        String address = "Moscow";
        boolean testTextF = testTextF(id,bossname,specialization,address);
        boolean expected = false;// значение которое должно вернуть правильно раблотающая ф-я
        assertEquals(expected,testTextF);;
    }

    @Test
    void textFid() {
        String id = "1";

        boolean testTextFid = testTextFid(id);
        boolean expected = true;// значение которое должно вернуть правильно раблотающая ф-я
        assertEquals(expected,testTextFid);


    }
}