import java.util.*;
import java.io.*;
/**
 * This class is for reading and writing serializable objects 
 * @author TAN Kheng Leong
 *
 */
public abstract class FileMgr 
{
	private static final long serialVersionUID = 1L;

	/**
	 * Reading Serializable Objects
	 * @param dir Name of File to be read from
	 * @return List of Objects
	 */
	@SuppressWarnings("rawtypes")
	public static List readSerialObj(String dir) {
		List info = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(dir);
			ois = new ObjectInputStream(fis);
			info = (ArrayList) ois.readObject();
			ois.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return info;
	}

	/**
	 * Writing Serializable Objects
	 * @param list List of Objects
	 * @param dir File to be written to
	 */
	public static void writeSerialObj(List list, String dir) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(new File(dir));
			oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}