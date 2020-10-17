package coronaMap;

public class PeopleManagement extends Manager implements Factory {
	
	public void run() {
		
	}

	@Override
	public Manageable create() {
		return new Person();
	}
}
