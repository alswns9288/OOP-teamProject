package coronaMap;

import GUI.*;
import manager.UserManager;

public class CoronaMapMain {
	public static PlaceManagement placeManagement = new PlaceManagement();
	public static PeopleManagement peopleManagement = new PeopleManagement();
	public static UserManager userManager = new UserManager();
	
	public static void main(String[] args) {
		placeManagement.run("PlaceSample.txt");
		peopleManagement.run("PersonPath.txt");
		GUIMain start = new GUIMain();
		start.createAndShowGUI();
	}
}