package coronaMap;

import java.io.*;
import java.time.LocalTime;
import java.util.*;

import manager.Manageable;

public class Person implements Manageable {
	public ArrayList<String> pathList = new ArrayList<>();
	public ArrayList<LocalTime> timeList = new ArrayList<>();
	public String date;
	int number;
	
	@Override
	public boolean matches(String keyword) {
		if (keyword.contentEquals(date)) {
			return true;
		}
		for (int i = 0; i < pathList.size(); i++) {
			String path = pathList.get(i) + "/" + timeList.get(i);
			if (keyword.contentEquals(path)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void readFile(Scanner scanFile) {
		String information = null;

		number = scanFile.nextInt();
		date = scanFile.next();
		information = scanFile.nextLine();
		information = information.trim();
		split(information);
	}

	@Override
	public void print() {
		System.out.printf("[%d] (%s) ", number, date);
		for (int i = 0; i < pathList.size(); i++) {
			System.out.print(pathList.get(i) + "/" + timeList.get(i));
			if (i == pathList.size() - 1) {
				break;
			}
			System.out.print(" => ");
		}
		System.out.println();
	}

	private void split(String information) {
		String[] registerdArray = null;
		String[] temp = null;

		registerdArray = information.split("-");
		for (String string : registerdArray) {
			temp = string.split("/");
			pathList.add(temp[0]);
			timeList.add(LocalTime.parse(temp[1])); // x:xx�� ��Ÿ�� �ð��� time format���� ����
		}
	}

	@Override
	public void addInformation(BufferedWriter writeFile, Scanner scan) { // �Է� �޴� ����: ���1/�ð�1 ���2/���2, management�� �Űܾ� �� ��?
		int newNumber = CoronaMapMain.peopleManagement.number;
		
		while (true) {
			String path[] = new String[100];
			newNumber += 1;
			System.out.print(">> ");
			path[0] = scan.next();
			if (path[0].contentEquals("end")) {
				break;
			}
			path[1] = scan.next();
			try { // ���࿡ ��θ� ���� �޴´ٸ� ArrayList�� ���� �� 
				writeFile.newLine();
				writeFile.write(newNumber + " ");
				writeFile.write(path[0]);
				writeFile.write("-");
				writeFile.write(path[1]);
				writeFile.flush(); // ���۸� ���
			} catch (IOException e) {
				System.out.println("Fail! Person.addInformation");
				System.exit(0);
			}
		}
	}
}