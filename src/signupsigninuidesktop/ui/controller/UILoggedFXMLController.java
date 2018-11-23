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
 * Shows the Logged window.
 * @author Iker
 */
public class UILoggedFXMLController extends GenericController {
    
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnReturnLogin;
    @FXML
    private Label lblBienvenido;
    @FXML
    private Label lblUsu;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblPrivilege;
    @FXML
    private Label lblLastConection;
    
    
    /**
     * Create the Stage and the Scene ando show the stage.
     * @param root: The Parent param
     */  
    
    public void initStage(Parent root){
        
        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Logged");
        stage.setResizable(false);
        stage.setOnShowing(this::handleWindowShowing);
        btnLogout.setOnAction(this::logOut);
        btnReturnLogin.setOnAction(this::goToLogin);
        stage.show();
        
    }
    
   /**
    * Shows a personal greeting to the user
    * @param event : A WindowEvent param
    */
    public void handleWindowShowing(WindowEvent event){
        lblBienvenido.setText("Buenas, " + user.getFullName()+ "!");
        lblUsu.setText("Usuario: "+user.getLogin());
        lblEmail.setText("Email: "+user.getEmail());
        lblStatus.setText("Status: "+user.getStatus());
        lblPrivilege.setText("Privilege: "+user.getPrivilege()); 
        lblLastConection.setText("Ultima conexion: "+user.getLastAccess());
    }
    
   /**
    * Shows a window that shows two options
    * If you click the option "OK" the application will close.
    * If yo click the option "CANCEL" the application will not close
    * @param event 
    */

    public void logOut(ActionEvent event){
        try{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cerrar Sesión");
            alert.setContentText("¿Desea cerrar sesion?");        
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()== ButtonType.OK){
                stage.close();
                previousStage.show();
            }else{
                LOGGER.info("Logout cancelled.");
            } 
        }catch(Exception ex){
            LOGGER.severe(ex.getMessage());
        }
    }
    
         /**
         * Exit from the application
         */
    
    public void exitLogged(){
        Platform.exit();
    }
    
     
    /**
     * Call to the UILoginFXML
     * @param event : ActionEvent param
     */
    public void goToLogin(ActionEvent event) {
     try{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Volver al Login");
            alert.setContentText("¿Desea volver al Login?");    
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()== ButtonType.OK){
                stage.close();
                previousStage.show();
            }else{
                LOGGER.info("No volvera.");
            } 
        } catch(Exception ex){
            LOGGER.severe(ex.getMessage());
        }
    }
}

