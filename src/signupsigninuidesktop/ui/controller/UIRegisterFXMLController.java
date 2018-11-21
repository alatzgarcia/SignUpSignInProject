/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.ui.controller;


import java.awt.Color;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.UIManager;
import signupsigninutilities.model.User;
import signupsigninuidesktop.exceptions.EmailExistsException;
import signupsigninuidesktop.exceptions.GenericException;
import signupsigninuidesktop.exceptions.LoginEmailExistException;
import signupsigninuidesktop.exceptions.LoginExistsException;
import signupsigninuidesktop.exceptions.NotAvailableConnectionsException;
import signupsigninuidesktop.exceptions.RegisterFailedException;
import signupsigninuidesktop.exceptions.ServerNotAvailableException;
import static signupsigninuidesktop.ui.controller.GenericController.LOGGER;



/**
 * Controller class for JavaFX view implementation of the Sign in Sign Up
 * application, this controller allows the user to register. The email and
 * user must be uniques.
 * @author Nerea Jimenez and Diego Travesí
 */
public class UIRegisterFXMLController extends GenericController{
     /**
      * The text field for the login username
      */
     @FXML
     private TextField txtUsername;
     @FXML
     /**
      * The text field for the full name of the user
      */
     private TextField txtFullName;
     /**
      * The text field for the email
      */
     @FXML
     private TextField txtEmail;
     /**
      * The text field for the password
      */
     @FXML
     private PasswordField pfPasswordReg;
     /**
      * The text field for the repeated password
      */
     @FXML
     private PasswordField pfSafetyPassword;
     /**
      * The label for the username error
      */
     @FXML
     private Label lblUsernameError;
     /**
      * The label for the password error
      */
     @FXML
     private Label lblPasswordError;
     /**
      * The label for the password error
      */
     @FXML
     private Label lblFullnameError;
     /**
      * The label for the email error
      */
     @FXML
     private Label lblEmailError;
     /**
      * The label for the password error
      */
     @FXML
     private Label lblSafetyPasswordError;
     /**
      * The register button
      */
     @FXML
     private Button btnRegister;
    
     
         
     
     /**
      * This method sets the listeners and shows the window at the start
      * of the program, set the title ans makes it unresizable.
      * @param root Parent: The base class for all the nodes
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
         pfPasswordReg.textProperty().addListener(this::onTextChanged);
         pfSafetyPassword.textProperty().addListener(this::onTextChanged);
         
         txtUsername.focusedProperty().addListener(this::onFocusChanged);
         txtEmail.focusedProperty().addListener(this::onFocusChanged);
         txtFullName.focusedProperty().addListener(this::onFocusChanged);
         pfPasswordReg.focusedProperty().addListener(this::onFocusChanged);
         pfSafetyPassword.focusedProperty().addListener(this::onFocusChanged);
         
         
         //Shows primary window
          stage.show();
         
     }
     
    /**
     * Action event handler for the button Register in the UI, this method creates
     * an user with the data the user has introduces and passes it to the logic 
     * @param event ActionEvent: represent an action
     */
     @FXML
    private void register(ActionEvent event) throws NotAvailableConnectionsException, ServerNotAvailableException, RegisterFailedException, IOException {
        try{
            //Creates an User with the user's inserted data
            User user = new User(txtUsername.getText(),txtEmail.getText(),
                    txtFullName.getText(),pfPasswordReg.getText());
            //Sends the user to the logic
            User dbUser=logicManager.register(user);
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/signupsigninuidesktop/ui/fxml/UILogged.fxml"));
            Parent root = loader.load();
            //Get controller from the loader
            UILoggedFXMLController loggedController = loader.getController();
            /*Set a reference in the controller 
                for the UILogin view for the logic manager object           
            */
            loggedController.setLogicManager(logicManager);
            //Send the user to the controller
            loggedController.setUser(user);
            //Initialize the primary stage of the application
            loggedController.initStage(root);
            
            stage.hide();
        } catch(LoginEmailExistException leee){
            LOGGER.severe("Error. El usuario y el email ya existen.");
            txtUsername.setStyle("-fx-border-color: red");
            lblUsernameError.setText("Error. El usuario ya existe");
            txtEmail.setStyle("-fx-border-color: red");
            lblEmailError.setText("Error. El email ya existe");
           
        
        } catch(LoginExistsException lee){
            LOGGER.severe("Error. El usuario ya existe.");
            txtUsername.setStyle("-fx-border-color: red");
            lblUsernameError.setText("Error. El usuario ya existe");
           
        } catch(EmailExistsException eee){
            LOGGER.severe("Error.Incorrect password.");
            txtEmail.setStyle("-fx-border-color: red");
            lblEmailError.setText("Error. El email ya existe");
            
        } catch(GenericException e){
            LOGGER.severe(e.getMessage());
            showErrorAlert("Error en el inicio de sesión.");
        }
    }
    
