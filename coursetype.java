
import java.io.Serializable;
import java.util.Calendar;



/**
 * Creates Coursetype.
 * Admin chooses if course is held as lecture, lecture+tutorial or as a lecture+tutorial+lab
 * @author CE2002 SE1 Group 1
 * @version 1.0
 * @since 2020-11-01 
 *
 */
public class coursetype implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Initialisation of lecture venue
	 */
	private String lectureVenue = null;
	/**
	 * Initialisation of tutorial venue
	 */
	private String tutorialVenue = null;
	/**
	 * Initialisation of lab venue
	 */
	private String labVenue = null;
	/**
	 * Initialisation of lecture weekly/odd or even
	 */
	private String lectureEvenOdd = null;
	/**
	 * Initialisation of tutorial weekly/odd or even
	 */
	private String tutorialEvenOdd = null;
	/**
	 * Initialisation of lab weekly/odd or even
	 */
	private String labEvenOdd = null;
/**
 * Initialisation of Course Type Structure
 */
	private int courseTypeStructure = 0;
	/**
	 * Initialisation of Lecture Type Structure
	 */
	private int lectureDayStructure = 0;
	/**
	 * Initialisation of Tutorial Type Structure
	 */
	private int tutorialDayStructure = 0;
	/**
	 * Initialisation of Lab Type Structure
	 */
	private int labDayStructure = 0;
	/**
	 * Initialisation of Calendar
	 */
	private Calendar lectureStartTime, lectureEndTime, tutorialStartTime, tutorialEndTime, labStartTime, labEndTime;
	
	
	coursetype(int coursestruct,Calendar lecturestart, Calendar lectureEnd,int lectureDayStructure,String lectureEvenOdd, String lectureVenue) {
		//System.out.println("Creating CourseType");
		this.courseTypeStructure=coursestruct;
		//System.out.println("Selected Course Structure");
		courseTypeSelection("Lecture",lecturestart,lectureEnd,lectureDayStructure,lectureEvenOdd,lectureVenue);
	}
	
	coursetype(int coursestruct,Calendar lecturestart, Calendar lectureEnd,int lectureDayStructure,String lectureEvenOdd, String lectureVenue
			,Calendar Tutorialstart, Calendar TutorialEnd,int TutorialDayStructure,String TutorialEvenOdd, String TutorialVenue) {
		//System.out.println("Creating CourseType");
		this.courseTypeStructure=coursestruct;
		//System.out.println("Selected Course Structure");
		courseTypeSelection("1","Tutorial",lecturestart,lectureEnd,lectureDayStructure,lectureEvenOdd,lectureVenue
				,Tutorialstart,TutorialEnd,TutorialDayStructure,TutorialEvenOdd,TutorialVenue);
	}
	
	coursetype(int coursestruct,Calendar lecturestart, Calendar lectureEnd,int lectureDayStructure,String lectureEvenOdd, String lectureVenue
			,Calendar Tutorialstart, Calendar TutorialEnd,int TutorialDayStructure,String TutorialEvenOdd, String TutorialVenue
			,Calendar Labstart, Calendar LabEnd,int LabDayStructure,String LabEvenOdd, String LabVenue) {
		//System.out.println("Creating CourseType");
		this.courseTypeStructure=coursestruct;
		//System.out.println("Selected Course Structure");
		courseTypeSelection("Lecture","Tutorial","Lab",lecturestart,lectureEnd,lectureDayStructure,lectureEvenOdd,lectureVenue
				,Tutorialstart,TutorialEnd,TutorialDayStructure,TutorialEvenOdd,TutorialVenue
				,Labstart,LabEnd,LabDayStructure,LabEvenOdd,LabVenue);
	}


	// ###############################LECTURE###################################
