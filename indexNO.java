import java.io.Serializable;
import java.util.Calendar;
/**
 * This Class repsents the index of a course and one course can have infinite number of indexes 
 * @author CE2002 SE1 Group 1
 * @version 1.0
 * @since 2020-11-01
 *
 */
public class indexNO implements Serializable {
	private static final long serialVersionUID = 1L;
	int indexnumber, maxcapacity, vacancy, studentcounter = 0, qcurrent = -1;
	String[] studentnamelist = new String[50];
	/**
	 * Waiting list if course is already full
	 */
	private WaitingList waitline;
	coursetype coursetiming = null;

	indexNO(int indexlabel, int maxcapacity, int coursestruct, Calendar lecturestart, Calendar lectureEnd, int lectureDayStructure, String lectureEvenOdd, String lectureVenue) {
		this.indexnumber = indexlabel;
		this.maxcapacity = maxcapacity;
		this.vacancy = this.maxcapacity - this.studentcounter;
		coursetiming = new coursetype(coursestruct, lecturestart, lectureEnd, lectureDayStructure, lectureEvenOdd, lectureVenue);
	}

	indexNO(int indexlabel, int maxcapacity, int coursestruct, Calendar lecturestart, Calendar lectureEnd, int lectureDayStructure, String lectureEvenOdd, String lectureVenue
			, Calendar Tutorialstart, Calendar TutorialEnd, int TutorialDayStructure, String TutorialEvenOdd, String TutorialVenue) {
		this.indexnumber = indexlabel;
		this.maxcapacity = maxcapacity;
		this.vacancy = this.maxcapacity - this.studentcounter;
		coursetiming = new coursetype(coursestruct, lecturestart, lectureEnd, lectureDayStructure, lectureEvenOdd, lectureVenue
				, Tutorialstart, TutorialEnd, TutorialDayStructure, TutorialEvenOdd, TutorialVenue);
	}

