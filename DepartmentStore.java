import Product.Category;
import Product.Product;
import Users.Admin;
import Users.Customer;
import Users.User;

import java.security.SecureRandom;
import java.util.*;

public class DepartmentStore{
    private ArrayList<Category> listOfCategories; //electronics
    private ArrayList<User> listOfUsers; // list of all users
    private ArrayList<Order> listOfCustOrders; // list of all the orders -> list of list
    //move listOfCustOrders to Customer class
    public void createUser(String type, String email, String password, String displayName) {
        if(type.equalsIgnoreCase("Users.Admin") || type.equalsIgnoreCase("Administrator"))
            listOfUsers.add(new Admin(email, password, displayName));
        else if(type.equalsIgnoreCase("Users.Customer"))
            listOfUsers.add(new Customer(email, password, displayName));
        else
            System.out.println("Invalid user type.");
    }

    //#7order management


    //Customer's credentials has already been authenticated
    public void newOrder(Customer dName){
        SecureRandom random = new SecureRandom();//order#
        Order o = null;
        Product p;
        for (Iterator<User> us = listOfUsers.iterator(); us.hasNext();) {
            //check if the list of users is = dName;
            if (listOfUsers.contains(dName)){
                for (Iterator<Order> or = listOfCustOrders.iterator(); or.hasNext(); ) {

                  //  if (or.next().prodsInOrder.contains(p.getProductName())){

                    }
                    /*if user has an existing order, continue-
                     * else create new order
                     * maybe an if-else statement?*/
                }else{
                o.setOrderNum(random);
            }

            }


    }

    public Product addProduct(Customer dName, Product p){
        //iterate through listCustOfOrder
        //check if propsInProduct conatins p
    }

    public Product removeProduct(Customer dName, Product p){
        for (Iterator<User> us = listOfUsers.iterator(); us.hasNext();) {
            if (listOfUsers.contains(dName)){
                for (Iterator<Order> or = listOfCustOrders.iterator(); or.hasNext(); ) {
                    if (or.next().prodsInOrder.contains(p.getProductName())) {
                    or.remove();
                    }
                }
            }
        }
        return;
    }

    public Order finalizeOrder(){

    }

    public Order cancelOrder(){

    }

}