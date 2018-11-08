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
 *
 * @author Alatz
 */
public interface ILogic {
    public User login(User user) throws IncorrectLoginException, IncorrectPasswordException;
    public User register(User user) throws LoginExistsException, EmailExistsException;
    public void close() throws Exception; //--TOFIX ????
}
