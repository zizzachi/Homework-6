import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LootGenerator {

	public static void main(String[] args) {
		File armor = new File("Homework 7/loot-generator-data/small/armor.txt");
		File prefix = new File("Homework 7/loot-generator-data/small/MagicPrefix.txt");
		File suffix = new File("Homework 7/loot-generator-data/small/MagicSuffix.txt");
		File monster = new File("Homework 7/loot-generator-data/small/monstats.txt");
		File treasure = new File("Homework 7/loot-generator-data/small/TreasureClassEx.txt");
		
		if(armor != null) {
			System.out.println("Not null.");
		}
		
		Map<String, ArrayList<String>> loot = new HashMap<String, ArrayList<String>>();
		
		/* Create a helper method that does these */
//		pickMonster(…)
//		fetchTreasureClass(…)
//		generateBaseItem(…)
//		generateBaseStats(…)
//		generateAffix(…)
	}
}
