package com.revature.service;

import com.revature.exception.*;
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

    public static void addReimbursement(Reimbursements reimbursement) throws SQLException, IllegalArgumentException, EmployeeIdNotFountException, DescriptionMissingException {

        if (reimbursement.getAmount() <= 0){
            throw new IllegalArgumentException("Please enter an amount greater than 0.");
        }
        if(reimbursement.getDescrip() == null){
            throw new DescriptionMissingException("Please enter a description of the reimbursement");
        }
        if (reimbursement.getEmployeeId() == 0) {
            throw new EmployeeIdNotFountException(("You have not provided an employee id/You provided a null employee id"));
        }
            ReimburseRepository.addReimbursement(reimbursement);

        }
    public boolean updateStatus(int reimbursementId, String status, int managerId) throws SQLException, ReimbursementNotFoundException, ReimbursementAlreadyDealtException, WrongUpdateWordException {
        //Check if grade is negative

        //check if reimbursement does not exist
        Reimbursements reimbursements = ReimburseRepository.getReimbursementById(reimbursementId);
        if ( reimbursements== null){
            throw new ReimbursementNotFoundException("Reimbursement with id " + reimbursementId + " was not found.");
        }

        //Already approved/denied
        if(!reimbursements.getStatus().equals("pending") ){
            throw new ReimbursementAlreadyDealtException("Reimbursement with id " + reimbursementId + " has already been " +reimbursements.getStatus());
        }
        if(!"approved".equals(status) &&  !"denied".equals(status) ){
            throw new WrongUpdateWordException("You can only approve or deny reimbursements. Use key words 'approved' or 'denied'.");
        }

        return ReimburseRepository.updateStatus(reimbursementId, status, managerId);
    }

}
