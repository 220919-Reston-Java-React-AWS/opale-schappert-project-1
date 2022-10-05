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
    public List<Reimbursements> getAllReimbursementsByStatus(String status) throws SQLException, ReimbursementNotFoundException {
        if(!status.equals("pending") && !status.equals("approved") && !status.equals("denied")){
            throw new ReimbursementNotFoundException("You have requested a status not supported by the system.");
        }

        return reimburseRepository.getReimbursementByStatus(status);
    }

    public static void addReimbursement(Reimbursements reimbursement) throws SQLException, IllegalArgumentException, EmployeeIdNotFountException, DescriptionMissingException {

        //Make sure the amount entered is not negative
        if (reimbursement.getAmount() <= 0){
            throw new IllegalArgumentException("Please enter an amount greater than 0.");
        }
        //Makes sure the employee provides a description of the reimbursement
        if(reimbursement.getdescription() == null || reimbursement.getdescription() == ""){
            throw new DescriptionMissingException("Please enter a description of the reimbursement");
        }

        //This one shouldn't get thrown but incase someone manages to input a null employee id this will catch that
        if (reimbursement.getEmployeeId() == 0) {
            throw new EmployeeIdNotFountException(("You have not provided an employee id/You provided a null employee id"));
        }
            ReimburseRepository.addReimbursement(reimbursement);

        }
    public boolean updateStatus(int reimbursementId, String status, int managerId) throws SQLException, ReimbursementNotFoundException, ReimbursementAlreadyDealtException, WrongUpdateWordException {

        //check if reimbursement does not exist
        Reimbursements reimbursements = reimburseRepository.getReimbursementById(reimbursementId);
        if ( reimbursements== null){
            throw new ReimbursementNotFoundException("Reimbursement with id " + reimbursementId + " was not found.");
        }

        //Already approved/denied
        if(!reimbursements.getStatus().equals("pending") ){
            throw new ReimbursementAlreadyDealtException("Reimbursement with id " + reimbursementId + " has already been " +reimbursements.getStatus());
        }
        //Make sure managers can't give out weird statuses
        if(!"approved".equals(status) &&  !"denied".equals(status) ){
            throw new WrongUpdateWordException("You can only approve or deny reimbursements. Use key words 'approved' or 'denied'.");
        }

        return ReimburseRepository.updateStatus(reimbursementId, status, managerId);
    }

}
