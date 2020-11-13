package coronaMap;

import manager.*;

public class PeopleManagement extends Manager implements Factory {
	static int number;
	
	public void run() {
		readAll();
//		printAll();
//		search();
//		addInformation("PersonPath.txt", this);
//		readAll("PersonPath.txt", this);
//		printAll();
	}
	
	public void readAll() {
		readAll("PersonPath.txt", this);
		number = objectList.size();
	}
	
	@Override
	public Manageable create() {
		return new Person();
	}
}