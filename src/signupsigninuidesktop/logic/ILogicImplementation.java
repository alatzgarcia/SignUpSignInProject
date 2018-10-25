/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import signupsigninuidesktop.exceptions.IncorrectLoginException;
import signupsigninuidesktop.exceptions.IncorrectPasswordException;
import signupsigninuidesktop.model.Message;
import signupsigninuidesktop.model.User;

/**
 *
 * @author Alatz
 */
public class ILogicImplementation implements ILogic{
    private static final Logger LOGGER = 
            Logger.getLogger("signupsigninuidesktop.logic.ILogicImplementation");
    
    private final String IP = "127.0.0.1";
    private final int PORT = 5001; //--TOFIX

    private Socket client;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    
    @Override
    public User login(User user) {
        try{
            if(client == null){
                start();
                /*try {
                    client = new Socket(IP, PORT);
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }*/
            }
            oos = new ObjectOutputStream(client.getOutputStream());
            LOGGER.info("Sending message to the server...");
            oos.writeObject(new Message("login", user));
            User dbUser = null;
            LOGGER.info("Awaiting for the server message...");
            ois = new ObjectInputStream(client.getInputStream());
            Message msg = (Message)ois.readObject();
            if(msg.getMessage().equalsIgnoreCase("ok")){
                dbUser = (User)msg.getData();
            } else if(msg.getMessage().equalsIgnoreCase("incorrectLogin")){
                //--TOFIX -- Añadir condiciones
                throw new IncorrectLoginException();
            } else if(msg.getMessage().equalsIgnoreCase("incorrectPassword")){
                throw new IncorrectPasswordException();
            } else if(msg.getMessage().equalsIgnoreCase("serverNotAvailable")){
                //--TOFIX --> Atrapar la excepción que salta de por sí
                //cuando el servidor no está disponible
            }
            return dbUser;
        } catch(Exception e){
            //--TOFIX
            return null;
        } finally {
            try {
                if(oos != null){
                    oos.close();
                }
                if(ois != null){
                    ois.close();   
                }
                if(client != null){
                    client.close();
                }
            } catch (IOException ex) {
                //LOGGER.log(Level.SEVERE, null, ex);
                LOGGER.info(ex.getMessage());
            }
        }
    }
    
    public User register(User user){
        try{
            if(client == null){
                start();  
            }
            oos = new ObjectOutputStream(client.getOutputStream());
            ois = new ObjectInputStream(client.getInputStream());
            oos.writeObject(new Message("register", user));
            return new User();
        } catch(Exception e){
            //--TOFIX
            return null;
        } finally {
            try {
                if(oos != null){
                    oos.close();
                }
                if(ois != null){
                    ois.close();   
                }
                if(client != null){
                    client.close();
                }
            } catch(IOException ex){
                //LOGGER.log(Level.SEVERE, null, ex);
                LOGGER.info(ex.getMessage());
            }
        }
        
    }

    public boolean validateLogin(String login){
        try{
            if(client == null){
                start();
            }
            oos = new ObjectOutputStream(client.getOutputStream());
            ois = new ObjectInputStream(client.getInputStream());
            oos.writeObject(new Message("validateLogin", login));
            ois.readObject();
            return true;
        } catch(Exception e){
            //--TOFIX
            return true;
        } finally {
            try{
                if(oos != null){
                    oos.close();
                }
                if(ois != null){
                    ois.close();   
                }
                if(client != null){
                    client.close();
                }
            } catch(IOException ex){
                //LOGGER.log(Level.SEVERE, null, ex);
                LOGGER.info(ex.getMessage());
            }
        }
    }
    
    public boolean validateEmail(String email){
        try{
            if(client == null){
                start(); 
            }
            oos = new ObjectOutputStream(client.getOutputStream());
            ois = new ObjectInputStream(client.getInputStream());
            oos.writeObject(new Message("validateEmail", email));
            return true;
        } catch(Exception e){
            //--TOFIX
            return true;
        } finally {
            try{
                if(oos != null){
                    oos.close();
                }
                if(ois != null){
                    ois.close();   
                }
            } catch(IOException ex){
                //LOGGER.log(Level.SEVERE, null, ex);
                LOGGER.info(ex.getMessage());
            }
        }
    }
    
    @Override
    public void close() throws Exception{
        if(client != null){
            client.close();
        }
    }
    
    public void start(){
        try {
            client = new Socket(IP, PORT);
        } catch (IOException ex) {
            //LOGGER.log(Level.SEVERE, null, ex);
            LOGGER.info(ex.getMessage());
        }
    }
}
