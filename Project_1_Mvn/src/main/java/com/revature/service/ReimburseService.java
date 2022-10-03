package com.revature.service;

import com.revature.exception.EmployeeIdNotFountException;
import com.revature.exception.ReimbursementAlreadyDealtException;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.exception.UserNameAlreadyTakenException;
import com.revature.model.Employee;
import com.revature.model.Reimbursements;
import com.revature.repository.ReimburseRepository;
import com.revature.repository.UserRepo;

import java.sql.SQLException;
import java.util.List;

public class ReimburseService {

    private ReimburseRepository reimburseRepository = new ReimburseRepository();

    public List<Reimbursements> getAllReimbursements() throws SQLException{

        return reimburseRepository.getAllReimbursements();
    }

    public List<Reimbursements> getAllReimbursementsforEmployee(int employeeId) throws SQLException{

        return reimburseRepository.getAllReimbursementsforEmployee(employeeId);
    }

    public static void addReimbursement(Reimbursements reimbursement) throws SQLException, IllegalArgumentException, EmployeeIdNotFountException {

        if (reimbursement.getAmount() <= 0){
            throw new IllegalArgumentException("Amount entered must be greater than 0.");
        }
//        if (reimbursement.getEmployeeId()== 0) ;{
//            throw new EmployeeIdNotFountException(("There is no employee with that Id"));
//        }
            ReimburseRepository.addReimbursement(reimbursement);

        }
    public boolean updateSTatus(int reimbursementId, String status, int managerId) throws SQLException, ReimbursementNotFoundException, ReimbursementAlreadyDealtException {
        //Check if grade is negative

        //check if assignment does not exist
        Reimbursements reimbursements = ReimburseRepository.getReimbursementById(reimbursementId);
        if ( reimbursements== null){
            throw new ReimbursementNotFoundException("Reimbursement with id" + reimbursementId + " was not found.");
        }

        //Already graded
        if(!reimbursements.getStatus().equals("pending") ){
            throw new ReimbursementAlreadyDealtException("Assignment with id " + reimbursementId + " has already been graded");
        }

        return ReimburseRepository.updateStatus(reimbursementId, status, managerId);
    }

}
