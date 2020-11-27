package coronaMap;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import manager.Split;

public class User implements Split {
	ArrayList<String> pathAndTime = new ArrayList<>();
	public String date;

	public User(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
		this.date = date.format(formatter);
	}

	public User(String date) {
		this.date = date;
	}

	public void addInformation(String path, String time) {
		pathAndTime.add(String.format("%s/%s", path, time));
	}

	public void addInformation(String information) {
		split(information);
	}

	@Override
	public void split(String information) {
		String[] registerdArray = null;

		registerdArray = information.split("-");
		for (String string : registerdArray) {
			pathAndTime.add(string);
		}
	}

	public ArrayList<String> getPathAndTime() {
		if (pathAndTime.size() == 0) {
			return null;
		}
		return pathAndTime;
	}

	public int calcRisk(Person person) {
		ArrayList<String> pathList = new ArrayList<>();
		ArrayList<LocalTime> timeList = new ArrayList<>();
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		String temp[] = new String[2];
		int result = 0;

		for (String tmp : pathAndTime) {
			temp = tmp.split("/");
			pathList.add(temp[0]);
			timeList.add(LocalTime.parse(temp[1]));
		}

		for (int i = 0; i < pathList.size(); i++) {
			for (int j = 0; j < person.pathList.size(); j++) {
				if (pathList.get(i).contentEquals(person.pathList.get(j))) {
					String state = null;

					state = compareTime(timeList.get(i), person.timeList.get(j));
					if (state.contentEquals("Before")) { // 유저가 확진자 전에 다녀감
						resultList.add(2);
					} else if (state.contentEquals("Equal")) {
						resultList.add(5);
					} else if (state.contentEquals("After")) {
						resultList.add(3);
					}
				} else {
					resultList.add(1);
				}
			}
		}
		result = calcResult(resultList);

		return result;
	}

	private int calcResult(ArrayList<Integer> resultList) {
		if (resultList.contains(5)) {
			return 5;
		}

		if (resultList.contains(3)) {
			int count = 0;

			for (int result : resultList) {
				if (result == 3) {
					count++;
				}
			}
			if (count > 1) {
				return 4;
			} else {
				return 3;
			}
		}
		
		if (resultList.contains(2)) {
			return 2;
		}
		return 1;
	}

	private String compareTime(LocalTime time, LocalTime PersonTime) {
		if (time.isBefore(PersonTime)) { // 유저가 확진자 전에 다녀감
			return "Before";
		} else if (time.toString().contentEquals(PersonTime.toString())) {
			return "Equal";
		} else if (time.isAfter(PersonTime)) {
			return "After";
		}
		return null;
	}
}
