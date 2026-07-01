package adopticare;

import auth.*;
import menu.*;
import java.util.Scanner;

public class AdoptiCareMain {

    static Scanner input = new Scanner(System.in);

    public static void onSystem() {

        int choice;

        do {
            System.out.println("\n===== ADOPTICARE =====");

            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            System.out.print("Choose: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    handleLogin();
                    break;

                case 2:
                    Register.registerCustomer();
                    break;

                case 3:
                    System.out.println("Thank you for using AdoptiCare!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    public static void handleLogin() {

        //Data Validation in where the user will proceed
        String role = Login.login();

        if (role == null) {
            System.out.println("Invalid username or password.");
            
            return;
        }
        
        switch (role) {
            
            case "Administrator":
                Admin.adminMenu();
                break;
                
            case "Veterinarian":
                Veterinarian.veterinarianMenu();
                break;
                
            case "Customer":
                Customer.customerMenu();
                break;
                
            default:
                System.out.println("Invalid role.");
        }
    }
}
