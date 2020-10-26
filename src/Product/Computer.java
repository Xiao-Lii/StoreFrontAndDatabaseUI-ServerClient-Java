package Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class Computer extends Electronic {
    private String ram;
    private String hardDrive;

    public Computer(String productID, String productName, String brandName, String productDesc,
                    LocalDate dateOfIncorp, ArrayList<Category> prodCategory, String serialNum,
                    Integer warrantyPer, String ram, String hardDrive) {
        super(productID, productName, brandName, productDesc, dateOfIncorp, prodCategory, serialNum, warrantyPer);
        this.ram = ram;
        this.hardDrive = hardDrive;
        // Category(String catID, String catName, String catDesc)
        Category electronic = new Category("Computers72","Computers",
                "Technological entertainment revolving around computers");
        prodCategory.add(electronic);
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(String hardDrive) {
        this.hardDrive = hardDrive;
    }
}