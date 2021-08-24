import java.io.Serializable;
/**
 * This class creates the object to be added into the waiting list.
 * @author CE2002 SE1 Group 1
 * @version 1.0
 * @since 2020-11-01
 *
 */
public class StudentNode implements Serializable {
    private String n;
    private String m;
/**
 * 
 * @param name Name of Student
 * @param matric Matriculation Number of Student
 */
    public StudentNode (String name,String matric) {
        n = name;
        m = matric;
    }
    public String getMatric(){
        return m;
    }
    public String getName(){
        return n;
    }
}
