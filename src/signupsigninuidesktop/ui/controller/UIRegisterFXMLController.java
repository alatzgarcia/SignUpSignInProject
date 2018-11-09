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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import signupsigninuidesktop.exceptions.EmailExistsException;
import signupsigninuidesktop.exceptions.LoginExistsException;
import signupsigninuidesktop.model.User;


/**
 * Controller class for JavaFX view implementation of the Sign in Sign Up
 * application, this controller allows the user to register. The email and
 * user must be uniques.
 * @author Nerea Jimenez and Diego Travesí
 */
public class UIRegisterFXMLController extends GenericController{
    
     @FXML
     private TextField txtUsername;
     @FXML
     private TextField txtFullName;
     @FXML
     private TextField txtEmail;
     @FXML
     private PasswordField pfPassword;
     @FXML
     private PasswordField pfSafetyPassword;
     @FXML
     private Label lblUsernameError;
     @FXML
     private Label lblPasswordError;
     @FXML
     private Label lblFullnameError;
     @FXML
     private Label lblEmailError;
     @FXML
     private Label lblSafetyPasswordError;
     @FXML
     private Button btnRegister;
     
         
     
     /**
      * This method sets the listeners and shows the window at the start
      * of the program, set the title ans makes it unresizable.
      * @param root Parent:
      */
     
     public void initStage(Parent root){
         //Create scene
         Scene scene = new Scene(root);
         stage = new Stage();
         //Associate scene to stage
         stage.setScene(scene);
        
         
         stage.setTitle("Register");
         stage.setResizable(false);
         //set window's events handlers
         stage.setOnShowing(this::handleWindowShowing);
         
        //set control events handlers
         
         txtUsername.textProperty().addListener(this::onTextChanged);
         txtFullName.textProperty().addListener(this::onTextChanged);
         txtEmail.textProperty().addListener(this::onTextChanged);
         pfPassword.textProperty().addListener(this::onTextChanged);
         pfSafetyPassword.textProperty().addListener(this::onTextChanged);
         
         txtUsername.focusedProperty().addListener(this::onFocusChanged);
         txtEmail.focusedProperty().addListener(this::onFocusChanged);
         txtFullName.focusedProperty().addListener(this::onFocusChanged);
         pfPassword.focusedProperty().addListener(this::onFocusChanged);
         pfSafetyPassword.focusedProperty().addListener(this::onFocusChanged);
         
         
         //Show primary window
          stage.show();
         
     }
     
    /**
     * Action event handler for the button Register in the UI, this method sends
     * the data of the user by parameter to the logic.
     * @param event AcionEvent
     */
     @FXML
    private void register(ActionEvent event) {
        try{
            //Creates an User with the user's inserted data
            User user = new User(txtUsername.getText(),txtEmail.getText(),txtFullName.getText(),pfPassword.getText());
            //Sends to the logic
            logicManager.register(user);
        } catch(LoginExistsException lee){
            LOGGER.info("Error. El usuario ya existe.");
            showErrorAlert(lee.getMessage());
        } catch(EmailExistsException eee){
            LOGGER.info("Error.Incorrect password.");
            showErrorAlert(eee.getMessage());
        } catch(Exception e){
            LOGGER.info(e.getMessage());
            showErrorAlert("Error en el inicio de sesión.");
        }
    }
    
    /**
     * Action event handler for the button Back in the UI. 
     * @param event AcionEvent
     */
    @FXML
    private void returnToLogin(ActionEvent event) {
        stage.close();
    }
    
