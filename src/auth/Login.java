package auth;

import database.DbConnection;
import java.sql.*;
import java.util.Scanner;

public class Login {
    
    public static int loggedInUserId = -1;
    
    public static String login() {
        
        Scanner input = new Scanner(System.in);
        
        try {
            System.out.println("\n===== LOGIN =====");
            
            System.out.print("Username: ");
            String username = input.nextLine();
            
            System.out.print("Password: ");
            String password = input.nextLine();
            
            Connection con = DbConnection.getConnection();
            
            String sql = "SELECT user_id, role FROM users WHERE username = ? AND password = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, username);
            pst.setString(2, password);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                
                loggedInUserId = rs.getInt("user_id");
                
                return rs.getString("role");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return null;
    }
}
