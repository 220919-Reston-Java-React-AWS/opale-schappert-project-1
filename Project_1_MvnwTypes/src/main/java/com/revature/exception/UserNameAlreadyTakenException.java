package com.revature.exception;

public class UserNameAlreadyTakenException extends Exception{

    public UserNameAlreadyTakenException(String message){
        super(message);
    }
}
