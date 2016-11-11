import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class ArmorStats {

	private String armor;
	private int min;
	private int max;
	File armortxt;
	
	public ArmorStats(String str, int min, int max) {
		this.armor = str;
		this.min = min;
		this.max = max;
		this.armortxt = new File("/Users/chiarazizza/Documents/workspace/Homework 7/loot-generator-data/small/armor.txt");
	}
	
	public void populate(String str) throws FileNotFoundException {
		String line = findLine(str);
		findNextWord(line, 0);
	}
	
	public void findNextWord(String str, int i) {
		if(!str.contains("\t")) {
			this.max = Integer.parseInt(str);
		} else {
			int tabIndex = str.indexOf("\t");
			
			//this.arr[i] = str.substring(0, tabIndex);
			findNextWord(str.substring(tabIndex + 1, str.length()), i + 1);
		}
	}
	
	public int findRandom() {
		int high = this.max - this.min;
		Random rand = new Random();
		return rand.nextInt(high) + min;
	}
	
	/** Finds the line in the file starting with the given Armor name
	 * @param str an Armor name
	 * @return line the line from the file starting with the given Armor name
	 * @throws FileNotFoundException
	 */
	public String findLine(String str) throws FileNotFoundException {
		Scanner scan = new Scanner(armortxt);
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			int tabIndex = line.indexOf("\t");
			String word = line.substring(0, tabIndex);

			if(str.equals(word)) {
				scan.close();
				return line;
			}
		}
		scan.close();
		return str;
	}
	
	
}
