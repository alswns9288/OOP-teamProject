package coronaMap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import manager.Split;

public class User implements Split {
	public ArrayList<String> pathAndTime = new ArrayList<>();
	public String date;
	
	public User(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
		this.date = date.format(formatter);
	}
	
	public User(String date) {
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
	
	public ArrayList<String> getPathAndTime() {
		return pathAndTime;
	}

	public void print() {
		System.out.print(date + " ");
		for (String s: pathAndTime) {
			System.out.print(s + " -> ");
		}
		System.out.println();
	}
}
