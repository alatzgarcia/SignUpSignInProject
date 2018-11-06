/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.ui.controller;

import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import signupsigninuidesktop.App;




/**
 *
 * @author Diego
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UIRegisterFXMLControllerIT extends ApplicationTest{
    
    @Override
    public void start(Stage stage) throws Exception{
        new App().start(stage);
    }
    
    @Test
    public void test1_initStage(){
        //pulso en el campo del usuario
        clickOn("#txtUsername");
        verifyThat("btn");
    }
}
