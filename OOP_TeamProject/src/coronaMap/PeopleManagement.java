package coronaMap;

import java.util.ArrayList;

import manager.*;

public class PeopleManagement extends Manager implements Factory {
	private static PeopleManagement instance;
	int number;
	
	private PeopleManagement() {}
	
	public static PeopleManagement getInstance() {
		if(instance == null) {
			instance = new PeopleManagement();
		}
		return instance;
	}
	
	public void run(String fileName) {
		readAll(fileName);
//		printAll();
//		search();
//		addInformation(fileName, this);
//		readAll(fileName);
//		printAll();
	}
	
	public void readAll(String fileName) {
		readAll(fileName, this);
		number = objectList.size();
	}
	
	@Override
	public Manageable create() {
		return new Person();
	}
	
	public boolean search(String keyword) {
		for (Manageable person : objectList) {
			if (person.matches(keyword)) {
				return true;
			}
		}
		return false;
	}
	
	public int searchNumber(String keyword) {
		int result = 0;
		for (Manageable person : objectList) {
			if (person.matches(keyword)) {
				result++;
			}
		}
		return result;
	}
	
	public ArrayList<Integer> NumOfPotives(String keyword){
		ArrayList<Integer> numbers = null;
		for (int i = 0; i<objectList.size(); i++) {
			Person m = (Person) objectList.get(i);
			if(m.matches(keyword))
				numbers.add(m.number);
		}
		return numbers;
	}
	
	public ArrayList<String> InfoOfPotives(String keyword){
		ArrayList<String> info = null;
		for (int i = 0; i<objectList.size(); i++) {
			Person m = (Person) objectList.get(i);
			if(m.matches(keyword))
				info.add(m.toString());
		}
		return info;
	}
	
	public ArrayList<String> matchPath(String keyword){
		ArrayList<String> matchPathList = null;
		for (int i = 0; i<objectList.size(); i++) {
			Person m = (Person) objectList.get(i);
			if(m.matches(keyword)) {
				matchPathList.add(m.searchOnePath(keyword)[0]);
				matchPathList.add(m.searchOnePath(keyword)[1]);	
			}
		}
		return matchPathList;
	}
}