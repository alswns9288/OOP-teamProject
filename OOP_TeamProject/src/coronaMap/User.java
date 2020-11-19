package coronaMap;

import java.util.*;

public class User {
	public ArrayList<String> information = new ArrayList<>();
	public String date;
	
	public User(String date) {
		this.date = date;
	}
	
	public void addInformation(String path, String time) {
		information.add(String.format("%s/%s", path, time));
	}
}
