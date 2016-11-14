import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class TreasureClass {
	private Map<String, ArrayList<String>> TCMap;
	private File file;
	
	public TreasureClass(File f) {
		this.TCMap = new HashMap<String, ArrayList<String>>();
		this.file = f;
	}
	
	/** Given a map and a Treasure Class file, populates the map with the name of each Treasure
	 * Class as keys and the following three names (in a list) as the values
	 * @param map a HashMap
	 * @param file a file containing Treasure Classes
	 * @throws FileNotFoundException
	 */
	public void populate() throws FileNotFoundException {
		Scanner scan = new Scanner(this.file);
		ArrayList<String> arr = new ArrayList<String>();
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			getTCList(line, arr);
			this.TCMap.put(getTCName(line), arr);
		}
		scan.close();
	}
	
	/** Given a line from a file of Treasure Classes, gets the name of that Treasure Class
	 * @param line a line from a file of Treasure Classes
	 * @return the name of the Treasure Class for that line
	 */
	public String getTCName(String line) {
		int tabIndex = line.indexOf("\t");
		return line.substring(0, tabIndex);
	}
	
	/**
	 * Treasure Class in a list
	 * @param line a line from a file of Treasure Classes
	 * @param arr a list containing the names of the given Treasure Class
	 */
	public void getTCList(String line, ArrayList<String> arr) {
		int tabIndex = line.indexOf("\t");
		if(tabIndex == -1) {
			arr.add(line);
		} else {
			String name = line.substring(0, tabIndex);
			arr.add(name);
			getTCList(line.substring(tabIndex + 1, line.length()), arr);
		}
	}
	
	/** Helper function to generateBaseItem. Checks to see if given string is a Treasure Class
	 * and recursively searches until it finds an armor piece
	 * @param map a Hashmap
	 * @param str the string being checked for Treasure Class/armor status
	 * @return an armor name
	 */
	public String generateBaseItemH(String str) {
		if(isTreasureClass(str)) {
			String result = chooseRandomTCElement(str); //choose a new random element from the TC, str		
			return generateBaseItemH(result);
		}
		return str;
	}
	
	/** Given the name of a Treasure Class, chooses a random element from the three options
	 * of that Treasure Class 
	 * @param map a Hashmap
	 * @param TC the Treasure Class name
	 * @return a random element of the Treasure Class
	 */
	public String chooseRandomTCElement(String TC) {
		ArrayList<String> lst = this.TCMap.get(TC); //a list of possible options from that TC

		Random rand = new Random();
		int randIndex = rand.nextInt(lst.size());
		return lst.get(randIndex); //random element from lst
	}
	
	/** Determines if the given string is a Treasure Class in the map
	 * @param str a potential Treasure Class name
	 * @param map a Hashmap
	 * @return true if str is a key in map; false otherwise
	 */
	public boolean isTreasureClass(String str) {
		return this.TCMap.containsKey(str);
	}
}
