
import java.io.Serializable;
import java.util.Calendar;
import java.util.*;
/**
 * Represents the courses which are available
 * Admin can add 'n' number of courses and Student can register for 'n' number of courses
 * @author CE2002 SE1 Group 1
 * @version 1.0
 * @since 2020-11-01
 *
 */
public class course implements Serializable{
	private static final long serialVersionUID = 1L;
	private String coursename=null,coursecode=null,school=null,examdateparsed;;
	private int AU=3,indexcount=0;
	public int indexnamelist[]=new int[10];
	indexNO[] IndexTree;
	int flag=0;
	
	
	
	
	/**
	 * Creates Course with the below mentioned parameters
	 * @param coursename This is name of the Course
	 * @param coursecode This is the unique code assigned to a Course
	 * @param school The school offering the Course
	 * @param AU The number of AUs offered for the Course
	 * @param indexcount Number of indexes offered for the Course
	 * @param examdateparsed Date of Examination for the Course
	 */
	course(String coursename,String coursecode,String school,int AU,int indexcount,String examdateparsed)
	{	this.set_coursename(coursename);
		this.set_coursecode(coursecode);
		this.set_school(school);
		this.set_courseAU(AU);
		this.set_indexcount(indexcount);
		this.set_examdate(examdateparsed);
		System.out.println("Course Created!");
		this.IndexTree=new indexNO[10];
	}
	/**
	 * Method to check for Clash between 2 Courses
	 * @param name Name of Student
	 * @param matric Matriculation Number of Student
	 * @param start Start Time of Lesson
	 * @param end End Time of Lesson
	 * @param day Day of Lesson
	 * @param Evenodd Is lesson weekly or held odd/even
	 * @return Boolean Clash
	 */
	public boolean CheckClash(String name,String matric, Calendar start,Calendar end,int day,String Evenodd )
	{
		for(int i=0;i<this.indexcount;i++)
		{
			for(int j=0;j<this.IndexTree[i].studentcounter;j++)
			{
				if(this.IndexTree[i].studentnamelist[j].equals(name))
				{
					switch(this.IndexTree[i].coursetiming.getCourseTypeStructure())
					{
						case 3: if(this.IndexTree[i].coursetiming.getLabDayStructure()==day)
								{
									if(this.IndexTree[i].coursetiming.getLabStartTime().compareTo(start)<=0 && this.IndexTree[i].coursetiming.getLabEndTime().compareTo(start)>=0)
									{
										if(this.IndexTree[i].coursetiming.getLabStartTime().compareTo(end)<=0 && this.IndexTree[i].coursetiming.getLabEndTime().compareTo(end)>=0)
										{
											if(Evenodd.equals("0") || this.IndexTree[i].coursetiming.getLabEvenOdd().equals("0") || this.IndexTree[i].coursetiming.getLabEvenOdd().equals(Evenodd))
											{
												return true;
											}
										}
									}
								}
						case 2: if(this.IndexTree[i].coursetiming.getTutorialDayStructure()==day)
								{
									if(this.IndexTree[i].coursetiming.getTutorialStartTime().compareTo(start)<=0 && this.IndexTree[i].coursetiming.getTutorialEndTime().compareTo(start)>=0)
									{
										if(this.IndexTree[i].coursetiming.getTutorialStartTime().compareTo(end)<=0 && this.IndexTree[i].coursetiming.getTutorialStartTime().compareTo(end)>=0)
										{
											if(Evenodd.equals("0") || this.IndexTree[i].coursetiming.getTutorialEvenOdd().equals("0") || this.IndexTree[i].coursetiming.getTutorialEvenOdd().equals(Evenodd))
											{
												return true;
											}
										}
									}
								}
						case 1: if(this.IndexTree[i].coursetiming.getLectureDayStructure()==day)
								{
									if(this.IndexTree[i].coursetiming.getLectureStartTime().compareTo(start)<=0 && this.IndexTree[i].coursetiming.getLectureEndTime().compareTo(start)>=0)
									{
										if(this.IndexTree[i].coursetiming.getLectureStartTime().compareTo(end)<=0 && this.IndexTree[i].coursetiming.getLectureEndTime().compareTo(end)>=0)
										{
											if(Evenodd.equals("0") || this.IndexTree[i].coursetiming.getLectureEvenOdd().equals("0") || this.IndexTree[i].coursetiming.getLectureEvenOdd().equals(Evenodd))
											{
												return true;
											}
										}
									}
								}
					}
				}
			}
		}
		return false;
	}
	/**
	 * Method to swap course between 2 students registered to different indexes
	 * @param index1 Index registered to Student 1
	 * @param index2 Index registered to Student 2
	 * @param name1 Name of Student 1
	 * @param name2 Name of Student 2
	 */
	public void swapCourse(int index1, int index2, String name1, String name2){
		int i,j=-1,k=-1;
		for (i = 0; i < indexcount; i++){
			if(IndexTree[i].get_Indexnumber()==index1) j=i;
			if(IndexTree[i].get_Indexnumber()==index2) k=i;
			}
			
		if((j==-1)|(k==-1)){
			System.out.println("Check with administrators, no changes to swapping "+name1+" and "+name2+"..");
			return;
			}
		
		int p,q;
		p=IndexTree[j].find(name1);
		q=IndexTree[k].find(name2);
		//IndexTree[j].print_indexdetails();
		//IndexTree[k].print_indexdetails();
		if(IndexTree[j].swap(name2,name1,p)==false) return;
		if(IndexTree[k].swap(name1,name2,q)==false) return;
		System.out.println("Successful	swapping "+name1+" and "+name2+"..");
		}
	/**
	 * This methods sets the coursename
	 * @param coursename - Name of the Course
	 */
	public void set_coursename(String coursename)
	{
		this.coursename=coursename;
	}
	/**
	 * This methods sets the date of the exam
	 * @param examdateparsed Date of Exam
	 */
	public void set_examdate(String examdateparsed)
	{
		this.examdateparsed=examdateparsed;
	}
	/**
	 * This methods sets the number of indexes
	 * @param indexcount Number of Indexes
	 */
	public void set_indexcount(int indexcount)
	{
		this.indexcount=indexcount;
	}
	/**
	 * This methods sets the code of the Course
	 * @param coursecode Code of the Course
	 */
	public void set_coursecode(String coursecode)
	{
		this.coursecode=coursecode;
	}
	
	
	/**
	 * This methods sets the number of AUs for a Course
	 * @param au Number of AUs for a Course
	 */
	public void set_courseAU(int au)
	{
		this.AU=au;
	}
	/**
	 * This methods sets the School offering the course
	 * @param school School offering the course
	 */
	public void set_school(String school)
	{
		this.school=school;
	}
	/**
	 * This methods gets the name of the Course
	 * @return Name of the Course
	 */
	public String get_coursename()
	{
		return this.coursename;
	}
	/**
	 * This methods gets the Course Code
	 * @return Course Code
	 */
	public String get_coursecode()
	{
		return this.coursecode;
	}
	/**
	 * This methods gets the number of AUs for a course
	 * @return Number of AUs
	 */
	public int get_academicunits()
	{
		return this.AU;
	}
	/**
	 * This methods gets the number of indexes created for a Course
	 * @return the number of indexes
	 */
	public int get_indexcount()
	{
		return this.indexcount;
	}
/**
 * This methods gets the School offering the Course
 * @return School Name
 */
	public String get_school()
	{
		return this.school;
	}
	/**
	 * This methods gets the date of the Exam
	 * @return Date of Exam
	 */
	public String get_examdate()
	{
		return this.examdateparsed;
	}
/**
 * 	Prints the details of the Course
 */
public void print() {
		
		System.out.println("1. Course Code: "+ this.coursecode);
		System.out.println("2. Course Name: "+ this.coursename);
		System.out.println("3. Academic Units: "+ this.AU);
		System.out.println("4. Exam Date: "+ this.examdateparsed);
		System.out.println("5. School: "+ this.school);
		System.out.println("6. Number of Indexes: "+ this.indexcount);
		System.out.print("Index List: ");
		for (int i = 0; i < this.indexcount; i++) 
	         {System.out.print(this.indexnamelist[i]+"\t"); }
		System.out.println();
		//this.print_course_details();
	}
/**
 * Prints Index Vacancy of all indexes for a particular course
 */
public void print_all_index_vacancy()
{	System.out.println("Index Vacancy Information");
	for (int i = 0; i < this.indexcount; i++) 
	{
		System.out.println(this.IndexTree[i].get_Indexnumber()+"\t"+this.IndexTree[i].get_vacancy()); 
	}
	System.out.println();
}
/**
 * Prints Course Details 		
 */
public void print_course_details()
{	
	System.out.println("Course code " + this.coursecode);
	System.out.println("Course name " + this.coursename);
	System.out.println("School name " + this.school);
	System.out.println("Au " + this.AU);
	//System.out.println("INDEX NUMBER " + this.in);

	//System.out.println("Index Information");
	int i;
	/*for(i=0;i<this.indexcount;i++)
	{
		this.IndexTree[i].print_indexdetails();
	}*/
}
/**
 * Prints individual Index Student list 
 * @param i Position of the Index in the Index Tree
 */
public void print_oneindex_studentlist(int i)
{	
	if(this.IndexTree[i].studentcounter>0)
	{
	System.out.println(i+" Index: "+ this.IndexTree[i].get_Indexnumber()+"\nStudent Count: "+this.IndexTree[i].studentcounter+" Student Vacancy: "+this.IndexTree[i].get_vacancy());
	this.IndexTree[i].printstudentlist();
	}
	else
	{
		System.out.println(i+" "+ this.IndexTree[i].get_Indexnumber()+"\nStudent Count: "+this.IndexTree[i].studentcounter+" Student Vacancy: "+this.IndexTree[i].get_vacancy());
		System.out.println("Student List Empty");
	}
}
/**
 * Prints Student List for all indexes in a particular Course
 */
public void print_allindex_studentlist()
{	int i=0,temp;
	System.out.println("Printing all students enrolled in this course");
	System.out.println(this.coursename);
	for(i=0;i<this.indexcount;i++)
	{	if(this.IndexTree[i].studentcounter>0)
		{
		System.out.println(i+" "+ this.IndexTree[i].get_Indexnumber()+"\nStudent Count: "+this.IndexTree[i].studentcounter+" Student Vacancy: "+this.IndexTree[i].get_vacancy());
		this.IndexTree[i].printstudentlist();
		}
		else
		{
			System.out.println(i+" "+ this.IndexTree[i].get_Indexnumber()+"\nStudent Count: "+this.IndexTree[i].studentcounter+" Student Vacancy: "+this.IndexTree[i].get_vacancy());
			System.out.println("Student List Empty");
		}
	}
	
	System.out.println();
}
/**
 * 	Removes index from Course
 * @param indextoremove Removes index from Course
 */
public void remove_index(int indextoremove) {
	
	int match=0;
	for (int j = 0; j < this.indexcount; j++) 
    {	
		if(indextoremove==this.indexnamelist[j])
    	{	match=j;
			break;
    	}
	}
	
	for (int j = match; j < this.indexcount-1; j++) 
    {	
		this.IndexTree[j]=this.IndexTree[j+1];
		this.indexnamelist[j]=this.indexnamelist[j+1];
	}
	this.indexcount--;
}

/**
 * To check the index number's Position
 * @param indextocheck Checks the Index Position
 * @return Position of Index
 */
public int check_index_pos(int indextocheck)
{
	for (int j = 0; j < this.indexcount; j++) 
    {	
		if(indextocheck==this.indexnamelist[j])
    	{
			return j;
    	}
	}
	
	return -1;
}
/**
 * Checks Index
 * @param indextocheck Checks the Index Position
 * @return Boolean
 */
public boolean check_index(int indextocheck)
{
	for (int j = 0; j < this.indexcount-1; j++) 
    {	
		if(indextocheck==this.indexnamelist[j])
    	{
			return false;
    	}
	}
	
	return true;
}
/**
 * Registers a Student
 * @param i Position of Index
 * @param name1 Name of Student to register
 * @param matric1 matric Number of Student to register
 */
public void regstudent(int i,String name1, String matric1)
{	if(IndexTree[i].get_vacancy()>0)
	{
	this.IndexTree[i].registerstudent(name1,matric1);
	System.out.println("Registration successful");
	}
	else
	{
		System.out.println("Registration unsuccessful");
	}

	
}
/**
 * Deregisters a Student
 * @param i Position of Index
 * @param name1 Name of Student to register
 */
public void deregstudent(int i,String name1)
{	
	this.IndexTree[i].deregisterstudent(name1);
	
}

}