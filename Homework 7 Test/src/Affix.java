import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Affix {
	private String affixName;
	private Map<String, Stats> affixMap;
	private File file;
	
	public Affix(File f) {
		this.affixName = "";
		this.affixMap = new HashMap<String, Stats>();
		this.file = f;
	}
	
	public String getAffixName() {
		return this.affixName;
	}
	
	/** Generates a random value (0 or 1) to determine if an Affix is produced
	 * @return true if 0 is generated; false otherwise
	 */
	public boolean hasAffix() {
		Random rand = new Random();
		int randValue = rand.nextInt(2);

		if(randValue == 0) {
			return true;
		}
		return false;
	}
	
	/** Given a map and an Affix file, populates the map with the given affixes
	 * @param map a Hashmap
	 * @param file an Affix file
	 * @throws FileNotFoundException
	 */
	public void populate() throws FileNotFoundException {
		Scanner scan = new Scanner(this.file);
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			
			int tabIndex = line.indexOf("\t");
			String affixName = line.substring(0, tabIndex);
			
			String affixStats = line.substring(tabIndex + 1, line.length());
			int tabIndex2 = affixStats.indexOf("\t");
			String affixEffect = affixStats.substring(0, tabIndex2);
			
			String affixValues = affixStats.substring(tabIndex2 + 1, affixStats.length());
			int tabIndex3 = affixValues.indexOf("\t");
			int affixMin = Integer.parseInt(affixValues.substring(0, tabIndex3));
			
			String affixVal2 = affixValues.substring(tabIndex3 + 1, affixValues.length());
			int affixMax = Integer.parseInt(affixVal2);
			
			Stats affixStat = new Stats(affixEffect, affixMin, affixMax);
			this.affixMap.put(affixName, affixStat);
		}
		scan.close();
	}
	
	public Stats generateRandomAffix() {
		ArrayList<String> affixList = new ArrayList<String>(this.affixMap.keySet());
		
		Random rand = new Random();
		int randElement = rand.nextInt(affixList.size());
		String choice = affixList.get(randElement);
		
		this.affixName = choice;
		
		return this.affixMap.get(choice);
	}
}
