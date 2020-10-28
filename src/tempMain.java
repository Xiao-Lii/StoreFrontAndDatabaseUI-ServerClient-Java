
import java.time.LocalDate;
import Users.*;

public class tempMain {
    public static void main(String[] args) {
        DepartmentStore departmentStore = new DepartmentStore();
        departmentStore.createUser( "email@email.com", "pw", "defaultUser");
        departmentStore.createUser( "email@email.com", "pw", "defaultUser");

        departmentStore.addProduct("Electronics", "Electronic72", "Samsung 55\" Smart TV",
                "Samsung", "A 55\" smart TV", departmentStore.getDateInput(), );




    }
}           // END OF MAIN


