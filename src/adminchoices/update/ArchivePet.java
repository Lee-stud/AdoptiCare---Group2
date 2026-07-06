package adminchoices.update;

import adminchoices.read.ViewArchivedPets;
import java.util.Scanner;
import java.sql.*;
import database.DbConnection;

public class ArchivePet {

    static Scanner input = new Scanner(System.in);

    //=====================
    //ARCHIVED PET MENU
    //=====================
    public static void archivedPetsMenu() {

        while (true) {
            System.out.println("\n🗄 ===== ARCHIVED PETS MENU =====");
            System.out.println("[1] 🗃 Archive Pet");
            System.out.println("[2] 📋 View Archived Pets");
            System.out.println("[3] ♻ Restore Pet");
            System.out.println("[4] 🔍 Search Archived Pet");
            System.out.println("[5] ↩ Return to Admin Menu");
            
            System.out.println("\n👉 Choose an Option [1-5]: ");
            
            int choice;
            
            if (!input.hasNextInt()) {
                System.out.println("❌ Invalid input!");
                input.nextLine();
                continue;
            }
            
            choice = input.nextInt();
            
            input.nextLine();
            
            switch (choice) {
                
                case 1:
                    archivePet();
                    break;
                
                case 2:
                    ViewArchivedPets.viewArchivedPets();
                    break;
                    
                case 3:
                    RestoreArchivedPets.restorePet();
                    break;
                    
                case 4:
                    SearchArchivedPet.searchArchivedPet();
                    break;
                    
                case 5:
                    System.out.println("\n↩ Returning to Admin Menu...");
                    break;
                    
                default:
                    System.out.println("❌ Invalid option!");
            }
        }
    }

    //===================
    //ARCHIVED PETS
    //===================
    public static void archivePet() {

        try {

            System.out.print("Enter Pet ID: ");
            int petId = input.nextInt();

            input.nextLine();

            Connection con = DbConnection.getConnection();

            String sql
                    = "UPDATE pets"
                    + "SET archived = 1 "
                    + "WHERE pet_id = ?;";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, petId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Pet archived.");
            } else {
                System.out.println("Archive failed.");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
