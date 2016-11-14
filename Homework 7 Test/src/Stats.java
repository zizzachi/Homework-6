import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Stats {
	public String name;
	public int min;
	public int max;
	
	public Stats(int low, int high) {
		this.min = low;
		this.max = high;
	}
	
	public Stats(String n, int low, int high) {
		this.name = n;
		this.min = low;
		this.max = high;
	}
	
	public int generateRandomStat(int min, int max) {
		int range = max - min;
		
		Random rand = new Random();
		int randValue = rand.nextInt(range + 1);
		
		return randValue + min;
	}
}