    /**
     * Action event handler for the button Exit in the UI. 
     * @param event AcionEvent
     */
    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
        
    }
    
    //The code when the window opens
    private void handleWindowShowing (WindowEvent event){
        //The Register button is disabled
        btnRegister.setDisable(true);
        // The first field will be focused
        txtUsername.requestFocus();
        
        //We declare the tooltip for the register button
        btnRegister.setTooltip(
                new Tooltip("Pulse el botón para registrarse")
        );
    }
    
    /**
     * This method will check everytime that any text is modified, this method manages
     * to enable or disable the button in case you modify the last text field 
     * without changing the focus checking the minimum length and maximun length, 
     * the email format, the passwords are equals and the email or login name hasn't 
     * been never used
     * @param observable The value being observed.
     * @param oldValue The old value of the observable.
     * @param newValue The new value of the observable.
     */
    private void onTextChanged(ObservableValue observable,
            String oldValue,
            String newValue){
        
        LOGGER.info("Text changed");
        
        if(this.txtUsername.getText().trim().equals("")||
                this.txtEmail.getText().trim().equals("")||
                this.txtFullName.getText().trim().equals("")||
                this.pfPassword.getText().trim().equals("")||
                this.pfSafetyPassword.getText().trim().equals("")){
                
            
                //if any field is empty the button is disabled
                btnRegister.setDisable(true);
        }else{
            /*If the fields have the correct length, password are the same
            * and email format is correct, the button will be enabled otherwise
            * it won't be.
            */
            if(checkUsernametc()&&checkEmailtc()&&checkFullNametc()&&checkPasswordtc()&&checkSafetyPasswordtc()){
                btnRegister.setDisable(false);
            }else{
                btnRegister.setDisable(true);
            }
        }         
      }
    
    /**
     * This method checks the text field when the user changes the focus, checks
     * the minimum length and maximun length, the email format, the passwords are
     * equals and the email or login name hasn't been never used
     * @param observable ObservaThe value being observed.
     * @param oldValue The old value of the observable.
     * @param newValue The new value of the observable.
     */
    private void onFocusChanged(ObservableValue observable,
            Boolean oldValue,
            Boolean newValue){
        
        if(oldValue){
            TextField field = (TextField)((ReadOnlyProperty)observable).getBean();
            if(!field.getText().trim().equals("")){
                if(field==txtUsername){
                    checkUsername();
                }else if (field==txtFullName){
                    checkFullName();
                }else if (field==txtEmail){
                    checkEmail();
                }else if(field==pfPassword){
                    checkPassword();
                }else if(field==pfSafetyPassword){
                    checkSafetyPassword();
                }
            }
        }
    }
    
    /**
     * Checks if the username fullfills the minimum and maximun length, if don't
     * shows an error in a label and the text field turns red, until the user 
     * corrects it.
     */
    private void checkUsername(){
        if(txtUsername.getText().trim().length()<userPasswordMinLength||txtUsername.getText().trim().length()>userPasswordMaxLength){
            lblUsernameError.setText("La longitud del nombre de usuario no es adecuada");
            focusError(txtUsername);
        }else{
            lblUsernameError.setText("");
            focusCorrected(txtUsername);
        }
    }
    
    /**
     * Checks if the full name of the user fullfills the minimum and maximun length, 
     * if don't shows an error in a label and the text field turns red, until the user 
     * corrects it.
     */
    private void checkFullName() {
        if(txtFullName.getText().trim().length()<fullNameMinLength||txtFullName.getText().trim().length()>fullNameMaxLength){
            lblFullnameError.setText("La longitud del nombre no es adecuada");
            focusError(txtFullName);
        }else{
            lblFullnameError.setText("");
            focusCorrected(txtFullName);
        }
    }

    /**
     * Checks if the email of the user fullfills the email format, if don't shows 
     * an error in a label and the text field turns red, until the user 
     * corrects it.
     */
    private void checkEmail() {
        if(!txtEmail.getText().matches("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$")){
            lblEmailError.setText("Email no válido");
            focusError(txtEmail);
        }else {
            lblEmailError.setText("");
            focusCorrected(txtEmail);
        }
    }
    
    /**
     * Checks if the password of the user fullfills the minimum and maximun length, 
     * if don't shows an error in a label and the text field turns red, until the user 
     * corrects it.
     */
    private void checkPassword() {
        checkSafetyPassword();
        if(pfPassword.getText().trim().length()<userPasswordMinLength||
                pfPassword.getText().trim().length()>userPasswordMaxLength){
            lblPasswordError.setText("La longitud de la contraseña no es adecuada");
            focusError(pfPassword);
        }else{
            lblPasswordError.setText("");
            focusCorrected(pfPassword);
        }
    }
    
    /**
     * Checks if the password of the user fullfills the minimum and maximun length
     * and its equals to the previous password if don't shows an error in a label 
     * and the text field turns red, until the user corrects it.
     */
    private void checkSafetyPassword() {
        if(!pfSafetyPassword.getText().trim().equals("")){
         if(pfSafetyPassword.getText().trim().length()<userPasswordMinLength||
                 pfSafetyPassword.getText().trim().length()>userPasswordMaxLength){
            lblSafetyPasswordError.setText("La longitud de la contraseña no es adecuada");
             focusError(pfSafetyPassword);            
        }else if (!pfPassword.getText().equals(pfSafetyPassword.getText())){
            lblSafetyPasswordError.setText("Las contraseñas no coinciden");
            focusError(pfSafetyPassword);    
        }else{
            lblSafetyPasswordError.setText("");
             focusCorrected(pfSafetyPassword);
        }
        }
    }
    
    /**
     * Checks if the username of the user fullfills the minimum and maximun length
     * when the text change (onTextChange method)
     * @return 
     */
    private boolean checkUsernametc(){
         return(txtUsername.getText().trim().length()>userPasswordMinLength&&txtUsername.getText().trim().length()<userPasswordMaxLength); 
        
    }
    
    /**
     * Checks if the full name of the user fullfills the minimum and maximun length
     * when the text change (onTextChange method)
     * @return 
     */
    private boolean checkFullNametc() {
         return (txtFullName.getText().trim().length()>fullNameMinLength&&txtFullName.getText().trim().length()<fullNameMaxLength); 
        
    }
    
    /**
     * Checks if the email of the user fullfills the email format
     * when the text change (onTextChange method)
     * @return 
     */
    private boolean checkEmailtc() {
         return (txtEmail.getText().matches("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$")&&
                 txtEmail.getText().trim().length()>userPasswordMinLength&&
                 txtEmail.getText().trim().length()<userPasswordMaxLength); 
        
    }

    /**
     * Checks if the password of the user fullfills the minimum and maximun length
     * when the text change (onTextChange method)
     * @return 
     */
    private boolean checkPasswordtc() {
         return (pfPassword.getText().trim().length()>=userPasswordMinLength&&pfPassword.getText().trim().length()<userPasswordMaxLength); 
        
    }
    
    /**
     * Checks if the password of the user fullfills the minimum and maximun length
     * and its equals to the previous password when the text change (onTextChange method)
     * @return 
     */
    private boolean checkSafetyPasswordtc() {
         return (pfSafetyPassword.getText().trim().length()>=userPasswordMinLength&&
                    pfSafetyPassword.getText().trim().length()<userPasswordMaxLength&&
                    pfSafetyPassword.getText().equals(pfPassword.getText())); 
        
    }
    
   /**
    * In case of error in a text field, the field is send by parameter is
    * changed to red
    * @param textField TextField: The field that is changed in case of error
    */
   private void focusError(TextField textField){
       textField.setStyle("-fx-border-color: red");
   }
   
   /**
    * In case there is no error (or the error was corrected) this method sets 
    * the field style as default
    * @param textField TextField: The field that is changed in case it have no error
    */
   private void focusCorrected(TextField textField){
       textField.setStyle("");
   }
}
