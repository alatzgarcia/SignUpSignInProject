/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.ui.controller;

import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import signupsigninutilities.model.User;
import signupsigninuidesktop.logic.ILogic;

/**
 * Generic controler for the application
 * @author Alatz
 */
public class GenericController {
    /**
     * Logger is used to display messages in the console
     */
    protected static final Logger LOGGER = Logger.getLogger("signupsigninuidesktop.ui.controller");
    /**
     * The min length for all the fields except email and user's fullname
     */
    protected final int userPasswordMinLength = 8;
    /**
     * The max length for all the fields except email and user's fullname
     */
    protected final int userPasswordMaxLength = 30;
    /**
     * The min length of the user's fullname
     */
    protected final int fullNameMinLength = 5;
    /**
     * The max length of the user's fullname
     */
    protected final int fullNameMaxLength = 50;
    
    
    
    /**
     * The Ilogic interface
     */
    protected ILogic logicManager;
    /**
     * The stage
     */
    protected Stage stage;
    /**
     * The User where the data will be stored
     */
    protected User user;

    /**
     * Shows an alert with an error message
     * @param errorMsg 
     */
    protected void showErrorAlert(String errorMsg){
        //Shows error dialog.
        Alert alert=new Alert(Alert.AlertType.ERROR,
                              errorMsg,
                              ButtonType.OK);
       
        alert.showAndWait();
        
    }
     
    /**
     * Sets the logic Manager
     * @param logicManager 
     */
    public void setLogicManager(ILogic logicManager){
        this.logicManager = logicManager;
    }
    
    /**
     * Sets the stage
     * @param stage 
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }
    /**
     * Sets the user
     * @param user User
     */
    public void setUser(User user){
        this.user=user;
        
    }

    
}
