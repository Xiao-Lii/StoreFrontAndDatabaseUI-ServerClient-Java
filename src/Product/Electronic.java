package Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class Electronic extends Product {
    private String serialNum;
    private String lengthOfWarranty;
    private ArrayList<Category> prodCategory;

    public Electronic(String productID, String productName, String brandName, String productDesc,
                      LocalDate dateOfIncorp, ArrayList<Category> prodCategory, String serialNum,
                      String lengthOfWarranty) {
        super(productID, productName, brandName, productDesc, dateOfIncorp, prodCategory);
        this.serialNum = serialNum;
        this.lengthOfWarranty = lengthOfWarranty;
        Category electronic = new Category("Electronic","Electronic",
                "Technological entertainment revolving around electronic devices and media.");
        prodCategory.add(electronic);
    }

    public Electronic(String productID, String productName, String brandName, String productDesc,
                      LocalDate dateOfIncorp, String serialNum,
                      String lengthOfWarranty) {
        super(productID, productName, brandName, productDesc, dateOfIncorp);
        this.serialNum = serialNum;
        this.lengthOfWarranty = lengthOfWarranty;
        this.prodCategory = new ArrayList<Category>();
        Category electronic = new Category("Electronic","Electronic",
                "Technological entertainment revolving around electronic devices and media.");
        this.prodCategory.add(electronic);
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
