package br.com.ifba.eng1.domain.exception;

public class UnauthorizedActionException extends RuntimeException{

    public UnauthorizedActionException(String message){
        super(message);
    }
}
