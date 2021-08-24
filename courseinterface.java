import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * An interface which acts as a boundary class that allows Course to interact with Student 
 * @author CE2002 SE1 Group 1
 * @version 1.0
 * @since 2020-11-01
 * 
 */
public abstract class courseinterface{
	static Scanner sc = new Scanner(System.in);
	/**
	 * Method to print all Courses available
	 */
	public static void PrintALLCourses()
	{
		 List pDetails = FileMgr.readSerialObj("course.dat");         
         course Target=null;
         int flag=0;
  	   System.out.println(pDetails.size());
         for (int i = 0 ; i < pDetails.size() ; i++)
         {
            course c = (course)pDetails.get(i);
            c.print_course_details();
           
         }
	}
	/**
	 * Method to Add New Course
	 */
	public static void AddCourse()
	{
		String coursename=null,coursecode=null,school=null;
		Calendar examdateparsed;
		int AU=3,indexcount=0;
		
		sc.nextLine();
		coursename=set_coursename();
		coursecode=set_coursecode();
		/*List<course> pDetails = FileMgr.readSerialObj("course.dat");   
		for(int i=0;i<pDetails.size();i++)
		{
			course c1 = pDetails.get(i);
			if(c1.get_coursecode().equals(coursecode) && c1.get_coursename().equals(coursename))
			{
				System.out.println("The course already exists! Try again");
				return;
			}
		}*/
		school=set_school();
		AU=set_academicunits();
		indexcount=set_indexnum();	
		sc.nextLine();
		examdateparsed=getValidDT();
		course c = new course(coursename,coursecode,school,AU,indexcount,dateString(examdateparsed));
		//courselibrary[this.coursecount]=new course(coursename,coursecode,school,AU,indexcount,dateString(this.examdateparsed));
		confirmation(c);
		create_index(c);
		List<course> pDetails= new ArrayList<course>();
		pDetails.add(c);
		FileMgr.writeSerialObj(pDetails, "course.dat");
	}
	/**
	 * Method to Update Changes to a Course
	 */
	public static void UpdateCourse()
	{
		int ch=-1;
	     int cnt=0;
	     while(ch!=1 && ch!=2)
	     {
	        System.out.print("Press 1 to modify by course name\nPress 2 to modify by course code\nPress 0 to go back to main menu\n");
	        ch=sc.nextInt();
	        if(ch!=1 && ch!=2)
	        {
	           System.out.print("Invalid Input try again");
	        }
	        if(ch==0)
	        {
	           return;
	        }
	     }
	     sc.nextLine();
	     switch(ch)
	     {
	        case 1: System.out.print("Enter the course name to modify");
	           String tempCourseName = sc.nextLine();
	           List pDetails = FileMgr.readSerialObj("course.dat");         
	           course Target=null;
	           int flag=0;
        	   System.out.println(pDetails.size());
	           for (int i = 0 ; i < pDetails.size() ; i++)
	           {
	              course c = (course)pDetails.get(i);
	              c.print_course_details();
	              if(c.get_coursename().equals(tempCourseName))
	              {
	                 pDetails.remove(c);
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
	        	   for(int i=0;i<Target.get_indexcount();i++)
	        	   {
	        		   if(Target.IndexTree[i].studentcounter>0)
	        		   {
	        			   System.out.println("Cannot edit as students are already endrolled");
	        			   return;
	        		   }
	        	   }
	              System.out.print("Press 1 to modify course code\nPress 2 to modify course name\nPress 3 to modify course number of AU\nPress 4 to modify school name\nPress 5 to modify exam date\nPress 6 to add a new index\nPress 7 to remove an existing index\n");
	              int choice=sc.nextInt();
	                 sc.nextLine();
	              switch(choice)
	              {
	                 case 1: Target.set_coursecode(set_coursecode());break;   // dont know the new functions to change these so plz change accordingly
	                 case 2: Target.set_coursename(set_coursename());break;	  // dont know the new functions to change these so plz change accordingly
	                 case 3: Target.set_courseAU(set_academicunits());break;// dont know the new functions to change these so plz change accordingly
	                 case 4: Target.set_school(set_school());break;       // dont know the new functions to change these so plz change accordingly
	                 case 5: Target.set_examdate(dateString(getValidDT()));break;     // dont know the new functions to change these so plz change accordingly
	                 case 6: System.out.print("Enter the number of indexes you want to add : \n");
	                       int noOfIndexes=sc.nextInt();
	                       if(noOfIndexes==0)
	                       {
	                          return;
	                       }
	                       else
	                       {
	                          add_index(Target,noOfIndexes);//// dont know the new functions to change these so plz change accordingly
	                       }
	                       break;
	                 case 7: System.out.print("Enter the index number you want to remove : \n");
	                       int indexNo=sc.nextInt();
	                       Target.remove_index(indexNo);
	                       break;
	                 default: System.out.print("Invalid Input!!");
	                        return;
	                
	              }
	              System.out.println("The modified course details are : ");
	              Target.print_course_details();
	              pDetails.add(Target);
	              FileMgr.writeSerialObj(pDetails,"Course.dat");           
	           }
	           break;
	        case 2: System.out.print("Enter the course code to modify");
	              String tempCourseCode = sc.nextLine();
	              List pDetails1 = FileMgr.readSerialObj("course.dat");         
	              course Target1=null;
	              int flag1=0;
	              System.out.println("Size :" + pDetails1.size());
	              for (int i = 0 ; i <pDetails1.size() ; i++)
	              {
	            	 
	                 course c = (course)pDetails1.get(i);
	   
	                 if(c.get_coursecode().equals(tempCourseCode))
	                 {
	                    pDetails1.remove(c);
	                    Target1=c;
	                    flag1=1;
	                 }
	              }
	              if(flag1==0)
	              {
	                 System.out.print("No Such course found");
	                 return;
	              }
	              else
	              {
	                 System.out.print("Press 1 to modify course code\nPress 2 to modify course name\nPress 3 to modify course number of AU\nPress 4 to modify school name\nPress 5 to modify exam date\nPress 6 to add a new index\nPress 7 to remove an existing index\n");
	                 int choice=sc.nextInt();
	                 sc.nextLine();
	                 switch(choice)
	                 {
	                    case 1: Target1.set_coursecode(set_coursecode());break;			// Same as above
	                    case 2: Target1.set_coursename(set_coursename());break;			//Same as above
	                    case 3: Target1.set_courseAU(set_academicunits());break;		//same as above
	                    case 4: Target1.set_school(set_school());break;				//Same as above
	                    case 5: Target1.set_examdate(dateString(getValidDT()));break;			//same as above
	                    case 6: System.out.print("Enter the number of indexes you want to add : \n");
	                          int noOfIndexes=sc.nextInt();
	                          if(noOfIndexes==0)
	                          {
	                             return;
	                          }
	                          else
	                          {
	                             add_index(Target1,noOfIndexes);	//same as above
	                          }
	                          break;
	                    case 7: System.out.print("Enter the index number you want to remove : \n");
	                          int indexNo=sc.nextInt();
	                          Target1.remove_index(indexNo);
	                          break;
	                    default:System.out.print("Invalid Input!!");
	                          return;
	                   
	                 }
	                 System.out.println("The modified course details are : ");
		             Target1.print_course_details();
	                 pDetails1.add(Target1);
	                 FileMgr.writeSerialObj(pDetails1,"course.dat");           
	              }
	              break;
	              default: System.out.print("Invalid Input!!");
	                    return;
	        }
	}
	/**
	 * Method to check vacancy for a Course
	 */
	public static void CheckVacancy()
	{
		 int targetVacancy;
		 sc.nextLine();
	     System.out.print("Enter the Subject code : \n" );
	     String tempCourseCode=sc.nextLine();
	     System.out.print("Enter the index Number : \n");
	     int tempIndexNum=sc.nextInt();
	     List pDetails = (ArrayList)FileMgr.readSerialObj("course.dat");         
	     course Target=null;
	     int flag=0;
	     for (int i = 0 ; i < pDetails.size() && flag==0; i++)
	     {
	        course c = (course)pDetails.get(i);
	        if(c.get_coursecode().equals(tempCourseCode))
	        {
	           Target=c;
	           flag=1;
	        }
	     }
	     if(flag==0)
	     {
	        System.out.print("No Such course found\n");
	        return;
	     }
	     else
	     {
	    	 int flag1=0;
	    	 //System.out.println("No of index : "+Target.get_indexcount());
	        for(int i=0;i<Target.get_indexcount();i++)
	        {
	        	//Target.IndexTree[i].print_indexdetails();
	           if(Target.IndexTree[i].get_Indexnumber()==tempIndexNum)
	           {
	        	  flag1=1;
	        	  //System.out.println("Yes");
	              targetVacancy=Target.IndexTree[i].get_vacancy();
	              System.out.println("Vacancy for the index " + tempIndexNum+" is "+ targetVacancy+"/"+Target.IndexTree[i].get_maxcapacity());
	              if(Target.IndexTree[i].get_qcurrent()>0)
	              {
	            	 // Target.IndexTree[i].printQueueList();
	            	  System.out.println("Waiting list : "+Target.IndexTree[i].get_qcurrent());
	            	  
	              }
	           }
	        }
	        if(flag1==0)
	        {
	        	System.out.println("No such index found.");
	        }
	     }
	}
	/**
	 * Method to print Student list for a Certain Course for all indexes
	 */
	public static void print_allindex_studentlist()
	{	sc.nextLine();
		System.out.print("Enter the Subject code : \n" );
	    String tempCourseCode=sc.nextLine();
	    List cDetails = FileMgr.readSerialObj("course.dat");    
	    List<Student> Sdetails= FileMgr.readSerialObj("student.dat");
	    int flag=0;
	    course c=null;
	    for(int i=0;i<cDetails.size();i++)
	    {
		   	 c=(course)cDetails.get(i);
		   	 if(c.get_coursecode().equals(tempCourseCode))
		   	 {
		   		 flag=1;
		   		break;
		   	 }
	    }
	    if(flag==0)
	    {
	    	System.out.print("The course you entered does not exist try again\n");
	    	return;
	    }
	    else
	    {
	    	System.out.println("Student list is :");
	    	String[] Stnamelist=null;
		    		 //c.print_course_details();
		    		 for(int j=0;j<c.get_indexcount();j++)
		             {
	    			 	
	    			 	Stnamelist=c.IndexTree[j].get_studentlist();
	    			 	 for(int k=0;k<Sdetails.size();k++)
	    			     {
	    			    	 Student s2=Sdetails.get(k);
	    			    	for(int l=0;l<c.IndexTree[j].studentcounter;l++)
	    			    	{
	    			    		if(Stnamelist[l].equals(s2.get_fullName()))
	    			    		{
	    			    			System.out.println("Name : "+s2.get_fullName());
	    			    			System.out.println("Matric no : "+s2.get_matric());
	    			    			System.out.println("Nationality: "+s2.get_nationality());
	    			    			System.out.println("Gender: "+s2.get_gender()+"\n");

	    			    		}
	    			    	}
	    			     }
	    			 	//break;
                	
		             }
		    		// break;
		    	 }
		    	// break;
		     
	    
	}
	// same as above
	//I have done this as well just check
	/**
	 * Method to print Student list for a particular index for a certain Course
	 */
	public static void print_studentlist_byindex()
	{
		sc.nextLine();
		System.out.print("Enter the Subject code : \n" );
	     String tempCourseCode=sc.nextLine();
	     System.out.print("Enter the index Number : \n");
		 int tempIndexNum=sc.nextInt();
		 List cDetails = FileMgr.readSerialObj("course.dat");         
	     int flag=0;
	     int stno=-1;
	     String[] Stnamelist=null;
	     for(int i=0;i<cDetails.size();i++)
	     {
	    	 course c=(course)cDetails.get(i);
	    	 
	    	 if(c.get_coursecode().equals(tempCourseCode))
	    	 {
	    		 //c.print_course_details();
	    		 for(int j=0;j<c.get_indexcount();j++)
	             {
	    			 	//System.out.print("YESYESY");
	    			 	//c.IndexTree[j].print_indexdetails();
	                	if(c.IndexTree[j].get_Indexnumber()==tempIndexNum)
	                	{
	                		//c.IndexTree[j].print_indexdetails();
		    			 	System.out.println("Student list is :");
		    			 	Stnamelist=c.IndexTree[j].get_studentlist();
		    			 	stno=c.IndexTree[j].studentcounter;
	                		//return;
		    			 	System.out.println(stno);
		    			 	break;
	                	}
	             }
	    		 //break;
	    	 }
	    	 //break;
	     }
	     if(stno==-1)
	     {
	    	 System.out.println("The index does not exist");
	    	 return;
	     }
	     else
	     {
		     List<Student> Sdetails=FileMgr.readSerialObj("student.dat");
		     for(int i=0;i<Sdetails.size();i++)
		     {
		    	 Student s=Sdetails.get(i);
		    	for(int j=0;j<stno;j++)
		    	{
		    		if(Stnamelist[j].equals(s.get_fullName()))
		    		{
		    			System.out.println("Name : "+s.get_fullName());
		    			System.out.println("Matric no : "+s.get_matric());
		    			System.out.println("Nationality: "+s.get_nationality());
		    			System.out.println("Gender: "+s.get_gender()+"\n");

		    		}
		    	}
		     }
	     }
	}

	/**
	 * Method to confirm all information given to create the Course is correct
	 * Otherwise select respective parameter to edit
	 * @param c1 Course to be checked
	 */
	public static void confirmation(course c1)
	{	int temp=-1;
		while(temp!=0)
		{	
			c1.print();
			System.out.println("Press 0 to confirm all information is correct. or Press the respective number to make the changes");
			temp=sc.nextInt();
			switch(temp)
			{
			case 1:	
					c1.set_coursecode(set_coursecode());
					
				 break;
			case 2:
				sc.nextLine();
				c1.set_coursename(set_coursename());
				break;
			case 3:c1.set_courseAU(set_academicunits());
				break;
			case 4:c1.set_examdate(dateString(getValidDT()));
				break;
			case 5:c1.set_school(set_school());
				break;
			case 6:add_index(c1,set_indexnum());
				break;
			default: temp=0;
					break;
			}
			
			
			
		}
	}
	
	/**
	 * Method to set the Name of the Course
	 * @return Name of Course
	 */
	public static String set_coursename()
	{	
		
		String coursename;
		System.out.print("Enter Course Name: ");
		coursename=sc.nextLine();
		return coursename;
	}
	/**
	 * Method to set the Code of the Course
	 * @return Code of the Course
	 */
	public static String set_coursecode()
	{	String coursecode;
	
		System.out.print("Enter Course Code: ");
		coursecode=sc.next();
		
		return coursecode;
		
	}
	/**
	 * Method to set the Number of Academic Units for the Course
	 * @return Number of Academic Units
	 */
	public static int set_academicunits()
	{	int AU;
		System.out.print("Enter Academic Units: ");
		AU=sc.nextInt();
		return AU;
	}
	/**
	 * Method to set the School offering the Course
	 * @return name of School in Uppercase format
	 */
	public static String set_school()
	{
		System.out.print("Enter the school offering the course: ");
		String school=sc.next().toUpperCase();
		return school;
	}
	/**
	 * Method to set the Number of Indexes to the Course
	 * @return number of indexes
	 */
	public static int set_indexnum()
	{
		System.out.print("How many indexes do you plan to Add: ");
		int newindex=sc.nextInt();
		
		return newindex;
	}


	/**
	 * Creates Index for the Course
	 * Checks and prevents duplicate indexes
	 * Requires Capacity and structure of Course
	 * @param c1 Course to add the index to
	 */
	private static void create_index(course c1) {
		int coursestruct=-1;
		int i,tempindexnumber,temp=-1,maxindexrange;
		int capacity;
		int start=0;
		maxindexrange=c1.get_indexcount();
		for(i=start;i<maxindexrange;i++)
		{	System.out.println("Index Created so far created for Course:"+c1.get_coursename()+" "+ c1.get_coursecode());
			
			do
			{
				sc.nextLine();
				System.out.println("Index number:");
				tempindexnumber=sc.nextInt();
				while(c1.check_index(tempindexnumber)==false)
				{
					System.out.println("Try Again! Index number already used. Key in again:");
					tempindexnumber=sc.nextInt();
				}
				System.out.print("Press 0 to confirm Index Number.Press 1 to change index number: ");
				temp=sc.nextInt();
				
			}while(temp!=0);
			
			System.out.println("Enter Capacity for Index number: "+tempindexnumber);	
			capacity=sc.nextInt();	
			System.out.println("Select Course Structure");
			//sc.nextLine();
			while(coursestruct<1 || coursestruct>3)
				{
				sc.nextLine();
				coursestruct=sc.nextInt();
				
			if (coursestruct == 1) {
				System.out.println("1.	Lecture only");
				
			} else if (coursestruct == 2) {
				System.out.println("2.	Lecture and Tutorial");
			} else if (coursestruct == 3) {
				System.out.println("3.	Lecture, Tutorial and Lab");
			} else {
				System.out.print("Try again");
				coursestruct=sc.nextInt();
			}
				}
			
			if (coursestruct == 1) {
				System.out.println("Set Lecture start time: ");
				Calendar lecturestart=getValidHM();
				System.out.println("Set Lecture end time: ");
				Calendar lectureEnd=getValidHM();
				int lectureDayStructure=setLectureDay();
				String lectureEvenOdd=setLectureEvenOdd();
				String lectureVenue=setLectureVenue();
				//courseTypeSelection("Lecture");
				c1.IndexTree[i]= new indexNO(tempindexnumber,capacity,coursestruct,lecturestart,lectureEnd,lectureDayStructure,lectureEvenOdd,lectureVenue);
				c1.indexnamelist[i]=tempindexnumber;
				coursestruct=-1;
			}

			else if (coursestruct == 2) {
				System.out.println("Set Lecture start time: ");
				Calendar lecturestart=getValidHM();
				System.out.println("Set Lecture end time: ");
				Calendar lectureEnd=getValidHM();
				int lectureDayStructure=setLectureDay();
				String lectureEvenOdd=setLectureEvenOdd();
				String lectureVenue=setLectureVenue();
				
				System.out.println("Set Tutorial start time: ");
				Calendar Tutorialstart=getValidHM();
				System.out.println("Set Lecture end time: ");
				Calendar TutorialEnd=getValidHM();
				int TutorialDayStructure=setTutorialDay();
				String TutorialEvenOdd=setTutorialEvenOdd();
				String TutorialVenue=setTutorialVenue();
				//courseTypeSelection("Lecture");
				c1.IndexTree[i]= new indexNO(tempindexnumber,capacity,coursestruct,lecturestart,lectureEnd,lectureDayStructure,lectureEvenOdd,lectureVenue
						,Tutorialstart,TutorialEnd,TutorialDayStructure,TutorialEvenOdd,TutorialVenue);
				c1.indexnamelist[i]=tempindexnumber;
				//courseTypeSelection("Lecture", "Tutorial");
				coursestruct=-1;

			} else if (coursestruct == 3) {
				System.out.println("Set Lecture start time: ");
				Calendar lecturestart=getValidHM();
				System.out.println("Set Lecture end time: ");
				Calendar lectureEnd=getValidHM();
				int lectureDayStructure=setLectureDay();
				String lectureEvenOdd=setLectureEvenOdd();
				String lectureVenue=setLectureVenue();
				
				System.out.println("Set Tutorial start time: ");
				Calendar Tutorialstart=getValidHM();
				System.out.println("Set Tutorial end time: ");
				Calendar TutorialEnd=getValidHM();
				int TutorialDayStructure=setTutorialDay();
				String TutorialEvenOdd=setTutorialEvenOdd();
				String TutorialVenue=setTutorialVenue();
				
				
				System.out.println("Set Lab start time: ");
				Calendar Labstart=getValidHM();
				System.out.println("Set Lab end time: ");
				Calendar LabEnd=getValidHM();
				int LabDayStructure=setLabDay();
				String LabEvenOdd=setLabEvenOdd();
				String LabVenue=setLabVenue();
				c1.IndexTree[i]= new indexNO(tempindexnumber,capacity,coursestruct,lecturestart,lectureEnd,lectureDayStructure,lectureEvenOdd,lectureVenue
						,Tutorialstart,TutorialEnd,TutorialDayStructure,TutorialEvenOdd,TutorialVenue
						,Labstart,LabEnd,LabDayStructure,LabEvenOdd,LabVenue);
				c1.indexnamelist[i]=tempindexnumber;
				coursestruct=-1;
			} 	
			
		}
	}
	
	/**
	 * Creates Index for the Course
	 * @param c1 Course to add the index to
	 * @param addindex The index number to be added
	 */
	public static void add_index(course c1,int addindex) {
		int i,tempindexnumber,temp=-1,maxindexrange,coursestruct=-1;
		int capacity;
		int start=c1.get_indexcount();
		maxindexrange=c1.get_indexcount()+addindex;
		c1.set_indexcount(maxindexrange);
		for(i=start;i<maxindexrange;i++)
		{	System.out.println("Index Created so far created for Course:"+c1.get_coursename()+" "+ c1.get_coursecode());
			
			do
			{
				System.out.println("Index number:");
				tempindexnumber=sc.nextInt();
				while(c1.check_index(tempindexnumber)==false)
				{
					System.out.println("Try Again! Index number already used. Key in again:");
					tempindexnumber=sc.nextInt();
				}
				System.out.print("Press 0 to confirm Index Number.Press 1 to change index number: ");
				temp=sc.nextInt();
				
			}while(temp!=0);
			
			System.out.println("Enter Capacity for Index number: "+tempindexnumber);	
			capacity=sc.nextInt();			
			System.out.println("Select Course Structure");
			sc.nextLine();
			while(coursestruct<1 || coursestruct>3)
				{
				coursestruct=sc.nextInt();
				
			if (coursestruct == 1) {
				System.out.println("1.	Lecture only");
				
			} else if (coursestruct == 2) {
				System.out.println("2.	Lecture and Tutorial");
			} else if (coursestruct == 3) {
				System.out.println("3.	Lecture, Tutorial and Lab");
			} else {
				System.out.print("Try again");
				coursestruct=sc.nextInt();
			}
				}
			
			if (coursestruct == 1) {
				System.out.println("Set Lecture start time: ");
				Calendar lecturestart=getValidHM();
				System.out.println("Set Lecture end time: ");
				Calendar lectureEnd=getValidHM();
				int lectureDayStructure=setLectureDay();
				String lectureEvenOdd=setLectureEvenOdd();
				String lectureVenue=setLectureVenue();
				//courseTypeSelection("Lecture");
				c1.IndexTree[i]= new indexNO(tempindexnumber,capacity,coursestruct,lecturestart,lectureEnd,lectureDayStructure,lectureEvenOdd,lectureVenue);
				c1.indexnamelist[i]=tempindexnumber;
			}

			else if (coursestruct == 2) {
				System.out.println("Set Lecture start time: ");
				Calendar lecturestart=getValidHM();
				System.out.println("Set Lecture end time: ");
				Calendar lectureEnd=getValidHM();
				int lectureDayStructure=setLectureDay();
				String lectureEvenOdd=setLectureEvenOdd();
				String lectureVenue=setLectureVenue();
				
				System.out.println("Set Tutorial start time: ");
				Calendar Tutorialstart=getValidHM();
				System.out.println("Set Lecture end time: ");
				Calendar TutorialEnd=getValidHM();
				int TutorialDayStructure=setTutorialDay();
				String TutorialEvenOdd=setTutorialEvenOdd();
				String TutorialVenue=setTutorialVenue();
				//courseTypeSelection("Lecture");
				c1.IndexTree[i]= new indexNO(tempindexnumber,capacity,coursestruct,lecturestart,lectureEnd,lectureDayStructure,lectureEvenOdd,lectureVenue
						,Tutorialstart,TutorialEnd,TutorialDayStructure,TutorialEvenOdd,TutorialVenue);
				c1.indexnamelist[i]=tempindexnumber;
				//courseTypeSelection("Lecture", "Tutorial");

			} else if (coursestruct == 3) {
				System.out.println("Set Lecture start time: ");
				Calendar lecturestart=getValidHM();
				System.out.println("Set Lecture end time: ");
				Calendar lectureEnd=getValidHM();
				int lectureDayStructure=setLectureDay();
				String lectureEvenOdd=setLectureEvenOdd();
				String lectureVenue=setLectureVenue();
				
				System.out.println("Set Tutorial start time: ");
				Calendar Tutorialstart=getValidHM();
				System.out.println("Set Tutorial end time: ");
				Calendar TutorialEnd=getValidHM();
				int TutorialDayStructure=setTutorialDay();
				String TutorialEvenOdd=setTutorialEvenOdd();
				String TutorialVenue=setTutorialVenue();
				
				
				System.out.println("Set Lab start time: ");
				Calendar Labstart=getValidHM();
				System.out.println("Set Lab end time: ");
				Calendar LabEnd=getValidHM();
				int LabDayStructure=setLabDay();
				String LabEvenOdd=setLabEvenOdd();
				String LabVenue=setLabVenue();
				c1.IndexTree[i]= new indexNO(tempindexnumber,capacity,coursestruct,lecturestart,lectureEnd,lectureDayStructure,lectureEvenOdd,lectureVenue
						,Tutorialstart,TutorialEnd,TutorialDayStructure,TutorialEvenOdd,TutorialVenue
						,Labstart,LabEnd,LabDayStructure,LabEvenOdd,LabVenue);
				c1.indexnamelist[i]=tempindexnumber;
				
			} 	
			
		}
			
		}
	
	
	/**
	 * Method to register a student for a course
	 * @param st Student to be registered
	 * @return false if there is clash or if already registered for same course
	 * @return true if no clash
	 */
	public static boolean regstudent(Student st)
	{
		System.out.print("Enter Course code : ");
		String tempCourseCode=sc.next();
		System.out.print("Enter Index Number : ");
		int tempIndexNum=sc.nextInt();
		List allCourse=FileMgr.readSerialObj("course.dat");
	    course c=null;
	    int flag=0;
	    for (int i = 0 ; i < allCourse.size() ; i++) {
	        c = (course) allCourse.get(i);
	        
	        //c.print_course_details();
	        if (c.get_coursecode().equals(tempCourseCode)){
	        	allCourse.remove(c);
	        	flag=1;
	        	//System.out.println("Yes");
	            break;
	        }
	    	}
	       if (flag==0){
	           System.out.println("Wrong course name.");
	           return false;
	       }
	       if(st.getCourseNo()>0)
	       {
		       List<course> studentcourselist=st.getCourseList();
		       for(int i=0;i<studentcourselist.size();i++)
		       {
		    	   course tempc=studentcourselist.get(i);
		    	   if(tempc.get_coursecode().equals(c.get_coursecode()) && tempc.get_coursename().equals(c.get_coursename()))
		    	   {
		    		   System.out.println("You are already registered to this course! Try again!");
		    		   return false;
		    	   }
		       }
	       }
	       c.print();
	       if(st.getCourseNo()>0)
	       {
		       Boolean clash=false;
		       List<course> Courselist=st.getCourseList();
		       int i=0;
		       for(i=0;i<c.get_indexcount();i++)
		       {
		    	   if(c.IndexTree[i].indexnumber==tempIndexNum)
		    	   {
		    		   break;
		    	   }
		       }
		       int type =  c.IndexTree[i].coursetiming.getCourseTypeStructure();
	 		  String[] DaysofWeek= new String[] {"","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	 		  switch(type)
	 		  {
	 		  	case 3: Calendar start3= c.IndexTree[i].coursetiming.getLabStartTime();
				  			Calendar end3 =	c.IndexTree[i].coursetiming.getLabEndTime();
				  			int day3=c.IndexTree[i].coursetiming.getLabDayStructure();
				  			String Evenoddlab=c.IndexTree[i].coursetiming.getLabEvenOdd();
				  			for(int k=0;k<st.getCourseNo();k++)
				 	        {
				 	    	   clash=Courselist.get(k).CheckClash(st.get_fullName(),st.get_matric(),start3,end3,day3,Evenoddlab);
				 	    	   if(clash==true)
				 	    	   {
				 	    		   System.out.println("There is a clash on "+DaysofWeek[day3]+"!");
				 	    		   break;
				 	    	   }
				 	        }
				  			if(clash==true)
				  			{
				  				break;
				  			}
	 		  	case 2: Calendar start2= c.IndexTree[i].coursetiming.getTutorialStartTime();
				  			Calendar end2 =	c.IndexTree[i].coursetiming.getTutorialEndTime();
				  			int day2=c.IndexTree[i].coursetiming.getTutorialDayStructure();
				  			String Evenoddtut=c.IndexTree[i].coursetiming.getTutorialEvenOdd();
				  			for(int k=0;k<st.getCourseNo();k++)
				 	        {
				 	    	   clash=Courselist.get(k).CheckClash(st.get_fullName(),st.get_matric(),start2,end2,day2,Evenoddtut);
				 	    	   if(clash==true)
				 	    	   {
				 	    		   System.out.println("There is a clash on "+DaysofWeek[day2]+"!");
				 	    		   break;
				 	    	   }
				 	        }
				  			if(clash==true)
				  			{
				  				break;
				  			}
	 		  	case 1: Calendar start= c.IndexTree[i].coursetiming.getLectureStartTime();
	 		  			Calendar end =	c.IndexTree[i].coursetiming.getLectureStartTime();
	 		  			int day=c.IndexTree[i].coursetiming.getLectureDayStructure();
			  			String Evenoddlec=c.IndexTree[i].coursetiming.getLectureEvenOdd();
	 		  			for(int k=0;k<st.getCourseNo();k++)
	 		 	       {
	 		 	    	   clash=Courselist.get(k).CheckClash(st.get_fullName(),st.get_matric(),start,end,day,Evenoddlec);
	 		 	    	   if(clash==true)
	 		 	    	   {
			 	    		   System.out.println("There is a clash on "+DaysofWeek[day]+"!");
	 		 	    		   break;
	 		 	    	   }
	 		 	       }
	 		  			if(clash==true)
	 		  			{
	 		  				break;
	 		  			}
	 		  					
	 		  }
	 		  
		    if(clash==true)
		    {
		    	//sc.nextLine();
		    	//System.out.print("Do you still want to continue (y/n)?");
		    	//String reply;
		        /// reply=sc.nextLine();
		        // if (reply.equals("n")) {
		        	 return false;
		        // }
		    }
	     }
	    boolean wait;
		int temp;
		for(int i=0;i<c.get_indexcount();i++)
		{
			System.out.println("Press " + i+" for  "+c.indexnamelist[i]+" with vacancy "+c.IndexTree[i].get_vacancy());
		}
		System.out.print("Confirm the number of the respective Index to register the student");
		
		temp=sc.nextInt();
		if(temp<c.get_indexcount())
		{
			
		}
		else
		{
			System.out.println("Registration failed");
			return false;
		}
		wait = c.IndexTree[temp].registerstudent(st.get_fullName(),st.get_matric());
		if(wait==true)
		{
			//c.IndexTree[temp].printQueueList();
			List studentList=FileMgr.readSerialObj("student.dat");
			Student s=null;
			for (int i = 0 ; i < studentList.size() ; i++) {
				s = (Student) studentList.get(i);
				//c.print_course_details();
				if (st.get_fullName().equals(s.get_fullName())){
					studentList.remove(s);
					//System.out.println("Yes");
					break;
				}
			}
			//s.printDetails();
			//c.print_course_details();
			s.AddtoCourselist(c);
			studentList.add(s);
			FileMgr.writeSerialObj(studentList, "student.dat");
			String message1="HELLO "+s.get_fullName()+"("+s.get_matric()+")"+" !\n CONGRATULATIONS YOU HAVE BEEN REGISTERED FOR THE FOLLOWING COURSE!\nCourse name: "+c.get_coursename()+"\nCourse code: "+c.get_coursecode()+"\nNumber of AU: "+c.get_academicunits()+"\n Index number: "+c.IndexTree[temp].get_Indexnumber()+"\n\n\nRegards\nSTARS TEAM";
			SendMailTLS.SENDMAIL(s.getEmail(), message1);
		}
		//c.print_allindex_studentlist();
		allCourse.add(c);
		FileMgr.writeSerialObj(allCourse, "course.dat");
		
		return true;
		
	}
	/**
	 * Method to register a student for an index in course
	 * @param st Student to add
	 * @param coursecode Coursecode to add
	 * @param index Index to add
	 * @return Boolean Status of Registration
	 */
	public static boolean regstudent(Student st,String coursecode,int index)
	{
		String tempCourseCode=coursecode;
		int tempIndexNum=index;
		List allCourse=FileMgr.readSerialObj("course.dat");
	    course c=null;
	    int flag=0;
	    for (int i = 0 ; i < allCourse.size() ; i++) {
	        c = (course) allCourse.get(i);
	        
	        //c.print_course_details();
	        if (c.get_coursecode().equals(tempCourseCode)){
	        	allCourse.remove(c);
	        	flag=1;
	        	//System.out.println("Yes");
	            break;
	        }
	    	}
	       if (flag==0){
	           System.out.println("Wrong course name.");
	           return false;
	       }
	       if(st.getCourseNo()>0)
	       {
		       List<course> studentcourselist=st.getCourseList();
		       for(int i=0;i<studentcourselist.size();i++)
		       {
		    	   course tempc=studentcourselist.get(i);
		    	   if(tempc.get_coursecode().equals(c.get_coursecode()) && tempc.get_coursename().equals(c.get_coursename()))
		    	   {
		    		   System.out.println("Course is already registered! Try again!");
		    		   return false;
		    	   }
		       }
	       }
	       c.print();
	       if(st.getCourseNo()>0)
	       {
		       Boolean clash=false;
		       List<course> Courselist=st.getCourseList();
		       int i=0;
		       for(i=0;i<c.get_indexcount();i++)
		       {
		    	   if(c.IndexTree[i].indexnumber==tempIndexNum)
		    	   {
		    		   break;
		    	   }
		       }
		       int type =  c.IndexTree[i].coursetiming.getCourseTypeStructure();
	 		 String[] daysofweek = new String[] {"","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	 		  switch(type)
	 		  {
	 		  	case 3: Calendar start3= c.IndexTree[i].coursetiming.getLabStartTime();
				  			Calendar end3 =	c.IndexTree[i].coursetiming.getLabEndTime();
				  			int day3=c.IndexTree[i].coursetiming.getLabDayStructure();
				  			String LabEvenODD = c.IndexTree[i].coursetiming.getLabEvenOdd();
				  			for(int k=0;k<st.getCourseNo();k++)
				 	        {
				 	    	   clash=Courselist.get(k).CheckClash(st.get_fullName(),st.get_matric(),start3,end3,day3,LabEvenODD);
				 	    	   if(clash==true)
				 	    	   {
				 	    		   System.out.println("There is a clash! on day " + daysofweek[day3] );
				 	    		   break;
				 	    	   }
				 	        }
				  			if(clash==true)
				  			{
				  				break;
				  			}
	 		  	case 2: Calendar start2= c.IndexTree[i].coursetiming.getTutorialStartTime();
				  			Calendar end2 =	c.IndexTree[i].coursetiming.getTutorialEndTime();
				  			int day2=c.IndexTree[i].coursetiming.getTutorialDayStructure();
				  			String TutEvenODD = c.IndexTree[i].coursetiming.getTutorialEvenOdd();
				  			for(int k=0;k<st.getCourseNo();k++)
				 	        {
				 	    	   clash=Courselist.get(k).CheckClash(st.get_fullName(),st.get_matric(),start2,end2,day2,TutEvenODD);
				 	    	   if(clash==true)
				 	    	   {
				 	    		   System.out.println("There is a clash! on day " + daysofweek[day2]);
				 	    		   break;
				 	    	   }
				 	        }
				  			if(clash==true)
				  			{
				  				break;
				  			}
	 		  	case 1: Calendar start= c.IndexTree[i].coursetiming.getLectureStartTime();
	 		  			Calendar end =	c.IndexTree[i].coursetiming.getLectureStartTime();
	 		  			int day=c.IndexTree[i].coursetiming.getLectureDayStructure();
			  			String LecEvenODD = c.IndexTree[i].coursetiming.getLectureEvenOdd();

	 		  			for(int k=0;k<st.getCourseNo();k++)
	 		 	       {
	 		 	    	   clash=Courselist.get(k).CheckClash(st.get_fullName(),st.get_matric(),start,end,day,LecEvenODD);
	 		 	    	   if(clash==true)
	 		 	    	   {
			 	    		   System.out.println("There is a clash! on day " + daysofweek[day]);
	 		 	    		   break;
	 		 	    	   }
	 		 	       }
	 		  			if(clash==true)
	 		  			{
	 		  				break;
	 		  			}
	 		  					
	 		  }
	 		  
		    if(clash==true)
		    {
		    	//sc.nextLine();
		    	//System.out.print("Do you still want to continue (y/n)?");
		    	//String reply;
		        // reply=sc.nextLine();
		         //if (reply.equals("n")) {
		        	 return false;
		         //}
		    }
	     }
	    boolean wait;
		int temp=-1;
		for(int i=0;i<c.get_indexcount();i++)
		{
			if(c.IndexTree[i].get_Indexnumber()==tempIndexNum)
			{
				temp=i;
				break;
			}
		}
		//System.out.print("Choose the number of the respective Index to register the student");
		
		temp=sc.nextInt();
		if(temp<c.get_indexcount())
		{
			
		}
		else if(temp==-1 || temp>c.get_indexcount())
		{
			System.out.println("Wrong index number! Registration failed");
			return false;
		}
		wait = c.IndexTree[temp].registerstudent(st.get_fullName(),st.get_matric());
		if(wait==true)
		{
			List studentList=FileMgr.readSerialObj("student.dat");
			Student s=null;
			for (int i = 0 ; i < studentList.size() ; i++) {
				s = (Student) studentList.get(i);
				//c.print_course_details();
				if (st.get_fullName().equals(s.get_fullName())){
					studentList.remove(s);
					//System.out.println("Yes");
					break;
				}
			}
			s.printDetails();
			//c.print_course_details();
			s.AddtoCourselist(c);
			studentList.add(s);
			FileMgr.writeSerialObj(studentList, "student.dat");
		}
		else
		{
			System.out.println("You have been added to the waitlist!");
		}
		c.print_allindex_studentlist();
		allCourse.add(c);
		FileMgr.writeSerialObj(allCourse, "course.dat");
		return true;
		
	}
	/**
	 * Method to deregister student
	 * @param name1 Name of Student to be deregistered
	 * @param c1 Course to be deregistered from
	 * @return Boolean Status of Deregistration
	 */
	public static boolean deregstudent(String name1,course c1)
	{
		StudentNode stu=null;
		int i=0,temp;
		c1.print_allindex_studentlist();
		System.out.println("Enter index number");
		int indextemp=sc.nextInt();
		String[] stnamelist=null;
		int stnumber=-1;
		for(i=0;i<c1.get_indexcount();i++)
		{
			if(c1.IndexTree[i].get_Indexnumber()==indextemp)
			{
				stnamelist=c1.IndexTree[i].get_studentlist();
				stnumber=c1.IndexTree[i].studentcounter;
				break;
			}
		}
		if(stnumber==-1)
		{
			System.out.println("The entered index does not exist");
			return false;
		}
		int tempflag=-1;
		for(i=0;i<stnumber;i++) {
			if(stnamelist[i].equals(name1))
			{
				tempflag=0;
			}
		}
		if(tempflag==-1)
		{
			System.out.println("The Name is not registered to this index");
			return false;
		}
		System.out.println("Confirm Deregistration of "+ name1+" Press 0 to Deregister or Press any number to cancel Deregistration");
		temp=sc.nextInt();
		if(temp!=0)
		{
			c1.print_allindex_studentlist();
			return false;
		}
		for(i=0;i<c1.get_indexcount();i++)
		{
			if(c1.IndexTree[i].get_Indexnumber()==indextemp)
			{
				stu=c1.IndexTree[i].deregisterstudent(name1);
		        //System.out.println("***"+stu.getName());
		        break;
			}
			
		}
		c1.print_allindex_studentlist();
		List pDetails = FileMgr.readSerialObj("course.dat");         
 	   System.out.println(pDetails.size());
        for (i = 0 ; i < pDetails.size() ; i++)
        {
           course c = (course)pDetails.get(i);
           c.print_course_details();
           if(c.get_coursecode().equals(c1.get_coursecode()))
           {
              pDetails.remove(c);
              break;
           }
        }
        if(stu.getName().equals("####")==false && stu.getMatric().equals("####")==false)
        {
        	//System.out.println("Writing it back");
        	int flag;
        	Student  stemp=null,sfinal=null;
        	List<Student> SDetails=FileMgr.readSerialObj("student.dat"); 
        	for(i=0;i<SDetails.size();i++)
        	{
        		stemp=SDetails.get(i);
        		if(stemp.get_fullName().equals(stu.getName()) && stemp.get_matric().equals(stu.getMatric()))
        		{
        			SDetails.remove(stemp);
        			sfinal=stemp;
        			break;
        		}
        	}
        	
        	sfinal.AddtoCourselist(c1);
			SDetails.add(sfinal);
			String message1="HELLO "+sfinal.get_fullName()+"("+sfinal.get_matric()+")"+" !\n CONGRATULATIONS YOU HAVE BEEN REGISTERED FOR THE FOLLOWING COURSE!\nCourse name: "+c1.get_coursename()+"\nCourse code: "+c1.get_coursecode()+"\nNumber of AU: "+c1.get_academicunits()+"\n Index number: "+c1.IndexTree[temp].get_Indexnumber()+"\n\n\nRegards\nSTARS TEAM";
			SendMailTLS.SENDMAIL(sfinal.getEmail(), message1);
			FileMgr.writeSerialObj(SDetails, "student.dat");
        }
        pDetails.add(c1);
        FileMgr.writeSerialObj(pDetails,"course.dat");
		return true;
	}
	/**
	 * Method for setting the Date and Time for Exam
	 * @return Exam Date
	 */
	public static Calendar getValidDT(){
        String date = "";
        Date parsedDate = null;
        boolean validDate = false;
        Calendar newD = Calendar.getInstance();
        do{
            System.out.print("Enter Exam Date and Time (dd/MM/yyyy HH:mm): ");
            date = sc.nextLine();
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            try{
                parsedDate = dateFormat.parse(date);
            }catch(ParseException e){
                System.out.println("Wrong format!");
                continue;
            }
            newD.setTime(parsedDate);
            validDate = true;
        }while(!validDate);
        return newD;
    }
	/**
	 * Method to set start time for Lab
	 * @return Start time for Lab
	 */
public static Calendar Labstart(){
	String time = "";
    Date parsedDate = null;
    boolean validTime = false;
    Calendar newT = Calendar.getInstance();
    do{
        System.out.print("Enter Time (HH:mm): ");
        time = sc.next();
        dateFormat = new SimpleDateFormat("HH:mm");
        try{
            parsedDate = dateFormat.parse(time);
        }catch(ParseException e){
            System.out.println("Wrong format!");
            continue;
        }
        newT.setTime(parsedDate);
        validTime = true;
    }while(!validTime);
    return newT;
}
/**
 * Method for changing integer to string for choosing Day
 * @param day Input from the User
 * @return Day
 */
public static String dayI2S(int day){
        String dayStr = null;
        switch(day){
            case 1: dayStr = "MON";
                break;
            case 2: dayStr = "TUE";
                break;
            case 3: dayStr = "WED";
                break;
            case 4: dayStr = "THU";
                break;
            case 5: dayStr = "FRI";
                break;
            case 6: dayStr = "SAT";
                break;
            case 7: dayStr = "SUN";
                break;
    }
    return dayStr;
    }
/**
 * Method for Date and Time
 */
static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
public static String dateString(Calendar cal){
    int day = cal.get(Calendar.DAY_OF_MONTH);
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int hour = cal.get(Calendar.HOUR_OF_DAY);
    int minute = cal.get(Calendar.MINUTE);
    return String.format("%02d/%02d/%04d %02d:%02d", day, month+1, year, hour, minute);
}

/**
 * Method to set Day of Lecture
 * @return Day of Lecture
 */
public static int setLectureDay() {
	System.out.println("Which day is your lecture held:");
	System.out.println("1.Monday");
	System.out.println("2.Tuesday");
	System.out.println("3.Wednesday");
	System.out.println("4.Thursday");
	System.out.println("5.Friday");
	System.out.println("6.Saturday");
	System.out.println("7.Sunday");
	System.out.println("Choose:");
	int lectureDayStructure = sc.nextInt();

	switch (lectureDayStructure) {

	case 1:
		System.out.println(" Selected Day is Monday");
		break;

	case 2:
		System.out.println(" Selected Day is Tuesday");
		break;

	case 3:
		System.out.println(" Selected Day is Wednesday");
		break;

	case 4:
		System.out.println(" Selected Day is Thrusday");
		break;

	case 5:
		System.out.println(" Selected Day is Friday");
		break;

	case 6:
		System.out.println(" Selected Day is Saturday");
		break;

	case 7:
		System.out.println(" Selected Day is Sunday");
		break;

	default:
		System.out.println(" None selected, Please select again");
		setLectureDay();
	}
	
	return lectureDayStructure;
}
/**
 * Method to set if Lecture is held weekly,odd or even weeks
 * @return weekly, odd or even
 */
public static String setLectureEvenOdd() {
	String lectureEvenOdd="aa";
	while(lectureEvenOdd.equals("0")==false && lectureEvenOdd.equals("1")==false && lectureEvenOdd.equals("2")==false)
	{
		System.out.println("\n Is the lesson held:\nPress 0 for Weekly\nPress 1 for odd\nPress 2 for even:\n ");
		lectureEvenOdd = sc.next();
		if(lectureEvenOdd.equals("0")==false && lectureEvenOdd.equals("1")==false && lectureEvenOdd.equals("2")==false)
		{
			System.out.println("Please select a valid option");
		}
	}
	return lectureEvenOdd;
}
/**
 * Method to set Venue for Lecture
 * @return Venue
 */
public static String setLectureVenue() {
	System.out.println(" Enter lecture venue:");
	String lectureVenue = sc.next();
	System.out.println(" Updated lecture venue: " + lectureVenue);
	
	return lectureVenue;
}


// ###############################TUTORIAL###################################

/**
 * Method to set Day of Tutorial
 * @return Day of Tutorial
 */
public static int setTutorialDay() {
	System.out.println("Which day is your tutorial held:");
	System.out.println("1.Monday");
	System.out.println("2.Tuesday");
	System.out.println("3.Wednesday");
	System.out.println("4.Thursday");
	System.out.println("5.Friday");
	System.out.println("6.Saturday");
	System.out.println("7.Sunday");
	System.out.println("Choose:");
	int tutorialDayStructure = sc.nextInt();

	switch (tutorialDayStructure) {

	case 1:
		System.out.println(" Selected Day is Monday");
		break;

	case 2:
		System.out.println(" Selected Day is Tuesday");
		break;

	case 3:
		System.out.println(" Selected Day is Wednesday");
		break;

	case 4:
		System.out.println(" Selected Day is Thrusday");
		break;

	case 5:
		System.out.println(" Selected Day is Friday");
		break;

	case 6:
		System.out.println(" Selected Day is Saturday");
		break;

	case 7:
		System.out.println(" Selected Day is Sunday");
		break;

	default:
		System.out.println(" None selected, Please select again");
		setTutorialDay();
	}
	
	return tutorialDayStructure;

}
/**
 * Method to set if Tutorial is held weekly,odd or even weeks
 * @return weekly, odd or even
 */
public static String setTutorialEvenOdd() {
	String tutorialEvenOdd="aa";
	while(tutorialEvenOdd.equals("0")==false && tutorialEvenOdd.equals("1")==false && tutorialEvenOdd.equals("2")==false)
	{
		System.out.println("\n Is the lesson held:\nPress 0 for Weekly\nPress 1 for odd\nPress 2 for even:\n ");
		tutorialEvenOdd = sc.next();
		if(tutorialEvenOdd.equals("0")==false && tutorialEvenOdd.equals("1")==false && tutorialEvenOdd.equals("2")==false)
		{
			System.out.println("Please select a valid option");
		}
	}
	return tutorialEvenOdd;
	}
/**
 * Method to set Venue for Lecture
 * @return Venue
 */
public static String setTutorialVenue() {
	System.out.println("\n Enter tutorial venue: ");
	String tutorialVenue = sc.next();
	System.out.println("Updated tutorial venue: " + tutorialVenue);
	
	return tutorialVenue;
}
/**
 * Method to set Day of Lab
 * @return Day of Lab
 */
public static int setLabDay() {
	System.out.println("Which day is your lab held:");
	System.out.println("1.Monday");
	System.out.println("2.Tuesday");
	System.out.println("3.Wednesday");
	System.out.println("4.Thursday");
	System.out.println("5.Friday");
	System.out.println("6.Saturday");
	System.out.println("7.Sunday");
	System.out.println("Choose:");
	int labDayStructure = sc.nextInt();
	switch (labDayStructure) {

	case 1:
		System.out.println(" Selected Day is Monday");
		break;

	case 2:
		System.out.println(" Selected Day is Tuesday");
		break;

	case 3:
		System.out.println(" Selected Day is Wednesday");
		break;

	case 4:
		System.out.println(" Selected Day is Thrusday");
		break;

	case 5:
		System.out.println(" Selected Day is Friday");
		break;

	case 6:
		System.out.println(" Selected Day is Saturday");
		break;

	case 7:
		System.out.println(" Selected Day is Sunday");
		break;

	default:
		System.out.println(" None selected, Please select again");
		setLabDay();
	}
	
	return labDayStructure;
}
/**
 * Method to set if Lab is held weekly,odd or even weeks
 * @return weekly, odd or even
 */
public static String setLabEvenOdd() {
	String labEvenOdd="aa";
	while(labEvenOdd.equals("0")==false && labEvenOdd.equals("1")==false && labEvenOdd.equals("2")==false)
	{
		System.out.println("\n Is the lesson held:\nPress 0 for Weekly\nPress 1 for odd\nPress 2 for even:\n ");
		labEvenOdd = sc.next();
		if(labEvenOdd.equals("0")==false && labEvenOdd.equals("1")==false && labEvenOdd.equals("2")==false)
		{
			System.out.println("Please select a valid option");
		}
	}
	return labEvenOdd;
}
/**
 * Method to set Venue for Lab
 * @return Venue
 */
public static String setLabVenue() {
	System.out.println("Enter lab venue: ");
	String labVenue = sc.next();
	System.out.println("Updated lab venue: " + labVenue);
	
	return labVenue;
}
/**
 * Method to Set Course Structure
 * @return Course Type
 */
public static int setCourseStructure() {
	System.out.println("What is the structure of your course:");
	System.out.println("1.	Lecture only");
	System.out.println("2.	Lecture and Tutorial");
	System.out.println("3.	Lecture, Tutorial and Lab");
	System.out.print("Choose:");
	int courseTypeStructure = sc.nextInt();
	
	return courseTypeStructure;

}
/**
 * 
 * @return temp
 */
public static int getval() {
	int temp=-1;
	boolean flag=false;
	do{
        try{
           temp=sc.nextInt();
           flag=true;
        }catch(Exception e){
            System.out.println("TryAgain!");
            continue;
        }

    }while(flag==false);
	return temp;
}
/**
 * Method used to set time for Lecture,Tutorial and Lab
 * @return Time
 */
public static Calendar getValidHM(){
	String time = "";
    Date parsedDate = null;
    boolean validTime = false;
    Calendar newT = Calendar.getInstance();
    do{
        System.out.print("Enter Time (HH:mm): ");
        time = sc.next();
        dateFormat = new SimpleDateFormat("HH:mm");
        try{
            parsedDate = dateFormat.parse(time);
        }catch(ParseException e){
            System.out.println("Wrong format!");
            continue;
        }
        newT.setTime(parsedDate);
        validTime = true;
    }while(!validTime);
    return newT;
}
}
