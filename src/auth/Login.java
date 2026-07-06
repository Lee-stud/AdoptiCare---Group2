package auth;

import database.DbConnection;
import java.sql.*;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

public class Login {

    //=============================================
    //BEFORE LOGIN (WHEN THE USER IS NOT LOGIN YET)
    //=============================================
    public static int loggedInUserId = -1;

    public static String login() {

        Scanner input = new Scanner(System.in);

        try {
            System.out.println("\n===== LOGIN =====");

            String username, password;

            //=====================
            //CHECK USER VALIDATION
            //=====================
            while (true) {

                System.out.print("👤 Username: ");
                username = input.nextLine();

                if (username.isEmpty()) {
                    System.out.println("⚠ Username cannot be empty!");
                    continue;
                }

                if (username.contains(" ")) {
                    System.out.println("⚠ Username cannot contain spaces, use special characters(_).");
                    continue;
                }

                if (username.length() < 4) {
                    System.out.println("⚠ Username must be atleast 4 or more than letters!");
                    continue;
                }

                break;
            }

            //=========================
            //CHECK PASSWORD VALIDATION
            //=========================
            while (true) {

                System.out.print("🔒 Password: ");
                password = input.nextLine();

                if (password.isEmpty()) {
                    System.out.println("⚠ Password cannot be empty!");
                    continue;
                }

                if (password.contains(" ")) {
                    System.out.println("⚠ Password cannot contain spaces, use special characters(_).");
                    continue;
                }

                if (password.length() < 8) {
                    System.out.println("⚠ Password must be atleast 8 or more than characters!");
                    continue;
                }
                
                break;
            }
            
            Connection con = DbConnection.getConnection();

            String sql = "SELECT user_id, password, role FROM users WHERE username = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                String hashedPassword = rs.getString("password");

                //======================
                //CHECKING HASH PASSWORD
                //======================
                if (BCrypt.checkpw(password, hashedPassword)) {
                    //===================================
                    //USERS ALREADY LOGIN (WITH THEIR ID)
                    //===================================
                    loggedInUserId = rs.getInt("user_id");

                    System.out.println("\n✅ Login Successful!");

                    return rs.getString("role");
                } else {
                    System.out.println("\n❌ Incorrect Password");
                }

            } else {
                System.out.println("\n👤❌ Username not found.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        return null;
    }
}
