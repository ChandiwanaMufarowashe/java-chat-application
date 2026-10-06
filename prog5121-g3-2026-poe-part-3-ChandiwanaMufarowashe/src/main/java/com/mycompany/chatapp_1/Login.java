/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp_1;

/**
 *
 * @author Administrator
 */
public class Login {
         //creating an account
    private String username;
    private String Password;
    private String Phone_Num = "";
    private String Name_Surname;
   
     // Getter and Setter for Username
     public void setUsername(String User){
        this.username= User;
    }
    public String getUsername(){
        return username;
        }
   
     // Getter and Setter for Password
     public void setPassword (String Pass){
        this.Password= Pass;
     }
     public String getPassword(){
        return Password;
        }
   
     // Getter and Setter for Phone_Num
     public void setPhone_Num(String Phone){
       this.Phone_Num= Phone;
    }
      public String getPhone_Num(){
        return Name_Surname;
        }
       // Getter and Setter for Name_Surname
     public void setName_Surname(String Name){
    this.Name_Surname = Name;
    }
      public String getName_Surname(){
        return Name_Surname;
        }
   
    //setting the conditions that need to be met for Username
    public boolean checkusername(){
        return username.contains("_") && username.length()<=5;
   
    }
    //setting the conditions that need to be met for Password
     public boolean checkPassword() {
  if (Password.length() <8) {
            return false;
        }
        boolean hasLowercase= false;
        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
       
       for (int i=0; i< Password.length(); i++) {
            char ch = Password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            }
            if (Character.isDigit(ch)) {
                hasDigit = true;
            }
            if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
            if(Character.isLowerCase(ch)){
                 hasLowercase = true;
            }
             
        }return hasUppercase && hasDigit && hasSpecial && hasLowercase;
        }
     
   //setting the conditions that need to be met for Phone_Num
   public boolean checkPhone_Num(){
       return Phone_Num != null && Phone_Num.matches("^\\+\\d{1,3}\\d{1,10}$");
               //Phone_Num.startsWith("+27") && Phone_Num.length() <= 12;
       }
 public String registerUser() {

    String message = "";

    if (!checkusername()) {
        message += "\nUsername is not correctly formatted\n"
                + "Please ensure that your username contains:\n"
                + "- An underscore\n"
                + "- And is no more than 5 characters long\n";
    }

    if (!checkPhone_Num()) {
        message += "\nCell phone number is incorrectly formatted\n"
                + "or does not contain international code\n";
    }

    if (!checkPassword()) {
        message += "\nPassword is incorrectly formatted. Please ensure it contains:\n"
                + "- At least 8 characters\n"
                + "- A capital letter\n"
                + "- A lowercase letter\n"
                + "- A number\n"
                + "- A special character\n";
    }
    //  SUCCESS CASE (ALL CORRECT)
    if (checkusername() && checkPhone_Num() && checkPassword()) {
        return "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n"
             +"Username successfully captured\n"
             + "Phone number successfully captured\n"
             + "Password successfully captured\n"
             +"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n"
             + "User successfully registered";
    }

    //RETURN ERRORS
    return message;
}
   //Compares the Login details to the stored ones
   public boolean loginUser(String enteredUsername, String enteredpassword){
      return this.username.equals(enteredUsername) && this.Password.equals(enteredpassword)   ;
   }
   //This method returns the Login message
   public String returnLoginStatus(String enteredUsername, String enteredPassword){
       if(loginUser(enteredUsername,enteredPassword)){
           return "Welcome! " + Name_Surname + ", it is great to see you again";
       }else{return "Username or Password incoreect please try again";
            }
       
   }
}
