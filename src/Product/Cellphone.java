package Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cellphone extends Electronic {
    private String imei;
    private String os;
    private ArrayList<Category> prodCategory;

    public Cellphone(String productID, String productName, String brandName, String productDesc,
                     LocalDate dateOfIncorp, Category selectedCategory, String serialNum,
                     String warrantyPer, String imei, String os) {
        super(productID, productName, brandName, productDesc, dateOfIncorp, selectedCategory, serialNum, warrantyPer);
        this.imei = imei;
        this.os = os;
        this.prodCategory = new ArrayList<Category>();
        this.prodCategory.add(selectedCategory);
        //Category electronic = new Category("Cellphone","Cellphone", "Technological entertainment revolving around personal cellphones");
        //this.prodCategory.add(electronic);
    }

    public Cellphone(String productID, String productName, String brandName, String productDesc,
                     LocalDate dateOfIncorp, String serialNum, String warrantyPer, String imei, String os) {
        super(productID, productName, brandName, productDesc, dateOfIncorp, serialNum, warrantyPer);
        this.imei = imei;
        this.os = os;
        this.prodCategory = new ArrayList<Category>();
        Category electronic = new Category("Cellphone","Cellphone",
                "Technological entertainment revolving around personal cellphones");
        this.prodCategory.add(electronic);
    }

    // THIS IS IMPORTANT WHEN DISPLAYING TO LISTVIEW TABLES
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format(" - %s - %s", this.getImei(), this.getOs()));
        return sb.toString();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

}