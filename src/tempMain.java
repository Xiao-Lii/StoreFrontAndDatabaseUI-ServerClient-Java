
/*

 This company wants a system that allows their employees to manage the products offered in the store as well
 the users and product categories. Moreover, they want an application for their customers to browse their catalog,
 search for products and make orders.



 */


import Users.DepartmentStore;
import server.MultiThreadServer;

public class tempMain {
    public static void main(String[] args) {
        DepartmentStore departmentStore = new DepartmentStore();
        departmentStore.createUser( "email@email.com", "pw", "defaultUser");
        //departmentStore.createUser( "email@email.com", "pw", "defaultUser");

        MultiThreadServer server = new MultiThreadServer(10000);
        new Thread(server).start();

        //System.out.println("Stopping Server");
        //server.stop();

    }
}           // END OF MAIN


