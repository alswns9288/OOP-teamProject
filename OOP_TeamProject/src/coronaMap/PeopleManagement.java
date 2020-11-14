package coronaMap;

import manager.*;

public class PeopleManagement extends Manager implements Factory {
	static int number;
	
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
}