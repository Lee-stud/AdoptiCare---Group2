package vetchoices.read;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchPet {

    //================
    //SEARCH PET MENU
    //================
    public static void searchPetMenu() {

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== 🔍 SEARCH PET =====");
            System.out.println("\n👉 Which would you like to search?");
            System.out.println("[1] 🐾 Search by Species");
            System.out.println("[2] 🏷 Search by Breed");
            System.out.println("[3] 🎂 Search by Age");
            System.out.println("[4] ↩ Back");
            System.out.print("\n👉 Choose an option [1-4]: ");

            if (!input.hasNextInt()) {
                System.out.println("❌ Invalid input!");
                input.nextLine();
                continue;
            }

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    searchBySpecies();
                    break;

                case 2:
                    searchByBreed();
                    break;

                case 3:
                    searchByAge();
                    break;

                case 4:
                    System.out.println("/n↩ Going back...");
                    return;

                default:
                    System.out.println("\n❌ Invalid option.");
            }
        }
    }

    //==================
    //SEARCH BY SPECIES
    //==================
    public static void searchBySpecies() {

        try {

            System.out.println("\n===== 🔍 SEARCH PET BY BREED 🐾 =====");

            Scanner input = new Scanner(System.in);

            System.out.print("\n🐾 Species: ");
            String species = input.nextLine();

            Connection con = DbConnection.getConnection();

            String sql
                    = "SELECT * FROM pets WHERE species LIKE ? "
                    + "AND archived = 0";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, "%" + species + "%");

            ResultSet rs = pst.executeQuery();

            System.out.printf(
                    "%-8s %-15s %-12s %-5s %-8s %-15s %-25s%n",
                    "Pet ID",
                    "Pet Name",
                    "Species",
                    "Age",
                    "Gender",
                    "Breed",
                    "Description"
            );

            System.out.println("--------------------------------------------------------------------------------");

            if (rs.next()) {

                do {

                    System.out.printf(
                            "%-8d %-15s %-12s %-5d %-8s %-15s %-25s%n",
                            rs.getInt("pet_id"),
                            rs.getString("pet_name"),
                            rs.getString("species"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("breed"),
                            rs.getString("description")
                    );

                    System.out.println("--------------------------------------------------------------------------------");

                } while (rs.next());
                
            } else {
                System.out.println("\n❌ Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //=================
    //SEARCH BY BREED
    //=================
    public static void searchByBreed() {

        try {

            System.out.println("\n===== 🔍 SEARCH PET BY BREED 🏷 =====");

            Scanner input = new Scanner(System.in);

            System.out.print("\n🏷 Breed: ");
            String breed = input.nextLine();

            Connection con = DbConnection.getConnection();

            String sql
                    = "SELECT * FROM pets WHERE breed LIKE ? "
                    + "AND archived = 0";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, "%" + breed + "%");

            ResultSet rs = pst.executeQuery();

            System.out.printf(
                    "%-8s %-15s %-12s %-5s %-8s %-15s %-25s%n",
                    "Pet ID",
                    "Pet Name",
                    "Species",
                    "Age",
                    "Gender",
                    "Breed",
                    "Description"
            );

            System.out.println("--------------------------------------------------------------------------------");

            if (rs.next()) {

                do {

                    System.out.printf(
                            "%-8d %-15s %-12s %-5d %-8s %-15s %-25s%n",
                            rs.getInt("pet_id"),
                            rs.getString("pet_name"),
                            rs.getString("species"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("breed"),
                            rs.getString("description")
                    );

                    System.out.println("--------------------------------------------------------------------------------");

                } while (rs.next());

            } else {
                System.out.println("\n❌ Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //===============
    //SEARCH BY AGE
    //===============
    public static void searchByAge() {

        try {

            System.out.println("\n===== 🔍 SEARCH PET BY BREED 🎂 =====");

            Scanner input = new Scanner(System.in);

            System.out.print("\n🎂 Breed: ");
            String age = input.nextLine();

            Connection con = DbConnection.getConnection();

            String sqlAge
                    = "SELECT * FROM pets WHERE age LIKE ? "
                    + "AND archived = 0";

            PreparedStatement pst = con.prepareStatement(sqlAge);

            pst.setString(1, "%" + age + "%");

            ResultSet rs = pst.executeQuery();

            System.out.printf(
                    "%-8s %-15s %-12s %-5s %-8s %-15s %-25s%n",
                    "Pet ID",
                    "Pet Name",
                    "Species",
                    "Age",
                    "Gender",
                    "Breed",
                    "Description"
            );

            System.out.println("--------------------------------------------------------------------------------");

            if (rs.next()) {

                do {

                    System.out.printf(
                            "%-8d %-15s %-12s %-5d %-8s %-15s %-25s%n",
                            rs.getInt("pet_id"),
                            rs.getString("pet_name"),
                            rs.getString("species"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("breed"),
                            rs.getString("description")
                    );

                    System.out.println("--------------------------------------------------------------------------------");

                } while (rs.next());
                
            } else {
                System.out.println("\n❌ Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
