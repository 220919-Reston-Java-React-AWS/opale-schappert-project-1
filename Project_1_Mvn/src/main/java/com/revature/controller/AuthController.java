package com.revature.controller;

import com.revature.exception.InvalidLoginException;
import com.revature.exception.MissingRegistrationInfoException;
import com.revature.exception.UserNameAlreadyTakenException;
import com.revature.model.Employee;
import com.revature.service.AuthService;
import io.javalin.Javalin;
import org.postgresql.util.PSQLException;

import javax.servlet.http.HttpSession;

public class AuthController {

    private AuthService authService = new AuthService();

    public void mapEndpoint(Javalin app){
        app.post("/login", (ctx) ->{
            Employee credentials = ctx.bodyAsClass(Employee.class);
            HttpSession sessioncheck = ctx.req.getSession(false);
            try{
                if(sessioncheck == null)
                {
                    Employee employee = authService.login(credentials.getUserName(), credentials.getPassWord());

                    HttpSession session = ctx.req.getSession();
                    session.setAttribute("employee", employee);
                    ctx.result("Log in successful!");
                }else{
                    ctx.result("You or someone else is already logged in");
                    ctx.status(409);
                }
            }catch(InvalidLoginException e){
                ctx.status(404);
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
               ctx.status(401);
           }
        });

        app.post("/register", (ctx) -> {
            Employee employee = ctx.bodyAsClass(Employee.class);
            HttpSession sessioncheck = ctx.req.getSession(false);
            try{
                if(sessioncheck == null) {
                    authService.register(employee);
                    ctx.result("Successfully registered. Please login to view reimbursements");
                }else {
                    ctx.result("You cannot register a new employee while someone else is logged in.");
                    ctx.status(409);
                }
            }catch (UserNameAlreadyTakenException  e){
                ctx.status(406);
                ctx.result(e.getMessage());
            }catch (MissingRegistrationInfoException e){
                ctx.status(405);
                ctx.result(e.getMessage());
            }

        });

        app.post("/logout", (ctx) ->{
            ctx.result("Logout successful");
           ctx.req.getSession().invalidate();
        });
    }
}
