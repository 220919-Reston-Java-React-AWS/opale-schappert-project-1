package com.revature.repository;

import com.revature.model.Employee;
import com.revature.model.Reimbursements;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimburseRepository {

    //GET REIMBURSEMENTS

    public List<Reimbursements> getAllReimbursements() throws SQLException{
        try(Connection connectionObj = ConnectionsFactory.createConnection()){
            List<Reimbursements> reimbursements = new ArrayList<>();
            String sql = "SELECT * FROM reimbursements ";

            Statement stmt = connectionObj.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt ("id");
                double amount = rs.getDouble("amount");
                String type = rs.getString("reimbursement_type");
                int employeeId = rs.getInt("employee_id");
                int managerId = rs.getInt("manager_id");
                String status = rs.getString("status");

                Reimbursements reimbursement = new Reimbursements(id, amount, type, employeeId, managerId, status);
                reimbursements.add(reimbursement);

            }
            return reimbursements;
        }

    }

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
                String type = rs.getString("reimbursement_type");
                int employeeid = rs.getInt("employee_id");
                int managerid = rs.getInt("manager_id");
                String status = rs.getString("status");

                Reimbursements reimbursement = new Reimbursements(id, amount, type, employeeid, managerid, status);
                reimbursements.add(reimbursement);

            }
            return reimbursements;
        }

    }


    //ADD NEW REIMBURSEMENT
    public static Reimbursements addReimbursement(Reimbursements reimbursements) throws SQLException{

        try(Connection connectionObj = ConnectionsFactory.createConnection()){

            String sql = "INSERT into reimbursements (amount, reimbursement_type, employee_id ) values (?,?,?)";

            PreparedStatement pstmt = connectionObj.prepareStatement(sql);

            pstmt.setDouble(1,reimbursements.getAmount());
            pstmt.setString(2,reimbursements.getDescrip());
            pstmt.setInt(3,reimbursements.getEmployeeId()); //This currently must be manually entered.


            int numberOfRecordsAdded = pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                int id = rs.getInt(1);

                return new Reimbursements(id, reimbursements.getAmount(), reimbursements.getDescrip(), reimbursements.getEmployeeId(), 0, "pending" );
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

    public static Reimbursements getReimbursementById(int id) throws SQLException{
        try(Connection connectionObj = ConnectionsFactory.createConnection()){
            String sql = "SELECT * FROM reimbursements WHERE id = ?";
            PreparedStatement pstmt = connectionObj.prepareStatement(sql);

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                int reimbursementId = rs.getInt("id");
                double amount = rs.getDouble("amount");
                String descrip = rs.getString("reimbursement_type");
                int employeeId = rs.getInt("student_id");
                int managerId = rs.getInt("grader_id");
                String status = rs.getString("status");

                return new Reimbursements(reimbursementId, amount, descrip, employeeId, managerId, status);
            }else{
                return null;
            }

        }
    }
}
