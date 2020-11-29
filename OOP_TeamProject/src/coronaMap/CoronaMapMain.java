package coronaMap;

import GUI.*;
import manager.UserManager;

public class CoronaMapMain {
	
	public static void main(String[] args) {
		CoronaMapMain main = new CoronaMapMain();
		main.mainRun();
	}
	
	private void mainRun() {
		PlaceManagement placeManagement = PlaceManagement.getInstance();
		placeManagement.run("PlaceSample.txt");
		
		PeopleManagement peopleManagement = PeopleManagement.getInstance();
		peopleManagement.run("PersonPath.txt");
		
		UserManager userManager = UserManager.getInstance();
		userManager.run("MemberList.txt");
		
		GUIMain start = new GUIMain();
		start.createAndShowGUI();
	}
}