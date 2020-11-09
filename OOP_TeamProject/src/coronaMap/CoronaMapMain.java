package coronaMap;

import GUI.*;

public class CoronaMapMain {
	static PlaceManagement placeManagement = new PlaceManagement();
	static PeopleManagement peopleManagement = new PeopleManagement();
	static GUIMain start = new GUIMain();
	
	public static void main(String[] args) {
		start.createAndShowGUI();
	}
}
