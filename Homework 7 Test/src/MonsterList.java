import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MonsterList {
	public ArrayList<Monster> arr;
	private File file;
	
	public MonsterList(File f) {
		this.arr = new ArrayList<Monster>();
		this.file = f;
	}
	
	/** Given a file containing possible monsters, generates a list of Monsters, 
	 * holding their name and Treasure Class
	 * @param lst a list of Monsters
	 * @param file a file containing possible monsters
	 * @throws FileNotFoundException
	 */
	public void generateMonsterList(ArrayList<Monster> lst) 
			throws FileNotFoundException {
		Scanner scan = new Scanner(this.file);

		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			int tabIndex = line.indexOf("\t");
			String name = line.substring(0, tabIndex);
			String TC = getTC(line);
			
			lst.add(new Monster(name, TC));
		}
		scan.close();
	}
	
	/** Given a line from a file containing possible monsters, retrieves 
	 * the Treasure Class of that monster
	 * @param line a line from a file containing possible monsters
	 * @return the Treasure Class of that monster
	 */
	private static String getTC(String line) {
		int tabIndex = line.indexOf("\t");
		if(tabIndex == -1) {
			return line;
		} else {
			return getTC(line.substring(tabIndex + 1, line.length()));
		}
	}
}