/**
 * Creating Lecture Type
 * @param lecture Type of Lesson
 * @param lecturestart Start Time of Lecture
 * @param lectureEnd End Time of Lecture
 * @param lectureDayStructure Day of Lecture
 * @param lectureEvenOdd Is lecture held weekly, odd or even
 * @param lectureVenue Venue of Lecture
 */
		public void courseTypeSelection(String lecture,Calendar lecturestart, Calendar lectureEnd,int lectureDayStructure,String lectureEvenOdd, String lectureVenue) {
			//System.out.println(" Enter "+ lecture +" Details: ");
			
			setLectureStart(lecturestart);
			setLectureEnd(lectureEnd);
			setLectureDay(lectureDayStructure);
			setLectureEvenOdd1(lectureEvenOdd);
			setLectureVenue1(lectureVenue);
		}
/**
 * Setting Start Time of Lecture
 * @param lecturestart Start Time of Lecture
 */
		public void setLectureStart(Calendar lecturestart) {
			
			this.lectureStartTime = lecturestart;
			//System.out.println("Lecture start time: " + courseinterface.TimeString(this.lectureStartTime));
		}
/**
 * Setting End Time of Lecture
 * @param lectureEnd End Time of Lecture
 */
		public void setLectureEnd(Calendar lectureEnd) {
			
			this.lectureEndTime =lectureEnd;

			//System.out.println("Lecture end time: " + courseinterface.TimeString(this.lectureEndTime));
		}
/**
 * 
 * @param lectureDayStructure Day of Lecture
 */
		public void setLectureDay(int lectureDayStructure) {
			this.lectureDayStructure=lectureDayStructure;

		}

		public void setLectureEvenOdd1(String lectureEvenOdd) {
			this.lectureEvenOdd=lectureEvenOdd;
		}

		public void setLectureVenue1(String lectureVenue) {
			this.lectureVenue=lectureVenue;
		}

		// ###############################TUTORIAL###################################

		/**
		 * Creating Lecture+Tutorial Type
		 * @param lecture Type of Lesson
		 * @param tutorial Type of Lesson
		 * @param lecturestart Start Time of Lecture
		 * @param lectureEnd End Time of Lecture
		 * @param lectureDayStructure Day of Lecture
		 * @param lectureEvenOdd Is Lecture held weekly, odd or even
		 * @param lectureVenue Venue of Lecture
		 * @param Tutorialstart Start Time of Tutorial
		 * @param TutorialEnd End Time of Tutorial
		 * @param TutorialDayStructure Day of Tutorial
		 * @param TutorialEvenOdd Is Tutorial held weekly, odd or even
		 * @param TutorialVenue Venue of Tutorial
		 */
		public void courseTypeSelection(String lecture, String tutorial,Calendar lecturestart, Calendar lectureEnd,int lectureDayStructure,String lectureEvenOdd, String lectureVenue
				,Calendar Tutorialstart, Calendar TutorialEnd,int TutorialDayStructure,String TutorialEvenOdd, String TutorialVenue) {
			//System.out.println(" Enter "+ tutorial +" Details: ");
			courseTypeSelection("Lecture",lecturestart,lectureEnd,lectureDayStructure,lectureEvenOdd,lectureVenue);
			setTutorialStart(Tutorialstart);
			setTutorialEnd(TutorialEnd);
			setTutorialDay(TutorialDayStructure);
			setTutorialEvenOdd1(TutorialEvenOdd);
			setTutorialVenue1(TutorialVenue);

		}

		public void setTutorialStart(Calendar Tutorialstart) {
			
			this.tutorialStartTime = Tutorialstart;
			
		}

		public void setTutorialEnd(Calendar TutorialEnd) {
			
			this.tutorialEndTime = TutorialEnd;
			
		}

		public void setTutorialDay(int TutorialDayStructure) {
			this.tutorialDayStructure=TutorialDayStructure;

		}

		public void setTutorialEvenOdd1(String TutorialEvenOdd) {
			this.tutorialEvenOdd=TutorialEvenOdd;
		}

		public void setTutorialVenue1(String TutorialVenue) {
			this.tutorialVenue=TutorialVenue;
		}

		// ###############################LAB###################################

		/**
		 * Creating Lecture+Tutorial+Lab
		 * @param lecture Type of Lesson
		 * @param tutorial Type of Lesson
		 * @param lab Type of Lesson
		 * @param lecturestart Start Time of Lecture
		 * @param lectureEnd End Time of Lecture
		 * @param lectureDayStructure Day of Lecture
		 * @param lectureEvenOdd Is Lecture held weekly, odd or even
		 * @param lectureVenue Venue of Lecture
		 * @param Tutorialstart Start Time of Tutorial
		 * @param TutorialEnd End Time of Tutorial
		 * @param TutorialDayStructure Day of Tutorial
		 * @param TutorialEvenOdd Is Tutorial held weekly, odd or even
		 * @param TutorialVenue Venue of Tutorial
		 * @param Labstart Start Time of Lab
		 * @param LabEnd End Time of Lab
		 * @param LabDayStructure Day of Lab
		 * @param LabEvenOdd Is Lab held weekly, odd or even
		 * @param LabVenue Venue of Lab
		 */
		public void courseTypeSelection(String lecture, String tutorial, String lab,Calendar lecturestart, Calendar lectureEnd,int lectureDayStructure,String lectureEvenOdd, String lectureVenue
				,Calendar Tutorialstart, Calendar TutorialEnd,int TutorialDayStructure,String TutorialEvenOdd, String TutorialVenue
				,Calendar Labstart, Calendar LabEnd,int LabDayStructure,String LabEvenOdd, String LabVenue) {
			//System.out.println(" Enter "+ lab +" Details: ");
			courseTypeSelection("Lecture","Tutorial",lecturestart,lectureEnd,lectureDayStructure,lectureEvenOdd,lectureVenue
					,Tutorialstart,TutorialEnd,TutorialDayStructure,TutorialEvenOdd,TutorialVenue);
			setLabStart(Labstart);
			setLabEnd(LabEnd);
			setLabDay(LabDayStructure);
			setLabEvenOdd1(LabEvenOdd);
			setLabVenue1(LabVenue);

		}

