package com.revature.controller;

import com.revature.exception.InvalidLoginException;
import com.revature.exception.UserNameAlreadyTakenException;
import com.revature.model.Employee;
import com.revature.service.AuthService;
import io.javalin.Javalin;

import javax.servlet.http.HttpSession;

public class AuthController {

    private AuthService authService = new AuthService();

    public void mapEndpoint(Javalin app){
        app.post("/login", (ctx) ->{
            Employee credentials = ctx.bodyAsClass(Employee.class);

            try{

                Employee employee = authService.login(credentials.getUserName(), credentials.getPassWord());

                HttpSession session = ctx.req.getSession();
                session.setAttribute("employee", employee);
                ctx.result("Log in successful!");
            }catch(InvalidLoginException e){
                ctx.status(402);
                ctx.result(e.getMessage());
            }
        });

        app.get("/currentuser", (ctx) ->{
           HttpSession session = ctx.req.getSession();
           Employee employee = (Employee) session.getAttribute("employee");

           if (employee != null){
               ctx.json(employee);
           }else{
               ctx.result("User is not logged in");
               ctx.status(400);
           }
        });

        app.post("/register", (ctx) -> {
            Employee employee = ctx.bodyAsClass(Employee.class);
            try{
                authService.register(employee);
                ctx.result("Successfully registered. Please login to view reimbursements");
            }catch (UserNameAlreadyTakenException e){
                ctx.status(402);
                ctx.result(e.getMessage());
            }

        });

        app.post("/logout", (ctx) ->{
           ctx.req.getSession().invalidate();
        });
    }
}
