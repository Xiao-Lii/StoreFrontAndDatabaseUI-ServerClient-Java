package Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class Electronic extends Product {
    private String serialNum;
    private String lengthOfWarranty;
    private ArrayList<Category> listOfProductCategories;

    public Electronic(String productID, String productName, String brandName, String productDesc,
                      LocalDate dateOfIncorp, Category selectedCategory, String serialNum,
                      String lengthOfWarranty) {
        super(productID, productName, brandName, productDesc, dateOfIncorp);
        this.serialNum = serialNum;
        this.lengthOfWarranty = lengthOfWarranty;
        this.listOfProductCategories = new ArrayList<>();
        this.listOfProductCategories.add(selectedCategory);
    }

    public Electronic(String productID, String productName, String brandName, String productDesc,
                      LocalDate dateOfIncorp, String serialNum, String lengthOfWarranty) {
        super(productID, productName, brandName, productDesc, dateOfIncorp);
        this.serialNum = serialNum;
        this.lengthOfWarranty = lengthOfWarranty;
        this.listOfProductCategories = new ArrayList<Category>();
        Category electronic = new Category("Electronic","Electronic",
                "Technological entertainment revolving around electronic devices and media.");
        this.listOfProductCategories.add(electronic);
    }

    // THIS IS IMPORTANT WHEN DISPLAYING TO LISTVIEW TABLES
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format(" - %s - %s", this.getSerialNum(), this.getLengthOfWarranty()));
        return sb.toString();
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getLengthOfWarranty() {
        return lengthOfWarranty;
    }

    public void setLengthOfWarranty(String lengthOfWarranty) {
        this.lengthOfWarranty = lengthOfWarranty;
    }
}
