import java.io.*;
import java.lang.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

/**
 *  It is an interface which acts as a boundary class interacting with Admin and Course
 * @author CE2002 SE1 Group 1
 * @version 1.0
 * @since 2020-11-01
 *
 */
public abstract class StudentInterface {
	static Scanner sc = new Scanner(System.in);
	/**
	 * Student Login Page
	 * @return Appropriate messages
	 */
	public static Student login() {
		List<Student> list1 = FileMgr.readSerialObj("student.dat");
		Student s2=list1.get(0);
		if(StudentInterface.checkAccess(s2)==true)
		{
			Student s = null;
			sc.nextLine();
			System.out.println("Username: ");
			String username = sc.nextLine();
			System.out.println("Password: ");
			String password = sc.nextLine();
			password=EncryptDecrypt.encrypt(password);
			List list = FileMgr.readSerialObj("student.dat");
			int i,flag=-1;
			for( i=0; i<list.size();i++) {
			    s = (Student) list.get(i);
				if(s.get_userName().equals(username))
				{
					if(s.get_passWord().equals(password)) 
					{
						return s;
					}
					flag=1;
				}
			}
			if(flag==1)
			{
				System.out.println("Wrong password");
			}
			else
			{
				System.out.println("Wrong username");
			}
			return null;
		}
		else
		{
			System.out.println("Access not allowed You are accessing it late. Try again within Access period");
			return null;
		}
	}
	/**
	 * Checking if Student is accessing Stars during access period
	 * @param st Student
	 * @return Boolean
	 */
	public static boolean checkAccess(Student st) {
		LocalDate currentDate = LocalDate.now();
		
		LocalTime currentTime = LocalTime.now();
		LocalDate startDate = st.get_startDate();
		LocalDate endDate = st.get_endDate();
		LocalTime startTime = st.get_startTime();
		LocalTime endTime = st.get_endTime();
		if(currentDate.compareTo(st.get_startDate())>0 && currentDate.compareTo(st.get_endDate())<0) {
			//System.out.println();
			return true;
		}
		else if(currentDate.compareTo(st.get_startDate())==0 && currentDate.compareTo(st.get_endDate())==0)
		{
			if(currentTime.compareTo(st.get_startTime())>=0 && currentTime.compareTo(st.get_endTime())<=0) {
				return true;
			}
			else {
				return false;
			}
		}
	
		else {
			return false;
		}
	}
	/**
	 * Registering Student in Course
	 * @param s Student
	 */
	public static void addCourse(Student s) {
		courseinterface.regstudent(s);
	}
	/**
	 * Registering Student in Index
	 * @param s Student
	 * @param coursecode Course Code to be added to
	 * @param index  Index of Course to be added to
	 */
	public static void addCourse(Student s,String coursecode,int index) {
		courseinterface.regstudent(s,coursecode,index);
	}
	/**
	 * Setting or Changing Access Period
	 */
	public static void setAccess(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter start date (Format: YYYY-MM-DD)");
		String startDate = sc.nextLine();
		System.out.println("Enter end date (Format: YYYY-MM-DD)");
		String endDate = sc.nextLine();
		System.out.println("Enter start time: hours");
		int startTime_h = sc.nextInt();
		System.out.println("Enter start time: minutes");
		int startTime_m = sc.nextInt();
		System.out.println("Enter end time: hours");
		int endTime_h = sc.nextInt();
		System.out.println("Enter end time: minutes");
		int endTime_m = sc.nextInt();
		String message1="PLEASE NOTE THAT THE ACCESS DATE HAS CHANGED TO : \nFROM "+startDate+" To "+endDate+"\nFROM TIME : "+startTime_h+":"+startTime_m+" to "+endTime_h+":"+endTime_m+"\n\n\nRegards STARS TEAM";
		
		List<Student> Sdetails=FileMgr.readSerialObj("student.dat");
		for(int i=0;i<Sdetails.size();i++)
		{
			Student s=Sdetails.get(i);
			s.set_Dates(startDate, endDate);
			s.set_Times(startTime_h, startTime_m, endTime_h, endTime_m);
			SendMailTLS.SENDMAIL(s.getEmail(), message1);
		}
		
		FileMgr.writeSerialObj(Sdetails, "student.dat");
	}
	/**
	 * Print All Students
	 */
	public static void printAllStudents()
	{
		 List pDetails = FileMgr.readSerialObj("student.dat");         
         Student Target=null;
         int flag=0;
  	   System.out.println(pDetails.size());
         for (int i = 0 ; i < pDetails.size() ; i++)
         {
            Student s = (Student)pDetails.get(i);
            s.printDetails();
         }
	}
	/**
	 * Adding a New Student
	 */
	public static void AddStudent() {
		Scanner sc = new Scanner(System.in);
		Student s = new Student();
		System.out.println("Student Name: ");
		String name = sc.nextLine();
		s.set_fullName(name);
		System.out.println("Matriculation Number: ");
		String matricno = sc.nextLine();
		s.set_matric(matricno);
		System.out.println("Gender: ");
		char gender = sc.next().charAt(0);
		s.set_gender(gender);
		sc.nextLine();
		System.out.println("Nationality: ");
		String nationality = sc.nextLine();
		s.set_nationality(nationality);
		System.out.println("Network Username: ");
		String username = sc.nextLine();
		s.set_userName(username);
		System.out.println("Network Password: ");
		String password = sc.nextLine();
		password=EncryptDecrypt.encrypt(password);
		s.set_password(password);
		System.out.println("Enter Email: ");
		String email = sc.nextLine();
		s.setEmail(email);
		System.out.println("School: ");
		String school = sc.nextLine();
		s.set_school(school);
		s.set_Dates("2020-11-10", "2020-12-15");
		s.set_Times(0, 0, 0, 0);
		System.out.println("Year of matriculation: ");
		int yearOfmatric = sc.nextInt();
		while(yearOfmatric>2020)
		{
			System.out.println("You cannot enter a future year please try registring the student again!");
			System.out.println("Year of matriculation: ");
			yearOfmatric = sc.nextInt();
			//return;
		}
		s.set_yearOfmatric(yearOfmatric);
		//List<Student> list= new ArrayList<Student>();
		List<Student> list = FileMgr.readSerialObj("student.dat");
		for(int i=0;i<list.size();i++)
		{
			Student st = list.get(i);
			if(st.get_fullName().equals(s.get_fullName()) && st.get_matric().equals(s.get_matric()))
			{
				System.out.println("Student already exists! try again!");
				return;
			}
		}
		String message1="HELLO "+s.get_fullName()+"!\nYou have been matriculated.\nPlease use the following username and password to access STARS in the assigned access period.\nUSERNAME: "+s.get_userName()+"\nPASSWORD: "+EncryptDecrypt.Decrypt(s.get_passWord());
		SendMailTLS.SENDMAIL(s.getEmail(), message1);
		list.add(s);
		FileMgr.writeSerialObj(list, "student.dat");
	}
	/**
	 * Printing Student Information
	 * @param s
	 */
	public static void printStudentInfo(Student s) {
		String un = s.get_userName();
		System.out.println("Username: " + un);
		String n = s.get_fullName();
		System.out.println("Full Name: " + n);
		String mn = s.get_matric();
		System.out.println("Matric No.: " + mn);
		char g = s.get_gender();
		System.out.println("Gender: " +  g);
		String nat = s.get_nationality();
		System.out.println("Nationality: " + nat);
		int au = s.get_au();
		System.out.println("AUs registered: " + au);
		//check if we need smth else	
	}
	/**
	 * Printing registered Course List for particular Student
	 * @param s Student
	 */
	public static void printCourseList(Student s) {
		if(s.getCourseNo()==0)
		{
			System.out.println("No courses registered");
			return;
		}
		List<course> list = s.getCourseList();
		for(int i=0;i<list.size();i++)
		{
			course c=list.get(i);
			c.print_course_details();
		}
	}
	/**
	 * Changing of index number for certain Course
	 * @param s Student
	 */
	public static void changeIndex(Student s) {
		/*Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Course Code");
		String courseCode = sc.nextLine();
		System.out.println("Enter the index number that you wish to change to");
		int indexNo = sc.nextInt();		*/
		removeCourse(s);
		System.out.println("ENTER OLD COURSE CODE AND NEW INDEX NUMBER TO CONFIRM");
		addCourse(s);
	}
	/**
	 * Swapping of Index number between 2 students
	 * @param s Student
	 */
	public static void swapIndex(Student s) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Course Code");
		String coursecode = sc.nextLine();
		System.out.println("Enter matric number of student you wish to swap with");
		String matricNo = sc.nextLine();
		System.out.println("Enter index number of student you wish to swap with");
		int indexno = sc.nextInt();
	}
	/**
	 * Remove a registered Course
	 * @param s Student
	 */
	public static void removeCourse(Student s) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the course code of the course you want to remove:");
		String tempCourseCode = sc.nextLine();
		int flag=-1;
		List<course> rundown = s.getCourseList();
		for(int i=0;i<s.getCourseNo();i++)
		{
			course c=rundown.get(i);
			
			if(c.get_coursecode().equals(tempCourseCode))
			{
				//c.print_course_details();
				flag=i;
			}
		}
		if(flag == -1)
		{
			System.out.println("You are not registered for this course!");    // can add option to register
		}
		if(flag!=-1)
		{
			course cfinal=null;
			List<course> Cdetails=FileMgr.readSerialObj("course.dat");
			for(int i=0;i<Cdetails.size();i++)
			{
				course c1=Cdetails.get(i);
				if(c1.get_coursecode().equals(rundown.get(flag).get_coursecode()))
				{
					cfinal=c1;
					break;
				}
			}
			boolean check=courseinterface.deregstudent(s.get_fullName(),cfinal); //edits here
			if(check==false)
			{
				System.out.print("DEREGISTRATION FAILED");
				return;
			}
			//System.out.println("Removeing from student");
			s.removeCourse(rundown.get(flag));
			rundown.remove(flag);
			s.setCourseList(rundown);
			//System.out.println("Removed from student");
			List<Student> Studentlist=FileMgr.readSerialObj("student.dat");
			for(int i=0;i<Studentlist.size();i++)
			{
				Student s2= Studentlist.get(i);
				if(s2.get_fullName().equals(s.get_fullName()))
				{
					Studentlist.remove(s2);
					break;
				}
			}
			Studentlist.add(s);
			FileMgr.writeSerialObj(Studentlist, "student.dat");
		}
		
		
	}
	/**
	 * Removing a non-registered Course
	 * @param s Student
	 * @param coursecode Coursecode of Course to be removed from
	 * @param index Index of course to be removed from
	 */
	public static void removeCourse(Student s,String coursecode,int index) {
		String tempCourseCode = coursecode;
		int flag=-1;
		List<course> rundown = s.getCourseList();
		for(int i=0;i<s.getCourseNo();i++)
		{
			course c=rundown.get(i);
			
			if(c.get_coursecode().equals(tempCourseCode))
			{
				//c.print_course_details();
				flag=i;
			}
		}
		if(flag == -1)
		{
			System.out.println("You are not registered for this course!");    // can add option to register
		}
		
	}
	/**
	 * Checking Vacancy of a Course
	 */
	public static void checkVacancy() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Course Code");
		String tempcoursecode = sc.nextLine();
		int targetVacancy;
	     List pDetails = FileMgr.readSerialObj("course.dat");      
	     course Target=null;
	     int flag=0;
	     for (int i = 0 ; i < pDetails.size() && flag==0; i++)
	     {
	        course c = (course)pDetails.get(i);
	        if(c.get_coursecode().contentEquals(tempcoursecode))
	        {
	           Target=c;
	           flag=1;
	        }
	     }
	     if(flag==0)
	     {
	        System.out.print("No Such course found");
	        return;
	     }
	     else
	     {
	    	System.out.println("For Course " + Target.get_coursecode() + " the vacancies are : \n");
	        for(int i=0;i<Target.get_indexcount();i++)
	        {
	              targetVacancy=Target.IndexTree[i].get_vacancy();
	              System.out.print("Vacancy for the index " + Target.IndexTree[i].get_Indexnumber()+" is "+ targetVacancy + "\n");
	        }
	     }
	}
	/**
	 * Swapping of Index between 2 students
	 * @param st Student
	 */
	public static void swap(Student st)
	{
		sc.nextLine();
		System.out.println("Enter the course code you want to swap:");
		String tempcourseCode=sc.nextLine();
		int tempindexnumber1,tempindexnumber2;
		List<course> stcourselist=st.getCourseList();
		course cfinal=null;
		for(int i=0;i<stcourselist.size();i++)
		{
			course tempc=stcourselist.get(i);
			if(tempc.get_coursecode().equals(tempcourseCode))
			{
				cfinal=tempc;
				break;
			}
		}
		if(cfinal==null)
		{
			System.out.println("You are not registered for this course try again!");
			return;
		}
		List cDetails = FileMgr.readSerialObj("course.dat");      
	     //course Target=null;
	     //int flag=0;
	     for (int i = 0 ; i <cDetails.size(); i++)
	     {
	        course c = (course)cDetails.get(i);
	        if(c.get_coursecode().equals(cfinal.get_coursecode()))
	        {
	        	cfinal=c;
	           //lag=1;
	        }
	     }
		int flag1=0;
		int pos=-1;
		System.out.println("Enter your index number for swap:");
		tempindexnumber1=sc.nextInt();
		for(int i=0;i<cfinal.get_indexcount();i++)
		{
			if(cfinal.IndexTree[i].get_Indexnumber()==tempindexnumber1)
			{
				//System.out.println(cfinal.IndexTree[i].indexnumber+"->"+cfinal.IndexTree[i].studentcounter);
				flag1=1;
				//cfinal.print_course_details();
				for(int j=0;j<cfinal.IndexTree[i].get_maxcapacity()-cfinal.IndexTree[i].get_vacancy();j++)
				{
					
					System.out.println(cfinal.IndexTree[i].get_Indexnumber());
					if(cfinal.IndexTree[i].studentnamelist[j].equals(st.get_fullName()))
					{
						//cfinal.print_course_details();
						flag1=2;
						pos=i;
					}
				}
			}
		}
		if(flag1==0)
		{
			System.out.println("No such index Exists! Try again");
			return;
		}
		else if(flag1==1)
		{
			System.out.println("You are not registered for this index!");
			return;
		}
		Student st2=login();
		if(st2==null)
		{
			System.out.println("Your peer does not exists! try again");
			return;
		}
		List<course> st2courselist=st2.getCourseList();
		course cfinal2=null;
		for(int i=0;i<st2courselist.size();i++)
		{
			course tempc2=st2courselist.get(i);
			if(tempc2.get_coursecode().equals(tempcourseCode))
			{
				cfinal2=tempc2;
				break;
			}
		}
		if(cfinal2==null)
		{
			System.out.println("Your peer is not registered for this course try again!");
			return;
		}
		 for (int i = 0 ; i <cDetails.size(); i++)
	     {
	        course c = (course)cDetails.get(i);
	        if(c.get_coursecode().equals(cfinal.get_coursecode()))
	        {
	        	cfinal2=c;
	           //lag=1;
	        }
	     }
		int flag2=0;
		int pos2=-1;
		System.out.println("Enter the index number of peer for swap:");
		tempindexnumber2=sc.nextInt();
		for(int i=0;i<cfinal2.get_indexcount();i++)
		{
			if(cfinal2.IndexTree[i].get_Indexnumber()==tempindexnumber2)
			{
				flag1=1;
				for(int j=0;j<cfinal2.IndexTree[i].studentcounter;j++)
				{
					if(cfinal2.IndexTree[i].studentnamelist[j].equals(st2.get_fullName()))
					{
						flag2=2;
						pos2=i;
					}
				}
			}
		}
		if(flag2==0)
		{
			System.out.println("No such index Exists! Try again");
			return;
		}
		else if(flag2==1)
		{
			System.out.println("Your peer is not registered for this index!");
		}
		cfinal.swapCourse(tempindexnumber1, tempindexnumber2, st.get_fullName(), st2.get_fullName());
		List<course> CDetails = FileMgr.readSerialObj("course.dat");
		for(int i=0;i<CDetails.size();i++)
		{
			course c1=CDetails.get(i);
			if(c1.get_coursecode().equals(cfinal.get_coursecode()) && c1.get_coursename().equals(cfinal.get_coursename()))
			{
				CDetails.remove(c1);
				break;
			}
		}
		String message1="HELLO "+st.get_fullName()+"("+st.get_matric()+")"+" !\nYour index has been succesfullfy swapped with "+st2.get_fullName()+"("+st2.get_matric()+")"+"\nOLD INDEX: "+tempindexnumber1+"\nNEW INDEX: "+tempindexnumber2+"\n\n\nRegards\nSTARS TEAM";
		SendMailTLS.SENDMAIL(st.getEmail(), message1);
		String message2="HELLO "+st2.get_fullName()+"("+st2.get_matric()+")"+" !\nYour index has been succesfullfy swapped with "+st.get_fullName()+"("+st.get_matric()+")"+"\nOLD INDEX: "+tempindexnumber2+"\nNEW INDEX: "+tempindexnumber1+"\n\n\nRegards\nSTARS TEAM";
		SendMailTLS.SENDMAIL(st2.getEmail(), message2);
		CDetails.add(cfinal);
		FileMgr.writeSerialObj(CDetails, "course.dat");
	}
	
}