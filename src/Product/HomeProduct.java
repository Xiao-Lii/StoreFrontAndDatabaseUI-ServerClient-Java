package Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class HomeProduct extends Product {
    private String intendedLoc;
    private ArrayList<Category> prodCategory;

    public HomeProduct(String productID, String productName, String brandName, String productDesc,
                       LocalDate dateOfIncorp, ArrayList<Category> prodCategory, String intendedLoc) {
        super(productID, productName, brandName, productDesc, dateOfIncorp, prodCategory);
        this.intendedLoc = intendedLoc;
        // Category(String catID, String catName, String catDesc)
        Category homeCategory = new Category("Home", "Home",
                "For decorating the household.");
        prodCategory.add(homeCategory);
    }

    public HomeProduct(String productID, String productName, String brandName, String productDesc,
                       LocalDate dateOfIncorp, String intendedLoc) {
        super(productID, productName, brandName, productDesc, dateOfIncorp);
        this.intendedLoc = intendedLoc;
        this.prodCategory = new ArrayList<Category>();
        Category homeCategory = new Category("Home", "Home",
                "For decorating the household.");
        this.prodCategory.add(homeCategory);
    }

    public String getIntendedLoc() {
        return intendedLoc;
    }

    public void setIntendedLoc(String intendedLoc) {
        this.intendedLoc = intendedLoc;
    }
}