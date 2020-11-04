package Users;

import Product.*;
import org.omg.CORBA.ORB;

import java.io.*;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;

public class StoreSystem implements Serializable {
    private static ArrayList<Category> listOfCategories;
    private static ArrayList<User> listOfUsers;
    private static ArrayList<Product> catalog;
    private static ArrayList<Order> listOfCustOrders;
    public static final String filename = "./storeSystem.ser";

    public StoreSystem() {
        // Establishing Empty Catalog and Empty List of Customer Orders
        catalog = new ArrayList<>();
        listOfCustOrders = new ArrayList<>();

        // Establishing Basic Default Categories
        listOfCategories = new ArrayList<>();
        Category defaultCategory = new Category("Default", "Default", "Default Category description");
        Category electronic = new Category("Electronic","Electronic",
                "Technological entertainment revolving around electronic devices and media");
        Category computer = new Category("Computer","Computer",
                "Technological entertainment revolving around computers");
        Category cellphone = new Category("Cellphone","Cellphone",
                "Technological entertainment revolving around personal cellphones");
        Category book = new Category("Book", "Book", "Paperback media entertainment");
        Category home = new Category("Home", "Home", "For decorating the household");
        listOfCategories.add(defaultCategory);
        listOfCategories.add(electronic);
        listOfCategories.add(computer);
        listOfCategories.add(cellphone);
        listOfCategories.add(book);
        listOfCategories.add(home);

        // Establishing Basic Default Users - Customer & Admin
        listOfUsers = new ArrayList<>();
        Admin admin = new Admin("admin@admin.com", "password", "admin");
        Customer customer = new Customer("customer@gmail.com", "pw", "user");
        listOfUsers.add(admin);
        listOfUsers.add(customer);

        // INSERT TEST VALUES BELOW FOR CHECKING STORE SYSTEM - WE CAN DELETE LATER
        // Establishing Some of the Customer's Orders
        customer.createOrder();

    }

    public ArrayList<Category> getListOfCategories() {
        return listOfCategories;
    }

    public ArrayList<String> getNameListOfCategories(){
        ArrayList<String> catNames = new ArrayList<>();
        for (Category c: listOfCategories){
            catNames.add(c.getCatName());
        }
        return catNames;
    }

