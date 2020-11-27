package coronaMap;

import java.time.LocalDate;
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
	
	public ArrayList<Person> find(String date){
		ArrayList <Person> data = new ArrayList<Person>();
		for (Manageable manageable : objectList) {
			Person person = (Person) manageable;
			if(person.matches(date))
				data.add(person);
		}
		return data;
	}
	
	public Person findByNumber(String number) {
		for (Manageable m : objectList) {
			Person p = (Person) m;
			if (p.matches(number)) {
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<Person> findByDate(String date) {
		ArrayList<Person> personList = new ArrayList<Person>();
		
		for (Manageable m : objectList) {
			Person p = (Person) m;
			if (p.matches(date)) {
				personList.add(p);
			}
		}
		return personList;
	}
}