/**
 * Setting Lab Start Time
 * @param Labstart Lab Start Time
 */
		public void setLabStart(Calendar Labstart) {
			
			this.labStartTime = Labstart;
		}
/**
 * Setting Lab End Time
 * @param LabEnd Lab End Time
 */
		public void setLabEnd(Calendar LabEnd) {
			
			this.labEndTime = LabEnd;
		}
/**
 * Setting Day of Lab
 * @param LabDayStructure Day of Lab
 */
		public void setLabDay(int LabDayStructure) {
			this.labDayStructure=LabDayStructure;
		}
/**
 * Setting Lab Weekly or Odd/Even
 * @param LabEvenOdd Is Lab Weekly/Odd/Even?
 */
		public void setLabEvenOdd1(String LabEvenOdd) {
			this.labEvenOdd=LabEvenOdd;
		}
/**
 * Setting Lab Venue
 * @param LabVenue Lab Venue
 */
		public void setLabVenue1(String LabVenue) {
			this.labVenue=LabVenue;
		}
/**
 * Getting Lecture Venue
 * @return Lecture Venue
 */
		public String getLectureVenue() {
			return this.lectureVenue;
		}
/**
 * Setting Lecture Venue
 * @param lectureVenue Lecture Venue
 */
		public void setLectureVenue(String lectureVenue) {
			this.lectureVenue = lectureVenue;
		}
/**
 * Getting Tutorial Venue
 * @return Tutorial Venue
 */
		public String getTutorialVenue() {
			return this.tutorialVenue;
		}
/**
 * Setting Tutorial Venue
 * @param tutorialVenue Tutorial Venue
 */
		public void setTutorialVenue(String tutorialVenue) {
			this.tutorialVenue = tutorialVenue;
		}
