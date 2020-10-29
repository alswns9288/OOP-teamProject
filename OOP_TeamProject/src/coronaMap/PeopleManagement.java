package coronaMap;

public class PeopleManagement extends Manager implements Factory {
	
	public void run() {
		readAll("PersonPath.txt", this);
		printAll();
		search();
	}

	@Override
	public Manageable create() {
		return new Person();
	}
}
