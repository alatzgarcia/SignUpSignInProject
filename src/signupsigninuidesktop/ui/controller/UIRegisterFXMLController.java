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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import signupsigninuidesktop.exceptions.EmailExistsException;
import signupsigninuidesktop.exceptions.LoginExistsException;
import signupsigninutilities.model.User;


/**
 * Controller class for JavaFX view implementation of the Sign in Sign Up
 * application .
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
     
         
     //private boolean validUser=false;
     //private boolean validEmail=false;
     
     /**
      * This method sets the listeners and shows the window at the start
      * of the program
      * @param root 
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
     * Action event handler for the button Register in the UI. 
     * @param event 
     */
     @FXML
    private void register(ActionEvent event) {
//descomentar cuando haya validate
        //the validate methods checks if the parameter already exists in the DB
        try{
            User user = new User(txtUsername.getText(),txtEmail.getText(),txtFullName.getText(),pfPassword.getText());
            User dbUser = logicManager.register(user);
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
            loggedController.setUser(dbUser);
            loggedController.initStage(root);
            stage.hide();
            //validateEmail(txtEmail.toString().trim());
            //validateLogin(txtUsername.toString().trim());
        
        /**
         * When you press the register button will check the validate methods return
         * if they doesn't exist, will be saved in the User.
         * If they exist an error will be showed.
         */
            /*if(validEmail&&validUser){
                User user = new User(txtUsername.getText(),txtEmail.getText(),txtFullName.getText(),pfPassword.getText());
               logicManager.register(user);
            }else{
                if(!validEmail){
                    lblEmailError.setText("El email ya existe");
                    focusError(txtEmail);
                }else{
                    lblEmailError.setText("");
                    focusCorrected(txtEmail);
                }
                if(!validUser){
                    lblUsernameError.setText("El nombre de usuario ya existe");
                    focusError(txtUsername);
                }else{
                    lblUsernameError.setText("");
                    focusCorrected(txtUsername);
                }
            }*/
        } catch(LoginExistsException lee){
            LOGGER.info("Error. Login already exists.");
            showErrorAlert(lee.getMessage());
        } catch(EmailExistsException eee){
            LOGGER.info("Error. Email already exists.");
            showErrorAlert(eee.getMessage());
        } catch(Exception e){
            LOGGER.info(e.getMessage());
            showErrorAlert("Error en el inicio de sesión.");
        }
    }
    
    /**
     * Action event handler for the button Back in the UI. 
     * @param event 
     */
    @FXML
    private void returnToLogin(ActionEvent event) {
        stage.close();
        
    }
    
    /**
     * Action event handler for the button Exit in the UI. 
     * @param event 
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
     * This method will check everytime that any text is modified
     * @param observable 
     * @param oldValue The last value of the text
     * @param newValue The new value of the text
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
            /*If the fields have the corrct length, password are the same
            and email format is correct, the button will be enabled*/
            if(checkUsernametc()&&checkEmailtc()&&checkFullNametc()&&checkPasswordtc()&&checkSafetyPasswordtc()){
                btnRegister.setDisable(false);
            }else{
                btnRegister.setDisable(true);
                    
            }
            
        }
                       
      }
    /**
     * 
     * @param observable
     * @param oldValue
     * @param newValue 
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

    /*private void validateEmail(String email) throws EmailExistsException{
        validEmail=logicManager.validateEmail(txtEmail.toString().trim());
         
    }
    
    private void validateLogin(String username)throws LoginExistsException{
        validUser= logicManager.validateLogin(txtUsername.toString().trim());
        
    }*/
    
    private void checkUsername(){
        if(txtUsername.getText().trim().length()<userPasswordMinLength||txtUsername.getText().trim().length()>userPasswordMaxLength){
            lblUsernameError.setText("La longitud del nombre de usuario no es adecuada");
            focusError(txtUsername);
        }else{
            lblUsernameError.setText("");
            focusCorrected(txtUsername);
        }
    }

    private void checkFullName() {
        if(txtFullName.getText().trim().length()<fullNameMinLength||txtFullName.getText().trim().length()>fullNameMaxLength){
            lblFullnameError.setText("La longitud del nombre no es adecuada");
            focusError(txtFullName);
        }else{
            lblFullnameError.setText("");
            focusCorrected(txtFullName);
        }
    }

    private void checkEmail() {
        if(!txtEmail.getText().matches("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$")){
            lblEmailError.setText("Email no válido");
            focusError(txtEmail);
        }else {
            lblEmailError.setText("");
            focusCorrected(txtEmail);
        }
    }

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
    
    
    private boolean checkUsernametc(){
         return(txtUsername.getText().trim().length()>userPasswordMinLength&&txtUsername.getText().trim().length()<userPasswordMaxLength); 
        
         
    }

    private boolean checkFullNametc() {
         return (txtFullName.getText().trim().length()>fullNameMinLength&&txtFullName.getText().trim().length()<fullNameMaxLength); 
        
    }

    private boolean checkEmailtc() {
         return (txtEmail.getText().matches("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$")&&
                 txtEmail.getText().trim().length()>userPasswordMinLength&&
                 txtEmail.getText().trim().length()<userPasswordMaxLength); 
        
    }

    private boolean checkPasswordtc() {
         return (pfPassword.getText().trim().length()>=userPasswordMinLength&&pfPassword.getText().trim().length()<userPasswordMaxLength); 
        
    }

    private boolean checkSafetyPasswordtc() {
         return (pfSafetyPassword.getText().trim().length()>=userPasswordMinLength&&
                    pfSafetyPassword.getText().trim().length()<userPasswordMaxLength&&
                    pfSafetyPassword.getText().equals(pfPassword.getText())); 
        
    }
    
   private void focusError(TextField textField){
       textField.setStyle("-fx-border-color: red");
   }
   private void focusCorrected(TextField textField){
       textField.setStyle("");
   }
}