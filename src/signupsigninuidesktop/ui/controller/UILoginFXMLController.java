/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.ui.controller;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import signupsigninuidesktop.logic.UIRegisterFXMLController;
import signupsigninuidesktop.model.User;

/**
 *
 * @author Alatz
 */
public class UILoginFXMLController extends GenericController{
    private TextField txtUsername;
    private PasswordField pfPassword; 
    private Label lblUsernameError;
    private Label lblPasswordError;
    private Button btnLogin;
    
    
    public void initStage(Parent root){
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setResizable(false);
        
        stage.setOnShowing(this::handleWindowShowing);
        
        txtUsername.textProperty().addListener(this::onTextChanged);
        pfPassword.textProperty().addListener(this::onTextChanged);
        
        txtUsername.focusedProperty().addListener(this::onFocusChanged);
        
        stage.show();
    }
    
    public void handleWindowShowing(WindowEvent event){
        btnLogin.setDisable(true);
        txtUsername.requestFocus();
        //Settear promptText
    }
    
    public void login(ActionEvent event){
        
        try{
            //Sends a user to the logic controller with the entered parameters
            logicManager.login(new User(txtUsername.getText(), 
                    pfPassword.getText()));   
        } catch(Exception e){
            //--TOFIX
        }
    }
    
    public void register(ActionEvent event){
        //calls the logicManager register functio
        try{
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("ui/fxml/UIRegister.fxml"));
            Parent root = loader.load();
            //Get controller from the loader
            UIRegisterFXMLController registerController = loader.getController();
            /*Set a reference in the controller 
                for the UILogin view for the logic manager object           
            */
            registerController.setLogicManager(logicManager);
            //Set a reference for Stage in the UILogin view controller
            registerController.setStage(stage);
            //Initialize the primary stage of the application
            registerController.initStage(root);
        }catch(Exception e){
            //--TOFIX
        }
    }
    
    public void exit(){
        //--TOFIX -- Close socket if opened
        Platform.exit();
    }
    
    /**
     * Method that checks if any any of the fillable fields are empty to enable 
     * or disable the "btnLogin" button depending on the result
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    public void onTextChanged(ObservableValue observable,
             String oldValue,
             String newValue){
        /*Checks if any of the fields have no text entered 
            and disables the btnLogin button if true  
        */
        if(txtUsername.getText().trim().length() == 0 
                || pfPassword.getText().trim().length() == 0){
            btnLogin.setDisable(true);
        }
        else {
            btnLogin.setDisable(false);
        }
    }
    
    /**
     * Method to check if the text of a fillable field that has lost the focus
     * has the correct length
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    //--TOFIX
    public void onFocusChanged(ObservableValue observable,
             Boolean oldValue,
             Boolean newValue){
        TextField tf = ((TextField)((ReadOnlyProperty)observable).getBean());
        if(oldValue){
            if(tf.getText().length() < userPasswordMinLength ||
                    tf.getText().length() > userPasswordMaxLength){
                if(tf == txtUsername){
                    lblUsernameError.setText("Error. El campo usuario "
                            + "debe contener entre 8 y 30 caracteres");
                } else {
                    lblPasswordError.setText("Error. El campo contraseña "
                        + "debe contener entre 8 y 30 caracteres");
                }
            } 
        }
    }
}
