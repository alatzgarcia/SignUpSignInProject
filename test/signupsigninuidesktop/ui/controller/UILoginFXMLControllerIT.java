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
    }
    
    @Test
    public void test2_BtnLoginEnabled(){
        clickOn("#txtUsername");
        write("loginName1");
        clickOn("#pfPassword");
        write("password1");
        verifyThat("#btnLogin", isEnabled());
    }
}
