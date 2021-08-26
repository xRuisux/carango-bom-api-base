package br.com.caelum.carangobom.erro;

public class InternalServerErrorException extends Exception{
    public InternalServerErrorException(String message) {
        super(message);
    }
}
