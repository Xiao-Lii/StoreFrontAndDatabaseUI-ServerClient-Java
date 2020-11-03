package Users;

import Product.Product;

import java.time.LocalDate;
import java.util.*;

public class Order {
    private int orderNum = 0;
    private boolean isFinalized;    // False = Finalized, True = Finalized
    private LocalDate finalizationDate;
    ArrayList<Product> prodsInOrder;

    public Order(){
        this.orderNum++;
        this.isFinalized = false;
        this.finalizationDate = null;
        this.prodsInOrder = new ArrayList<>();
    }

    public Order(Integer orderNum){
        this.orderNum++;
        this.isFinalized = false;
        this.finalizationDate = null;
        this.prodsInOrder = new ArrayList<>();
    }

    // THIS IS IMPORTANT WHEN DISPLAYING TO LISTVIEW TABLES
    @Override
    public String toString(){
        return String.format("%-10d\t - \t%d\t - \tFinalized On: %tD", this.getOrderNum(), this.getIsFinalized(), this.getFinalizationDate());
    }

    public void addProductToOrder(Product product){
        this.prodsInOrder.add(product);
    }

    public void removeProductFromOrder(Product product){
        for (Product p : prodsInOrder){
            if (p.equals(product)){

            }
        }
    }

    public  ArrayList<Product> getProdsInOrder(){return prodsInOrder; }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public boolean getIsFinalized() {
        return isFinalized;
    }

    public void setIsFinalized(boolean status) {
        this.isFinalized = status;
    }

    public LocalDate getFinalizationDate() {
        return finalizationDate;
    }

    public void setFinalizationDate(LocalDate finalizationDate) {
        this.finalizationDate = finalizationDate;
    }
}