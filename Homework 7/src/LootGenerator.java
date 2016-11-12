import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {
	
	/** Given a line from a file containing possible monsters, retrieves 
	 * the Treasure Class of that monster
	 * @param line a line from a file containing possible monsters
	 * @return the Treasure Class of that monster
	 */
	private static String getTC(String line) {
		int tabIndex = line.indexOf("\t");
		if(tabIndex == -1) {
			return line;
		} else {
			return getTC(line.substring(tabIndex + 1, line.length()));
		}
	}
	
	/** Given a file containing possible monsters, generates a list of Monsters, 
	 * holding their name and Treasure Class
	 * @param lst a list of Monsters
	 * @param file a file containing possible monsters
	 * @throws FileNotFoundException
	 */
	public static void generateMonsterList(ArrayList<Monster> lst, File file) 
			throws FileNotFoundException {
		Scanner scan = new Scanner(file);

		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			int tabIndex = line.indexOf("\t");
			String name = line.substring(0, tabIndex);
			String TC = getTC(line);
			
			lst.add(new Monster(name, TC));
		}
		scan.close();
	}

	/** Randomly selects a Monster from a list of Monsters
	 * @param lst a list of Monsters
	 * @return a Monster
	 */
	public static Monster pickMonster(ArrayList<Monster> lst) {
		Random rand = new Random();
		int randIndex = rand.nextInt(lst.size());
		return lst.get(randIndex);
	}
	
	/** Given a map and a Treasure Class file, populates the map with the name of each Treasure
	 * Class as keys and the following three names (in a list) as the values
	 * @param map a HashMap
	 * @param file a file containing Treasure Classes
	 * @throws FileNotFoundException
	 */
	public static void populateMap(Map<String, ArrayList<String>> map, File file) 
			throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		ArrayList<String> arr = new ArrayList<String>();
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			getTCList(line, arr);
			map.put(getTCName(line), arr);
		}
		scan.close();
	}
	
	/** Given a line from a file of Treasure Classes, gets the name of that Treasure Class
	 * @param line a line from a file of Treasure Classes
	 * @return the name of the Treasure Class for that line
	 */
	public static String getTCName(String line) {
		int tabIndex = line.indexOf("\t");
		return line.substring(0, tabIndex);
	}
	
	/** Given a line from a file of Treasure Classes, stores the three names of the given
	 * Treasure Class in a list
	 * @param line a line from a file of Treasure Classes
	 * @param arr a list containing the names of the given Treasure Class
	 */
	public static void getTCList(String line, ArrayList<String> arr) {
		int tabIndex = line.indexOf("\t");
		if(tabIndex == -1) {
			arr.add(line);
		} else {
			String name = line.substring(0, tabIndex);
			arr.add(name);
			getTCList(line.substring(tabIndex + 1, line.length()), arr);
		}
	}
	
	/** Determines if the given string is a Treasure Class in the map
	 * @param str a potential Treasure Class name
	 * @param map a Hashmap
	 * @return true if str is a key in map; false otherwise
	 */
	public static boolean isTreasureClass(String str, Map<String, ArrayList<String>> map) {
		return map.containsKey(str);
	}
	
//	public static String chooseRandomTC(Map<String, ArrayList<String>> map) {
//		Random rand = new Random();
//		int randIndex = rand.nextInt(map.size());
//		String[] arr = (String[]) map.keySet().toArray();
//		
//		return arr[randIndex];
//	}
	
	/** Given the name of a Treasure Class, chooses a random element from the three options
	 * of that Treasure Class 
	 * @param map a Hashmap
	 * @param TC the Treasure Class name
	 * @return a random element of the Treasure Class
	 */
	public static String chooseRandomTCElement(Map<String, ArrayList<String>> map, String TC) {
		ArrayList<String> lst = map.get(TC); //a list of possible options from that TC

		Random rand = new Random();
		int randIndex = rand.nextInt(lst.size());
		return lst.get(randIndex); //random element from lst
	}
	
	/** Given a Treasure Class name, chooses a random element from it and generates an armor piece
	 * from it
	 * @param map a Hashmap
	 * @param TC a TreasureClass name
	 * @return an armor piece
	 */
	public static String generateBaseItem(Map<String, ArrayList<String>> map, String TC) {
		String TCElement = chooseRandomTCElement(map, TC); //a random TCElement from TC options array
		String item = generateBaseItemH(map, TCElement);		
		return item;
	}
	
	/** Helper function to generateBaseItem. Checks to see if given string is a Treasure Class
	 * and recursively searches until it finds an armor piece
	 * @param map a Hashmap
	 * @param str the string being checked for Treasure Class/armor status
	 * @return an armor name
	 */
	public static String generateBaseItemH(Map<String, ArrayList<String>> map, String str) {
		if(isTreasureClass(str, map)) {
			String result = chooseRandomTCElement(map, str); //choose a new random element from the TC, str		
			return generateBaseItemH(map, result);
		}
		return str;
	}

	public static void main(String[] args) throws FileNotFoundException {
		//File armor = new File("Homework 7/loot-generator-data/small/armor.txt");
		File prefix = new File("Homework 7/loot-generator-data/small/MagicPrefix.txt");
		File suffix = new File("Homework 7/loot-generator-data/small/MagicSuffix.txt");
		File monster = new File("/Users/chiarazizza/Documents/workspace/Homework 7/loot-generator-data/large/monstats.txt");
		File treasure = new File("/Users/chiarazizza/Documents/workspace/Homework 7/loot-generator-data/large/TreasureClassEx.txt");
		

		/* TEST FOR SMALL DATA FILES */
		ArrayList<Monster> mlist = new ArrayList<Monster>();
		generateMonsterList(mlist, monster);
		Monster mon = pickMonster(mlist);
		System.out.println(mon.name);
		
		Map<String, ArrayList<String>> TCMap = new HashMap<String, ArrayList<String>>();
		populateMap(TCMap, treasure);
		//System.out.println(TCMap.keySet());
		//System.out.println(isTreasureClass("armo60", TCMap));
		
		String item = generateBaseItem(TCMap, mon.TC);
		System.out.println(item);
		
		
		
		
		/* TEST FOR LARGE DATA FILES */
//		ArrayList<Monster> mlist = new ArrayList<Monster>();
//		generateMonsterList(mlist, monster);
//		Map<String, ArrayList<String>> TCMap = new HashMap<String, ArrayList<String>>();
//		populateMap(TCMap, treasure);
//		while(true) {
//			Monster mon = pickMonster(mlist);
//			System.out.println(mon.name);
//			String item = generateBaseItem(TCMap, mon.TC);
//			System.out.println(item + "\n");
//		}
	}
}


/* Create a helper method that does these */
//pickMonster(…)
//fetchTreasureClass(…)
//generateBaseItem(…)
//generateBaseStats(…)
//generateAffix(…)
