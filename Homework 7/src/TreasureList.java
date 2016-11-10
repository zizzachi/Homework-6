import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TreasureList {
	private String[] arr;
	File treasure;

	public TreasureList() {
		this.arr = new String[4];
		this.treasure = new File("/Users/user1/workspace/207-homework-07/"
				+ "loot-generator-data/small/TreasureClassEx.txt");
	}

	public void populate() throws FileNotFoundException {
		Scanner scan = new Scanner(treasure);
		String line = scan.nextLine();
		populateHelper(line, 0);
		scan.close();
	}

	public void populateHelper(String str, int i) {
		if (!str.contains("\t")) {
			this.arr[i] = str;
		} else {
			int tabIndex = str.indexOf("\t");
			this.arr[i] = str.substring(0, tabIndex);
			populateHelper(str.substring(tabIndex + 1, str.length()), i + 1);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		TreasureList list = new TreasureList();
		list.populate();
		System.out.println(Arrays.toString(list.arr));
	}
}
