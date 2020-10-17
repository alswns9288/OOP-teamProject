package coronaMap;

public class CoronaMapMain {
	
	public static void main(String[] args) {
		PlaceManagement placeManagement = new PlaceManagement();
		placeManagement.run();
		
		PeopleManagement peopleManagement = new PeopleManagement();
		peopleManagement.run();
	}
}