    /**
     * Action event handler for the button Back in the UI. 
     * @param event ActionEvent: represent an action
     */
    @FXML
    private void returnToLogin(ActionEvent event) {
        stage.close();
    }
    
    /**
     * Action event handler for the button Exit in the UI. 
     * @param event ActionEvent: represent an action
     */
    @FXML
    private void exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setContentText("¿Desea salir de la aplicación?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        }

    }
    
    /**
     * The operations that will be done at the showing at the application
     * @param event Event: related to window showing/hiding actions.
     */
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
     * This method will check everytime that any text is modified, 
     * If any field is empty or incorrect the register button will be disabled
     * When all the fields are correct the button will be enabled
     *
     * @param observable The value being observed.
     * @param oldValue The old value of the observable.
     * @param newValue The new value of the observable.
     */
    private void onTextChanged(ObservableValue observable,
            String oldValue,
            String newValue){
        if(this.txtUsername.getText().trim().equals("")||
                this.txtEmail.getText().trim().equals("")||
                this.txtFullName.getText().trim().equals("")||
                this.pfPasswordReg.getText().trim().equals("")||
                this.pfSafetyPassword.getText().trim().equals("")){
                
            
                //if any field is empty the button is disabled
                btnRegister.setDisable(true);
        }else{
            /*If the fields have the correct length, password are the same
            * and email format is correct, the button will be enabled otherwise
            * it won't be.
            */
            if(checkUsernametc()&&checkEmailtc()&&checkFullNametc()
                    &&checkPasswordtc()&&checkSafetyPasswordtc()){
                btnRegister.setDisable(false);
            }else{
                btnRegister.setDisable(true);
            }
        }         
      }
    
    /**
     * This method checks the text field when the user changes the focus, checks
     * the minimum length and maximun length, the email format and if the passwords 
     * are equals
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
                }else if(field==pfPasswordReg){
                    checkPassword();
                }else if(field==pfSafetyPassword){
                    checkSafetyPassword();
                }
            }
        }
    }
    
    /**
     * Checks if the username fullfills the minimum and maximun length, if it doesn't
     * shows an error in a label and the text field turns red, until the user 
     * corrects it.
     */
    private void checkUsername(){
        if(txtUsername.getText().trim().length()<userPasswordMinLength||
                txtUsername.getText().trim().length()>userPasswordMaxLength){
            lblUsernameError.setText("El usuario "
                                + "debe contener entre 8 y 30 caracteres.");
            txtUsername.setStyle("-fx-border-color: red");
            
        }else{
            lblUsernameError.setText("");
            txtUsername.setStyle("");
            
        }
    }
    
    /**
     * Checks if the full name of the user fullfills the minimum and maximun length, 
     * if it doesn't, shows an error in a label and the text field turns red, until the user 
     * corrects it.
     */
    private void checkFullName() {
        if(txtFullName.getText().trim().length()<fullNameMinLength||txtFullName.getText().trim().length()>fullNameMaxLength){
            lblFullnameError.setText("El nombre "
                                + "debe contener entre 8 y 50 caracteres.");
            txtFullName.setStyle("-fx-border-color: red");
            
        }else{
            lblFullnameError.setText("");
            txtFullName.setStyle("");
            
        }
    }

