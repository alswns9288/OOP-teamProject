package coronaMap;

import manager.*;

public class PlaceManagement extends Manager implements Factory {
	private static PlaceManagement instance;
	
	private PlaceManagement() {}
	
	public static PlaceManagement getInstance() {
		if(instance == null) {
			instance = new PlaceManagement();
		}
		return instance;
	}
	
	public void run(String fileName) {
		readAll(fileName, this);
	}
	
	@Override
	public Manageable create() {
		return new Place();
	}
	
	public boolean search(String keyword) {
		for (Manageable place : objectList) {
			if (place.matches(keyword)) {
				return true;
			}
		}
		return false;
	}
	public void printList() {
		for (Manageable place : objectList) {
			place.print();
		}
	}
}