package coronaMap;

public class PlaceManagement extends Manager implements Factory {
	
	public void run() {
		readAll("PlaceSample.txt", this);
		printAll();
		search();
		addInformation("PlaceSample.txt", this);
		readAll("PlaceSample.txt", this);
		printAll();
	}
	
	@Override
	public Manageable create() {
		return new Place();
	}
}