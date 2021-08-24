

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Interface or a boundary class to interact with the admin
 * Implements all the functionalities of the admin class
 * @author CE2002 SE1 Group 1
 * @version 1.0
 * @since 2020-11-01
 */
public abstract class Admininterface {
   static Scanner scan=new Scanner(System.in);
/**
 * Log in method for Admin
 * Asks Admin for Username and crosschecks with File Manager| 
 * If Username not found @return false|
 * If Username found break freom loop and ask for password|
 * If password not matched, print (Please check details and try again..)|
 * If password matched, print (Logging In..) @return true
 */
   public static boolean logInAdmin(){
       String user,pass;
       List list;
       System.out.println("Admin LogIn...");
       System.out.print("Username:");
       user=scan.nextLine();
       list = (ArrayList)FileMgr.readSerialObj("admin.dat");
       int i;
       ADMIN ad;
       ad = null;
       for (i = 0 ; i < list.size() ; i++) {
           ad = (ADMIN) list.get(i);
           if(user.equals(ad.getUsername()))break;
       }
       int secure=0;
       if (ad.getUsername().equals("#")) 
    	   return false;
       do {
           System.out.print("Password:");
           pass = scan.nextLine();
           pass=EncryptDecrypt.encrypt(pass);
           if (i == list.size()) {
               System.out.println("Please check details and try again..");
               return false;
           }
           //pls update need security
           if (pass.equals(ad.getPassword())) {
               System.out.println("Logging In..");
               return true;
           }
           secure++;
           System.out.println(". ".repeat(secure));
       }while(secure<3);
       //send email to all admins
       return false;

   }
   //Course
   /**
    * Adds a New Course
    */
   public static void addNewCourse(){
       courseinterface.AddCourse();;
   }
   /**
    * Method to edit course details after course has been created
    */
   public static void editCourse(){
       courseinterface.UpdateCourse();
   }
   /**
    * Method to Check for Course Vacancy
    */
   public static void vacancy() {
      courseinterface.CheckVacancy();
   }


   //Student
   /**
    * Method to set access period for Students to register for courses
    */
   public static void editAccessPeriod(){
	   StudentInterface.setAccess();
   }
   /**
    * Method to Add New Student Object
    */
   public static void addNewStudent(){
	   StudentInterface.AddStudent();
   }
   /**
    * Method to Print Student list according to Index for particular Course
    */
   public static void Printbyindex()
   {
	   courseinterface.print_studentlist_byindex();
   }
   /**
    * Method to Print Student list for a particular course
    */
   public static void printbycourse()
   {
	   courseinterface.print_allindex_studentlist();
   }
   //Reset password Idea

}



