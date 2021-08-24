import java.io.Serializable;
/**
 * The Admin object where we can add all the students info and entire menu
 * Represents the Admin object which can be any NTU staff
 * @author CE2002 SE1 Group 1
 * @version 1.0
 * @since 2020-11-01
 *
 */
public class ADMIN implements Serializable{
/**
 * The username of the Admin
 */
  private String username;
 /**
  * Name of the Admin
  */
  private String Name;
 /**
  * The password of the Admin
  */
  private String password;
 /**
  * Creates a new Admin with given name, username and password
  * @param username This is Admin's username
  * @param password This is Admin's password
  * @param name This is Admin's name
  */
  //private static int numberofcourse =0;
  //private static String key = "#ILOVEntu";

  ADMIN(String username,String password,String name){
     this.username=username;
     this.password=password;
     this.Name=name;
  }
  /**
   * Gets the username of the Admin
   * @return username
   */
  public String getUsername(){ return username;}
  /**
   * Gets the password of the Admin
   * @return password
   */
  public String getPassword(){return password;} //pls update need security
  /**
   * Prints the Admin information|
   * Prints the Admin Name|
   * Prints the Admin username
   */
  public void printAdminInfo() { //Abhi
     System.out.println(Name);
     System.out.println(username);
  }


}



