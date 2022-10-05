package com.revature.service;

import com.revature.exception.ReimbursementNotFoundException;
import com.revature.repository.ReimburseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReimbursementService {

    @Mock
    private ReimburseRepository reimburseRepository;

    @InjectMocks
    private ReimburseService reimServ;

    @Test
    public void testReimbursementDoesNotExist() throws SQLException{
        when(reimburseRepository.getReimbursementById(eq(20))).thenReturn(null);

        Assertions.assertThrows(ReimbursementNotFoundException.class, () ->{
            reimServ.updateStatus(20,"approved",2);
        });
    }



}
