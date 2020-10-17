package coronaMap;

public class StoresManagement extends Manager implements Factory {
	
	public void run() {
		
	}
	
	@Override
	public Manageable create() {
		return new Store();
	}
}
