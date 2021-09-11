package Models;

public class Seller {
    private int idSeller;
    private String name;
    private float salary;

    public Seller(int idSeller, String name, float salary) {
        this.idSeller = idSeller;
        this.name = name;
        this.salary = salary;
    }

    public int getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(int idSeller) {
        this.idSeller = idSeller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
