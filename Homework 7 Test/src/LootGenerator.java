import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {
	
	/** Randomly selects a Monster from a list of Monsters
	 * @param lst a list of Monsters
	 * @return a Monster
	 * @throws FileNotFoundException 
	 */
	public static Monster pickMonster(File monsterFile) throws FileNotFoundException {		
		MonsterList monList = new MonsterList(monsterFile);
		monList.generateMonsterList(monList.arr);
		
		Random rand = new Random();
		int randIndex = rand.nextInt(monList.arr.size());
		return monList.arr.get(randIndex);
	}
	
	/** Determines the Treasure Class of the selected Monster
	 * @param mon a Monster
	 * @return the name of the Monster
	 */
	public static String fetchTreasureClass(Monster mon) {
		return mon.getTreasureClass();
	}
	
	/** Determines the random item dropped my a Monster
	 * @param treasureFile a file containing all possible item drops from Monsters
	 * @param monsterTC the Treasure Class of the selected Monster
	 * @return the name of the item dropped
	 * @throws FileNotFoundException
	 */
	public static String generateBaseItem(File treasureFile, String monsterTC, GenerateItem result) throws FileNotFoundException {
		TreasureClass TC = new TreasureClass(treasureFile);
		TC.populate();
		String TCElement = TC.chooseRandomTCElement(monsterTC);
		
		String armorName = TC.generateBaseItemH(TCElement);
		result.addName(armorName);
		return armorName;
	}
	
	/** Given an item, determines the base stats of that item
	 * @param file a file containing Armor stats
	 * @param armorName the name of the Armor whose stats are being determined
	 * @param result a GenerateItem
	 * @throws FileNotFoundException
	 */
	public static void generateBaseStats(File file, String armorName, GenerateItem result) throws FileNotFoundException {
		Armor armor = new Armor(file);
		armor.populate();
		int armorStat = armor.getRandomArmorStat(armorName);
		
		result.addBaseStat(armorStat);
	}
	
	/** Determines if an Affix (prefix and/or suffix) is generated
	 * @param prefixFile a file containing Prefixes
	 * @param suffixFile a file containing Suffixes
	 * @param result a GenerateItem
	 * @throws FileNotFoundException
	 */
	public static void generateAffix(File prefixFile, File suffixFile, GenerateItem result) throws FileNotFoundException {
		Affix prefix = new Affix(prefixFile);
		Affix suffix = new Affix(suffixFile);
		
		prefix.populate();
		suffix.populate();
		
		if(prefix.hasAffix()) {
			Stats prefixStats = prefix.generateRandomAffix();
			result.addPrefix(prefix.getAffixName());
			result.addPrefixStat(prefixStats.name);
			result.addPrefixValue(prefixStats.generateRandomStat(prefixStats.min, prefixStats.max));
		}
		
		if(suffix.hasAffix()) {
			Stats suffixStats = suffix.generateRandomAffix();
			result.addSuffix(suffix.getAffixName());
			result.addSuffixStat(suffixStats.name);
			result.addSuffixValue(suffixStats.generateRandomStat(suffixStats.min, suffixStats.max));
		}
	}
	
	/** Prompts the user if he or she wants to continue playing
	 * @param scan a scanner
	 * @return true if the user enters 'Y'; false otherwise
	 */
	public static boolean wantContinue(Scanner scan) {
		while(true) {
			System.out.print("Fight again [y/n]? ");
			String response = scan.next();
			
			if(response.equalsIgnoreCase("n")) {
				scan.close();
				return false;
			} else if(response.equalsIgnoreCase("y")) {
				System.out.println();
				return true;
			} else {
				System.out.println("Not a valid response.");
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		File monsterFile = new File("/Users/chiarazizza/Documents/workspace/Homework 7/loot-generator-data/large/monstats.txt");
		File treasureFile = new File("/Users/chiarazizza/Documents/workspace/Homework 7/loot-generator-data/large/TreasureClassEx.txt");
		File armorFile = new File("/Users/chiarazizza/Documents/workspace/Homework 7/loot-generator-data/large/armor.txt");
		File prefixFile = new File("/Users/chiarazizza/Documents/workspace/Homework 7/loot-generator-data/large/MagicPrefix.txt");
		File suffixFile = new File("/Users/chiarazizza/Documents/workspace/Homework 7/loot-generator-data/large/MagicSuffix.txt");
		
		Scanner scan = new Scanner(System.in);
		boolean proceed = true;
		
		while(proceed) {
			GenerateItem result = new GenerateItem();
			Monster monster = pickMonster(monsterFile);
			
			System.out.println("Fighting " + monster.getMonsterName());
			System.out.println("You have slain " + monster.getMonsterName() + "!");
			System.out.println(monster.getMonsterName() + " dropped:");
			
			String armor = generateBaseItem(treasureFile, fetchTreasureClass(monster), result);
			generateBaseStats(armorFile, armor, result);		
			generateAffix(prefixFile, suffixFile, result);

			System.out.println();
			result.printItem();
			System.out.println();
			
			proceed = wantContinue(scan);
		}
	}

}
