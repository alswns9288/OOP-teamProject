package coronaMap;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import manager.Manageable;
import manager.Split;

public class Person implements Manageable, Split {
	public ArrayList<String> pathList = new ArrayList<>();
	public ArrayList<LocalTime> timeList = new ArrayList<>();
	public String date;
	public int number;
	
	@Override
	public boolean matches(String keyword) {
		if (keyword.contentEquals(date)) {
			return true;
		}
		if (keyword.contentEquals(Integer.toString(number))) {
			return true;
		}
		for (int i = 0; i < pathList.size(); i++) {
			String path = pathList.get(i) + "/" + timeList.get(i);
			if (path.contains(keyword)) {
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

	public void split(String information) {
		String[] registerdArray = null;
		String[] temp = null;

		registerdArray = information.split("-");
		for (String string : registerdArray) {
			temp = string.split("/");
			pathList.add(temp[0]);
			timeList.add(LocalTime.parse(temp[1])); // x:xx로 나타낸 시간을 time format으로 변경
		}
	}

	public boolean matchesNumber(String number) {
		if (number.contentEquals(Integer.toString(this.number))) {
			return true;
		}
		return false;
	}
}