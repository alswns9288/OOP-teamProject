package coronaMap;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.FirstGUI;
import manager.*;

public class PeopleManagement extends Manager implements Factory {
	private static PeopleManagement instance;
	String fileName;
	public int number;

	private PeopleManagement() {
	}

	public static PeopleManagement getInstance() {
		if (instance == null) {
			instance = new PeopleManagement();
		}
		return instance;
	}

	public void run(String fileName) {
		this.fileName = fileName;
		readAll();
	}

	public void readAll() {
		readAll(fileName, this);
		number = getList().size();
	}

	@Override
	public Manageable create() {
		return new Person();
	}

	public boolean search(String keyword) {
		for (Manageable person : getList()) {
			if (person.matches(keyword)) {
				return true;
			}
		}
		return false;
	}

	public int searchNumber(String keyword) {
		int result = 0;
		for (Manageable person : getList()) {
			if (person.matches(keyword)) {
				result++;
			}
		}
		return result;
	}

	public ArrayList<Person> find(String date) {
		ArrayList<Person> data = new ArrayList<Person>();
		for (Manageable manageable : getList()) {
			Person person = (Person) manageable;
			if (person.matches(date))
				data.add(person);
		}
		return data;
	}

	public Person findByNumber(String number) {
		for (Manageable m : getList()) {
			Person p = (Person) m;
			if (p.matchesNumber(number)) {
				return p;
			}
		}
		return null;
	}

	public ArrayList<Person> findByDate(String date) {
		ArrayList<Person> personList = new ArrayList<Person>();

		for (Manageable m : getList()) {
			Person p = (Person) m;
			if (p.matches(date)) {
				personList.add(p);
			}
		}
		return personList;
	}

	public void addInformation(ArrayList<String> pathAndTimeList) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
		BufferedWriter writeFile = (BufferedWriter) openFile(fileName, false);
		int newNumber = number + 1;
		int count = 0;

		try {
			writeFile.newLine();
			writeFile.write(newNumber + " " + FirstGUI.getDate().format(formatter) + " ");
			for (String pathAndTime : pathAndTimeList) {
				count++;
				if (count == pathAndTimeList.size()) {
					writeFile.write(pathAndTime);
					continue;
				}
				writeFile.write(pathAndTime + "-");
			}
			writeFile.close();
		} catch (IOException e) {
			System.out.println("Fail! PoepleManagement.addInformation");
			System.exit(0);
		}
	}
}