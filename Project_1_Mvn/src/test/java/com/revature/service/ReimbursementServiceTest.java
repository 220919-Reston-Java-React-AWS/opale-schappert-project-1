package com.revature.service;

import com.revature.exception.ReimbursementAlreadyDealtException;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.model.Reimbursements;
import com.revature.repository.ReimburseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReimbursementServiceTest {

    @Mock
    private Reimbursements reimbursements;

    @Mock
    private ReimburseRepository rr;

    @InjectMocks
    private ReimburseService reimServ;

    @Test
    public void testReimbursementDoesNotExist() throws SQLException{
        reimbursements.setId(50);
        when(rr.getReimbursementById(eq( 50))).thenReturn(eq(null));


        Assertions.assertThrows(ReimbursementNotFoundException.class, () ->{
            reimServ.updateStatus(50,"approved",2);
        });
    }

    @Test
    public void testReimbursementAmountNegative() throws SQLException{
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            reimbursements.setAmount(-500);
           reimServ.addReimbursement(reimbursements);
        });
    }



}
