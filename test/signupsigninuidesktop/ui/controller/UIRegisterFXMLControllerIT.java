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
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxAssert;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.*;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;


import signupsigninuidesktop.App;

/**
 *
 * @author Nerea Jimenez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UIRegisterFXMLControllerIT extends ApplicationTest {

    /**
     *
     * @param stage
     * @throws Exception
     */
   @Override
   public void start(Stage stage) throws Exception{
       new App().start(stage);
       
   }
   
  
   
   @Test
   public void test1_testInitStage(){
      clickOn("#txtFullName");
      verifyThat("#txtUsername", hasText(""));
      verifyThat("#txtFullName", hasText(""));
      verifyThat("#txtEmail", hasText(""));
      verifyThat("#pfPassword", hasText(""));
      verifyThat("#pfSafetyPassword", hasText(""));
      verifyThat("#btnRegister",isDisabled());
       
   }
   
   @Test
   public void test2_ButtonRegisterEnabled(){
      
      clickOn("#txtUsername");
      write("loginName1");
      verifyThat("#txtUsername", hasText("loginName1"));
      clickOn("#txtFullName");
      write("Nombre Completo");
      clickOn("#txtEmail");
      write("email@gmail.com");
      clickOn("#pfPassword");
      write("password");
      clickOn("#pfSafetyPassword");
      write("password");
      verifyThat("#btnRegister",isEnabled());
   }
   
     @Test
   public void test3_ButtonRegisterEnabled(){
         clickOn("#txtFullName");
         write("Nombre Completo");
         clickOn("#txtEmail");
         write("email@gmail.com");
         clickOn("#pfPassword");
         write("password");
         clickOn("#pfSafetyPassword");
         write("password");
         verifyThat("#btnregister", isEnabled());
   }
    
}
