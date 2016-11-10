import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TreasureList {
	private String[] arr;
	File treasure;
	
	public TreasureList() {
		this.arr = new String[4];
		this.treasure = new File("Homework 7/loot-generator-data/small/TreasureClassEx.txt");
	}
	
	public void populate() throws FileNotFoundException {
		Scanner scan = new Scanner(treasure);
		String line = scan.nextLine();
		for(int i = 0; i < 4; i++) {   /////MAYBE SWAP ORDER?
			arr[i] = findNextWord(line);
		}
		scan.close();
	}
	
	public String findNextWord(String str) {
		if(str.contains("\t")) {
			int tabIndex = str.indexOf("\t");
			findNextWord(str.substring(tabIndex + 1, str.length()));
			return str.substring(0, tabIndex);
		} else {
			return str;
		}
	}
}
