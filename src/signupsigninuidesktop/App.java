package signupsigninuidesktop;


import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import signupsigninuidesktop.logic.ILogic;
import signupsigninuidesktop.logic.ILogicImplementationFactory;
import signupsigninuidesktop.ui.controller.UILoginFXMLController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Application class for the SignUpSignIn application
 * @author Alatz
 */
public class App extends Application {
    private static final Logger LOGGER = Logger.getLogger("signupsigninuidesktop.App");
    
    /**
     * Main method of the client application
     * @param args arguments that the application receives on its
     * initialization
     */
    public static void main(String[] args){
        launch(args);
    }

    /**
     * Method start for the JavaFX Application
     * @param primaryStage the first/primary stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        try{
            //Get the logic manager object for the initial stage
            ILogic logicManager = ILogicImplementationFactory.getLogic();
            
            //Load the fxml file
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/signupsigninuidesktop/ui/fxml/UILogin.fxml"));
            Parent root = loader.load();
            //Get controller from the loader
            UILoginFXMLController loginController = loader.getController();
            /*Set a reference in the controller 
                for the UILogin view for the logic manager object           
            */
            loginController.setLogicManager(logicManager);
            //Set a reference for Stage in the UILogin view controller
            loginController.setStage(primaryStage);
            //Initialize the primary stage of the application
            loginController.initStage(root);
        }catch(Exception e){
            LOGGER.severe(e.getMessage());
        }  
    }
}

