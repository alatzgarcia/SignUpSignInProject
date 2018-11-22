/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import signupsigninuidesktop.exceptions.ConfigurationParameterNotFoundException;
import signupsigninutilities.model.Message;
import signupsigninutilities.model.User;
import signupsigninuidesktop.exceptions.EmailExistsException;
import signupsigninuidesktop.exceptions.GenericException;
import signupsigninuidesktop.exceptions.IncorrectLoginException;
import signupsigninuidesktop.exceptions.IncorrectPasswordException;
import signupsigninuidesktop.exceptions.LoginEmailExistException;
import signupsigninuidesktop.exceptions.LoginExistsException;
import signupsigninuidesktop.exceptions.NotAvailableConnectionsException;
import signupsigninuidesktop.exceptions.RegisterFailedException;
import signupsigninuidesktop.exceptions.ServerNotAvailableException;


/**
 * Class that implements the socket for the client side of the application
 * and allows the client to connect to the server
 * @author Alatz
 */
public class ILogicImplementation implements ILogic{
    private static final Logger LOGGER = 
            Logger.getLogger("signupsigninuidesktop.logic.ILogicImplementation");
   
    private String ip;
    private int port;
    
    private Socket client;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    
    /**
     * Method that connects client and server for the "login" of a user 
     * @param user
     * @return logged in user
     * @throws signupsigninuidesktop.exceptions.IncorrectLoginException
     * @throws signupsigninuidesktop.exceptions.IncorrectPasswordException
     * @throws signupsigninuidesktop.exceptions.ServerNotAvailableException
     */
    @Override
    public User login(User user) throws IncorrectLoginException, 
            IncorrectPasswordException, ServerNotAvailableException, 
            GenericException,NotAvailableConnectionsException,ConfigurationParameterNotFoundException {
        try{
            start();
            
            LOGGER.info(ip);
            LOGGER.info(String.valueOf(port));
            oos = new ObjectOutputStream(client.getOutputStream());
            LOGGER.info("Sending message to the server...");
            oos.writeObject(new Message("login", user));
           
            LOGGER.info("Awaiting for the server message...");
            ois = new ObjectInputStream(client.getInputStream());
            Message msg = (Message)ois.readObject();
            
            LOGGER.info("Server message arrived to the client.");
            LOGGER.info(msg.getMessage());
            if(msg.getMessage().equalsIgnoreCase("ok")){
                User dbUser;
                dbUser = (User)msg.getData();
                return dbUser;
            } else if(msg.getMessage().equalsIgnoreCase("incorrectLogin")){
                throw new IncorrectLoginException();
            } else if(msg.getMessage().equalsIgnoreCase("incorrectPassword")){
                throw new IncorrectPasswordException();
            } else if(msg.getMessage().equalsIgnoreCase("serverNotAvailable")){
                throw new ServerNotAvailableException();
            } else if(msg.getMessage().equalsIgnoreCase("error")){
                throw new GenericException(); 
            } else if(msg.getMessage().equalsIgnoreCase("configParamNotFound")){
                throw new ConfigurationParameterNotFoundException(); 
            } else if(msg.getMessage().equalsIgnoreCase("notAvailableConnections")){
                throw new NotAvailableConnectionsException(); 
            }else{
                throw new GenericException(); 
            }
        } catch(IncorrectPasswordException ipe){
            throw new IncorrectPasswordException();
        } catch(IncorrectLoginException ile){
            throw new IncorrectLoginException();
        } catch(ServerNotAvailableException snae){
            throw new ServerNotAvailableException();
        }catch(NotAvailableConnectionsException nace){
            throw new NotAvailableConnectionsException();           
        }catch(ConfigurationParameterNotFoundException cpnfe){
            throw new ConfigurationParameterNotFoundException();
        } catch(Exception e){
            LOGGER.severe(e.getMessage());
            throw new GenericException(); 
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
                LOGGER.severe(ex.getMessage());
            }
        }
    }
    
    /**
     * Method that connects client and server for the "register" of a user
     * @param user
     * @return registered user
     * @throws signupsigninuidesktop.exceptions.LoginExistsException
     * @throws signupsigninuidesktop.exceptions.EmailExistsException
     * @throws signupsigninuidesktop.exceptions.LoginEmailExistException
     */
    @Override
    public User register(User user) throws LoginExistsException, 
            EmailExistsException,LoginEmailExistException, 
            RegisterFailedException, ServerNotAvailableException,
            NotAvailableConnectionsException, GenericException, ConfigurationParameterNotFoundException{
        try{
            start();
           
            oos = new ObjectOutputStream(client.getOutputStream());
            LOGGER.info("Sending message to the server...");
            oos.writeObject(new Message("register", user));
           
            LOGGER.info("Awaiting for the server message...");
            ois = new ObjectInputStream(client.getInputStream());
            Message msg = (Message)ois.readObject();
                        
            LOGGER.info(msg.getMessage());
            LOGGER.info("Server message arrived to the client.");
            if(msg.getMessage().equalsIgnoreCase("ok")){
                User dbUser;
                dbUser = (User)msg.getData();
                return dbUser;
            } else if(msg.getMessage().equalsIgnoreCase("loginExists")){
                throw new LoginExistsException();
            } else if(msg.getMessage().equalsIgnoreCase("emailExists")){
                throw new EmailExistsException();
            } else if(msg.getMessage().equalsIgnoreCase("loginEmailExist")){
                throw new LoginEmailExistException();
            } else if(msg.getMessage().equalsIgnoreCase("registerFailed")){
                throw new RegisterFailedException();
            } else if(msg.getMessage().equalsIgnoreCase("serverNotAvailable")){
                throw new ServerNotAvailableException();
            } else if(msg.getMessage().
                    equalsIgnoreCase("notAvailableConnections")){
                throw new NotAvailableConnectionsException();
            } else if(msg.getMessage().equalsIgnoreCase("error")){
                throw new GenericException(); 
            } else if(msg.getMessage().equalsIgnoreCase("configParamNotFound")){
                throw new ConfigurationParameterNotFoundException(); 
            }else{
                throw new GenericException(); 
            }
         } catch(LoginExistsException lee){
            throw new LoginExistsException();        
         } catch(EmailExistsException eee){
            throw new EmailExistsException();
        } catch(LoginEmailExistException leee){
            throw new LoginEmailExistException();           
        } catch(RegisterFailedException rfe){
            throw new RegisterFailedException();           
        }catch(ServerNotAvailableException snae){
            throw new ServerNotAvailableException();           
        }catch(NotAvailableConnectionsException nace){
            throw new NotAvailableConnectionsException();           
        }catch(ConfigurationParameterNotFoundException cpnfe){
            throw new ConfigurationParameterNotFoundException();
        }catch(GenericException ge){
            throw new GenericException();           
        }catch(Exception e){
            LOGGER.severe(e.getMessage());
            throw new GenericException();
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
                LOGGER.severe(ex.getMessage());
            }
        }
    }
    
    /**
     * Method to close the socket for the client when he logs out
     * @throws Exception 
     */
    @Override
    public void close() throws Exception{
        if(client != null){
            client.close();
        }
        else{
           //throw new AlreadyLoggedOutException();
        }
    }
    
    /**
     * Method to start the socket for the client
     */
    public void start() throws IOException{
        getData();
        client = new Socket(ip, port);
    }

    /**
     * Method that takes the parameters for the socket from a config file.
     */
    private void getData() {
       Properties config = new Properties();
	FileInputStream input = null;
	try {
            input = new FileInputStream("src/signupsigninuidesktop/config/connection.properties");
            config.load(input); 
            ip = config.getProperty("ip");
            port=Integer.parseInt(config.getProperty("port"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ILogicImplementation.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(ILogicImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (input != null)
		try {
                    input.close();
            } catch (IOException ex) {
                Logger.getLogger(ILogicImplementation.class.getName()).log(Level.SEVERE, null, ex);
            } 
	}
    }
}

