/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.ui.controller;

import javafx.stage.Stage;
import org.junit.Test;

import javafx.stage.Stage;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.*;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.*;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import signupsigninuidesktop.App;


import signupsigninuidesktop.App;

/**
 *
 * @author Nerea Jimenez y Diego Travesí
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
   
   
   /**
    * Checks the fields and the buttons when the window opens
    */
   @Test
   public void test01_testInitStage(){
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
   
   /**
    * Checks if the button register is enabled
    */
   @Test
   public void test02_ButtonRegisterEnabled(){
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
   
   /**
    * Checks if the button register is disabled if one field is missing and the rest
    * its completed with the min and man length
    */
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
   
   /**
    * Checks if the button register is disabled if one field is missing and the rest
    * its completed with the min and man length
    */
   @Test
   public void test04_ButtonRegisterDisabled(){
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
   
   /**
    * Checks if the button register is disabled if one field is missing and the rest
    * its completed with the min and man length
    */
      @Test
     public void test05_ButtonRegisterDisabled(){
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
     /**
    * Checks if the button register is disabled if one field is missing and the rest
    * its completed with the min and man length
    */
    @Test  
    public void test06_ButtonRegisterDisabled(){
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
    
    /**
    * Checks if the button register is disabled if one field is missing and the rest
    * its completed with the min and man length
    */
    @Test   
    public void test07_ButtonRegisterDisabled(){
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
    
   /**
    * Checks if the button disbles if the password aren't equals
    */
   @Test
   public void test08_SafetyPasswordNotEquals(){
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
   
   /**
    * Validates if the button register disables with the max length 
    */
   @Test
   public void test09_MaxLenghtValidation(){
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
    
   /**
    * Validates if the button register disables with the max length 
    */
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
   /**
    * Validates if the button register disables with the max length 
    */
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
   /**
    * Validates if the button register disables with the max length 
    */
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
   /**
    * Validates if the button register disables with the max length 
    */
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
   /**
    * Validates if the button register disables with the max length 
    */
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
   /**
    * Validates if the button register disables with the min length 
    */
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
   
    /**
    * Validates if the button register disables with the min length 
    */
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
   
   /**
    * Validates if the button register disables with the min length 
    */
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
   
   /**
    * Validates if the button register disables with the min length 
    */
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
   /**
    * Validates if the button register disables with the min length 
    */
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
   /**
    * Validates if the button register disables with the min length 
    */
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
   
   /**
    * Checks when everything is correct the button is enabled, then erases one field and checks if the
    * button is disabled
    */
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
       verifyThat("#btnRegister", isEnabled());
       clickOn("#txtUsername");
       eraseText(20);
       write("a");
       verifyThat("#btnRegister", isDisabled());
   }
   
   /**
    * Checks when everything is correct the button is enabled, then erases one field and checks if the
    * button is disabled
    */
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
       //sleep(1000);
       //verifyThat("#btnRegister", isEnabled());
       clickOn("#txtFullName");
       eraseText(20);
       write("a");
       verifyThat("#btnRegister", isDisabled());
   }
   
   /**
    * Checks when everything is correct the button is enabled, then erases one field and checks if the
    * button is disabled
    */
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
       //sleep(1000);
       //verifyThat("#btnRegister", isEnabled());
       clickOn("#txtEmail");
       eraseText(50);
       write("a");
       verifyThat("#btnRegister", isDisabled());
   }
   
   /**
    * Checks when everything is correct the button is enabled, then erases one field and checks if the
    * button is disabled
    */
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
       //verifyThat("#btnRegister", isEnabled());
       clickOn("#pfPassword");
       eraseText(20);
       write("a");
       verifyThat("#btnRegister", isDisabled());
   }
   
   
   /**
    * Checks when everything is correct the button is enabled, then erases one field and checks if the
    * button is disabled
    */
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
      // sleep(1000);
       verifyThat("#btnRegister", isEnabled());
       clickOn("#pfSafetyPassword");
       eraseText(20);
       write("a");
       verifyThat("#btnRegister", isDisabled());
   }
   
   /**
    * Checks the button register when everything is correct but one field exceeds
    * the max length
    */
   @Test
   public void test27_oneMaxLength(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#txtFullName");
       write("Full Name");
       clickOn("#txtEmail");
       write("email@gmail.com");
       clickOn("#pfPassword");
       write("password");
       clickOn("#pfSafetyPassword");
       write("password");
       verifyThat("#btnRegister", isDisabled());
   }
   
   /**
    * Checks the button register when everything is correct but one field exceeds
    * the max length
    */
   @Test
   public void test28_oneMaxLength(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("Login Name");
       clickOn("#txtFullName");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#txtEmail");
       write("email@gmail.com");
       clickOn("#pfPassword");
       write("password");
       clickOn("#pfSafetyPassword");
       write("password");
       verifyThat("#btnRegister", isDisabled());
   }
   /**
    * Checks the button register when everything is correct but one field exceeds
    * the max length
    */
   @Test
   public void test29_oneMaxLength(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("Login Name");
       clickOn("#txtFullName");
       write("Full Name");
       clickOn("#txtEmail");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaemail@gmail.com");
       clickOn("#pfPassword");
       write("password");
       clickOn("#pfSafetyPassword");
       write("password");
       verifyThat("#btnRegister", isDisabled());
   }
   /**
    * Checks the button register when everything is correct but one field exceeds
    * the max length
    */
   @Test
   public void test30_oneMaxLength(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("Login Name");
       clickOn("#txtFullName");
       write("Full Name");
       clickOn("#txtEmail");
       write("email@gmail.com");
       clickOn("#pfPassword");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       clickOn("#pfSafetyPassword");
       write("password");
       verifyThat("#btnRegister", isDisabled());
   }
   /**
    * Checks the button register when everything is correct but one field exceeds
    * the max length
    */
   @Test
   public void test31_oneMaxLength(){
       clickOn("#hlRegister");
       clickOn("#txtUsername");
       write("Login Name");
       clickOn("#txtFullName");
       write("Full Name");
       clickOn("#txtEmail");
       write("email@gmail.com");
       clickOn("#pfPassword");
       write("password");
       clickOn("#pfSafetyPassword");
       write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
       verifyThat("#btnRegister", isDisabled());
   }
   
   
   /**
    * This test methods checks the error of the labels
    */   
   @Test
   public void test32_checkErrorAtFocus(){
       clickOn("#hlRegister");
       write("a");
       clickOn("#txtFullName");
       verifyThat("#txtUsername", isNotFocused());
       verifyThat("#lblUsernameError", org.testfx.matcher.control.LabeledMatchers.hasText("La longitud del nombre de usuario no es adecuada"));
       write("a");
       clickOn("#txtEmail");
       verifyThat("#txtFullName", isNotFocused());
       verifyThat("#lblFullnameError", org.testfx.matcher.control.LabeledMatchers.hasText("La longitud del nombre no es adecuada"));
       write("a");
       clickOn("#pfPassword");
       verifyThat("#txtEmail", isNotFocused());
       verifyThat("#lblEmailError", org.testfx.matcher.control.LabeledMatchers.hasText("Email no válido"));
       write("a");
       clickOn("#pfSafetyPassword");
       verifyThat("#pfPassword", isNotFocused());
       verifyThat("#lblPasswordError", org.testfx.matcher.control.LabeledMatchers.hasText("La longitud de la contraseña no es adecuada"));
       write("a");
       clickOn("#pfPassword");
       eraseText(1);
       write("12345678");
       verifyThat("#pfSafetyPassword", isNotFocused());
       verifyThat("#lblSafetyPasswordError", org.testfx.matcher.control.LabeledMatchers.hasText("La longitud de la contraseña no es adecuada"));
       clickOn("#pfSafetyPassword");
       eraseText(8);
       write("123456789");
       clickOn("#txtFullName");
       verifyThat("#lblSafetyPasswordError", org.testfx.matcher.control.LabeledMatchers.hasText("Las contraseñas no coinciden"));
       
       }
   /**
    * Checks the email format
    */
   @Test
   public void test33_checkEmail(){
      clickOn("#hlRegister");
      clickOn("#txtUsername");
      write("Login Name");
      clickOn("#txtFullName");
      write("Full Name");
      clickOn("#txtEmail");
      write("email@gmail.com");
      clickOn("#pfPassword");
      write("password");
      clickOn("#pfSafetyPassword");
      write("password");
      verifyThat("#btnRegister", isEnabled());
      clickOn("#txtEmail");
      eraseText(20);
      write("@gmail.com");
      verifyThat("#btnRegister", isDisabled());
      eraseText(20);
      write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@gmail.com");
      verifyThat("#btnRegister", isDisabled());
      eraseText(90);
      write("email@.com");
      verifyThat("#btnRegister", isDisabled());
      eraseText(20);
      write("email@gmail.commm");
      verifyThat("#btnRegister", isDisabled());
      eraseText(20);
      write("email@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.com");
      verifyThat("#btnRegister", isDisabled());
      eraseText(50);
      write("email@gmail.com");
      verifyThat("#btnRegister", isEnabled());
      
   }
   
   /**
    * Checks if the back button is enabled and clicks it
    */
   @Test
   public void test34_btnBackEnabledAndWork(){
       clickOn("#hlRegister");
       verifyThat("#btnBack", isEnabled());
       clickOn("#btnBack");
       
   }
   /**
    * Checks the exit Button and clicks it
    */
   @Test
   public void test35_btnExitEnabledAndWork(){
       clickOn("#hlRegister");
       verifyThat("#btnExit", isEnabled());
       clickOn("#btnExit");
       
   }
   
}