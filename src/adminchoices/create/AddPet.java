package adminchoices.create;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddPet {

    static Scanner input = new Scanner(System.in);

    public static void addPet() {

        try {
            System.out.println("\n🐾 ===== ADD NEW PET =====");

            String name, gender;
            int age;

            while (true) {

                //============
                //PET NAME
                //============
                System.out.print("🐶 Pet Name: ");
                name = input.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("\n❌ Pet name cannot be empty!\n");
                    continue;
                }

                //============
                //GENDER
                //============
                System.out.print("⚧ Gender (Male/Female): ");
                gender = input.nextLine().trim();

                if (gender.isEmpty()) {
                    System.out.println("\n❌ Gender cannot be empty!\n");
                    continue;
                }

                //===========
                //AGE
                //===========
                System.out.print("🎂 Age: ");

                if (!input.hasNextInt()) {
                    System.out.println("\n❌ Invalid age input!\n");
                    input.nextLine();
                    continue;
                }

                age = input.nextInt();
                input.nextLine();

                if (age < 0 || age > 30) {
                    System.out.println("\n❌ Invalid age range!\n");
                    continue;
                }

                break;

            }

            //=============
            //SPECIES
            //=============
            System.out.print("Species: ");
            String species = input.nextLine().trim();

            //============
            //BREED
            //============
            System.out.print("Breed: ");
            String breed = input.nextLine().trim();

            //============
            //DESCRIPTION
            //============
            System.out.print("Description: ");
            String description = input.nextLine().trim();

            Connection con = DbConnection.getConnection();

            
            //=============
            //INSERT QUERY
            //=============
            String sql
                    = "INSERT INTO pets "
                    + "(pet_name, gender, age, species, breed, description, adoption_status, date_added) "
                    + "VALUES (?, ?, ?, ?, ?, ?, 'Available', CURRENT_TIMESTAMP);";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setString(2, gender);
            pst.setInt(3, age);
            pst.setString(4, species);
            pst.setString(5, breed);
            pst.setString(6, description);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\n✅ Pet Added Successfully!");
            } else {
                System.out.println("\n❌ Failed to Add Pet.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
