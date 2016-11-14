import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Armor {
	private Map<String, Stats> armorMap;
	private File file;
	
	public Armor(File f) {
		this.armorMap = new HashMap<String, Stats>();
		this.file = f;
	}
	
	/** Given a map of Strings and ItemStats and a file, populates the map with each line from the file
	 * @param map a HashMap
	 * @param file an Armor file
	 * @throws FileNotFoundException
	 */
	public void populate() throws FileNotFoundException {
		Scanner scan = new Scanner(this.file);
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			
			int tabIndex = line.indexOf("\t");
			String armorName = line.substring(0, tabIndex);
			
			String armorRange = line.substring(tabIndex + 1, line.length());
			int tabIndex2 = armorRange.indexOf("\t");
			int armorMin = Integer.parseInt(armorRange.substring(0, tabIndex2));
			
			String armorVal2 = armorRange.substring(tabIndex2 + 1, armorRange.length());
			int armorMax = Integer.parseInt(armorVal2);
			
			Stats armorStats = new Stats(armorMin, armorMax);
			this.armorMap.put(armorName, armorStats);
		}
		scan.close();
	}
	
	public int getRandomArmorStat(String armorName) {
		Stats armor = armorMap.get(armorName);
		return armor.generateRandomStat(armor.min, armor.max);
	}

}
