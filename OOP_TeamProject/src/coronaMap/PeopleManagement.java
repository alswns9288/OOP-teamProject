package coronaMap;

import manager.*;

public class PeopleManagement extends Manager implements Factory {
	static int number;
	
	public void run() {
		readAll("PersonPath.txt", this);
		number = getList().size();
		printAll();
		search();
		addInformation("PersonPath.txt", this);
		readAll("PersonPath.txt", this);
		printAll();
		number = getList().size();
		System.out.println();
	}

	@Override
	public Manageable create() {
		return new Person();
	}
}