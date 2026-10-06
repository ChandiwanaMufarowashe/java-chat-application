/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

//package Login;

import com.mycompany.chatapp_1.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Administrator
 */
public class Login_Test {
   
  public Login_Test() {
         //assertEquals (excpected Value, actaul Value)
        //expectedValue - what we THINK should happen
        //actaulValue - is what ACTUALLY happens
    }
   
    @Test
    public void testcheckusername_Valid() {
        Login obj2 = new  Login();
   
    //give input
    obj2.setUsername("kyl_1");
    //what is the epected outcome
    boolean expectedValue= true;
    //actaulValue
    boolean actualValue= obj2.checkusername();
    obj2.setName_Surname("Kyle");
    System.out.println("Welcome " + obj2.getName_Surname() +" It is great to see you again");
    //compae expected vs actual
    Assertions.assertEquals(expectedValue, actualValue);
    }
   
      //then you must also create one for when it is not valid
   @Test
   public void testcheckusername_notVaid(){
    Login obj2 = new  Login();
   
    //give input
    obj2.setUsername("kyle!!!!!!!!");
    //what is the epected outcome
    boolean expectedValue= false; //if should be false cause the input does not have an star
    //actaulValue
    boolean actualValue= obj2.checkusername();
    System.out.println("\nUsername is not correctly formatted\n"
                + "Please ensure that your username contains:\n"
                + "- An underscore\n"
                + "- And is no more than 5 characters long\n");
    //compae expected vs actual
    Assertions.assertEquals(expectedValue, actualValue);
   }  
   
   @Test
   public void testcheckusername_True(){
       //Step 1: Create an Object and add an input statement
        Login obj2 = new  Login();
       //give input
        obj2.setUsername("kyl_1");
          //what is the epected outcome
          Assertions.assertTrue(obj2.checkusername());
          }
   @Test
     public void testCheckAccountHolderNme_False(){
      //Step 1: Create an Object and add an input statement
        Login obj2 = new  Login();
       //give input
        obj2.setUsername("kyle!!!!!!!!");
          //what is the epected outcome
          Assertions.assertFalse(obj2.checkusername());
          //It gave us an error because the input is incorrect  
         }
     
    @Test
    public void testcheckPassword_Valid(){
    Login obj2 = new  Login();
   
    //give input
    obj2.setPassword("Ch&&sec@ke99!");
    //what is the epected outcome
    boolean expectedValue= true;
    //actaulValue
    boolean actualValue= obj2.checkPassword();
     System.out.println("Password successfully captureds");
    //compae expected vs actual
    Assertions.assertEquals(expectedValue, actualValue);
   
    }      
    //then you must also create one for when it is not valid
   @Test
   public void testcheckPassword_notVaid(){
    Login obj2 = new  Login();
   
    //give input
    obj2.setPassword("password");
    //what is the epected outcome
    boolean expectedValue= false; //if should be false cause the input does not have an star
    //actaulValue
    boolean actualValue= obj2.checkPassword();
    System.out.println("\nPassword is incorrectly formatted. Please ensure it contains:\n"
                + "- At least 8 characters\n"
                + "- A capital letter\n"
                + "- A lowercase letter\n"
                + "- A number\n"
                + "- A special character\n");
    //compares expected vs actual
    Assertions.assertEquals(expectedValue, actualValue);
   }  
   
   @Test
   public void testcheckPassword_True(){
       //Step 1: Create an Object and add an input statement
        Login obj2 = new  Login();
       //give input
        obj2.setPassword("Ch&&sec@ke99!");
          //what is the epected outcome
          Assertions.assertTrue(obj2.checkPassword());
          }
   @Test
     public void testcheckPassword_False(){
      //Step 1: Create an Object and add an input statement
        Login obj2 = new  Login();
       //give input
        obj2.setPassword("password");
          //what is the epected outcome
          Assertions.assertFalse(obj2.checkPassword());
          //It gave us an error because the input is incorrect  
         }
      @Test
    public void testcheckPhone_Num_Valid(){
    Login obj2 = new  Login();
   
    //give input
    obj2.setPhone_Num("+27838968976");
    //what is the epected outcome
    boolean expectedValue= true;
    //actaulValue
    boolean actualValue= obj2.checkPhone_Num();
    System.out.println("Phone number successfully captured");
    //compae expected vs actual
    Assertions.assertEquals(expectedValue, actualValue);
   
    }      
    //then you must also create one for when it is not valid
   @Test
   public void testcheckPhone_Num_notVaid(){
    Login obj2 = new  Login();
   
    //give input
    obj2.setPhone_Num("0838968976");
    //what is the epected outcome
    boolean expectedValue= false; //if should be false cause the input does not have an star
    //actaulValue
    boolean actualValue= obj2.checkPhone_Num();
     System.out.println("\nCell phone number is incorrectly formatted\n"
                + "or does not contain international code\n"
                + "Please orrect the phone number and try again");
    //compae expected vs actual
    Assertions.assertEquals(expectedValue, actualValue);
   }  
   
   @Test
   public void testcheckPhone_Num_True(){
       //Step 1: Create an Object and add an input statement
        Login obj2 = new  Login();
       //give input
        obj2.setPhone_Num("+27838968976");
          //what is the epected outcome
          Assertions.assertTrue(obj2.checkPhone_Num());
          }
   @Test
     public void testcheckPhone_Num_False(){
      //Step 1: Create an Object and add an input statement
        Login obj2 = new  Login();
       //give input
        obj2.setPhone_Num("0838968976");
          //what is the epected outcome
          Assertions.assertFalse(obj2.checkPhone_Num());
          //It gave us an error because the input is incorrect  
         }
 @Test
   public void testLogin_True(){
       //Step 1: Create an Object and add an input statement
        Login obj2 = new  Login();
        obj2.setUsername("kyl_1");
        obj2.setPassword("Ch&&sec@ke99!");

        String enteredUsername = "kyl_1";
        String enteredpassword = "Ch&&sec@ke99!";
       //give input
       obj2.loginUser(enteredUsername, enteredpassword);
          //what is the epected outcome
          Assertions.assertTrue(obj2.loginUser(enteredUsername, enteredpassword));
          }  
   @Test
   public void testLogin_False(){
       //Step 1: Create an Object and add an input statement
        Login obj2 = new  Login();
        obj2.setUsername("kyle!!!!!!!!!");
        obj2.setPassword("password");

        String enteredUsername = "kyle!!!!!!!!!";
        String enteredpassword = "password";
       //give input
       obj2.loginUser(enteredUsername, enteredpassword);
          //what is the expected outcome
          Assertions.assertFalse(obj2.loginUser(enteredUsername, enteredpassword));
          } 
     
   
     
   
}
   