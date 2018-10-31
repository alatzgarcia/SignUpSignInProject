/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.ui.controller;

import javafx.stage.Stage;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.*;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import signupsigninuidesktop.App;

/**
 *
 * @author Alatz
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UILoginFXMLControllerIT extends ApplicationTest{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        new App().start(primaryStage);
    }
    
    @Test
    public void test1_InitStage(){
        verifyThat("#txtUsername", hasText(""));
        verifyThat("#pfPassword", hasText(""));
        verifyThat("#txtUsername", isEnabled());
        verifyThat("#pfPassword", isEnabled());
        verifyThat("#btnLogin", isDisabled());
        verifyThat("#btnExit", isEnabled()); 
        verifyThat("#txtUsername", isFocused());
        verifyThat("#loginPane", isVisible());
    }
    
    @Test
    public void test2_BtnLoginEnabled(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        verifyThat("#btnLogin", isEnabled());
    }
    
    //--TOFIX --> Separar en más métodos dependiendo el fallo
    @Test
    public void test3_BtnLoginDisabled(){
        clickOn("#txtUsername");
        write("login");
        clickOn("#pfPassword");
        write("passwd");
        verifyThat("#btnLogin", isDisabled());
    }
    
    //--TOFIX --> Codificar método/métodos de evento de cambio de foco
    
    //--TOFIX --> Implement when project is 100% functional with DB
    // --> Change method name
    @Test
    public void test4_LoginError(){
        clickOn("#txtUsername");
        write("lasfjafsa");
        clickOn("#pfPassword");
        write("password1");
        verifyThat("#btnLogin", isEnabled());
        clickOn("#btnLogin");
        verifyThat("#loginPane", isVisible());
        //--TOFIX --> Poner texto correcto y correspondiente en ambos verify
        verifyThat("#lblUsernameError", hasText(""));
        verifyThat("#lblPasswordError", hasText(""));
    }
    
    /*//--TOFIX --> Implement when project is 100% functional with DB
    // --> Change method name
    @Test
    public void test5_PasswordError(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("sdlahdhaksdlas");
        verifyThat("#btnLogin", isEnabled());
        clickOn("#btnLogin");
        verifyThat("#loginPane", isVisible());
        //--TOFIX --> Poner texto correcto y correspondiente en ambos verify
        verifyThat("#lblUsernameError", hasText(""));
        verifyThat("#lblPasswordError", hasText(""));
    }
    
    //--TOFIX --> Implement when project is 100% functional with DB
    // --> Change method name
    @Test
    public void test6_LoginPasswordError(){
        
    }*/
    
    @Test
    public void test7_IsLoggedViewVisible(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        verifyThat("#btnLogin", isEnabled());
        clickOn("#btnLogin");
        verifyThat("#loggedPane", isVisible());
        //verifyThat("", isVisible());
    }
    
    @Test 
    public void test8_IsRegisterViewVisible(){
        clickOn("#hlRegister");
        verifyThat("#registerPane", isVisible());
        //verifyThat("", isVisible());
    }
    
}
