package Product;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Product implements Serializable {
    private String productID;
    private String productName;
    private String brandName;
    private String productDesc;
    private LocalDate dateOfIncorp;
    private ArrayList<Category> prodCategory;

    public Product(String productID, String productName, String brandName, String productDesc,
                   LocalDate dateOfIncorp, Category selectedCategory) {
        this.productID = productID;
        this.productName = productName;
        this.brandName = brandName;
        this.productDesc = productDesc;
        this.dateOfIncorp = dateOfIncorp;
        this.prodCategory = new ArrayList<>();
        this.prodCategory.add(selectedCategory);
    }

    public Product(String productID, String productName, String brandName, String productDesc,
                   LocalDate dateOfIncorp) {
        this.productID = productID;
        this.productName = productName;
        this.brandName = brandName;
        this.productDesc = productDesc;
        this.dateOfIncorp = dateOfIncorp;
        this.prodCategory = new ArrayList<Category>();
    }

    // THIS IS IMPORTANT WHEN DISPLAYING TO LISTVIEW TABLES
    @Override
    public String toString(){
        return String.format("%s - %s - %s - %s - %tD", this.getProductID(), this.getProductName(), this.getBrandName(),
                this.getProductDesc(), this.getDateOfIncorp());
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public LocalDate getDateOfIncorp() {
        return dateOfIncorp;
    }

    public void setDateOfIncorp(LocalDate dateOfIncorp) {
        this.dateOfIncorp = dateOfIncorp;
    }

    public ArrayList<Category> getProdCategory() {
        return prodCategory;
    }

    public void setProdCategory(ArrayList<Category> prodCategory) {
        this.prodCategory = prodCategory;
    }

}
