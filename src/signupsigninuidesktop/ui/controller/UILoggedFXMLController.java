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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Iker
 */
public class UILoggedFXMLController extends GenericController {
    
    @FXML
    private Button btnLogout;
    
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

    public void handleWindowShowing(WindowEvent event){
        
    }
    
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
            LOGGER.info(ex.getMessage());
        }
    }
}