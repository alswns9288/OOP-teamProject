package coronaMap;

import java.io.BufferedWriter;
import java.util.Scanner;

public class Place implements Manageable {
	String placeName;
	
	@Override
	public boolean matches(String keyword) {
		if (keyword.contentEquals(placeName)) {
			return true;
		}
		return false;
	}

	@Override
	public void readFile(Scanner scanFile) {
		placeName = scanFile.nextLine();
	}

	@Override
	public void print() {
		System.out.printf("%s\n", placeName);
	}

	@Override
	public void addInformation(BufferedWriter writeFile, Scanner scan) {
		// TODO Auto-generated method stub
		
	}
}