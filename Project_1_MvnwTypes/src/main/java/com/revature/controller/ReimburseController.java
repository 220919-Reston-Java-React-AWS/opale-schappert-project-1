package com.revature.controller;

import com.revature.exception.*;
import com.revature.model.Employee;
import com.revature.model.Reimbursements;
import com.revature.service.ReimburseService;
import io.javalin.Javalin;

import javax.servlet.http.HttpSession;
import java.util.List;

public class ReimburseController {

    private ReimburseService reimburseService = new ReimburseService();

    public void mapEndpoint(Javalin app){

        app.get("/reimbursements", (ctx) ->{
            HttpSession httpSession = ctx.req.getSession();

            Employee employee = (Employee) httpSession.getAttribute("employee");

            if(employee != null){
                if(employee.getRoleId() == 2){
                    List<Reimbursements> reimbursements = reimburseService.getAllReimbursements();

                    //Print out list
                    ctx.json(reimbursements);

                }else if (employee.getRoleId() == 1){
                    int employeeId = employee.getId();
                    List<Reimbursements> reimbursements = reimburseService.getAllReimbursementsforEmployee(employeeId);
                    //Print out list
                    if(reimbursements.isEmpty()){
                        ctx.result("There are currently no reimbursements.");
                    }else {
                        //Print out list
                        ctx.json(reimbursements);
                    }

                }else{
                    ctx.result("You are not logged in as an employee or manager");
                }
            }else{
                ctx.result("You are not logged in");
                ctx.status(401);
            }
        });

        app.get("/reimbursements/{status}", (ctx) ->{
            HttpSession httpSession = ctx.req.getSession();

            Employee employee = (Employee) httpSession.getAttribute("employee");
            try {

                if (employee != null) {
                    if (employee.getRoleId() == 2) {
                        String reimbursementStatus = (ctx.pathParam("status"));
                        List<Reimbursements> reimbursements = reimburseService.getAllReimbursementsByStatus(reimbursementStatus);

                        if(reimbursements.isEmpty()){
                            ctx.result("There are currently no " + reimbursementStatus +" reimbursements.");
                        }else {
                            //Print out list
                            ctx.json(reimbursements);
                        }

                    } else {
                        ctx.result("You are not logged in as a manager");
                    }
                } else {
                    ctx.result("You are not logged in");
                    ctx.status(401);
                }
            }catch (ReimbursementNotFoundException e){
                ctx.status(400);
                ctx.result(e.getMessage());
            }
        });

        app.get("/reimbursements/types/{type}", (ctx) ->{
            HttpSession httpSession = ctx.req.getSession();

            Employee employee = (Employee) httpSession.getAttribute("employee");
            try {

                if (employee != null) {
                    if (employee.getRoleId() == 2) {
                        String reimburseType = (ctx.pathParam("type"));
                        List<Reimbursements> reimbursements = reimburseService.getAllReimbursementsByType(reimburseType);

                        if(reimbursements.isEmpty()){
                            ctx.result("There are currently no " + reimburseType + " reimbursements" );
                        }else {
                            //Print out list
                            ctx.json(reimbursements);
                        }

                    } else if (employee.getRoleId() == 1) {
                        int employeeId = employee.getId();
                        String reimburseType = (ctx.pathParam("type"));
                        List<Reimbursements> reimbursements = reimburseService.getAllReimbursementsforEmployeeByType(reimburseType, employeeId);
                        if(reimbursements.isEmpty()){
                            ctx.result("There are currently no " + reimburseType + " reimbursements" );
                        }else {
                            //Print out list
                            ctx.json(reimbursements);
                        }
                    }
                } else {
                    ctx.result("You are not logged in");
                    ctx.status(401);
                }
            }catch (ReimbursementNotFoundException e){
                ctx.status(400);
                ctx.result(e.getMessage());
            }
        });

        app.post("/addReimbursement", (ctx) -> {
            HttpSession httpSession = ctx.req.getSession();
            Employee employee = (Employee) httpSession.getAttribute("employee");
            Reimbursements reimbursements = ctx.bodyAsClass(Reimbursements.class);
            if(employee != null){

                int employeeId = employee.getId();
                reimbursements.setEmployeeId(employeeId);
                try {
                    ReimburseService.addReimbursement(reimbursements);
                    ctx.result("Reimbursement request made. A manager will get to your request shortly.");
                } catch (IllegalArgumentException e) {
                    ctx.status(402);
                    ctx.result(e.getMessage());
                }catch (EmployeeIdNotFountException | DescriptionMissingException | TypeMissingException |WrongUpdateWordException e){
                    ctx.status(400);
                    ctx.result(e.getMessage());
                }
            }else{
                ctx.result("You are not logged in");
                ctx.status(401);
            }
            });

        //Update the status
        app.patch("/reimbursement/{reimbursementId}", (ctx) ->{
            HttpSession httpSession = ctx.req.getSession();
            Employee employee = (Employee) httpSession.getAttribute("employee");

            if (employee!= null){

                if(employee.getRoleId() == 2){
                    int managerId = employee.getId();
                    int reimbursementId = Integer.parseInt(ctx.pathParam("reimbursementId"));

                    Reimbursements r = ctx.bodyAsClass(Reimbursements.class);
                    String status = r.getStatus();

                    try{
                        reimburseService.updateStatus(reimbursementId, status, managerId);
                        ctx.result("Status updated");
                    }catch(ReimbursementAlreadyDealtException | IllegalArgumentException | WrongUpdateWordException e){
                        ctx.result(e.getMessage());
                        ctx.status(400);
                    }catch (ReimbursementNotFoundException e){
                        ctx.result(e.getMessage());
                        ctx.status(404);
                    }
                }else{
                    ctx.result("You do not have the authorization to update the status.");
                    ctx.status(401);
                }
            }else{
                ctx.result("You are not logged in");
                ctx.status(401);

            }

        });


    }


}
