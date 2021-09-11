package Models;

import java.util.Objects;


public class Product {
    private int idProduct;

    private String name;

    private float price;

    private int number;

    public Product(int idProduct, String name, float price, int number) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public Product() {

    }

    public Product(int idProduct) {
    }


    public int getIdProduct() {
        return idProduct;
    }

    public boolean setIdProduct(int idProduct) {
        if (idProduct >= 0 && idProduct <= 9999) {
            this.idProduct = idProduct;
            return true;
        }else return false;
    }


    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if (name.length() > 2 && name.length() < 128 ){
            this.name = name;
            return true;
        } else return false;
    }


    public float getPrice() {
        return price;
    }

    public  boolean setPrice(float price){
        if (price >= 0.0f && price <= 9999.0f){
            this.price = price;
            return true;
        } else return false;
    }


    public int getNumber() {
        return number;
    }

    public boolean setNumber(int number) {
        if (number >= 0 && number <= 1024) {
            this.number = number;
            return true;
        } else return false;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", number=" + number +
                '}';
    }
    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return idProduct == that.idProduct && Double.compare(that.price, price) == 0 && number == that.number && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, name, price, number);
    }*/
}
