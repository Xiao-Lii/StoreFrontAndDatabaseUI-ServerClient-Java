package Product;

public class Category {
    private String catID;
    private String catName;
    private String catDesc;

    public Category(String catID, String catName, String catDesc) {
        this.catID = catID;
        this.catName = catName;
        this.catDesc = catDesc;
    }

    public Category() {
        this.catID = "0000";
        this.catName = "defaultCategory";
        this.catDesc = "default";
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }
}