    /**
     * Checks if the email of the user fullfills the email format, if it doesn't shows 
     * an error in a label and the text field turns red, until the user 
     * corrects it.
     */
    private void checkEmail() {
        if(!txtEmail.getText().matches("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$")){
            lblEmailError.setText("Email no válido");
            txtEmail.setStyle("-fx-border-color: red");
        }else {
            lblEmailError.setText("");
            txtEmail.setStyle("");
            
        }
    }
    
    /**
     * Checks if the password of the user fullfills the minimum and maximun length, 
     * if don't shows an error in a label and the text field turns red, until the user 
     * corrects it.
     */
    private void checkPassword() {
        checkSafetyPassword();
        if(pfPasswordReg.getText().trim().length()<userPasswordMinLength||
                pfPasswordReg.getText().trim().length()>userPasswordMaxLength){
            lblPasswordError.setText("La "
                                + "debe contener entre 8 y 30 caracteres.");
            pfPasswordReg.setStyle("-fx-border-color: red");
            
        }else{
            lblPasswordError.setText("");
            pfPasswordReg.setStyle("");
            
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
            lblSafetyPasswordError.setText("La contraseña "
                                + "debe contener entre 8 y 30 caracteres.");
            pfSafetyPassword.setStyle("-fx-border-color: red");
                      
        }else if (!pfPasswordReg.getText().equals(pfSafetyPassword.getText())){
            lblSafetyPasswordError.setText("Las contraseñas no coinciden");
            pfSafetyPassword.setStyle("-fx-border-color: red");
               
        }else{
            lblSafetyPasswordError.setText("");
            pfSafetyPassword.setStyle("");
            
        }
        }
    }
    
    /**
     * Checks if the username of the user fullfills the minimum and maximun length
     * when the text change (onTextChange method)
     * @return Boolean: If the text in the field fullfills the format, returns true
     */
    private boolean checkUsernametc(){
         return(txtUsername.getText().trim().length()>=userPasswordMinLength&&
                 txtUsername.getText().trim().length()<=userPasswordMaxLength); 
        
    }
    
    /**
     * Checks if the full name of the user fullfills the minimum and maximun length
     * when the text change (onTextChange method)
     * @return Boolean: If the text in the field fullfills the format, returns true
     */
    private boolean checkFullNametc() {
         return (txtFullName.getText().trim().length()>=fullNameMinLength&&
                 txtFullName.getText().trim().length()<=fullNameMaxLength); 
        
    }
    
    /**
     * Checks if the email of the user fullfills the email format
     * when the text change (onTextChange method)
     * @return Boolean: If the text in the field fullfills the format, returns true
     */
    private boolean checkEmailtc() {
         return (txtEmail.getText().matches("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$")&&
                 txtEmail.getText().trim().length()>=userPasswordMinLength&&
                 txtEmail.getText().trim().length()<=userPasswordMaxLength); 
        
    }

    /**
     * Checks if the password of the user fullfills the minimum and maximun length
     * when the text change (onTextChange method)
     * @return Boolean: If the text in the field fullfills the format, returns true
     */
    private boolean checkPasswordtc() {
         return (pfPasswordReg.getText().trim().length()>=userPasswordMinLength&&
                 pfPasswordReg.getText().trim().length()<=userPasswordMaxLength); 
        
    }
    
    /**
     * Checks if the password of the user fullfills the minimum and maximun length
     * and its equals to the previous password when the text change (onTextChange method)
     * @return Boolean: If the text in the field fullfills the format, returns true
     */
    private boolean checkSafetyPasswordtc() {
         return (pfSafetyPassword.getText().trim().length()>=userPasswordMinLength&&
                    pfSafetyPassword.getText().trim().length()<=userPasswordMaxLength&&
                    pfSafetyPassword.getText().equals(pfPasswordReg.getText())); 
        
    }
}

