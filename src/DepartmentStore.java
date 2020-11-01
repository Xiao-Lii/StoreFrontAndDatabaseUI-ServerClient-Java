import Product.Category;
import Product.Product;
import Users.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.security.SecureRandom;
import java.util.*;

public class DepartmentStore{
    private ArrayList<Category> listOfCategories; //electronics
    private ArrayList<User> listOfUsers; // list of all users
   // private ArrayList<Order> listOfCustOrders; // moved to Customers class

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
    public void countDuplicateItems(Customer c){
        Set<Order> orderList = new HashSet<Order>(c.getListOfCustOrders());
        for (Order temp : orderList){
            System.out.println(temp + ": " + Collections.frequency(orderList, temp));
        }
    }

    //is this right???
    public Order finalizeOrder(Customer c, Order o){
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
        return o;
    }

    public void cancelOrder(Customer c, Order o){
        if(listOfUsers.contains(c)){
            if(c.getListOfCustOrders().contains(o.getOrderNum())){
                c.getListOfCustOrders().remove(o.getOrderNum());
            }
        }
    }

}