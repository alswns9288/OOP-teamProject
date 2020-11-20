package coronaMap;

import java.time.LocalDate;
import java.util.*;

public class User {
	public ArrayList<String> information = new ArrayList<>();
	public LocalDate date;
	
	public User(LocalDate date) {
		this.date = date;
	}
	
	public void addInformation(String path, String time) {
		information.add(String.format("%s/%s", path, time));
	}
}
