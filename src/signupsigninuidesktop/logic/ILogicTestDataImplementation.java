/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.logic;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import signupsigninuidesktop.exceptions.EmailExistsException;
import signupsigninuidesktop.exceptions.IncorrectLoginException;
import signupsigninuidesktop.exceptions.IncorrectPasswordException;
import signupsigninuidesktop.exceptions.LoginExistsException;
import signupsigninuidesktop.model.User;

/**
 *
 * @author Alatz
 */
public class ILogicTestDataImplementation implements ILogic{

    private ArrayList<User>users = new ArrayList<User>();
    
    public ILogicTestDataImplementation(){
        for(int i=0; i<10; i++){
            User user = new User();
            user.setLogin("loginName"+i);
            user.setPassword("password"+i);
            user.setEmail("email"+i);
            user.setFullName("fullName"+i);
            users.add(user);
        }
    }
    
    @Override
    public User login(User user) throws IncorrectLoginException, IncorrectPasswordException{
        if(users.stream().filter(u -> u.getLogin().equalsIgnoreCase(user.getLogin()) 
                && u.getPassword().equals(user.getPassword())).count() == 1){
            return user;
        } else {
            if(users.stream().filter(u -> u.getLogin().equalsIgnoreCase(user.getLogin())).count() != 0){
                throw new IncorrectPasswordException();
            } else{
                throw new IncorrectLoginException();
            }
        }
            
    }

    @Override
    public User register(User user) throws LoginExistsException, EmailExistsException {
        if(users.stream().filter(u -> u.getLogin().equalsIgnoreCase(user.getLogin()) 
                || u.getEmail().equalsIgnoreCase(user.getEmail())).count() == 0){
            users.add(user);
            return user;
        } else{
            if(users.stream().filter(u -> u.getLogin().equalsIgnoreCase(user.getLogin())).count() != 0){
                throw new EmailExistsException();
            } else{
                throw new LoginExistsException();
            }
        }
    }

    @Override
    public void close() throws Exception {
        //Does nothing
    }
    
}
