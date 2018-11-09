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
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.*;
import static org.testfx.matcher.control.LabeledMatchers.hasText;


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
   
  
   /*
   @Test
   public void test1_testInitStage(){
      clickOn("#hlRegister");
      clickOn("#txtUsername");
      verifyThat("#txtUsername", isFocused());
      verifyThat("#txtUsername", isEnabled());
      verifyThat("#txtFullName", isEnabled());
      verifyThat("#txtEmail", isEnabled());
      verifyThat("#pfPassword", isEnabled());
      verifyThat("#pfSafetyPassword", isEnabled());
      verifyThat("#btnRegister",isDisabled());
      verifyThat("#btnExit", isEnabled());
      verifyThat("#btnBack", isEnabled());
       
   }
   
   @Test
   public void test2_ButtonRegisterEnabled(){
      clickOn("#hlRegister");
      clickOn("#txtUsername");
      write("loginName1");
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
   public void test3_ButtonRegisterDisabled(){
       clickOn("#hlRegister");
         clickOn("#txtFullName");
         write("Nombre Completo");
         clickOn("#txtEmail");
         write("email@gmail.com");
         clickOn("#pfPassword");
         write("password");
         clickOn("#pfSafetyPassword");
         write("password");
         verifyThat("#btnRegister", isDisabled());
   }
    @Test
     public void test4_ButtonRegisterDisabled(){
         clickOn("#hlRegister");
         clickOn("#txtUsername");
         write("loginName1");
         clickOn("#txtEmail");
         write("email@gmail.com");
         clickOn("#pfPassword");
         write("password");
         clickOn("#pfSafetyPassword");
         write("password");
         verifyThat("#btnRegister", isDisabled());
   }
      @Test
     public void test5_ButtonRegisterDisabled(){
         clickOn("#hlRegister");
         clickOn("#txtUsername");
         write("loginName1");
         clickOn("#txtFullName");
         write("Nombre Completo");
         clickOn("#pfPassword");
         write("password");
         clickOn("#pfSafetyPassword");
         write("password");
         verifyThat("#btnRegister", isDisabled());
   }
    @Test  
    public void test6_ButtonRegisterDisabled(){
         clickOn("#hlRegister");
         clickOn("#txtUsername");
         write("loginName1");
         clickOn("#txtFullName");
         write("Nombre Completo");
         clickOn("#txtEmail");
         write("email@gmail.com");
         clickOn("#pfSafetyPassword");
         write("password");
         verifyThat("#btnRegister", isDisabled());
   }
    @Test   
    public void test7_ButtonRegisterDisabled(){
         clickOn("#hlRegister");
         clickOn("#txtUsername");
         write("loginName1");
         clickOn("#txtFullName");
         write("Nombre Completo");
         clickOn("#txtEmail");
         write("email@gmail.com");
         clickOn("#pfPassword");
         write("password");
         verifyThat("#btnRegister", isDisabled());
   }
   
   @Test
   public void test8_SafetyPasswordNotEquals(){
       clickOn("#hlRegister");
         clickOn("#txtUsername");
         write("loginName1");
         clickOn("#txtFullName");
         write("Nombre Completo");
         clickOn("#txtEmail");
         write("email@gmail.com");
         clickOn("#pfPassword");
         write("password");
         clickOn("#pfSafetyPassword");
         write("password2");
         verifyThat("#btnRegister", isDisabled());
   }
   
      @Test
   public void test9_MaxLenghtValidation(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#txtFullName");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#txtEmail");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#pfPassword");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#pfSafetyPassword");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       verifyThat("#btnRegister", isDisabled());
   }
    
   @Test
   public void test10_MaxLenghtValidation(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("aaaaaaaaaaaaa");
       clickOn("#txtFullName");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#txtEmail");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#pfPassword");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#pfSafetyPassword");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       verifyThat("#btnRegister", isDisabled());
   }
   
   @Test
   public void test11_MaxLenghtValidation(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#txtFullName");
       write("aaaaaaaaaaa");
       clickOn("#txtEmail");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#pfPassword");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#pfSafetyPassword");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       verifyThat("#btnRegister", isDisabled());
   }
   @Test
   public void test12_MaxLenghtValidation(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#txtFullName");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#txtEmail");
       write("email@gmail.com");
       clickOn("#pfPassword");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#pfSafetyPassword");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       verifyThat("#btnRegister", isDisabled());
   }
   @Test
   public void test13_MaxLenghtValidation(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#txtFullName");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#txtEmail");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#pfPassword");
       write("password");
       clickOn("#pfSafetyPassword");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       verifyThat("#btnRegister", isDisabled());
   }
   @Test
   public void test14_MaxLenghtValidation(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#txtFullName");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#txtEmail");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#pfPassword");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#pfSafetyPassword");
       write("password");
       verifyThat("#btnRegister", isDisabled());
   }
   
     @Test
   public void test15_MinLenghtValidation(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("aa");
       clickOn("#txtFullName");
       write("aa");
       clickOn("#txtEmail");
       write("aa");
       clickOn("#pfPassword");
       write("aa");
       clickOn("#pfSafetyPassword");
       write("aa");
       verifyThat("#btnRegister", isDisabled());
   }
    
   @Test
   public void test16_MinLenghtValidation(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("aaaaaaaaaaaaa");
       clickOn("#txtFullName");
       write("aa");
       clickOn("#txtEmail");
       write("aa");
       clickOn("#pfPassword");
       write("aa");
       clickOn("#pfSafetyPassword");
       write("aa");
       verifyThat("#btnRegister", isDisabled());
   }
   
   @Test
   public void test17_MinLenghtValidation(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("aa");
       clickOn("#txtFullName");
       write("aaaaaaaaaaa");
       clickOn("#txtEmail");
       write("aa");
       clickOn("#pfPassword");
       write("aa");
       clickOn("#pfSafetyPassword");
       write("aa");
       verifyThat("#btnRegister", isDisabled());
   }
   @Test
   public void test18_MinLenghtValidation(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("aa");
       clickOn("#txtFullName");
       write("aa");
       clickOn("#txtEmail");
       write("email@gmail.com");
       clickOn("#pfPassword");
       write("aa");
       clickOn("#pfSafetyPassword");
       write("aa");
       verifyThat("#btnRegister", isDisabled());
   }
   @Test
   public void test19_MinLenghtValidation(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("aa");
       clickOn("#txtFullName");
       write("aa");
       clickOn("#txtEmail");
       write("aa");
       clickOn("#pfPassword");
       write("password");
       clickOn("#pfSafetyPassword");
       write("aa");
       verifyThat("#btnRegister", isDisabled());
   }
   @Test
   public void test20_MinLenghtValidation(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("aa");
       clickOn("#txtFullName");
       write("aa");
       clickOn("#txtEmail");
       write("aa");
       clickOn("#pfPassword");
       write("aa");
       clickOn("#pfSafetyPassword");
       write("password");
       verifyThat("#btnRegister", isDisabled());
   }
   @Test
   public void test21_CheckEnableAndDisable(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("username");
       clickOn("#txtFullName");
       write("fullnamee");
       clickOn("#txtEmail");
       write("email@gmail.com");
       clickOn("#pfPassword");
       write("password");
       clickOn("#pfSafetyPassword");
       write("password");
       sleep(1000);
       verifyThat("#btnRegister", isEnabled());
       clickOn("#txtUsername");
       write("a");
       verifyThat("#btnRegister", isDisabled());
   }
   @Test
   public void test22_CheckEnableAndDisable(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("username");
       clickOn("#txtFullName");
       write("fullnamee");
       clickOn("#txtEmail");
       write("email@gmail.com");
       clickOn("#pfPassword");
       write("password");
       clickOn("#pfSafetyPassword");
       write("password");
       sleep(1000);
       verifyThat("#btnRegister", isEnabled());
       clickOn("#txtFullName");
       write("a");
       verifyThat("#btnRegister", isDisabled());
   }
   @Test
   public void test23_CheckEnableAndDisable(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("username");
       clickOn("#txtFullName");
       write("fullnamee");
       clickOn("#txtEmail");
       write("email@gmail.com");
       clickOn("#pfPassword");
       write("password");
       clickOn("#pfSafetyPassword");
       write("password");
       sleep(1000);
       verifyThat("#btnRegister", isEnabled());
       clickOn("#txtEmail");
       write("a");
       verifyThat("#btnRegister", isDisabled());
   }
   @Test
   public void test24_CheckEnableAndDisable(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("username");
       clickOn("#txtFullName");
       write("fullnamee");
       clickOn("#txtEmail");
       write("email@gmail.com");
       clickOn("#pfPassword");
       write("password");
       clickOn("#pfSafetyPassword");
       write("password");
       sleep(1000);
       verifyThat("#btnRegister", isEnabled());
       clickOn("#pfPassword");
       write("a");
       verifyThat("#btnRegister", isDisabled());
   }
   @Test
   public void test25_CheckEnableAndDisable(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("username");
       clickOn("#txtFullName");
       write("fullnamee");
       clickOn("#txtEmail");
       write("email@gmail.com");
       clickOn("#pfPassword");
       write("password");
       clickOn("#pfSafetyPassword");
       write("password");
       sleep(1000);
       verifyThat("#btnRegister", isEnabled());
       clickOn("#pfSafetyPassword");
       write("a");
       verifyThat("#btnRegister", isDisabled());
   }
*/
   @Test
   public void test27_btnBackEnabledAndWork(){
       clickOn("#hlRegister");
       verifyThat("#btnBack", isEnabled());
       clickOn("#btnBack");
       
   }
   @Test
   public void test26_btnExitEnabledAndWork(){
       clickOn("#hlRegister");
       verifyThat("#btnExit", isEnabled());
       clickOn("#btnExit");
       
   }
   
}