	indexNO(int indexlabel, int maxcapacity, int coursestruct, Calendar lecturestart, Calendar lectureEnd, int lectureDayStructure, String lectureEvenOdd, String lectureVenue
			, Calendar Tutorialstart, Calendar TutorialEnd, int TutorialDayStructure, String TutorialEvenOdd, String TutorialVenue
			, Calendar Labstart, Calendar LabEnd, int LabDayStructure, String LabEvenOdd, String LabVenue) {
		this.indexnumber = indexlabel;
		this.maxcapacity = maxcapacity;
		this.vacancy = this.maxcapacity - this.studentcounter;
		coursetiming = new coursetype(coursestruct, lecturestart, lectureEnd, lectureDayStructure, lectureEvenOdd, lectureVenue
				, Tutorialstart, TutorialEnd, TutorialDayStructure, TutorialEvenOdd, TutorialVenue
				, Labstart, LabEnd, LabDayStructure, LabEvenOdd, LabVenue);
	}
/**
 * Capacity,Vacancy and Waiting List details of an Index
 */
	public void print_indexdetails() {
		System.out.println("Index Number: " + this.indexnumber);
		System.out.println("Max Capacity: " + this.maxcapacity);
		System.out.println("Vacancy: " + this.vacancy);
		if (qcurrent > 0) {
			System.out.println("Students in Queue: " + qcurrent);
		}
		System.out.println();
		this.coursetiming.printcourseinfo();
		;
	}

/**
 * Getting Index Number
 * @return Index Number
 */
	public int get_Indexnumber() {
		return this.indexnumber;
	}
/**
 * Getting Max Capacity of an Index
 * @return Max Capacity
 */
	public int get_maxcapacity() {
		return this.maxcapacity;
	}
/**
 * Get Vacancy of Index
 * @return Vacancy of Index
 */
	public int get_vacancy() {
		return this.vacancy;
	}
	/**
	 * Get current waiting list
	 * @return waiting list
	 */
	public int get_qcurrent(){
		return qcurrent;
	}

/**
 * Print student list of index
 */
	public void printstudentlist() {
		int i;
		for (i=0; i < this.studentcounter; i++) {
			System.out.println(this.studentnamelist[i]);
		}
	}
/**
 * Getting Student list of Index
 * @return Student List of Index
 */
	public String[] get_studentlist() {
		return this.studentnamelist;
	}

/**
 * Registration of Student into an Index
 * @param name Name of the Student
 * @param matric Matriculation number of the Student
 * @return true if there is vacancy else return false if added to waiting list
 */
	public Boolean registerstudent(String name,String matric) {//

		if (studentcounter <maxcapacity) {
			this.studentnamelist[this.studentcounter] = name;
			this.studentcounter++;
			this.vacancy = this.maxcapacity - this.studentcounter;
			return true;
		}
		else {
			StudentNode node = new StudentNode(name,matric);
			if (qcurrent == -1) {
				waitline = new WaitingList(indexnumber);
				qcurrent++;
			}
			waitline.addWaiting(node);
			qcurrent++;
			return false;
		}
	}
/**
 * Deregistration of Student from an Index
 * @param name Name of the Student
 * @return Student Node if added to Waiting List
 */
	public StudentNode deregisterstudent(String name) {
		StudentNode challenger;
		int i;
		boolean flag = false;
		
		i=find(name);													//calls find
		if(i!=-1) 
		{
			//System.out.println("FOUND");
			flag = true;
		}
		//System.out.print(this.indexnumber+"!!!!!!!!!!"+this.qcurrent);
		if (flag == false) {
			//System.out.println("INSIDE FALSE");
			if (qcurrent>0)
			{
				flag = waitline.removeByName(name);  
			}//Remove from queue
			if (flag == true) {
				qcurrent--;
			} else {                                                    //Not in list or queue, quit
				System.out.println("Deregistration unsuccessful");
			}
			return challenger = new StudentNode("####", "####");
		}
		else {
			//System.out.print(this.indexnumber+"!!!!!!!!!!"+this.qcurrent);
			//System.out.println("INSIDE ELSE");
			for (;i < this.studentcounter - 1; i++)        //Shifting List
			{
				this.studentnamelist[i] = this.studentnamelist[i + 1];
			}
			
			this.studentcounter--;
			this.vacancy = this.maxcapacity - this.studentcounter;
			//System.out.print(this.indexnumber+"!!!!!!!!!!"+this.qcurrent);
			if (qcurrent > 0)                                                //moving from queue
			{
				//System.out.println("INSIDE qcurrent");
				challenger = waitline.endWaiting();
				qcurrent--;
				studentnamelist[studentcounter] = challenger.getName();
				studentcounter++;
				this.vacancy = this.maxcapacity - this.studentcounter;
				
			} 
			else 
			{
				challenger = new StudentNode("####", "####");
			}
			System.out.println("Deregistration successful");
			return challenger;
		}

	}
/**
 * Finding Student Position into course from Student Name List list
 * @param name Name of the Student
 * @return Student position else -1 if not equal
 */
	public int find(String name) {
		int num;
		for(num=0;num<this.studentcounter;num++)                            //Checking list
		{if (name.equals(this.studentnamelist[num])) return num;}
		return -1;
		}

	/**
	 * Swaps names in 2 Student Name Lists
	 * @param added Name of Student added
	 * @param removed Name of Student Removed
	 * @param position Position of Student Added
	 * @return Boolean value on whether swap was successful or not
	 */
	public Boolean swap(String added,String removed,int position){
		if(studentnamelist[position].equals(removed)==false)
		{
			System.out.println("Check with administrators, no changes to index "+indexnumber);
			return false;
		}
		else
		{
			studentnamelist[position]=added;
			return true;
		}
	}
	/**
	 * Prints Queue
	 */
	public void printQueueList(){waitline.printQueue();
	System.out.println("&&&&&&&&&&&"+this.qcurrent);
	}

}