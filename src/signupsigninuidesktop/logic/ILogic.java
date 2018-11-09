/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.logic;

import signupsignin.User;
import signupsigninuidesktop.exceptions.EmailExistsException;
import signupsigninuidesktop.exceptions.IncorrectLoginException;
import signupsigninuidesktop.exceptions.IncorrectPasswordException;
import signupsigninuidesktop.exceptions.LoginEmailExistException;
import signupsigninuidesktop.exceptions.LoginExistsException;


/**
 * Interface for Logic.
 * 
 * @author Diego
 */
public interface ILogic {
    /**
     * Prototype for the function which the UI will be need to be checked 
     * @param user User: This will be the class that carries all the user's data 
     */
       //This method sends all the data by the User object and it will be registered
    public User register(User user)throws LoginExistsException, EmailExistsException,LoginEmailExistException;;
    
    //This methods checks if the user and password are correct
    public User login(User user)throws IncorrectLoginException, IncorrectPasswordException;;
    
    //This method validates if the user exists
    //public boolean validateLogin(String login);
    
    //This method validates if the email exists
    //public boolean validateEmail(String email);
    
    public void close() throws Exception; 
}
