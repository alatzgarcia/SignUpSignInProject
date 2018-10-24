/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.ui.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Alatz
 */
public class UIRegisterFXMLController extends GenericController {
    public void initStage(Parent root){
        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.setResizable(false);
        
        stage.show();
    }
}
