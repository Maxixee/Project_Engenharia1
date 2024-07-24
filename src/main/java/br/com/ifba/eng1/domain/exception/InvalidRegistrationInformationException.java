package br.com.ifba.eng1.domain.exception;

public class InvalidRegistrationInformationException extends RuntimeException{

    public InvalidRegistrationInformationException(String message){
        super(message);
    }
}
