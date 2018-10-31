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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import signupsignin.User;
import signupsigninuidesktop.exceptions.IncorrectLoginException;
import signupsigninuidesktop.exceptions.IncorrectPasswordException;


/**
 *
 * @author Alatz
 */
public class UILoginFXMLController extends GenericController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pfPassword; 
    @FXML
    private Label lblUsernameError;
    @FXML
    private Label lblPasswordError;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnExit;
    @FXML
    private Hyperlink hlRegister;
    
    /**
     * 
     * @param root 
     */
    public void initStage(Parent root){
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setResizable(false);
        
        stage.setOnShowing(this::handleWindowShowing);
        
        txtUsername.textProperty().addListener(this::onTextChanged);
        pfPassword.textProperty().addListener(this::onTextChanged);
        
        txtUsername.focusedProperty().addListener(this::onFocusChanged);
        pfPassword.focusedProperty().addListener(this::onFocusChanged);
        
        btnExit.setOnAction(this::exit);
        btnLogin.setOnAction(this::login);
        hlRegister.setOnAction(this::register);
        
        stage.show();
    }
    
    /**
     * 
     * @param event 
     */
    public void handleWindowShowing(WindowEvent event){
        btnLogin.setDisable(true);
        txtUsername.requestFocus();
        //Settear promptText
    }
    
    /**
     * 
     * @param event 
     */
    public void login(ActionEvent event){
        
        try{
            //Sends a user to the logic controller with the entered parameters
            logicManager.login(new User(txtUsername.getText(), 
                    pfPassword.getText()));
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/signupsigninuidesktop/ui/fxml/UILogged.fxml"));
            Parent root = loader.load();
            //Get controller from the loader
            UILoggedFXMLController loggedController = loader.getController();
            /*Set a reference in the controller 
                for the UILogin view for the logic manager object           
            */
            loggedController.setLogicManager(logicManager);
            //Initialize the primary stage of the application
            loggedController.initStage(root);
            
            stage.hide();
        } catch(IncorrectLoginException ile){
            LOGGER.info("Error. Incorrect login.");
            showErrorAlert(ile.getMessage());
        } catch(IncorrectPasswordException ipe){
            LOGGER.info("Error.Incorrect password.");
            showErrorAlert(ipe.getMessage());
        } catch(Exception e){
            LOGGER.info(e.getMessage());
            showErrorAlert("Error en el inicio de sesión.");
        }
    }
    
    /**
     * 
     * @param event 
     */
    public void register(ActionEvent event){
        //calls the logicManager register functio
        try{
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/signupsigninuidesktop/ui/fxml/UIRegister.fxml"));
            Parent root = loader.load();
            //Get controller from the loader
            UIRegisterFXMLController registerController = loader.getController();
            /*Set a reference in the controller 
                for the UIController view for the logic manager object           
            */
            registerController.setLogicManager(logicManager);
            //Initialize the primary stage of the application
            registerController.initStage(root);
            
            stage.hide();
        }catch(Exception e){
            LOGGER.info(e.getMessage());
            showErrorAlert("Error al redirigir al registro de usuario.");
        }
    }
    
    /**
     * Method to exit the application
     */
    public void exit(ActionEvent event){
        try {
            logicManager.close();
            Platform.exit();
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
            showErrorAlert("Error al intentar cerrar la aplicación.");
        }
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
        else if(txtUsername.getText().trim().length()>=userPasswordMinLength 
                && txtUsername.getText().trim().length()<=userPasswordMaxLength 
                && pfPassword.getText().trim().length()>=userPasswordMinLength
                && pfPassword.getText().trim().length()<=userPasswordMaxLength){
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
        if(oldValue){
            TextField tf = ((TextField)((ReadOnlyProperty)observable).getBean());
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