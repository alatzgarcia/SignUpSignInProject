/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.exceptions;

/**
 *
 * @author Alatz
 */
public class LoginEmailExistException {
    private static final String MESSAGE = "Error. El nombre de usuario"
            + "y el email introducidos ya existen.";
    
    public String getMessage(){
        return MESSAGE;
    }
}