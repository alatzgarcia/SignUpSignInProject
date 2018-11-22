/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.ui.controller;

import javafx.scene.Node;
import javafx.stage.Stage;
import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.api.FxAssert;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.testfx.toolkit.PrimaryStageApplication;
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
     * Shows the Logged window.
     */
    @Ignore
    @Test
    public void test1_initStage(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        clickOn("#btnLogin");
        
    }
    
    /**
     * Clicks the "LogOut" button and then clicks the "OK" button.
     */
    @Ignore
   @Test
    public void test2_Disconnect(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        clickOn("#btnLogin");
        clickOn("#btnLogout");
        clickOn("Aceptar");
        verifyThat("#loginPane", isVisible());
    }
     
    /**
     * Clicks the "Return to Login" and then click the "OK" button.
     */
     
    @Test
    public void test3_ReturnLogin(){

        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        clickOn("#btnLogin");
        clickOn("#btnReturnLogin");
        clickOn("Aceptar");
        verifyThat("#loginPane", isVisible());
        
    }
    
    /**
     * Clicks the "LogOut" button and then clicks the "CANCEL" button.
     */
    
    @Ignore 
    @Test
    public void test4_LogOutCancel(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        clickOn("#btnLogin");
        clickOn("#btnLogout");
        clickOn("Cancelar");
        
    }
    
    /**
     *  Clicks the "Return to Login" and then click the "OK" button.
     */
     @Ignore 
    @Test
    public void test5_ReturnLoginCancel(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        clickOn("#btnLogin");
        clickOn("#btnReturnLogin");
        clickOn("Cancelar");
        
    }
   
    /**
     * Clicks the FILE menu and click in the disconnect button.
     */
     @Ignore 
    @Test
    public void test6_MenuDisconect(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        clickOn("#btnLogin");
        clickOn("File");
        clickOn("Desconectar");
    
        
    }
      /**
     * Clicks the FILE menu and click in the exit button.
     */
     @Ignore 
    @Test
    public void test7_MenuExit(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        clickOn("#btnLogin");
        clickOn("File");
        clickOn("Desconectar");
    
        
    }

    
    
       
}