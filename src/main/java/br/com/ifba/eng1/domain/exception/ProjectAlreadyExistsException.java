package br.com.ifba.eng1.domain.exception;

public class ProjectAlreadyExistsException extends RuntimeException{

    public ProjectAlreadyExistsException(String message){
        super(message);
    }
}
