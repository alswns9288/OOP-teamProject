package coronaMap;

import java.io.*;
import java.util.*;

public abstract class Manager {
	Scanner scan = new Scanner(System.in);
	ArrayList<Manageable> objectList = new ArrayList<>();

	void readAll(String fileName, Factory factory) {
		Scanner scanFile = (Scanner) openFile(fileName, true);
		Manageable object = null;
		while (scanFile.hasNext()) {
			object = factory.create();
			object.readFile(scanFile);
			objectList.add(object);
		}
		scanFile.close();
	}

	void printAll() {
		for (Manageable object : objectList) {
			object.print();
		}
	}

	void addInformation(String fileName, Factory factory) {
		BufferedWriter writeFile = (BufferedWriter) openFile(fileName, false);
		Manageable object = null;

		object = factory.create();
		object.addInformation(writeFile, scan);
	}

	void search() {
		String keyword = null;

		while (true) {
			System.out.print(">> ");
			keyword = scan.nextLine();
			if (keyword.contentEquals("end")) {
				break;
			}
			for (Manageable object : objectList) {
				if (object.matches(keyword)) {
					object.print();
				}
			}
		}
	}
	
	ArrayList<Manageable> getList() {
		return objectList;
	}

	Object openFile(String fileName, boolean type) {
		if (type) {
			Scanner scanFile = null;

			try {
				scanFile = new Scanner(new File(fileName));
			} catch (IOException e) {
				System.out.println("Fail! scanFile " + fileName);
				System.exit(0);
			}
			return scanFile;
		}

		BufferedWriter writeFile = null;

		try {
			writeFile = new BufferedWriter(new FileWriter(fileName, true));
		} catch (IOException e) {
			System.out.println("Fail! writeFile " + fileName);
			System.exit(0);
		}
		return writeFile;
	}
}
