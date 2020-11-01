package Users;

import Product.*;
import Users.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StoreSystem {
    private ArrayList<Category> listOfCategories;
    private ArrayList<User> listOfUsers;
    private ArrayList<Product> catalog;

    public StoreSystem(ArrayList<Category> listOfCategories, ArrayList<User> listOfUsers, ArrayList<Product> catalog) {
        this.listOfCategories = listOfCategories;
        this.listOfUsers = listOfUsers;
        this.catalog = catalog;
    }

    public StoreSystem() {
        this.listOfCategories = new ArrayList<Category>();
        this.listOfUsers = new ArrayList<User>();
        this.catalog = new ArrayList<Product>();
    }

    public void createUser(String email, String password, String displayName) {
        for (User u : listOfUsers){
            try {
                if (u.getEmail().equalsIgnoreCase(email))
                    throw new Exception();
                listOfUsers.add(new Customer(email, password, displayName));
            } catch (Exception e){ System.out.println("Error: Email already exists."); }
        }
        if (listOfUsers.size() == 0)
            listOfUsers.add(new Customer(email, password, displayName));
    }

    public void loginUser(String email, String password){
        for (User u : listOfUsers){
            try {
                if (u.getEmail().equalsIgnoreCase(email)){
                    if (u.getPassword().equals(password)){
                        // THIS IS THE PART WHERE WE NEED TO CONNECT TO THE SERVER
                    }
                }
                else
                    throw new Exception();
            } catch (Exception e){ System.out.println("Error: Invalid email or password."); }
        }
    }

    public Product getProduct(String productID, String productName){
        for (Product p : catalog){
            if (p.getProductID().equalsIgnoreCase(productID) && p.getProductName().equalsIgnoreCase(productName))
                return p; }
        throw new IllegalArgumentException("Error: Product is not found in the catalog.");
    }

    // WHEN ATTEMPTING TO ADD PRODUCT - IF THESE VALUES ARE EMPTY, THESE VALUES = NULL
    public void addProduct(String prodType, String productID, String productName, String brandName, String productDesc,
                           LocalDate dateOfIncorp, ArrayList<Category> prodCategory, String serialNum, String imei,
                           String os, String ram, String hardDrive, String authorName, String intendedLoc,
                           Integer yearWarranty, Integer numPages, Integer edition, LocalDate datePublished)
                            throws IllegalArgumentException{

        // WHEN IN APPLICATION - ADMIN CHOOSES PRODUCT TYPE - THIS STRING DECIDES WHICH ADD-CASE TO EXECUTE
        try{
            this.getProduct(productID, productName);
        }
        catch (IllegalArgumentException iae){
            switch (prodType){
                case "Electronics":
                    // SERIAL NO, WARRANTY PERIOD
                    catalog.add(new Electronic(productID, productName, brandName, productDesc, dateOfIncorp,
                            prodCategory, serialNum, yearWarranty));
                    break;
                case "Cellphones":
                    // SERIAL NO, WARRANTY PERIOD, IMEI, OS
                    catalog.add(new Cellphone(productID, productName, brandName, productDesc, dateOfIncorp,
                            prodCategory, serialNum, yearWarranty, imei, os));
                    break;
                case "Computers":
                    // SERIAL NO, WARRANTY PERIOD, RAM, HARD DRIVE
                    catalog.add(new Computer(productID, productName, brandName, productDesc, dateOfIncorp,
                            prodCategory, serialNum, yearWarranty, ram, hardDrive));
                    break;
                case "Books":
                    // AUTHOR NAME, PUBLICATION DATA, NUM PAGES, EDITION
                    catalog.add(new Book(productID, productName, brandName, productDesc, dateOfIncorp,
                            prodCategory, authorName, datePublished, numPages, edition));
                    break;
                case "Homelines":
                    // INTENDED LOCATION
                    catalog.add(new HomeProduct(productID, productName, brandName, productDesc, dateOfIncorp,
                            prodCategory, intendedLoc));
                    break;
                default:
                    System.out.println("ERROR ADDING PRODUCT TO CATALOG.\n");
                    break;

            }           // END OF SWITCH CASE - ADD BY PRODUCT TYPE
        }               // END OF CATCH ILLEGAL ARGUMENT EXCEPTION - IF PRODUCT WASN'T ALREADY IN CATALOG
    }                   // END OF ADD PRODUCT METHOD

    public void removeProduct(Product product){
        for (Product p : catalog) {
            if (catalog.contains(product))
                catalog.remove(product);
            else
                throw new IllegalArgumentException("ERROR: NO SUCH PRODUCT EXISTS");
        }
    }                   // END OF REMOVE PRODUCT FROM CATALOG METHOD

    public void addCategory(String catID, String catName, String catDesc) {
        listOfCategories.add(new Category(catID,catName,catDesc));
        if(listOfCategories.size() == 0) {
            listOfCategories.add(new Category(catID,catName,catDesc));
            System.out.println("Category " + catName + " successfully added!");
            return;
        }
        for(Category c : listOfCategories){
            if(c.getCatID().equalsIgnoreCase(catID)) {
                System.out.println("Error adding category: Category already exists.");
                return;
            }
        }
        listOfCategories.add(new Category(catID,catName,catDesc));
        System.out.println("Category " + catName + " successfully added!");
    }

    public void removeCategory(String catID) {
        for(int i = 0; i < listOfCategories.size(); i ++) {
            if(listOfCategories.get(i).getCatID().equalsIgnoreCase(catID)) {
                listOfCategories.remove(i);
            }
        }
        for(Product p : catalog) {
            if(p.getProductID().equalsIgnoreCase(catID))
                p.setProductID("Default"); //If a product's category was deleted, change to Default.
        }
    }

}                       // END OF DEPARTMENT STORE CLASS