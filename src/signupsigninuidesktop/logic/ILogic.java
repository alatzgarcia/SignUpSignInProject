/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.logic;

import signupsigninuidesktop.exceptions.EmailExistsException;
import signupsigninuidesktop.exceptions.IncorrectLoginException;
import signupsigninuidesktop.exceptions.IncorrectPasswordException;
import signupsigninuidesktop.exceptions.LoginExistsException;
import signupsigninuidesktop.model.User;

/**
 * Interface for Logic.
 * 
 * @author Diego
 */
public interface ILogic {
    /**
     * This method sends all the user's data and it signs up, if there are no exceptions
     * @param user User: The user's data
     * @return User
     * @throws IncorrectLoginException Exception that throws when the username 
     *  doesn't exist for the login
     * @throws IncorrectPasswordException Exception that throws when the entered
     *  password doesn't match with the user's password
     */
    public User login(User user) throws IncorrectLoginException, IncorrectPasswordException;
    
    /**
     * This method sends the user's username and password and it signs in if
     * there are no exceptions
     * @param user User: The user's data
     * @return User
     * @throws LoginExistsException If the username already exists its thrown
     * @throws EmailExistsException If the email already exists its thrown
     */
    public User register(User user) throws LoginExistsException, EmailExistsException;
    
    /**
     * It makes sure that the user's logs out
     * @throws Exception general exception
     */
    public void close() throws Exception; 
}
