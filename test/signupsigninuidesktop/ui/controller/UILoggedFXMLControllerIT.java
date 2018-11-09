/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.ui.controller;

import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import signupsigninuidesktop.App;

/**
 *
 * @author Iker
 */
public class UILoggedFXMLControllerIT extends ApplicationTest {
    
    @Override public void start(Stage primaryStage) throws Exception{
        new App().start(primaryStage);
    }
    
    /**
     * Load the Login window, to connect with the Logged window.
     */
    @Before
    public void login(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
    }
    
    /**
     * Shows the window with the two buttons enabled.
     */
    @Test
    public void test1_initStage(){
       
        verifyThat("#btnLogout", isEnabled());
        verifyThat("#menuBar", isEnabled()); 
    }
    
    /**
     * Clicks the "File" menu and then clicks the "Desconectar" button.
     */
   @Test
    public void test2_menuDisconnect(){
        clickOn("#menuBar");
        clickOn("#menuFile");
        clickOn("#mItLogout");
    }
    
       
    
    /**
     * Clicks the "File" menu and then clicks the "Salir" button.
     */
    @Test
    public void test3_menuExit(){
        clickOn("#menuBar");
        clickOn("#menuFile");
        clickOn("#mItExit");
        
    }
    
    /**
     * Clicks the LogOut button and clicks in "OK" option.
     */
    @Test
    public void test4_logOutExit(){
       //verifyThat("#loggedPane", isVisible());
        clickOn("#btnLogout");
        clickOn("#OK");
    }
    
    /**
     * Clicks the LogOut button and clicks in "OK" option.
     */
    @Test
    public void test5_logOutCancel(){
        
        clickOn("btnLogOut");
        clickOn("#NO");
    }
}