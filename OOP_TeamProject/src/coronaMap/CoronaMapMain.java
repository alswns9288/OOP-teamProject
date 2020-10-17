package coronaMap;

public class CoronaMapMain {
	
	public static void main(String[] args) {
		StoresManagement storesManagement = new StoresManagement();
		storesManagement.run();
		
		PeopleManagement peopleManagement = new PeopleManagement();
		peopleManagement.run();
	}
}
