package Users;

import Product.*;

import java.io.*;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;

public class StoreSystem {
    private static ArrayList<Category> listOfCategories;
    private static ArrayList<User> listOfUsers;
    private static ArrayList<Product> catalog;
    private static ArrayList<Order> listOfCustOrders;
    public static final String filename = "./storeSystem.ser";

    public StoreSystem() {
        listOfCategories = new ArrayList<Category>();
        listOfUsers = new ArrayList<User>();
        catalog = new ArrayList<Product>();
        listOfUsers.add(new Admin("admin@admin.com", "password", "admin"));
        listOfUsers.add(new Customer("customer@gmail.com", "pw", "user"));
    }

    public static ArrayList<Category> getListOfCategories() {
        return listOfCategories;
    }

    public static ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }

    public static ArrayList<Product> getCatalog() {
        return catalog;
    }

    public static ArrayList<Order> getListOfCustOrders() {
        return listOfCustOrders;
    }


    public void createUser(String email, String password, String displayName) throws IllegalArgumentException {
        if(listOfUsers.size() == 0) {
            listOfUsers.add(new Customer(email,password,displayName));
            System.out.println("User " + displayName + " successfully added!");
            return;
        }
        for(User u : listOfUsers)
            if(u.getEmail().equalsIgnoreCase(email) || u.getDisplayName().equalsIgnoreCase(displayName)) {
                throw new IllegalArgumentException("Email or username already exists.");
            }
        listOfUsers.add(new Customer(email,password,displayName));
        System.out.println("User " + displayName + " successfully added!");
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

    public void addElectronic(String prodType, String productID, String productName, String brandName, String productDesc,
                              LocalDate dateOfIncorp, String serialNum, String yearWarranty)
                            throws IllegalArgumentException {
        for (Product p : catalog) {
            if (p.getProductID().equalsIgnoreCase(productID)) {
                throw new IllegalArgumentException("Error: Product already exists in catalog.");
            }
            if (prodType != "Electronic")
                throw new IllegalArgumentException("Error: Invalid product addition.");
        }
        catalog.add(new Electronic(productID, productName, brandName, productDesc, dateOfIncorp, serialNum, yearWarranty));
    }

    public void addCellphone(String prodType, String productID, String productName, String brandName, String productDesc,
                             LocalDate dateOfIncorp, String serialNum, String yearWarranty, String imei, String os)
                            throws IllegalArgumentException {
        for (Product p : catalog) {
            if (p.getProductID().equalsIgnoreCase(productID)) {
                throw new IllegalArgumentException("Error: Product already exists in catalog.");
            }
            if (prodType != "Cellphone")
                throw new IllegalArgumentException("Error: Invalid product addition.");
        }
        catalog.add(new Cellphone(productID, productName, brandName, productDesc, dateOfIncorp, serialNum, yearWarranty,
                imei, os));
    }

    public void addComputer(String prodType, String productID, String productName, String brandName, String productDesc,
                             LocalDate dateOfIncorp, String serialNum, String yearWarranty, String ram, String hardDrive)
                            throws IllegalArgumentException {
        for (Product p : catalog) {
            if (p.getProductID().equalsIgnoreCase(productID)) {
                throw new IllegalArgumentException("Error: Product already exists in catalog.");
            }
            if (prodType != "Computer")
                throw new IllegalArgumentException("Error: Invalid product addition.");
        }
        catalog.add(new Computer(productID, productName, brandName, productDesc, dateOfIncorp, serialNum, yearWarranty,
                ram, hardDrive));
    }

    public void addBook(String prodType, String productID, String productName, String brandName, String productDesc,
                            LocalDate dateOfIncorp, String authorName, LocalDate datePublished, Integer numPages,
                            Integer edition) throws IllegalArgumentException {
        for (Product p : catalog) {
            if (p.getProductID().equalsIgnoreCase(productID)) {
                throw new IllegalArgumentException("Error: Product already exists in catalog.");
            }
            if (prodType != "Book")
                throw new IllegalArgumentException("Error: Invalid product addition.");
        }
        catalog.add(new Book(productID, productName, brandName, productDesc, dateOfIncorp, authorName, datePublished,
                numPages, edition));
    }

    public void addHome(String prodType, String productID, String productName, String brandName, String productDesc,
                        LocalDate dateOfIncorp, String intendedLoc) throws IllegalArgumentException {
        for (Product p : catalog) {
            if (p.getProductID().equalsIgnoreCase(productID)) {
                throw new IllegalArgumentException("Error: Product already exists in catalog.");
            }
            if (prodType != "Home")
                throw new IllegalArgumentException("Error: Invalid product addition.");
        }
        catalog.add(new HomeProduct(productID, productName, brandName, productDesc, dateOfIncorp, intendedLoc));
    }

    public void removeProduct(Product product){
        for (Product p : catalog) {
            if (catalog.contains(product))
                catalog.remove(product);
            else
                throw new IllegalArgumentException("ERROR: NO SUCH PRODUCT EXISTS");
        }
    }                   // END OF REMOVE PRODUCT FROM CATALOG METHOD

    public void addCategory(String catID, String catName, String catDesc) throws IllegalArgumentException {
        if(listOfCategories.size() == 0) {
            listOfCategories.add(new Category(catID,catName,catDesc));
            System.out.println("Category " + catName + " successfully added!");
            return;
        }
        for(Category c : listOfCategories){
            if(c.getCatID().equalsIgnoreCase(catID)) {
                System.out.println("Error adding category: Category already exists.");
                throw new IllegalArgumentException("Category already exists.");
            }
        }
        listOfCategories.add(new Category(catID,catName,catDesc));
        System.out.println("Category " + catName + " successfully added!");
    }

    public void removeCategory(String catID) throws IllegalArgumentException {
        boolean removed = false;
        for(int i = 0; i < listOfCategories.size(); i ++) {
            if(listOfCategories.get(i).getCatID().equalsIgnoreCase(catID)) {
                listOfCategories.remove(i);
                removed = true;
                break;
            }
        }
        if(removed) {
            for (Product p : catalog) {
                if (p.getProductID().equalsIgnoreCase(catID))
                    p.setProductID("Default"); //If a product's category was deleted, change to Default.
            }
        }else{
            throw new IllegalArgumentException("Category doesn't exist.");
        }
    }


    //Customer's credentials should already been authenticated
    public void newOrder(Customer c, Product p) {
        SecureRandom random = new SecureRandom();//order#
        Order o = null;

        for (Iterator<User> us = listOfUsers.iterator(); us.hasNext(); ) {
            //if user has an account...
            if (listOfUsers.contains(c)) {
                //if user has a open order
                for (Iterator<Order> or = c.getListOfCustOrders().iterator(); or.hasNext(); ) {
                    o.getOrderNum();
                    addProduct(c, p, o);
                }
            }else {//user with no open orders will get an order number +
                o.setOrderNum(Integer.valueOf(String.valueOf(random)));
                addProduct(c, p, o);
            }
        }
    }

    public void addProduct(Customer c, Product p, Order o){
        StoreSystem sp = null;
        for (Iterator<User> us = listOfUsers.iterator(); us.hasNext();) {
            if(listOfUsers.contains(c)){//if user is in listsOfUsers

                //need to figure out how to check prod availability
                for (Iterator<Order> or= c.getListOfCustOrders().iterator(); or.hasNext();){
                    if (c.getListOfCustOrders().contains(o.getOrderNum())){//if user has order number
                        // if(sp.getProduct(p.getProductID(), p.getProductName()) == IS AVAILABLE! )//how to find out prod avail?
                        o.getProdsInOrder().add(sp.getProduct(p.getProductID(), p.getProductName()));// prod will be added
                    }//else{
                    //prod not available!!!

                }
            }

        }
    }

    public void removeProduct(Customer c, Product p){
        for (Iterator<User> us = listOfUsers.iterator(); us.hasNext();) {
            if (listOfUsers.contains(c)){
                for (Iterator<Order> or = c.getListOfCustOrders().iterator(); or.hasNext(); ) {
                    if (or.next().getProdsInOrder().contains(p.getProductName())) {
                        or.remove();
                    }
                }
            }
        }
    }

    //counts list of orders... will change if needed
    public void countDuplicateItems(String userName){
        Customer c = new Customer();
        Set<Order> orderList = new HashSet<Order>(c.getListOfCustOrders());
        for (Order temp : orderList){
            System.out.println(temp + ": " + Collections.frequency(orderList, temp));
        }
    }

    //is this right???
    public void finalizeOrder(String userName, int orderNum){
        Customer c = new Customer();
        Order o = new Order();

        c.setDisplayName(userName);
        o.setOrderNum(orderNum);
        for(Iterator<User> u = listOfUsers.iterator(); u.hasNext();){//can I delete this?
            if(listOfUsers.contains(c)){
                for(Iterator<Order> or = c.getListOfCustOrders().iterator(); or.hasNext();){
                    if(c.getListOfCustOrders().contains(o.getOrderNum())){
                        o.setStatus(true);// is this right?
                    }else{
                        o.setStatus(false);

                    }
                }
            }
        }
    }

    public void cancelOrder(Customer c, Order o){
        if(listOfUsers.contains(c)){
            if(c.getListOfCustOrders().contains(o.getOrderNum())){
                c.getListOfCustOrders().remove(o.getOrderNum());
            }
        }
    }

    // LOAD STORE SYSTEM DATABASE - REFERENCE URL: https://ucdenver.instructure.com/courses/440985/modules/items/2561187
    // BEGIN AT TIME 22:30 TO SEE JAVIER'S EXAMPLE OF LOADING IN A UNIVERSITY
    public static StoreSystem loadFromFile(){
        ObjectInputStream ois = null;
        StoreSystem storeSystem = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(StoreSystem.filename));
            storeSystem = (StoreSystem) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            storeSystem = new StoreSystem();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return storeSystem;
    }

    public void saveToFile() {
        ObjectOutputStream objectOutputStream = null;

        try {
            // Opening the stream to a file - Saves to filename
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

}                       // END OF DEPARTMENT STORE CLASS
