package coronaMap;

import manager.*;

public class PlaceManagement extends Manager implements Factory {
	
	public void run(String fileName) {
		readAll(fileName, this);
	}
	
	@Override
	public Manageable create() {
		return new Place();
	}
	
	public boolean search(String keyword) {
		for (Manageable palce : objectList) {
			if (palce.matches(keyword)) {
				return true;
			}
		}
		return false;
	}
}