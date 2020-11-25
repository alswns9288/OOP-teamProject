package coronaMap;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import manager.Split;

public class User implements Split {
	public ArrayList<String> pathAndTime = new ArrayList<>();
	public LocalDate date;
	
	public User(LocalDate date) {
		this.date = date;
	}
	
	public void addInformation(String path, String time) {
		pathAndTime.add(String.format("%s/%s", path, time));
	}

	public void addInformation(String information) {
		split(information);
	}
	
	@Override
	public void split(String information) {
		String[] registerdArray = null;

		registerdArray = information.split("-");
		for (String string : registerdArray) {
			pathAndTime.add(string);
		}
	}
}
