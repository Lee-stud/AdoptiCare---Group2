package adminchoices.update;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchArchivedPet {

    public static void searchArchivedPet() {

        Scanner input = new Scanner(System.in);

        try {

            Connection con = DbConnection.getConnection();

            while (true) {
                System.out.println("\n===== 🔍 SEARCH ARCHIVED PET =====");

                System.out.print("🆔 Enter Pet ID (0 to return): ");

                if (!input.hasNextInt()) {
                    System.out.println("\n❌ Invalid input: Please enter a number.\n");
                    input.nextLine();
                    continue;
                }

                int petId = input.nextInt();
                input.nextLine();

                if (petId == 0) {
                    System.out.println("\n↩ Returning to Archived Menu...");
                    return;
                }

                String sql
                        = "SELECT * "
                        + "FROM pets "
                        + "WHERE pet_id = ? AND archived = 1";

                PreparedStatement pst = con.prepareStatement(sql);

                pst.setInt(1, petId);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    System.out.println("\n🗄 Archived Pet Details");
                    System.out.println("--------------------------------------------------------------------------------");

                    System.out.printf("%-5s %-15s %-8s %-5s %-12s %-15s %-25s %-15s%n",
                            "ID", "Name", "Gender", "Age", "Species", "Breed", "Description", "Status");

                    System.out.println("--------------------------------------------------------------------------------");

                    System.out.printf("%-5d %-15s %-8s %-5d %-12s %-15s %-25s %-15s%n",
                            rs.getInt("pet_id"),
                            rs.getString("pet_name"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getString("species"),
                            rs.getString("breed"),
                            rs.getString("description"),
                            rs.getString("adoption_status"));

                    System.out.println("--------------------------------------------------------------------------------");

                    break;

                } else {
                    System.out.println("\n❌ Archived pet not found. Try again.");
                }
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }

    }
}
