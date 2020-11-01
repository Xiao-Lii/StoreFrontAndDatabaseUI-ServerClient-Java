import Product.Category;
import Product.Product;
import Users.Admin;
import Users.Customer;
import Users.Order;
import Users.User;
import com.sun.org.apache.xpath.internal.operations.Or;

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
            //if user has an account...
            if (listOfUsers.contains(dName)) {
                //if user has a open order
                for (Iterator<Order> or = listOfCustOrders.iterator(); or.hasNext(); ) {
                    o.getOrderNum();
                    addProduct(dName, p, o);
                }
            }else {//user with no open orders will get an order number +
                o.setOrderNum(Integer.valueOf(String.valueOf(random)));
                addProduct(dName, p, o);
            }
        }
    }

    public void addProduct(Customer dName, Product p, Order o){
        for (Iterator<User> us = listOfUsers.iterator(); us.hasNext();) {
            if(listOfUsers.contains(dName)){//if user is in listsOfUsers
                for (Iterator<Order> or= listOfCustOrders.iterator(); or.hasNext();){
                    if (listOfCustOrders.contains(o.getOrderNum())){//if user has order number
                      //  o.getProdsInOrder().add(p.getProductName());// how to add product?
                    }
                }
            }

            }
        }
        //iterate through listCustOfOrder
        //check if propsInProduct contains p

    public void removeProduct(Customer dName, Product p){
        for (Iterator<User> us = listOfUsers.iterator(); us.hasNext();) {
            if (listOfUsers.contains(dName)){
                for (Iterator<Order> or = listOfCustOrders.iterator(); or.hasNext(); ) {
                    if (or.next().getProdsInOrder().contains(p.getProductName())) {
                    or.remove();
                    }
                }
            }
        }
    }

    //is this right???
    public Order finalizeOrder(Customer dName, Order o){
        for(Iterator<User> u = listOfUsers.iterator(); u.hasNext();){//can I delete this?
            if(listOfUsers.contains(dName)){
                for(Iterator<Order> or = listOfCustOrders.iterator(); or.hasNext();){
                    if(listOfCustOrders.contains(o.getOrderNum())){
                         o.setStatus(true);// is this right?
                    }else{
                        o.setStatus(false);

                    }
                }
            }
        }
        return o;
    }

    public void cancelOrder(Customer dName, Order o){
        if(listOfUsers.contains(dName)){
            if(listOfCustOrders.contains(o.getOrderNum())){
                listOfCustOrders.remove(o.getOrderNum());
            }
        }
    }

}