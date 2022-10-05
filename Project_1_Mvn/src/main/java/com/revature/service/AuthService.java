package com.revature.service;

import com.revature.exception.*;
import com.revature.model.Employee;
import com.revature.repository.UserRepo;
import org.eclipse.jetty.server.Authentication;
import org.postgresql.util.PSQLException;

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

    public void register(Employee employee) throws SQLException, UserNameAlreadyTakenException, MissingRegistrationInfoException, PSQLException {

        if (UserRepo.getEmployeeByUsername(employee.getUserName()) != null){
            throw new UserNameAlreadyTakenException("This username is already in use. Please select another");
        }
        if(employee.getUserName() == null || employee.getPassWord()== null || employee.getFirstName() == null || employee.getLastName() ==null){
            throw new MissingRegistrationInfoException("Please make sure you have included a username, password, as well as your first and last name.");
        }

        userRepo.addEmployee(employee);
    }
    }







