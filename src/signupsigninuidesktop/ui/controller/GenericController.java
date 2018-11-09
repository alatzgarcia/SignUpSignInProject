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
import signupsignin.User;
import signupsigninuidesktop.logic.ILogic;

/**
 *
 * @author Alatz
 */
public class GenericController {
    //Meter aqui maxLength y MinLength para los diferentes campos
    protected static final Logger LOGGER = Logger.getLogger("signupsigninuidesktop.ui.controller");
    protected final int userPasswordMinLength = 8;
    protected final int userPasswordMaxLength = 30;
    protected final int fullNameMinLength = 5;
    protected final int fullNameMaxLength = 50;
    
    protected ILogic logicManager;
    protected Stage stage;
    protected User user;

    
    protected void showErrorAlert(String errorMsg){
        //Shows error dialog.
        Alert alert=new Alert(Alert.AlertType.ERROR,
                              errorMsg,
                              ButtonType.OK);
        // --TOFIX
        //alert.getDialogPane().getStylesheets().add(
              //getClass().getResource("/ui/fxml/customCascadeStyleSheet.css").toExternalForm());
        alert.showAndWait();
        
    }
     
    public void setLogicManager(ILogic logicManager){
        this.logicManager = logicManager;
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public void setUser(User user){
        this.user=user;
        
    }

    
}
