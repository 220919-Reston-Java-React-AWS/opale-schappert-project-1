package com.revature.repository;

import com.revature.exception.UserNameAlreadyTakenException;
import com.revature.model.Employee;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {

    //Support registration
    public Employee addEmployee(Employee employee) throws SQLException {

        try(Connection connectionObj = ConnectionsFactory.createConnection()){

            String sql = "INSERT into users (user_name, pass_word, first_name, last_name, role_id) values (?,?,?,?,?)";

            PreparedStatement pstmt = connectionObj.prepareStatement(sql);

            pstmt.setString(1,employee.getUserName());
            pstmt.setString(2,employee.getPassWord());
            pstmt.setString(3,employee.getFirstName());
            pstmt.setString(4,employee.getLastName());
            pstmt.setInt(5,1);

            int numberOfRecordsAdded = pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                int id = rs.getInt(1);

                return new Employee(id, employee.getUserName(), employee.getPassWord(), employee.getFirstName(), employee.getLastName(),1 );
            }else{
                return null;
            }


        }
    }

    //Login Support
    public static Employee getEmployeeByUsernameAndPassword(String username, String password) throws SQLException {
        try (Connection connectionObj = ConnectionsFactory.createConnection()){

            String sql = "SELECT * FROM users  WHERE user_name = ? AND pass_word = ?";
            PreparedStatement pstmt = connectionObj.prepareStatement(sql);

            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                int id = rs.getInt("id");
                String un = rs.getString("user_name");
                String pw = rs.getString("pass_word");
                String fN = rs.getString("first_name");
                String lN = rs.getString("last_name");
                int roleId = rs.getInt("role_id");

                return new Employee(id, un, pw, fN, lN, roleId);

            }else{
                return null;
            }
        }
    }

    public static Employee getEmployeeByUsername(String username) throws SQLException {
        try (Connection connectionObj = ConnectionsFactory.createConnection()){

            String sql = "SELECT * FROM users  WHERE user_name = ?";
            PreparedStatement pstmt = connectionObj.prepareStatement(sql);

            pstmt.setString(1,username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                int id = rs.getInt("id");
                String un = rs.getString("user_name");
                String pw = rs.getString("pass_word");
                String fN = rs.getString("first_name");
                String lN = rs.getString("last_name");
                int roleId = rs.getInt("role_id");

                return new Employee(id, un, pw, fN, lN, roleId);

            }else{
                return null;
            }
        }
    }


}
