package application;
import Product.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class CatalogApp {
    //app for customers

    /*1.Browse: list of categories -> when user choose a category, will show all products in category
    * 2.Search: display all products with same category
    * 3.Get Product details: system will show prod details
    * 4.Create new empty order
    * 5.add product to order
    * 6.list order products
    * 7.remove product
    * 8.finalize
    * 9.cancel*/

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        System.out.println("Product Catalog Menu\n" +
                "1. Browse Categories\n" +
                "2. Search Products\n");
        //idk if I need to create order here.

        int userInput = input.nextInt();

        switch (userInput){
            case 1:
                Product p = p.getProdCategory();
                browseCategory(p);
                break;
            case 2:
                searchProducts();
                break;
            default:
                System.out.println("Eyy, Maybe choose the right option??");
                break;

        }

    }
    public static void browseCategory(Product p){
        Scanner in = new Scanner(System.in);

        System.out.println("\nPlease choose a Product Category to browse\n" +
                "1. Books\n" +
                "2. Electronics\n" +
                "3. Home Products\n");

        int userIn = in.nextInt();
        switch (userIn){
            case 1:
                if(p.getProdCategory().contains("books")) {
                    for (int i = 0; i < p.getProdCategory().size(); i++) {
                        System.out.println(p.getProdCategory());
                    }
                }
                break;

            case 2:
                //electronics
                break;
            case 3:
                //home prod
                break;
        }
    }

    public static void searchProducts(){

    }

}
