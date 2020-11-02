package Users;

import Product.Product;
import sun.awt.HKSCS;

import java.time.LocalDate;
import java.util.*;

public class Order {
    private int orderNum;
    private boolean status; //0 = pending, 1 = finished
    private LocalDate finalizationDate;
    ArrayList<Product> prodsInOrder;

    public Order(int orderNum, boolean status, LocalDate finalizationDate, ArrayList<Product> prodsInOrder) {
        this.orderNum = orderNum;
        this.status = status;
        this.finalizationDate = finalizationDate;
        this.prodsInOrder = prodsInOrder;
    }

    public Order(){
        this.orderNum = 0;
        this.status = false;
        this.finalizationDate = null;
        this.prodsInOrder = null;
    }

    public  ArrayList<Product> getProdsInOrder(){return prodsInOrder; }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getFinalizationDate() {
        return finalizationDate;
    }

    public void setFinalizationDate(LocalDate finalizationDate) {
        this.finalizationDate = finalizationDate;
    }
}