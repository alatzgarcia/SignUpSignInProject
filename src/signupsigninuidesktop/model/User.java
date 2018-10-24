/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.model;

import java.util.Date;

/**
 *
 * @author Alatz
 */
public class User {
    private int id;
    private String login;
    private String email;
    private String fullName;
    private String status;
    private String privilege;
    private String password;
    private Date lastAccess; //--TOFIX -- Change type
    private Date lastPasswordChange; //--TOFIX -- Change type
    
    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public User(String login, String email, String fullName, String password){
        this.login = login;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }
    
    //--TOFIX -- Delete when no longer needed
    public User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
