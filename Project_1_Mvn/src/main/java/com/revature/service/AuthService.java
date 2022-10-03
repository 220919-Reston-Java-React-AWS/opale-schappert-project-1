package com.revature.service;

import com.revature.exception.InvalidLoginException;
import com.revature.exception.UserNameAlreadyTakenException;
import com.revature.model.Employee;
import com.revature.repository.UserRepo;
import org.eclipse.jetty.server.Authentication;

import java.sql.SQLException;

public class AuthService {

    private UserRepo userRepo = new UserRepo();

    public Employee login(String username, String password) throws SQLException, InvalidLoginException {
        Employee employee = userRepo.getEmployeeByUsernameAndPassword(username, password);

        if (employee == null){
            throw new InvalidLoginException("Invalid username and/or password");
        }
        return employee;
    }

    public void register(Employee employee) throws SQLException, UserNameAlreadyTakenException {

        if (UserRepo.getEmployeeByUsernameAndPassword(employee.getUserName(), employee.getPassWord()) != null){
            throw new UserNameAlreadyTakenException("This username is already in use. Please select another");
        }
        userRepo.addEmployee(employee);
    }
    }







