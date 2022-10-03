package com.revature;

import com.revature.controller.AuthController;
import com.revature.controller.ReimburseController;
import io.javalin.Javalin;

public class Main {

    public static void main(String[] args) {

        Javalin app =Javalin.create();

        AuthController ac = new AuthController();
        ac.mapEndpoint(app);

        ReimburseController rc = new ReimburseController();
        rc.mapEndpoint(app);







        app.start(8080);

    }
    

}
