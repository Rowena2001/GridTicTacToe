
public class Data {
    private String dataKey;
    private int dataScore;
    private int dataLevel;
	
	public Data(String key, int score, int level) {
		dataKey = key;
		dataScore = score;
		dataLevel = level;
	}
	
	public String getKey() { // returns the string
		return dataKey;
	}
	
	public  int getScore() { // returns the first int
		return dataScore;
	}
	
	public int getLevel() { // returns the second int
		return dataLevel;
	}

}
