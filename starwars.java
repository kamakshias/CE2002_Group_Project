import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import javax.swing.JPasswordField;
/**
 * This class is the highest level user interface.
 * @author CE2002 SE1 Group 1
 * @version 1.0
 * @since 2020-11-01
 *
 */
public class starwars{
	  //public static final long serialVersionUID=1L;
	/**
	 * Home Page where Admin and Student have to login
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException{
		System.out.println("-------------------------------------------------");
		System.out.println("Welcome to Star Wars");
		System.out.println("Choose your Domain");
		System.out.println("1) Admin\n2) Student\n0) Exit");
		Scanner sc = new Scanner(System.in);
		
		int choice,ch;
		String username,password;
		//char [] pass;
		//JPasswordField password =new JPasswordField(50);
		//password.setEchoChar('#');
		choice=sc.nextInt();
		System.out.println("-------------------------------------------------");
		while(choice!=0)
		{
			switch(choice)
				{
					case 1:
							
							Boolean check=Admininterface.logInAdmin();
							if(check==false)
							{
								System.out.println("LOGIN FAILED TRY AGAIN");
								break;
							}
							System.out.println();
							ch=-1;
							while(ch!=0)
							{
								System.out.println("-------------------------------------------------");
								System.out.println("(ADMIN)	Main Menu");
								System.out.println("1. Change access period");
								System.out.println("2. Add a Student");
								System.out.println("3. Add a Course");
								System.out.println("4. Update a Course");
								System.out.println("5. Check availablity slot for an Index Number");
								System.out.println("6. Print Student List by Index Number");
								System.out.println("7. Print Student List by Course");
								System.out.println("0. Sign Out");
								System.out.println("-------------------------------------------------");
								ch=sc.nextInt();
								switch(ch)
								{
									case 1:
										
										//courseinterface.PrintALLCourses();
										StudentInterface.setAccess();
											break;
									case 2:
										Admininterface.addNewStudent();
										StudentInterface.printAllStudents();
										break;
										
									case 3:
										Admininterface.addNewCourse();
										courseinterface.PrintALLCourses();
										break;
									case 4:
										Admininterface.editCourse();
										break;
									case 5:
										Admininterface.vacancy();
										break;
									case 6:
										Admininterface.Printbyindex();
										break;
									case 7:
										
										Admininterface.printbycourse();
										break;
									case 0:
										choice=0;
										break;
									default:
										System.out.print("Invalid Input. Please Try again");
										break;
								}
							}
							break;
					case 2: 
						Student st=StudentInterface.login();
						if(st==null)
						{
							System.out.println("LOGIN FAILED TRY AGAIN");
							break;
						}
					System.out.println();
					ch=-1;
					while(ch!=0)
					{
						System.out.println("-------------------------------------------------");
						System.out.println("(STUDENT) Main Menu");
						System.out.println("1. ADD a course");
						System.out.println("2. Drop a Student");
						System.out.println("3. Print courses registered");
						System.out.println("4. Check index vacancy");
						System.out.println("5. Change index for a course");
						System.out.println("6. Swap index for a course");
						System.out.println("0. Sign Out");
						System.out.println("-------------------------------------------------");
						ch=sc.nextInt();
						switch(ch)
						{
							case 1:
								StudentInterface.addCourse(st);;
									break;
							case 2:
								StudentInterface.removeCourse(st);
								break;
								
							case 3:
								StudentInterface.printCourseList(st);;
								break;
							case 4:
								courseinterface.CheckVacancy();
								break;
							case 5:
								StudentInterface.changeIndex(st);
								break;
							case 6:
								StudentInterface.swap(st);
								break;
							case 0:
								choice=0;
								break;
							default:
								System.out.print("Invalid Input. Please Try again");
								break;
						}
					}
					break;
				}
			System.out.println("-------------------------------------------------");
			System.out.println("Welcome to Star Wars");
			System.out.println("Choose your Domain");
			System.out.println("1) Admin\n2) Student\n0) Exit");
			choice=sc.nextInt();

		}
		
	}

}