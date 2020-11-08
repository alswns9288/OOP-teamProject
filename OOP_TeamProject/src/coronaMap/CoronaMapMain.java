package coronaMap;

import GUI.*;

public class CoronaMapMain {
	static PlaceManagement placeManagement = new PlaceManagement();
	static PeopleManagement peopleManagement = new PeopleManagement();
	
	private void mainRun() {
		GUIMain start = new GUIMain();
		start.createAndShowGUI();
	}
	
	public static void main(String[] args) {
		CoronaMapMain run = new CoronaMapMain();
		run.mainRun();
	}
}
