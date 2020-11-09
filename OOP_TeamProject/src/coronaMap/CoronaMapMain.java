package coronaMap;

import GUI.*;

public class CoronaMapMain {
	static PlaceManagement placeManagement = new PlaceManagement();
	static PeopleManagement peopleManagement = new PeopleManagement();
	
	public static void main(String[] args) {
		GUIMain start = new GUIMain();
		start.createAndShowGUI();
	}
}
