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
public class RegisterFailedException {
    private static final String MESSAGE = "Error. El registro de usuario"
            + "no se ha podido completar con éxito. Vuelva a intentarlo.";
    
    public String getMessage(){
        return MESSAGE;
    }
}