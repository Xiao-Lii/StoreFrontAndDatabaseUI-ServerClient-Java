package Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class Electronic extends Product {
    private String serialNum;
    private Integer yearsOnWarranty;

    public Electronic(String productID, String productName, String brandName, String productDesc,
                      LocalDate dateOfIncorp, ArrayList<Category> prodCategory, String serialNum,
                      Integer yearsOnWarranty) {
        super(productID, productName, brandName, productDesc, dateOfIncorp, prodCategory);
        this.serialNum = serialNum;
        this.yearsOnWarranty = yearsOnWarranty;
        // Category(String catID, String catName, String catDesc)
        Category electronic = new Category("Electronics72","Electronics",
                "Technological entertainment revolving around electronic devices and media.");
        prodCategory.add(electronic);
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public Integer getYearsOnWarranty() {
        return yearsOnWarranty;
    }

    public void setYearsOnWarranty(Integer yearsOnWarranty) {
        this.yearsOnWarranty = yearsOnWarranty;
    }
}
