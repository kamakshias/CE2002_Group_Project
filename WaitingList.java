import java.io.Serializable;
import java.util.LinkedList;
/**
 * This class represents the waiting list for each index  
 * @author CE2002 SE1 Group 1
 * @version 1.0
 * @since 2020-11-01
 *
 */

public class WaitingList implements Serializable {
    private LinkedList<StudentNode> studentList;
    private int index;
    public WaitingList(int i){
        studentList = new LinkedList<>();
        index=i;
    }
    /**
     * Adding Student to Waiting list
     * @param node Student node to be added to the Waiting  List
     */
    public void addWaiting(StudentNode node) {
        studentList.addLast(node);
        System.out.println("Added to waiting list!");
    }
    /**
     * Removing Student from Waiting List
     * @return Student Node removed from Waiting List
     */
    public StudentNode endWaiting(){
        return studentList.removeFirst();
    }
    /**
     * Checking Student at top of waiting list
     * @return Student Node 
     */
    public String nextInLine(){    //peek
        StudentNode student=studentList.peek();
        if (student != null){
            return student.getName();
        }
        System.out.println("None in queue");
        return "a";
    }
    /**
     * Method to remove Student by using Matriculation Number
     * @param matric Matriculation Number of Student
     * @return Boolean
     */
    public Boolean removeByMatric(String matric)  //returns removing success or failure

    {
        int x=0;
        StudentNode remove;
        String m;
        while (x<studentList.size()){
            m=studentList.get(x).getMatric();
            if (m.equals(matric)) break;
            x++;
        }
        if (x==studentList.size()) return false;
        remove=studentList.remove(x);
        System.out.println("Removing "+remove.getName()+"from waitlist");
        return true;
    }
    /**
     * Method to remove Student by using Name
     * @param name Name of Student
     * @return Boolean
     */
    public Boolean removeByName(String name)  //returns removing success or failure

    {
        int x=0;
        StudentNode remove;
        String n;
        while (x<studentList.size()){
            n=studentList.get(x).getName();
            if (n.equals(name)) break;
            x++;
        }
        if (x==studentList.size()) return false;
        remove=studentList.remove(x);
        System.out.println("Removing "+remove.getName()+"from waitlist");
        return true;
    }
    /**
     * Printing Students in Waiting List
     */
    public void printQueue(){
        int x=0;
        System.out.println("/n /n In Queue /n Name    Matric No.");
        while (x<studentList.size()){
            System.out.println(studentList.get(x).getName()+"   "+studentList.get(x).getMatric());
            x++;
        }
    }
    /**
     * Getting Index Number for respective waiting list
     * @return Index Number
     */
    public int getIndex(){
        return index;
    }
    public void changeIndex(int i){ //unused as of now (for transferring all students in queue to another index/course)
        index = i;
    }

}