/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.ui.controller;


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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import signupsigninuidesktop.exceptions.ConfigurationParameterNotFoundException;
import signupsigninutilities.model.User;
import signupsigninuidesktop.exceptions.EmailExistsException;
import signupsigninuidesktop.exceptions.LoginEmailExistException;
import signupsigninuidesktop.exceptions.LoginExistsException;
import signupsigninuidesktop.exceptions.NotAvailableConnectionsException;
import signupsigninuidesktop.exceptions.ServerNotAvailableException;
import static signupsigninuidesktop.ui.controller.GenericController.LOGGER;



/**
 * Controller class for JavaFX view implementation of the Sign in Sign Up
 * application, this controller allows the user to register. The email and
 * user must be unique.
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
     private PasswordField pfPasswordP;
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
     @FXML
     private Button btnBack;
     @FXML
     private Button btnExit;
         
     
     /**
      * This method sets the listeners and shows the window at the start
      * of the program, sets the title ans makes it unresizable.
      * @param root Parent: Parent object
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
         pfPasswordP.textProperty().addListener(this::onTextChanged);
         pfSafetyPassword.textProperty().addListener(this::onTextChanged);
         
         txtUsername.focusedProperty().addListener(this::onFocusChanged);
         txtEmail.focusedProperty().addListener(this::onFocusChanged);
         txtFullName.focusedProperty().addListener(this::onFocusChanged);
         pfPasswordP.focusedProperty().addListener(this::onFocusChanged);
         pfSafetyPassword.focusedProperty().addListener(this::onFocusChanged);
         
         btnRegister.setMnemonicParsing(true);
         btnRegister.setText("_Registrar");
         btnBack.setMnemonicParsing(true);
         btnBack.setText("_Atras");
         btnExit.setMnemonicParsing(true);
         btnExit.setText("_Salir");
         //Shows primary window
          stage.show();
         
     }
     
    /**
     * Action event handler for the button Register in the UI, this method creates
     * an user with the data the user has introduce and passes it to the logic 
     * @param event The ActionEvent
     */
     @FXML
    private void register(ActionEvent event) {
        try{
            //Creates an User with the user's inserted data
            User user = new User(txtUsername.getText(),txtEmail.getText(),
                    txtFullName.getText(),pfPasswordP.getText());
            //Sends the user to the logic
            User dbUser=logicManager.register(user);
            
            if(dbUser !=null){
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
            }
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
            
        }catch(ServerNotAvailableException snae){
            LOGGER.severe(snae.getMessage());
            showErrorAlert("El servidor no está disponible.");
        }catch(ConfigurationParameterNotFoundException cpnfe){
            LOGGER.severe(cpnfe.getMessage());
            showErrorAlert("Error en los parámetros de configuración");
        }catch(NotAvailableConnectionsException nace){
            LOGGER.severe(nace.getMessage());
            showErrorAlert("Error. No hay conexiones disponibles");
        } catch(Exception e){
            LOGGER.severe(e.getMessage());
            showErrorAlert("Error en el inicio de sesión.");
        }
    }
    
    /**
     * Action event handler for the button Back in the UI. 
     * @param event The ActionEvent
     */
    @FXML
    private void returnToLogin(ActionEvent event) {
        previousStage.show();
        stage.close();
    }
    
    /**
     * Action event handler for the button Exit in the UI. 
     * @param event The Action Event
     */
    @FXML
    private void exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesión");
        alert.setContentText("¿Desea cerrar sesion?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()== ButtonType.OK){
             Platform.exit();
        }
        
    }
    
    /**
     * Method for the WindowEvent event. Sets the initial stage of the window
     * when it opens
     * @param event The WindowEvent
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
                this.pfPasswordP.getText().trim().equals("")||
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
                lblUsernameError.setText("");
                lblFullnameError.setText("");
                lblEmailError.setText("");
                lblPasswordError.setText("");
                lblSafetyPasswordError.setText("");
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
                }else if(field==pfPasswordP){
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
                                + "debe contener entre 5 y 50 caracteres.");
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
        if(pfPasswordP.getText().trim().length()<userPasswordMinLength||
                pfPasswordP.getText().trim().length()>userPasswordMaxLength){
            lblPasswordError.setText("La "
                                + "debe contener entre 8 y 30 caracteres.");
            pfPasswordP.setStyle("-fx-border-color: red");
            
        }else{
            lblPasswordError.setText("");
            pfPasswordP.setStyle("");
            
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
                      
        }else if (!pfPasswordP.getText().equals(pfSafetyPassword.getText())){
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
     * @return 
     */
    private boolean checkUsernametc(){
         return(txtUsername.getText().trim().length()>=userPasswordMinLength&&
                 txtUsername.getText().trim().length()<=userPasswordMaxLength); 
        
    }
    
    /**
     * Checks if the full name of the user fullfills the minimum and maximun length
     * when the text change (onTextChange method)
     * @return 
     */
    private boolean checkFullNametc() {
         return (txtFullName.getText().trim().length()>=fullNameMinLength&&
                 txtFullName.getText().trim().length()<=fullNameMaxLength); 
        
    }
    
    /**
     * Checks if the email of the user fullfills the email format
     * when the text change (onTextChange method)
     * @return 
     */
    private boolean checkEmailtc() {
         return (txtEmail.getText().matches("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$")&&
                 txtEmail.getText().trim().length()>=userPasswordMinLength&&
                 txtEmail.getText().trim().length()<=userPasswordMaxLength); 
        
    }

    /**
     * Checks if the password of the user fullfills the minimum and maximun length
     * when the text change (onTextChange method)
     * @return 
     */
    private boolean checkPasswordtc() {
         return (pfPasswordP.getText().trim().length()>=userPasswordMinLength&&
                 pfPasswordP.getText().trim().length()<=userPasswordMaxLength); 
        
    }
    
    /**
     * Checks if the password of the user fullfills the minimum and maximun length
     * and its equals to the previous password when the text change (onTextChange method)
     * @return 
     */
    private boolean checkSafetyPasswordtc() {
         return (pfSafetyPassword.getText().trim().length()>=userPasswordMinLength&&
                    pfSafetyPassword.getText().trim().length()<=userPasswordMaxLength&&
                    pfSafetyPassword.getText().equals(pfPasswordP.getText())); 
        
    }
    
   

}

