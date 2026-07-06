package adopticare;

import auth.*;
import menu.*;
import java.util.Scanner;

public class AdoptiCareMain {

    static Scanner input = new Scanner(System.in);

    public static void onSystem() {

        int choice;

        do {
            System.out.println("\n🏡 Welcome to AdoptiCare! 🐶🐱 ");

            System.out.println("[1] 👤 Customer Login");
            System.out.println("[2] 👑 Admin Login");
            System.out.println("[3] 👨‍⚕ Veterinarian Login");
            System.out.println("[4] 📝 Register");
            System.out.println("[5] ⏻ Exit");

            System.out.print("\n👉 Choose an option [1-5]: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    handleCustomerLogin();
                    break;
                    
                case 2:
                    handleAdminLogin();
                    break;
                    
                case 3:
                    handleVetLogin();
                    break;
                    
                case 4:
                    Register.registerCustomer();
                    break;

                case 5:
                    System.out.println("\n🏡 Thank you for using AdoptiCare! 🐶🐱");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    public static void handleCustomerLogin() {

        //Data Validation in where the user will proceed
        String role = Login.login();
        
        

        if (role == null) {
            System.out.println("Invalid username or password.");
            
            return;
        }
        
        switch (role) {
            case "Customer":
                Customer.customerMenu();
                break;
                
            default:
                System.out.println("Invalid role.");
        }
    }

    public static void handleAdminLogin() {
        
       String role = AdminLogin.adminLogin();
       
       if (role == null) {
           System.out.println("Invalid username or password.");
           
           return;
       }
       
       switch (role) {
           case "Administrator":
               Admin.adminMenu();
               
           default:
               System.out.println("Invalid role.");
       }
    }

    public static void handleVetLogin() {
        
        String role = VetLogin.vetLogin();
        
        if (role == null) {
            System.out.println("Invalid username or password");
            
            return;
        }
        
        switch (role) {
            case "Veterinarian":
                Veterinarian.veterinarianMenu();
                
            default:
                System.out.println("Invalid role.");
        }
    }
}
