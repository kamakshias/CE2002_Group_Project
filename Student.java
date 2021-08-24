import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
/**
 * This class represents a student object 
 * One student object can be registered to many courses.
 * @author CE2002 SE1 Group 1
 * @version 1.0
 * @since 2020-11-01
 *
 */
public class Student  implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Initialisation of Matriculation Number and Full name of Student
	 */
	private String matric, fullName;
	/**
	 * Initialisation of Academic Units taken by Student
	 */
	private int auTaken=0;
	/**
	 * Initialisation of Gender
	 */
	private char gender;
	/**
	 * Initialisation of Nationality
	 */
	private String nationality;
	/**
	 * Initialisation of School
	 */
	private String school;
	/**
	 * Initialisation of Matriculation Year
	 */
	private int yearOfmatric;
	/**
	 * Initialisation of Username, Password and Email
	 */
	private String username, password,Email;
	/**
	 * Initialisation of Number of Courses
	 */
	private int noOfCourse=0;
	/**
	 * Initialisation of Course Rundown
	 */
	private List<course> rundown;
	/**
	 * Initialisation of Start and End Dates of Course Registration
	 */
	private  LocalDate startDate, endDate;
	/**
	 * Initialisation of Start and End Times of Course Registration
	 */
	private  LocalTime startTime, endTime;

	/**
	 * Setting the Full name of the Student
	 * @param name Name of Student
	 */
   	public void set_fullName(String name) {
		fullName = name;
	}
   	/**
   	 * Setting the Matriculation Number of the Student
   	 * @param matricno Matriculation Number of the Student
   	 */
	public void set_matric(String matricno) {
		matric = matricno;
	}
	/**
	 * Setting the Username of the Student
	 * @param username Username of the Student
	 */
	public void set_userName(String username) {
		this.username = username;
	}
	/**
	 * Setting the Password for the Student
	 * @param password Password of the Student
	 */
	public void set_password(String password) {
		this.password = password;
	}
	/**
	 * Setting the gender of the Student
	 * @param gender Gender of the Student
	 */
	public void set_gender(char gender) {
		this.gender = gender;
	}
	/**
	 * Setting the Nationality of the Student
	 * @param nationality Nationality of the Student
	 */
	public void set_nationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * Setting the Year of Matriculation for the Student
	 * @param yearOfmatric Year of Matriculation for the Student
	 */
	public void set_yearOfmatric(int yearOfmatric) {
		this.yearOfmatric = yearOfmatric;
	}
	/**
	 * Setting the School of the Student
	 * @param school School of the Student
	 */
	public void set_school(String school) {
		this.school = school;
	}
	/**
	 * Getting the Number of Courses
	 * @return Number of Courses
	 */

	public int getCourseNo(){
		return noOfCourse;
	}
	/**
	 * Getting the Rundown of Courses
	 * @return Rundown
	 */
	public List getCourseList() {
		return rundown;
	}
	/**
	 * Getting the Matriculation Number of the Student
	 * @return Matriculation Number
	 */
	public String get_matric() {
		return matric;
	}
	/**
	 * Getting the Start Date of the Registration Period
	 * @return Start Date of the Registration Period
	 */
	public LocalDate get_startDate() {
		return startDate;
	}
	/**
	 * Getting the End Date of the Registration Period
	 * @return End Date of the Registration Period
	 */
	public LocalDate get_endDate() {
		return endDate;
	}
	/**
	 * Getting the Start Time of the Registration Period
	 * @return Start Time of the Registration Period
	 */
	public LocalTime get_startTime() {
		return startTime;
	}
	/**
	 * Getting the End Time of the Registration Period
	 * @return End Time of the Registration Period
	 */
	public LocalTime get_endTime() {
		return endTime;
	}
	/**
	 * Getting the Username of the Student
	 * @return Username of the Student
	 */
	public String get_userName() {
		return username;
	}
	/**
	 * Getting the Password of the Student
	 * @return the password of the Student
	 */
	public String get_passWord() {
		return password;
	}
	/**
	 * Getting Full Name of the Student
	 * @return Full Name of Student
	 */
	public String get_fullName() {
		return fullName;
	}
	/**
	 * Getting the Nationality of the Student 
	 * @return Nationality of the Student
	 */
	public String get_nationality() {
		return nationality;
	}
	/**
	 * Getting Gender of the Student 
	 * @return Gender of the Student
	 */
	public char get_gender() {
		return gender;
	}
	/**
	 * Getting total number of AUs Student has registered for
	 * @return Number of AUs registered for by Student
	 */
	public int get_au() {
		return auTaken;
	}
	/**
	 * Getting the Student's email
	 * @return Student's email
	 */
	public String getEmail()
	{
		return Email;
	}
	/**
	 * Setting the email of the Student
	 * @param Email Email of the Student
	 */
	public void setEmail(String Email)
	{
		this.Email=Email;
	}
	/**
	 * Setting the Start and End Date for the Course Registration period
	 * @param start Start Date for the Course Registration period
	 * @param end End Date for the Course Registration period
	 */
	public void set_Dates(String start, String end){
		startDate = LocalDate.parse(start);
		endDate = LocalDate.parse(end);
	}
	/**
	 * Setting the Start and End Time for the Course Registration period in Hours and Minutes
	 * @param startTime_h Start Time for the Course Registration period in Hours
	 * @param startTime_m Start Time for the Course Registration period in Minutes
	 * @param endTime_h End Time for the Course Registration period in Hours
	 * @param endTime_m End Time for the Course Registration period in Minutes
	 */
	public void set_Times(int startTime_h, int startTime_m, int endTime_h, int endTime_m){
		startTime = LocalTime.of(startTime_h, startTime_m);
		endTime = LocalTime.of(endTime_h, endTime_m);
	}
	/**
	 * Set rundown of list of courses
	 * @param rundown Rundown of list of courses
	 */
	public void setCourseList(List<course> rundown)
	{
		this.rundown=rundown;
	}
	/**
	 * Details of Student
	 */
	public Student() {
		matric = "XXXXXXXXX";
		Email="XXX@XX.com";
		fullName = "XYZ";
		username = "ABCD1234";
		password = "ABCD1234";
		gender = 'N';
		nationality = "Singaporean";
		rundown = null;
		yearOfmatric = 2021;
		school = "SCSE";
		startDate = LocalDate.parse("2020-11-15");
		endDate = LocalDate.parse("2020-12-15");
		startTime = LocalTime.of(6, 30);
		endTime = LocalTime.of(23, 30);
	}
	/**
	 * Addition of Course
	 * @param c Course to be added
	 */
	public void AddtoCourselist(course c)
	{
		c.print_course_details();
		//System.out.print(this.rundown[0]);
		if(this.getCourseNo()==0)
		{
			rundown=new ArrayList<course>();
		}
		rundown.add(c);
		this.noOfCourse++;
		this.auTaken+=c.get_academicunits();
	}
	/**
	 * Removal of Course
	 * @param c Course to be removed
	 */
	public void removeCourse(course c)
	{
		this.noOfCourse--;
		this.auTaken-=c.get_academicunits();
	}
	/**
	 * Print details of Student
	 */
	public void printDetails()
	{
		System.out.println("Name : " + this.fullName);
		System.out.println("PASS : " + this.password);

		System.out.println("Matric number : " + this.matric);
		System.out.println("Gender : " + this.gender);
		System.out.println("Username : " + this.username);
		System.out.println("Nationality : " + this.nationality);
		System.out.println("School : " + this.school);
		System.out.println("year of matric : " + this.yearOfmatric);

	}
	/**
	 * All required details when creating a Student Object
	 * @param m Matriculation Number
	 * @param n Full Name of Student
	 * @param pass Password of Student
	 * @param g Gender of Student
	 * @param home Nationality of Student
	 * @param user Username of Student
	 * @param sch School of Student
	 * @param year Matriculation Year of Student
	 */
	public Student(String m,String n,String pass, char g, String home, String user, String sch, int year){
       matric=m;
       fullName=n;
       password=pass;
       gender=g;
       username = user;
       nationality=home;
       yearOfmatric = year;
       school = sch;
       startDate = LocalDate.parse("2020-11-15");
       endDate = LocalDate.parse("2020-12-15");
       startTime = LocalTime.of(6, 30);
       endTime = LocalTime.of(23, 30);
   };
}