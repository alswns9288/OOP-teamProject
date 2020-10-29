package coronaMap;

public class CoronaMapMain {
	static PlaceManagement placeManagement = new PlaceManagement();
	static PeopleManagement peopleManagement = new PeopleManagement();
	
	private void mainRun() {
		placeManagement.run();
		peopleManagement.run();
	}
	
	public static void main(String[] args) {
		CoronaMapMain run = new CoronaMapMain();
		run.mainRun();
	}
}
