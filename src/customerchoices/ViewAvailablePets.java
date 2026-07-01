package customerchoices;

import database.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewAvailablePets {

    public static void viewAvailablePets() {
        
        try {
            
            System.out.println("\n===== AVAILABLE PETS =====");
            
            Connection con = DbConnection.getConnection();
            
            String sql = "SELECT * "
                    + "FROM pets "
                    + "WHERE adoption_status = 'Available'";
            
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()) {
                
                do {
                    
                    System.out.println("Pet ID: " + rs.getInt("pet_id"));
                    
                    System.out.println("Pet Name: " + rs.getString("pet_name"));
                    
                    System.out.println("Gender: " + rs.getString("gender"));
                    
                    System.out.println("Age: " + rs.getInt("age"));
                    
                    System.out.println("Species: " + rs.getString("species"));
                    
                    System.out.println("Breed:" + rs.getString("breed"));
                    
                    System.out.println("Description: " + rs.getString("description"));
                    
                    System.out.println("Adoption_Status: " + rs.getString("adoption_status"));
                    
                } while (rs.next());
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
