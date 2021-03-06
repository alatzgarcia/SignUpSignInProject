/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.exceptions;

/**
 * Exception class for generic errors
 * @author Alatz
 */
public class GenericException extends Exception{
    private static final String MESSAGE = "Error. ";
    
    /**
     * Method to get the message of the exception
     * @return returns the error message
     */
    @Override
    public String getMessage(){
        return MESSAGE;
    }
}