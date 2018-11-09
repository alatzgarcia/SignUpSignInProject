/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.exceptions;

/**
 * Exception class for the login existing error
 * @author Alatz
 */
public class LoginExistsException extends Exception{
    private static final String MESSAGE = "Error. El login introducido "
            + "ya existe. Por favor, introduzca otro login diferente.";
    /**
     * Method to get the message of the exception
     * @return returns the error message
     */
    @Override
    public String getMessage(){
        return MESSAGE;
    }
}
