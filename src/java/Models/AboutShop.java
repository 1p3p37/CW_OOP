package Models;

public class AboutShop {

    private int idAboutShop;
    private String bossName;
    private String specialization;
    private String address;

    public AboutShop(int idAboutShop, String bossName, String specialization, String address) {
        this.idAboutShop = idAboutShop;
        this.bossName = bossName;
        this.specialization = specialization;
        this.address = address;
    }

    public int getIdAboutShop() {
        return idAboutShop;
    }

    public void setIdAboutShop(int idAboutShop) {
        this.idAboutShop = idAboutShop;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
