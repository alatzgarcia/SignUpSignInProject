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
 * Test class for the UILoginFXMLController class
 * @author Alatz
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UILoginFXMLControllerIT extends ApplicationTest{
    
    /**
     * Method to start the application
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        new App().start(primaryStage);
    }
    
    /**
     * Method to test the init stage of the UILogin
     */
    @Test
    public void test01_InitStage(){
        verifyThat("#txtUsername", hasText(""));
        verifyThat("#pfPassword", hasText(""));
        verifyThat("#txtUsername", isEnabled());
        verifyThat("#pfPassword", isEnabled());
        verifyThat("#btnLogin", isDisabled());
        verifyThat("#btnExit", isEnabled()); 
        verifyThat("#txtUsername", isFocused());
        verifyThat("#loginPane", isVisible());
    }
    
    /**
     * Method to test the button login is enabled when both textfields are
     * correctly filled
     */
    @Test
    public void test02_BtnLoginEnabled(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        verifyThat("#btnLogin", isEnabled());
    }
    
    /**
     * Method to test that the button login is disabled if the login textfield
     * is filled with a shorter text than needed
     */
    @Test
    public void test03_BtnLoginDisabledOnShortLogin(){
        clickOn("#txtUsername");
        write("login");
        clickOn("#pfPassword");
        write("password1");
        verifyThat("#btnLogin", isDisabled());
    }
    
    /**
     * Method to test that the button login is disabled if the login textfield
     * is filled with a longer text than needed
     */
    @Test
    public void test04_BtnLoginDisabledOnLongLogin(){
        clickOn("#txtUsername");
        write("aadadjadapdajdpajdadjasdjadjñadajdasjñdadjasñjkda");
        clickOn("#pfPassword");
        write("password1");
        verifyThat("#btnLogin", isDisabled());
    }
    
    /**
     * Method to test that the button login is disabled if the password
     * passwordfield is filled with a shorter text than needed
     */
    @Test
    public void test05_BtnLoginDisabledOnShortPassword(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("passwd");
        verifyThat("#btnLogin", isDisabled());
    }
    
    /**
     * Method to test that the button login is disabled if the password
     * passwordfield is filled with a larger text than needed
     */
    @Test
    public void test06_BtnLoginDisabledOnLongPassword(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("passwdadaldjadadadpasdjapsdjasdadsadadadadadsad");
        verifyThat("#btnLogin", isDisabled());
    }
    
    /**
     * Method to test that after enabling, the button login gets disabled
     * again when its txtUsername textfield's text goes back to being too
     * short again
     */
    @Test
    public void test07_BtnLoginDisablesOnShortLogin(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        verifyThat("#btnLogin", isEnabled());
        clickOn("#txtUsername");
        eraseText(10);
        write("login"); 
        verifyThat("#btnLogin", isDisabled());
    }
    
    /**
     * Method to test that after enabling, the button login gets disabled
     * again when its txtUsername textfield's text goes back to being too
     * long again
     */
    @Test
    public void test08_BtnLoginDisablesOnLongLogin(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        verifyThat("#btnLogin", isEnabled());
        clickOn("#txtUsername");
        eraseText(10);
        write("login"); 
        verifyThat("#btnLogin", isDisabled());
    }
    
    /**
     * Method to test that after enabling, the button login gets disabled
     * again when its pfPassword passwordfield's text goes back to being
     * too short again
     */
    @Test
    public void test09_BtnLoginDisablesOnShortPassword(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        verifyThat("#btnLogin", isEnabled());
        clickOn("#pfPassword");
        eraseText(10);
        write("passwd");
        verifyThat("#btnLogin", isDisabled());
    }
    
    /**
     * Method to test that after enabling, the button login gets disabled
     * again when its pfPassword passwordfield's text goes back to being
     * too long again
     */
    @Test
    public void test10_BtnLoginDisablesOnLongPassword(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        verifyThat("#btnLogin", isEnabled());
        clickOn("#pfPassword");
        eraseText(10);
        write("passwd");
        verifyThat("#btnLogin", isDisabled());
    }
    
    /**
     * Method to test that the correct message is shown when txtUsername
     * textfield loses the focus with wrong data on it
     */
    @Test
    public void test11_LoginErrorTextAppearsOnFocusChange(){
        clickOn("#txtUsername");
        write("login");
        clickOn("#pfPassword");
        verifyThat("#lblUsernameError", org.testfx.matcher.control.
                LabeledMatchers.hasText("Error. El campo usuario "
                                + "debe contener entre 8 y 30 caracteres."));
    }
    
    /**
     * Method to test that the correct message is shown when pfPassword
     * passwordfield loses the focus with wrong data on it
     */
    @Test
    public void test12_PasswordErrorTextAppearsOnFocusChange(){
        clickOn("#pfPassword");
        write("passwd");
        clickOn("#txtUsername");
        
        verifyThat("#lblPasswordError", org.testfx.matcher.control.
                LabeledMatchers.hasText("Error. El campo contraseña "
                                + "debe contener entre 8 y 30 caracteres."));
    }
    
    /**
     * Method to test that the correct message is shown when login
     * is wrong on login operation
     */
    @Test
    public void test13_LoginError(){
        clickOn("#txtUsername");
        write("lasfjafsa");
        clickOn("#pfPassword");
        write("password1");
        verifyThat("#btnLogin", isEnabled());
        clickOn("#btnLogin");
        verifyThat("#loginPane", isVisible());
        //--TOFIX --> Poner texto correcto y correspondiente en ambos verify
        verifyThat("#lblUsernameError", org.testfx.matcher.control.
                LabeledMatchers.hasText("Error. El usuario introducido"
                + "no existe."));
        verifyThat("#lblPasswordError", org.testfx.matcher.control.
                LabeledMatchers.hasText(""));
    }
    
    /**
     * Method to test that the correct message is shown when password is
     * wrong on login operation
     */
    @Test
    public void test14_PasswordError(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("sdlahdhaksdlas");
        verifyThat("#btnLogin", isEnabled());
        clickOn("#btnLogin");
        verifyThat("#loginPane", isVisible());
        //--TOFIX --> Poner texto correcto y correspondiente en ambos verify
        verifyThat("#lblUsernameError", org.testfx.matcher.control.
                LabeledMatchers.hasText(""));
        verifyThat("#lblPasswordError", org.testfx.matcher.control.
                LabeledMatchers.hasText("Error. La contraseña "
                + "introducida es incorrecta."));
    }
    
    /**
     * Method to test that the correct message is shown when both login and
     * password are wrong on login operation
     */
    @Test
    public void test15_LoginPasswordError(){
        clickOn("#txtUsername");
        write("ajnnsdldaa");
        clickOn("#pfPassword");
        write("asdadadas");
        verifyThat("#btnLogin", isEnabled());
        clickOn("#btnLogin");
        verifyThat("#loginPane", isVisible());
        //--TOFIX --> Poner texto correcto y correspondiente en ambos verify
        verifyThat("#lblUsernameError", org.testfx.matcher.control.
                LabeledMatchers.hasText("Error. El usuario introducido"
                + "no existe."));
        verifyThat("#lblPasswordError", org.testfx.matcher.control.
                LabeledMatchers.hasText("Error. La contraseña "
                + "introducida es incorrecta."));
    }
    
    /**
     * Method to test that the UILogged is visible when the login is correctly
     * made
     */
    @Test
    public void test16_IsLoggedViewVisible(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        verifyThat("#btnLogin", isEnabled());
        clickOn("#btnLogin");
        verifyThat("#loggedPane", isVisible());
    }
    
    /**
     * Method to test that the UIRegister is visible when the 
     * hyperlink is clicked
     */
    @Test 
    public void test17_IsRegisterViewVisible(){
        clickOn("#hlRegister");
        verifyThat("#registerPane", isVisible());
    }
    
}
