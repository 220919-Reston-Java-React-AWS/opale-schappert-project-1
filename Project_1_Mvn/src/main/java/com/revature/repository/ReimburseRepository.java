package com.revature.repository;

import com.revature.model.Employee;
import com.revature.model.Reimbursements;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimburseRepository {

    //GET REIMBURSEMENTS

    //Get every reimbursement currently in the database regardless of status
    public List<Reimbursements> getAllReimbursements() throws SQLException{
        try(Connection connectionObj = ConnectionsFactory.createConnection()){
            List<Reimbursements> reimbursements = new ArrayList<>();
            String sql = "SELECT * FROM reimbursements ";

            Statement stmt = connectionObj.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt ("id");
                float amount = rs.getFloat("amount");
                String descrip = rs.getString("reimbursement_descrip");
                int employeeId = rs.getInt("employee_id");
                int managerId = rs.getInt("manager_id");
                String status = rs.getString("status");

                Reimbursements reimbursement = new Reimbursements(id, amount, descrip, employeeId, managerId, status);
                reimbursements.add(reimbursement);

            }
            return reimbursements;
        }

    }
    //Get every reimbursement currently in the database associated with a particular employee regardless of status
    public List<Reimbursements> getAllReimbursementsforEmployee(int employeeId) throws SQLException{
        try(Connection connectionObj = ConnectionsFactory.createConnection()){
            List<Reimbursements> reimbursements = new ArrayList<>();
            String sql = "SELECT * FROM reimbursements WHERE employee_id = ?";

            PreparedStatement pstmt = connectionObj.prepareStatement(sql);

            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt ("id");
                float amount = rs.getFloat("amount");
                String descrip = rs.getString("reimbursement_descrip");
                int employeeid = rs.getInt("employee_id");
                int managerid = rs.getInt("manager_id");
                String status = rs.getString("status");

                Reimbursements reimbursement = new Reimbursements(id, amount, descrip, employeeid, managerid, status);
                reimbursements.add(reimbursement);

            }
            return reimbursements;
        }

    }


    //ADD NEW REIMBURSEMENT
    public static Reimbursements addReimbursement(Reimbursements reimbursements) throws SQLException{

        try(Connection connectionObj = ConnectionsFactory.createConnection()){

            String sql = "INSERT into reimbursements (amount, reimbursement_descrip,employee_id ) values (?,?,?)";


            PreparedStatement pstmt = connectionObj.prepareStatement(sql);

            pstmt.setFloat(1,reimbursements.getAmount());
            pstmt.setString(2,reimbursements.getdescription());
            pstmt.setInt(3,reimbursements.getEmployeeId()); //This currently must be manually entered.


            int numberOfRecordsAdded = pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                int id = rs.getInt(1);

                return new Reimbursements(id, reimbursements.getAmount(), reimbursements.getdescription(), reimbursements.getEmployeeId(), 0, "pending" );
            }else{
                return null;
            }


        }
    }

    //Update Reimbursement status
    public static boolean  updateStatus(int reimbursementId, String status, int managerId) throws SQLException {
        try (Connection connectionObj = ConnectionsFactory.createConnection()) {

            String sql = "UPDATE reimbursements SET status = ?, manager_id = ? WHERE id = ?";
            PreparedStatement pstmt = connectionObj.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, managerId);
            pstmt.setInt(3, reimbursementId);

            int numberOfRecordsUpdated = pstmt.executeUpdate();

            return numberOfRecordsUpdated == 1;


        }
    }
        //Get  only the reimbursement whose id is specified
    public static Reimbursements getReimbursementById(int id) throws SQLException{
        try(Connection connectionObj = ConnectionsFactory.createConnection()){
            String sql = "SELECT * FROM reimbursements WHERE id = ?";
            PreparedStatement pstmt = connectionObj.prepareStatement(sql);

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                int reimbursementId = rs.getInt("id");
                float amount = rs.getFloat("amount");
                String descrip = rs.getString("reimbursement_descrip");
                int employeeId = rs.getInt("employee_id");
                int managerId = rs.getInt("manager_id");
                String status = rs.getString("status");



                return new Reimbursements(reimbursementId, amount, descrip, employeeId, managerId, status);
            }else{
                return null;
            }

        }
    }

    //Get reimbursements by status to allow for managers to pull only pending reimbursements
    public static List<Reimbursements> getReimbursementByStatus(String status) throws SQLException{
        try(Connection connectionObj = ConnectionsFactory.createConnection()){
            List<Reimbursements> reimbursements = new ArrayList<>();
            String sql = "SELECT * FROM reimbursements WHERE status = ?";

            PreparedStatement pstmt = connectionObj.prepareStatement(sql);

            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt ("id");
                float amount = rs.getFloat("amount");
                String descrip = rs.getString("reimbursement_descrip");
                int employeeid = rs.getInt("employee_id");
                int managerid = rs.getInt("manager_id");
                String reimburse_status = rs.getString("status");

                Reimbursements reimbursement = new Reimbursements(id, amount, descrip, employeeid, managerid, reimburse_status);
                reimbursements.add(reimbursement);

            }
            return reimbursements;
        }

    }
}
