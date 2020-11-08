package coronaMap;

import java.io.*;
import java.util.Scanner;

import manager.Manageable;

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
	public void addInformation(BufferedWriter writeFile, Scanner scan) { 	// 입력형식 : 장소1 장소2
		while (true) {
			System.out.print(">> ");
			String inputPlace = scan.next();
			if (inputPlace.contentEquals("end"))
				break;
			try {
				writeFile.newLine();
				writeFile.write(inputPlace);
				writeFile.flush();
			} catch (IOException e) {
				System.out.println("Fail! Place.addInformation");
				System.exit(0);
			}
		}
	}

}