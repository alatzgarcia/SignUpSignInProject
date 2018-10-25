package signupsigninuidesktop;


import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import signupsigninuidesktop.logic.ILogic;
import signupsigninuidesktop.logic.ILogicImplementationFactory;
import signupsigninuidesktop.ui.controller.UIRegisterFXMLController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alatz
 */
public class App extends Application {
    private static final Logger LOGGER = Logger.getLogger("signupsigninuidesktop.App");
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
            //Get the logic manager object for the initial stage
            ILogic logicManager = ILogicImplementationFactory.getLogic();
            
            //Load the fxml file
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/signupsigninuidesktop/ui/fxml/UIRegister.fxml"));
            Parent root = loader.load();
            //Get controller from the loader
            UIRegisterFXMLController registerController = loader.getController();
            /*Set a reference in the controller 
                for the UILogin view for the logic manager object           
            */
            registerController.setLogicManager(logicManager);
            //Set a reference for Stage in the UILogin view controller
            registerController.setStage(primaryStage);
            //Initialize the primary stage of the application
            registerController.initStage(root);
            
        }  
    }