/**
 * Get Lab Venue
 * @return Lab Venue
 */
		public String getLabVenue() {
			return this.labVenue;
		}
/**
 * Setting Lab Venue
 * @param labVenue Lab Venue
 */
		public void setLabVenue(String labVenue) {
			this.labVenue = labVenue;
		}
/**
 * Get Lecture Weekly/Odd/Even
 * @return Lecture Weekly/Odd/Even
 */
		public String getLectureEvenOdd() {
			return this.lectureEvenOdd;
		}
/**
 * Setting Lecture Weekly/Odd/Even
 * @param lectureEvenOdd Is Lecture Weekly/Odd/Even?
 */
		public void setLectureEvenOdd(String lectureEvenOdd) {
			this.lectureEvenOdd = lectureEvenOdd;
		}
/**
 * Getting Tutorial Weekly/Odd/Even
 * @return Tutorial Weekly/Odd/Even
 */
		public String getTutorialEvenOdd() {
			return this.tutorialEvenOdd;
		}
/**
 * Setting Tutorial Weekly/Odd/Even
 * @param tutorialEvenOdd Is Tutorial Weekly/Odd/Even?
 */
		public void setTutorialEvenOdd(String tutorialEvenOdd) {
			this.tutorialEvenOdd = tutorialEvenOdd;
		}
/**
 * Getting Lab Weekly/Odd/Even
 * @return Lab Weekly/Odd/Even
 */
		public String getLabEvenOdd() {
			return this.labEvenOdd;
		}
/**
 * Setting Lab Weekly/Odd/Even
 * @param labevEnOdd Is Lab Weekly/Odd/Even?
 */
		public void setLabEvenOdd(String labevEnOdd) {
			this.labEvenOdd = labevEnOdd;
		}
/**
 * Getting Course Type Structure
 * @return Course Type Structure
 */
		public int getCourseTypeStructure() {
			return this.courseTypeStructure;
		}
/**
 * Setting Course Type Structure
 * @param courseTypeStructure Structure of Course
 */
		public void setCourseTypeStructure(int courseTypeStructure) {
			this.courseTypeStructure = courseTypeStructure;
		}
/**
 * Getting Day of Lecture
 * @return Lecture Day Structure
 */
		public int getLectureDayStructure() {
			return this.lectureDayStructure;
		}
/**
 * Setting Day of Lecture
 * @param lectureDayStructure Day of Lecture
 */
		public void setLectureDayStructure(int lectureDayStructure) {
			this.lectureDayStructure = lectureDayStructure;
		}
/**
 * Getting Day of Tutorial
 * @return Tutorial Day
 */
		public int getTutorialDayStructure() {
			return this.tutorialDayStructure;
		}
/**
 * Setting Day of Tutorial
 * @param tutorialDayStructure Day of Tutorial
 */
		public void setTutorialDayStructure(int tutorialDayStructure) {
			this.tutorialDayStructure = tutorialDayStructure;
		}
/**
 * Getting Day of Lab
 * @return Day of Lab
 */
		public int getLabDayStructure() {
			return this.labDayStructure;
		}
/**
 * Setting Day of Lab
 * @param labDayStructure Day of Lab
 */
		public void setLabDayStructure(int labDayStructure) {
			this.labDayStructure = labDayStructure;
		}

		
/**
 * Get Lecture Start Time
 * @return Lecture Start Time
 */
		public Calendar getLectureStartTime() {
			return lectureStartTime;
		}
/**
 * Set Lecture Start Time
 * @param lectureStartTime Lecture Start Time
 */
		public void setLectureStartTime(Calendar lectureStartTime) {
			this.lectureStartTime = lectureStartTime;
		}
/**
 * Get Lecture End Time
 * @return Lecture End Time
 */
		public Calendar getLectureEndTime() {
			return lectureEndTime;
		}
