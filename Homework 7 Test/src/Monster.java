
public class Monster {
	private String name;
	private String TC;
	
	public Monster(String n, String tc) {
		this.name = n;
		this.TC = tc;
	}
	
	public String getMonsterName() {
		return this.name;
	}
	
	public String getTreasureClass() {
		return this.TC;
	}
}
