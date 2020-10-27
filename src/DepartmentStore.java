import Product.*;
import Users.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DepartmentStore {
    private ArrayList<Category> listOfCategories;
    private ArrayList<Order> listOfCustOrders;
    private ArrayList<User> listOfUsers;
    private ArrayList<Product> catalog;

    public DepartmentStore(ArrayList<Category> listOfCategories, ArrayList<Order> listOfCustOrders,
                           ArrayList<User> listOfUsers, ArrayList<Product> catalog) {
        this.listOfCategories = listOfCategories;
        this.listOfCustOrders = listOfCustOrders;
        this.listOfUsers = listOfUsers;
        this.catalog = catalog;
    }

    public DepartmentStore() {
        this.listOfCategories = new ArrayList<Category>();
        this.listOfCustOrders = new ArrayList<Order>();
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

    public LocalDate getDateInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a date in the format of: M/D/YYYY.\n");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/D/YYYY");
        LocalDate date = LocalDate.parse(input.nextLine(), dateFormat);
        return date;
    }

    public void addProduct(String prodType, String productID, String productName, String brandName, String productDesc,
                           LocalDate dateOfIncorp, ArrayList<Category> prodCategory) throws IllegalArgumentException{
        // WHEN IN APPLICATION - ADMIN CHOOSES ELECTRONIC - SERIAL NOS - UPDATE TAB
        Scanner input = new Scanner(System.in);
        try{
            this.getProduct(productID, productName);
        }
        catch (IllegalArgumentException iae){
            String serialNum, imei, os, ram, hardDrive, authorName, intendedLoc;
            Integer yearWarranty, numPages, edition;
            LocalDate datePublished;

            switch (prodType){
                case "Electronics":
                    // SERIAL NO, WARRANTY PERIOD
                    System.out.println("Please input the serial number.\n");
                    serialNum = input.nextLine();
                    System.out.println("Please input how many years the item's warranty is valid for.\n");
                    yearWarranty = input.nextInt();
                    catalog.add(new Electronic(productID, productName, brandName, productDesc, dateOfIncorp,
                            prodCategory, serialNum, yearWarranty));
                    break;
                case "Cellphones":
                    // SERIAL NO, WARRANTY PERIOD, IMEI, OS
                    System.out.println("Please input the serial number.\n");
                    serialNum = input.nextLine();
                    System.out.println("Please input how many years the item's warranty is valid for.\n");
                    yearWarranty = input.nextInt();
                    System.out.println("Please input the IMEI number.\n");
                    imei = input.nextLine();
                    System.out.println("Please input the operating number.\n");
                    os = input.nextLine();
                    catalog.add(new Cellphone(productID, productName, brandName, productDesc, dateOfIncorp,
                            prodCategory, serialNum, yearWarranty, imei, os));
                    break;
                case "Computers":
                    // SERIAL NO, WARRANTY PERIOD, RAM, HARD DRIVE
                    System.out.println("Please input the serial number.\n");
                    serialNum = input.nextLine();
                    System.out.println("Please input how many years the item's warranty is valid for.\n");
                    yearWarranty = input.nextInt();
                    System.out.println("Please input the computer's RAM.\n");
                    ram = input.nextLine();
                    System.out.println("Please input the computer's hard drive.\n");
                    hardDrive = input.nextLine();
                    catalog.add(new Computer(productID, productName, brandName, productDesc, dateOfIncorp,
                            prodCategory, serialNum, yearWarranty, ram, hardDrive));
                    break;
                case "Books":
                    // AUTHOR NAME, PUBLICATION DATA, NUM PAGES, EDITION
                    System.out.println("Please input the author's name.\n");
                    authorName = input.nextLine();
                    System.out.println("Please input the book's published date.\n");
                    datePublished = getDateInput();
                    System.out.println("Please input the number of pages the book contains.\n");
                    numPages = input.nextInt();
                    System.out.println("Please input the number to indicate which edition this book is.\n");
                    edition = input.nextInt();
                    catalog.add(new Book(productID, productName, brandName, productDesc, dateOfIncorp,
                            prodCategory, authorName, datePublished, numPages, edition));
                    break;
                case "Homelines":
                    // INTENDED LOCATION
                    System.out.println("Please input the intended location for this home product.\n");
                    intendedLoc = input.nextLine();
                    catalog.add(new HomeProduct(productID, productName, brandName, productDesc, dateOfIncorp,
                            prodCategory, intendedLoc));
                    break;
                default:
                    System.out.println("Error: The object type is invalid.\n");
                    break;

            }           // END OF SWITCH CASE - ADD BY PRODUCT TYPE
        }               // END OF CATCH ILLEGAL ARGUMENT EXCEPTION - IF PRODUCT WASN'T ALREADY IN CATALOG
    }                   // END OF ADD PRODUCT METHOD

    public void removeProduct(Product product){
        for (Product p : catalog) {
            if (catalog.contains(product))
                catalog.remove(product);
            else
                throw new IllegalArgumentException("Error: There's no such item that can be removed.");
        }
    }                   // END OF REMOVE PRODUCT FROM CATALOG METHOD

    public void addCatToProduct(Product product){
        product.getProdCategory();
    }

    public void removeCatFromProduct(Product product){

    }

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