/**
 * Set Lecture End Time
 * @param lectureEndTime Lecture End Time
 */
		public void setLectureEndTime(Calendar lectureEndTime) {
			this.lectureEndTime = lectureEndTime;
		}
/**
 * Get Tutorial Start Time
 * @return Tutorial Start Time
 */
		public Calendar getTutorialStartTime() {
			return tutorialStartTime;
		}
/**
 * Set Tutorial Start Time
 * @param tutorialStartTime Tutorial Start Time
 */
		public void setTutorialStartTime(Calendar tutorialStartTime) {
			this.tutorialStartTime = tutorialStartTime;
		}
/**
 * Get Tutorial End Time
 * @return Tutorial End Time
 */
		public Calendar getTutorialEndTime() {
			return tutorialEndTime;
		}
/**
 * Set Tutorial End Time
 * @param tutorialEndTime Tutorial End Time
 */
		public void setTutorialEndTime(Calendar tutorialEndTime) {
			this.tutorialEndTime = tutorialEndTime;
		}
/**
 * Get Lab Start Time
 * @return Lab Start Time
 */
		public Calendar getLabStartTime() {
			return labStartTime;
		}
/**
 * Set Lab Start Time
 * @param labStartTime Lab Start Time
 */
		public void setLabStartTime(Calendar labStartTime) {
			this.labStartTime = labStartTime;
		}
/**
 * Get Lab End Time
 * @return Lab End Time
 */
		public Calendar getLabEndTime() {
			return labEndTime;
		}
/**
 * Set Lab End Time
 * @param labEndTime Lab End Time
 */
		public void setLabEndTime(Calendar labEndTime) {
			this.labEndTime = labEndTime;
		}
/*
		public CalendarTimeTable getLessontimecal() {
			return lessontimecal;
		}

		public void setLessontimecal(CalendarTimeTable lessontimecal) {
			this.lessontimecal = lessontimecal;
		}
*/		
		/**
		 * Printing the Course Type Information
		 */
		public void printcourseinfo() {

			System.out.println("User Entered Details ");
			System.out.println("Course Type Structure " + this.courseTypeStructure);
			int flag=-1;
			if (this.courseTypeStructure == 1) {
				print("Lecture");
			} else if (this.courseTypeStructure == 2) {
				print("Lecture", "Tutorial");
			} else if (this.courseTypeStructure == 3) {
				print("Lecture", "Tutorial", "Lab");
			}
		}