    public ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }

    public ArrayList<Product> getCatalog() {
        return catalog;
    }

    // GETS LIST OF ALL CUSTOMER ORDERS UNDER LIST OF USERS
    public ArrayList<Order> getListOfCustOrders() {
        for (User u : listOfUsers){
            if (u.getClass().getSimpleName().equalsIgnoreCase("Customer")){
                for (Order o : u.getListOfCustOrders())
                    this.listOfCustOrders.add(o);
            }
        }
        return this.listOfCustOrders;
    }

    // GETS LIST OF ALL FINALIZED CUSTOMER ORDERS UNDER LIST OF USERS
    public ArrayList<Order> getFinalizedListOfCustOrders() {
        for (User u : listOfUsers){
            if (u.getClass().getSimpleName().equalsIgnoreCase("Customer")){
                for (Order o : u.getListOfCustOrders()) {
                    if (o.getIsFinalized() == true)
                        this.listOfCustOrders.add(o);
                }
            }
        }
        return this.listOfCustOrders;
    }

    @Override
    public String toString() {
        return String.format("Department Store with %d items and %d categories.", getCatalog().size(),
                getListOfCategories().size());
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

    public void addElectronic(String productID, String productName, String brandName, String productDesc,
                              LocalDate dateOfIncorp, String serialNum, String warranty)
                            throws IllegalArgumentException {
        if (productID == "" | productName == "" | brandName == "" | productDesc == "" |
                dateOfIncorp == null | serialNum == "" | warranty == "")
            throw new IllegalArgumentException();

        for (Product p : catalog) {
            if (p.getProductID().equalsIgnoreCase(productID)) {
                throw new IllegalArgumentException();
            }
        }
        catalog.add(new Electronic(productID, productName, brandName, productDesc, dateOfIncorp, serialNum, warranty));
    }

    public void addCellphone(String productID, String productName, String brandName, String productDesc,
                             LocalDate dateOfIncorp, String serialNum, String yearWarranty, String imei, String os)
                            throws IllegalArgumentException {
        if (productID == "" | productName == "" | brandName == "" | productDesc == "" |
                dateOfIncorp == null | serialNum == "" | yearWarranty == "" | imei == "" | os == "")
            throw new IllegalArgumentException();

        for (Product p : catalog) {
            if (p.getProductID().equalsIgnoreCase(productID)) {
                throw new IllegalArgumentException();
            }
        }
        catalog.add(new Cellphone(productID, productName, brandName, productDesc, dateOfIncorp, serialNum, yearWarranty,
                imei, os));
    }

    public void addComputer(String productID, String productName, String brandName, String productDesc,
                             LocalDate dateOfIncorp, String serialNum, String yearWarranty, String ram, String hardDrive)
                            throws IllegalArgumentException {
        if (productID == "" | productName == "" | brandName == "" | productDesc == "" |
                dateOfIncorp == null | serialNum == "" | yearWarranty == "" | serialNum == "" | yearWarranty == null
                | ram == "" | hardDrive == "")
            throw new IllegalArgumentException();

        for (Product p : catalog) {
            if (p.getProductID().equalsIgnoreCase(productID)) {
                throw new IllegalArgumentException();
            }
        }
        catalog.add(new Computer(productID, productName, brandName, productDesc, dateOfIncorp, serialNum, yearWarranty,
                ram, hardDrive));
    }

    public void addBook(String productID, String productName, String brandName, String productDesc,
                            LocalDate dateOfIncorp, String authorName, LocalDate datePublished, Integer numPages,
                            Integer edition) throws IllegalArgumentException {
        if (productID == "" | productName == "" | brandName == "" | productDesc == "" |
                dateOfIncorp == null | authorName == "" | datePublished == null | numPages == null | edition == null)
            throw new IllegalArgumentException();

        for (Product p : catalog) {
            if (p.getProductID().equalsIgnoreCase(productID)) {
                throw new IllegalArgumentException();
            }
        }
        catalog.add(new Book(productID, productName, brandName, productDesc, dateOfIncorp, authorName, datePublished,
                numPages, edition));
    }

    public void addHome(String productID, String productName, String brandName, String productDesc,
                        LocalDate dateOfIncorp, String intendedLoc) throws IllegalArgumentException {
        if (productID == "" | productName == "" | brandName == "" | productDesc == "" |
                dateOfIncorp == null | intendedLoc == "")
            throw new IllegalArgumentException();

        for (Product p : catalog) {
            if (p.getProductID().equalsIgnoreCase(productID)) {
                throw new IllegalArgumentException();
            }
        }
        catalog.add(new HomeProduct(productID, productName, brandName, productDesc, dateOfIncorp, intendedLoc));
    }

    // REMOVE A PRODUCT BY PRODUCT SELECTION
    public void removeProduct(Product product){
        for (Product p : catalog) {
            if (catalog.contains(product))
                catalog.remove(product);
            else
                throw new IllegalArgumentException("ERROR: NO SUCH PRODUCT EXISTS");
        }
    }                   // END OF REMOVE PRODUCT FROM CATALOG METHOD

    // REMOVE PRODUCT BY SEARCHING PRODUCT ID
    public void removeProductByID(String productID) {
        try {
            for (int i = 0; i < catalog.size(); i++) {
                if (catalog.get(i).getProductID().equalsIgnoreCase(productID)) {
                    catalog.remove(i);
                    break;
                }
                else
                    throw new IllegalArgumentException();
            }
        }
        catch (Exception e){
            throw new IllegalArgumentException();
        }
    }

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

    public ArrayList<Product> getProductsInOrder (Order order){
        // LOOK AT ORDER
        // GET LIST OF PRODUCTS IN ORDER
        // RETURN THAT LIST
        return null;
    }

    // MAY NEED TO REVIEW THIS - SHOULDN'T BE ABLE TO HAVE A CUSTOMER ACCOUNT W/ NO EMAIL, USERNAME, PW
    //counts list of orders... will change if needed
    public void countDuplicateItems(String userName){
        Customer c = new Customer();
        Set<Order> orderList = new HashSet<>(c.getListOfCustOrders());
        for (Order temp : orderList){
            System.out.println(temp + ": " + Collections.frequency(orderList, temp));
        }
    }

    // IF WE WANT TO FINALIZE AN ORDER - WE NEED TO LOOK AT CUSTOMER'S LIST OF ORDERS
    // WHAT THEY HAVE SELECTED (THEIR CURRENT ORDER) IS CORRESPONDING TO CUSTOMER'S CURRENT ORDER
    // SHOULD IN THEORY - BE MANIPULATED TO SAY FINALIZED
    public void finalizeOrder(Customer customer, Order order) {
        for (Order o : customer.getListOfCustOrders()){
            if (o.equals(order)){
                o.setIsFinalized(true);     // Changes order status to true = Finalized
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
    // THIS WAS A STATIC METHOD AND I CHANGED IT NOT TO BE STATIC TO ACCESS METHOD IN CONTROLLER CLASS
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
