package coronaMap;

import java.io.*;
import java.util.*;

public abstract class Manager {
	Scanner scan = new Scanner(System.in);
	ArrayList<Manageable> objectList = new ArrayList<>();
	
	void readAll(String fileName, Factory factory) {
		Scanner scanFile = openFile(fileName);
		Manageable object = null;
		
		while (scanFile.hasNext()) {
			object = factory.create();
			object.readFile(scanFile);
			objectList.add(object);
		}
		scanFile.close();
	}
	
	void printAll() {
		for (Manageable object: objectList) {
			object.print();
		}
	}
	
	void search() {
		String keyword = null;
		
		while (true) {
			System.out.print(">> ");
			keyword = scan.next();
			if (keyword.contentEquals("end")) {
				break;
			}
			for (Manageable object: objectList) {
				if (object.matches(keyword)) {
					object.print();
				}
			}
		}
	}
	
	Scanner openFile(String fileName) {
		Scanner scanFile = null;
		
		try {
			scanFile = new Scanner(new File(fileName));
		} catch (IOException e) {
			System.out.println("Fail!" + fileName);
			System.exit(0);
		}
		return scanFile;
	}
}