/*		
		public void print() {

			System.out.println("User Entered Details ");
			System.out.println("Course Type Structure " + this.courseTypeStructure);
			int flag=-1;
			if (this.courseTypeStructure == 1) {
				print("Lecture");
			} else if (this.courseTypeStructure == 2) {
				print("Lecture", "Tutorial");
			} else if (this.courseTypeStructure == 3) {
				print("Lecture", "Tutorial", "Lab");
			}
			System.out.println("Press 0 to Confirm all details are correct else press the corresponding number to make changes");
			if (this.courseTypeStructure == 1) {
				flag=courseinterface.getval();
				switch(flag)
				{
				case 1:this.setLectureStart();
				print();
					break;
				case 2:this.setLectureEnd();
				print();
					break;
				case 3:this.setLectureDay();
				print();
					break;
				case 4:this.setLectureEvenOdd();
				print();
					break;
				case 5:this.setLectureVenue();
				print();
					break;
				case 0:
					break;
				default:
					break;
				}
				
			} else if (this.courseTypeStructure == 2) {
				flag=courseinterface.getval();
				switch(flag)
				{
				case 1:this.setLectureStart();
				print();
					break;
				case 2:this.setLectureEnd();
				print();
					break;
				case 3:this.setLectureDay();
				print();
					break;
				case 4:this.setLectureEvenOdd();
				print();
					break;
				case 5:this.setLectureVenue();
				print();
					break;
				case 6:this.setTutorialStart();
				print();
					break;
				case 7:this.setTutorialEnd();
				print();
					break;
				case 8:this.setTutorialDay();
				print();
					break;
				case 9:this.setTutorialEvenOdd();
				print();
					break;
				case 10:this.setTutorialVenue();
				print();
					break;
				case 0:
					break;
				default:
					break;
				}
			} else if (this.courseTypeStructure == 3) {
				flag=courseinterface.getval();
				switch(flag)
				{
				case 1:this.setLectureStart();
				print();
					break;
				case 2:this.setLectureEnd();
				print();
					break;
				case 3:this.setLectureDay();
				print();
					break;
				case 4:this.setLectureEvenOdd();
				print();
					break;
				case 5:this.setLectureVenue();
				print();
					break;
				case 6:this.setTutorialStart();
				print();
					break;
				case 7:this.setTutorialEnd();
				print();
					break;
				case 8:this.setTutorialDay();
				print();
					break;
				case 9:this.setTutorialEvenOdd();
				print();
					break;
				case 10:this.setTutorialVenue();
				print();
					break;
				case 11:this.setLabStart();
				print();
					break;
				case 12:this.setLabEnd();
				print();
					break;
				case 13:this.setLabDay();
				print();
					break;
				case 14:this.setLabEvenOdd();
				print();
					break;
				case 15:this.setLabVenue();
				print();
					break;
				case 0:
					break;
				default:
					break;
				}
			}
			System.out.println();
		}
*/
	/**
	 * Calendar for setting start and end time	
	 * @param cal Calendar
	 * @return time 
	 */
		public String TimeString(Calendar cal){
		    
		    int hour = cal.get(Calendar.HOUR_OF_DAY);
		    int minute = cal.get(Calendar.MINUTE);
		    return String.format("%02d:%02d",hour, minute);
		}
		/**
		 * Print Lecture Details
		 * @param str1 Lecture Details
		 */
		private void print(String str1) {
			System.out.println("1. "+str1 + " Start Time 		- " + TimeString(this.lectureStartTime));
			System.out.println("2. "+str1 + " End Time   	    - " + TimeString(this.lectureEndTime));
			System.out.println("3. "+str1 + " Day              - " + this.lectureDayStructure);
			System.out.println("4. "+str1 + " Weekly Odd Even  - " + this.lectureEvenOdd);
			System.out.println("5. "+str1 + " Venue            - " + this.lectureVenue);
			
			
			
			
		}
		/**
		 * Print Lecture + Tutorial Details
		 * @param str1 Lecture Details
		 * @param str2 Tutorial Details
		 */
		private void print(String str1, String str2) {
			print("Lecture");
			System.out.println("6. "+str2 + " Start Time 	    - " + TimeString(this.tutorialStartTime));
			System.out.println("7. "+str2 + " End Time   	    - " + TimeString(this.tutorialEndTime));
			System.out.println("8. "+str2 + " Day              - " + this.tutorialDayStructure);
			System.out.println("9. "+str2 + " Weekly Odd Even  - " + this.tutorialEvenOdd);
			System.out.println("10. "+str2 + " Venue            - " + this.tutorialVenue);
			
		
			
		}
		/**
		 * Print Lecture + Tutorial + Lab Details
		 * @param str1 Lecture Details
		 * @param str2 Tutorial Details
		 * @param str3 Lab Details
		 */
		private void print(String str1, String str2, String str3) {
			print("Lecture", "Tutorial");
			System.out.println("11. "+str3 + " Start Time 		- " + TimeString(this.labStartTime));
			System.out.println("12. "+str3 + " End Time  		- " + TimeString(this.labEndTime));
			System.out.println("13. "+str3 + " Day              - " + this.labDayStructure);
			System.out.println("14. "+str3 + " Weekly Odd Even  - " + this.labEvenOdd);
			System.out.println("15. "+str3 + " Venue            - " + this.labVenue);
			
			
			
		}
}
