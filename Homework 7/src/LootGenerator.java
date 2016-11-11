import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {
	
	private static String getTC(String line) {
		int tabIndex = line.indexOf("\t");
		if(tabIndex == -1) {
			return line;
		} else {
			return getTC(line.substring(tabIndex + 1, line.length()));
		}
	}
	
	public static void generateMonsterList(ArrayList<Monster> lst, File file) throws FileNotFoundException {
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

	public static Monster pickMonster(ArrayList<Monster> lst) {
		Random rand = new Random();
		int randIndex = rand.nextInt(lst.size());
		return lst.get(randIndex);
	}
	
	public static void populateMap(Map<String, ArrayList<String>> map, File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		ArrayList<String> arr = new ArrayList<String>();
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			getTCList(line, arr);
			map.put(getTCName(line), arr);
		}
		scan.close();
	}
	
	public static String getTCName(String line) {
		int tabIndex = line.indexOf("\t");
		return line.substring(0, tabIndex);
	}
	
	
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
	
	
	
	
	public static boolean isTreasureClass(String str, Map<String, ArrayList<String>> map) {
		return map.containsKey(str);
	}

	public static void main(String[] args) throws FileNotFoundException {
		//File armor = new File("Homework 7/loot-generator-data/small/armor.txt");
		File prefix = new File("Homework 7/loot-generator-data/small/MagicPrefix.txt");
		File suffix = new File("Homework 7/loot-generator-data/small/MagicSuffix.txt");
		File monster = new File("/Users/chiarazizza/Documents/workspace/Homework 7/loot-generator-data/large/monstats.txt");
		File treasure = new File("/Users/chiarazizza/Documents/workspace/Homework 7/loot-generator-data/small/TreasureClassEx.txt");
		
		//Map<String, ArrayList<String>> loot = new HashMap<String, ArrayList<String>>();
		
		ArrayList<Monster> mlist = new ArrayList<Monster>();
		generateMonsterList(mlist, monster);
		Monster mon = pickMonster(mlist);
		System.out.println(mon.name + ",  " + mon.TC);
		
		Map<String, ArrayList<String>> TCMap = new HashMap<String, ArrayList<String>>();
		populateMap(TCMap, treasure);
		System.out.println(TCMap.keySet());
		
		/* Create a helper method that does these */
//		pickMonster(…)
//		fetchTreasureClass(…)
//		generateBaseItem(…)
//		generateBaseStats(…)
//		generateAffix(…)
	}
}
