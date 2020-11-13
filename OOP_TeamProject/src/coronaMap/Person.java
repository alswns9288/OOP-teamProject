package coronaMap;

import java.io.*;
import java.text.*;
import java.util.*;

import manager.Manageable;

public class Person implements Manageable {
	ArrayList<String> pathList = new ArrayList<>();
	ArrayList<String> dateList = new ArrayList<>();
	int number;

	@Override
	public boolean matches(String keyword) {
		for (int i = 0; i < pathList.size(); i++) {
			String path = pathList.get(i) + "/" + dateList.get(i);
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
		information = scanFile.nextLine();
		information = information.trim();
		split(information);
	}

	@Override
	public void print() {
		System.out.printf("[%d] ", number);
		for (int i = 0; i < pathList.size(); i++) {
			System.out.print(pathList.get(i) + "/" + dateList.get(i));
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
			dateList.add(toDateFormat(temp[1])); // x:xx로 나타낸 시간을 time format으로 변경
		}
	}

	private String toDateFormat(String string) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // Date의 format 설정
		String result = null;

		try {
			Date date = format.parse(string);
			result = format.format(date);
			return result;
		} catch (ParseException e) {
			System.out.println("DateFormat Error!" + string);
			System.exit(1);
		}
		return null;
	}

	@Override
	public void addInformation(BufferedWriter writeFile, Scanner scan) { // 입력 받는 형식: 장소1/시간1 장소2/장소2
		int newNumber = PeopleManagement.number;
		
		while (true) {
			String path[] = new String[100];
			newNumber += 1;
			System.out.print(">> ");
			path[0] = scan.next();
			if (path[0].contentEquals("end")) {
				break;
			}
			path[1] = scan.next();
			try { // 만약에 경로를 많이 받는다면 ArrayList로 받을 것 
				writeFile.newLine();
				writeFile.write(newNumber + " ");
				writeFile.write(path[0]);
				writeFile.write("-");
				writeFile.write(path[1]);
				writeFile.flush(); // 버퍼를 출력
			} catch (IOException e) {
				System.out.println("Fail! Person.addInformation");
				System.exit(0);
			}
		}
	}
}