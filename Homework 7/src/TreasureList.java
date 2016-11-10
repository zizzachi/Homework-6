import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TreasureList {
	private String[] arr;
	File treasure;
	
	public TreasureList() {
		this.arr = new String[4];
		this.treasure = new File("/Users/chiarazizza/Documents/workspace/Homework 7/loot-generator-data/small/TreasureClassEx.txt");
	}
	
	public void populate() throws FileNotFoundException {
		Scanner scan = new Scanner(treasure);
		String line = scan.nextLine();
//		for(int i = 0; i < 4; i++) {   /////MAYBE SWAP ORDER?
//			arr[i] = findNextWord(line, 0);
//		}
		findNextWord(line, 0);
		scan.close();
	}
	
//	public String findNextWord(String str, String TC, String, item1, ) {
//		if(str.contains("\t")) {
//			int tabIndex = str.indexOf("\t");
//			System.out.println(tabIndex);
//			findNextWord(str.substring(tabIndex + 1, str.length()));
//			
//			System.out.println(str.substring(tabIndex + 1, str.length()));
//			return str.substring(0, tabIndex);
//		} else {
//			return str;
//		}
//	}
	
	public void findNextWord(String str, int i) {
		if(!str.contains("\t")) {
			this.arr[i] = str;
		} else {
			int tabIndex = str.indexOf("\t");
			System.out.println(tabIndex);
			
			this.arr[i] = str.substring(0, tabIndex);
			findNextWord(str.substring(tabIndex + 1, str.length()), i++);
			
			System.out.println(str.substring(tabIndex + 1, str.length()));
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		TreasureList list = new TreasureList();
		list.populate();
		System.out.println(Arrays.toString(list.arr));
	}
}
