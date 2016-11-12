
public class GenerateItem {
	private String name;
	private int baseStat;
	
	private String prefix;
	private String prefixStat;
	private int prefixValue;
	
	private String suffix;
	private String suffixStat;
	private int suffixValue;
	
	public GenerateItem() {
		this.name = "";
		this.baseStat = 0;
		this.prefix = "";
		this.prefixStat = "";
		this.prefixValue = 0;
		this.suffix = "";
		this.suffixStat = "";
		this.suffixValue = 0;
	}
	
	public void addName(String str) {
		this.name = str;
	}
	
	public void addBaseStat(int value) {
		this.baseStat = value;
	}
	
	public void addPrefix(String p) {
		this.prefix = p;
	}
	
	public void addPrefixStat(String stat) {
		this.prefixStat = stat;
	}
	
	public void addPrefixValue(int value) {
		this.prefixValue = value;
	}
	
	public void addSuffix(String s) {
		this.suffix = s;
	}
	
	public void addSuffixStat(String stat) {
		this.suffixStat = stat;
	}
	
	public void addSuffixValue(int value) {
		this.suffixValue = value;
	}
	
	public void printItem() {
		if(!prefix.equals("")) {
			System.out.print(prefix + " ");
		}
		System.out.print(name + " ");
		System.out.println(suffix);
		System.out.println("Defense: " + baseStat);
		if(prefixValue != 0) {
			System.out.println(prefixStat + " " + prefixValue);
		}
		if(suffixValue != 0) {
			System.out.println(suffixStat + " " + suffixValue);
		}
	}
}
