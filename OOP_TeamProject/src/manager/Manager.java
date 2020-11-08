package manager;

import java.io.*;
import java.util.*;

public abstract class Manager {
	Scanner scan = new Scanner(System.in);
	ArrayList<Manageable> objectList = new ArrayList<>();

	public void readAll(String fileName, Factory factory) {
		Scanner scanFile = (Scanner) openFile(fileName, true);
		Manageable object = null;
		objectList.clear();
		
		while (scanFile.hasNext()) {
			object = factory.create();
			object.readFile(scanFile);
			objectList.add(object);
		}
		scanFile.close();
	}

	public void printAll() {
		for (Manageable object : objectList) {
			object.print();
		}
	}

	public void addInformation(String fileName, Factory factory) {
		BufferedWriter writeFile = (BufferedWriter) openFile(fileName, false);
		Manageable object = null;
		System.out.println("�߰�\n");

		object = factory.create();
		object.addInformation(writeFile, scan);
		objectList.add(object);
	}

	public void search() {
		String keyword = null;
		
		System.out.println("�˻�\n");
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

	public ArrayList<Manageable> getList() { // Person�� �ִ� addInformation�� PeopleManagement�� ���� �ұ�?
		return objectList;
	}

	Object openFile(String fileName, boolean type) { // ���� ����� ������ openFile��������ϰ�, ���� ��ɰ� �߰� ��� ��� ���� ���� �޼ҵ� ����� �ǳ�?
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
