/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.ui.controller;

import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Iker
 */
public class UILoggedFXMLController extends GenericController {
    
    @FXML
    private Button btnLogout;
    @FXML
    private Label lblBienvenido;
    
    public void initStage(Parent root){
        
        stage = new Stage();
       Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Logged");
        stage.setResizable(false);
        
        stage.setOnShowing(this::handleWindowShowing);
        btnLogout.setOnAction(this::logOut);
        
        stage.show();
    }

      	 /**
         * Shows a personal greeting to the user
         */

    public void handleWindowShowing(WindowEvent event){
        //user.setLogin("Juan");
        LOGGER.info(user.getFullName());
        lblBienvenido.setText("Buenas, " + user.getFullName()+ "!");
    }
    
            /**
        * Shows a window that shows two options
        * If you click the option "OK" the application will close.
        * If yo click the option "CANCEL" the application will not close
        */

    
    public void logOut(ActionEvent event){
        try{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cerrar Sesión");
            alert.setContentText("¿Desea cerrar sesion?");
        
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()== ButtonType.OK){
                Platform.exit();
            }else{
                LOGGER.info("Logout cancelled.");
            } 
        } catch(Exception ex){
            LOGGER.severe(ex.getMessage());
        }
    }
    
         /**
         * Exit from the application
         */

    
    public void exitLogged(){
        
        LOGGER.info("Adios");
        Platform.exit();
    }
    
    
      /**
       * Calls and load the UILogin.fxml
       */

    public void goToLogin(){
      
        try{
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/signupsigninuidesktop/ui/fxml/UILogin.fxml"));
            Parent root = loader.load();
            //Get controller from the loader
            UILoggedFXMLController loggedController = loader.getController();
            /*Set a reference in the controller 
                for the UIController view for the logic manager object           
            */
            loggedController.setLogicManager(logicManager);
            //Initialize the primary stage of the application
            loggedController.initStage(root);
            
            stage.hide();
        }catch(Exception e){
            LOGGER.severe(e.getMessage());
            showErrorAlert("Error al redirigir al login de usuario.");
        }
    }


}