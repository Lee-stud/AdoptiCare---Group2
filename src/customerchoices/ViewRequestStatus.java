package customerchoices;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewRequestStatus {

    public static void viewRequestStatus(int userId) {

        try {

            Connection con = DbConnection.getConnection();

            String sql
                    = "SELECT ar.request_id, "
                    + "p.pet_name, "
                    + "ar.request_date, "
                    + "ar.status, "
                    + "ar.review_date,"
                    + "ar.remarks "
                    + "FROM adoption_requests ar "
                    + "LEFT JOIN pets p "
                    + "ON ar.pet_id = p.pet_id "
                    + "WHERE ar.user_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, userId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                do {
                    System.out.println("\n===== REQUEST STATUS =====");

                    System.out.println("Request ID: " + rs.getInt("request_id"));

                    System.out.println("Pet Name: " + rs.getString("pet_name"));

                    System.out.println("Request Date: " + rs.getTimestamp("request_date"));

                    System.out.println("Status: " + rs.getString("status"));

                    System.out.println("Review Date: " + rs.getTimestamp("review_date"));

                    System.out.println("Remarks: " + rs.getString("remarks"));

                    System.out.println("=========================");
                } while (rs.next());
            } else {
                System.out.println("You have no adoption requests.");  
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
