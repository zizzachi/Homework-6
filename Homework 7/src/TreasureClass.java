import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TreasureClass {

	private HashMap<String, ArrayList<String>> TCMap;
	
	public TreasureClass() {
		this.TCMap = null;
	}
	
	
	/** Given a map and a Treasure Class file, populates the map with the name of each Treasure
	 * Class as keys and the following three names (in a list) as the values
	 * @param map a HashMap
	 * @param file a file containing Treasure Classes
	 * @throws FileNotFoundException
	 */
//	public static void populateTCMap(Map<String, ArrayList<String>> map, File file) 
//			throws FileNotFoundException {
//		Scanner scan = new Scanner(file);
//		ArrayList<String> arr = new ArrayList<String>();
//		while(scan.hasNextLine()) {
//			String line = scan.nextLine();
//			getTCList(line, arr);
//			map.put(getTCName(line), arr);
//		}
//		scan.close();
//	}
}
