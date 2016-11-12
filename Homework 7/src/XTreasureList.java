import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class XTreasureList {
	private String[] arr;
	File treasure;
	
	public XTreasureList() {
		this.arr = new String[4];
		this.treasure = new File("/Users/chiarazizza/Documents/workspace/Homework 7/loot-generator-data/large/TreasureClassEx.txt");
	}
	
	public void populate(String str) throws FileNotFoundException {
		String line = findLine(str);
		findNextWord(line, 0);
	}
	
	public void findNextWord(String str, int i) {
		if(!str.contains("\t")) {
			this.arr[i] = str;
		} else {
			int tabIndex = str.indexOf("\t");
			
			this.arr[i] = str.substring(0, tabIndex);
			findNextWord(str.substring(tabIndex + 1, str.length()), i + 1);
		}
	}

	public boolean isTreasureClass(String str) throws FileNotFoundException {
		String line = findLine(str);
		//System.out.println(line);
		
		int tabIndex = line.indexOf("\t");
		
		//System.out.println(tabIndex);
		if(tabIndex == -1) {
			return false;
		} else {
			String TC = null;
			
			if(line != null) {
				TC = line.substring(0, tabIndex);
			}
			return str.equals(TC);
		}
	}
	
	public String chooseRandomElement() {
		Random rand = new Random();
		int randIndex = rand.nextInt(3) + 1;
		return arr[randIndex];
	}
	
	/** Finds the line in the file starting with the given Treasure Class name
	 * @param str a Treasure Class name
	 * @return line the line from the file starting with the given Treasure Class name
	 * @throws FileNotFoundException
	 */
	public String findLine(String str) throws FileNotFoundException {
		Scanner scan = new Scanner(treasure);
		
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
	
	public static void main(String[] args) throws FileNotFoundException {
		XTreasureList list = new XTreasureList();
		list.populate("Act 1 H2H A");
		System.out.println(Arrays.toString(list.arr));

		String choice = list.chooseRandomElement();
		
		while(list.isTreasureClass(choice)) {
			String TC = list.findLine(choice);
			list.populate(TC);

			choice = list.chooseRandomElement();
		}
		System.out.println(choice);
	}
}
