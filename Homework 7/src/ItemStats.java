import java.util.Random;

public class ItemStats {
	public int min;
	public int max;
	
	public ItemStats(int low, int high) {
		this.min = low;
		this.max = high;
	}
	
	public int generateRandomItemStat(int min, int max) {
		int range = max - min;
		
		Random rand = new Random();
		int randValue = rand.nextInt(range + 1);
		
		return randValue + min;
	}
}
