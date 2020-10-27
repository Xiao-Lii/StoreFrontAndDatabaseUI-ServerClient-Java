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
    // move listOfCustOrders to Customer class

    public void createUser(String type, String email, String password, String displayName) {
        if(type.equalsIgnoreCase("Users.Admin") || type.equalsIgnoreCase("Administrator"))
            listOfUsers.add(new Admin(email, password, displayName));
        else if(type.equalsIgnoreCase("Users.Customer"))
            listOfUsers.add(new Customer(email, password, displayName));
        else
            System.out.println("Invalid user type.");
    }

    //#7order management


    //Customer's credentials should already been authenticated
    public void newOrder(Customer dName, Product p) {
        SecureRandom random = new SecureRandom();//order#
        Order o = null;


        for (Iterator<User> us = listOfUsers.iterator(); us.hasNext(); ) {
            //check if the list of users is = dName;
            if (listOfUsers.contains(dName)) {
                for (Iterator<Order> or = listOfCustOrders.iterator(); or.hasNext(); ) {
                    //need to figure out how to access product inventory... HELP!!
                    o.getOrderNum();
                    addProduct(dName, p);
                 //   if (or.next().prodsInOrder.contains(p.getProductName())) {}
                }
            }else {//user with no open orders will get an order number +
                o.setOrderNum(Integer.valueOf(String.valueOf(random)));
                addProduct(dName, p);
            }
        }
    }

    public void addProduct(Customer dName, Product p){
        //Order o; should this be part of the addProduct parameter???
        for (Iterator<User> us = listOfUsers.iterator(); us.hasNext();) {
            if (listOfUsers.contains(dName)) {
                 {

                }
            }
        }
        //iterate through listCustOfOrder
        //check if propsInProduct conatins p
    }

    public void removeProduct(Customer dName, Product p){
        for (Iterator<User> us = listOfUsers.iterator(); us.hasNext();) {
            if (listOfUsers.contains(dName)){
                for (Iterator<Order> or = listOfCustOrders.iterator(); or.hasNext(); ) {
                    if (or.next().prodsInOrder.contains(p.getProductName())) {
                    or.remove();
                    }
                }
            }
        }
    }

    public Order finalizeOrder(){

    }

    public Order cancelOrder(){

    }

}