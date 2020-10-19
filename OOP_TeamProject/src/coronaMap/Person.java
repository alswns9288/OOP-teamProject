package coronaMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Person implements Manageable {
	ArrayList<String> pathList = new ArrayList<>();
	ArrayList<String> dateList = new ArrayList<>();
	int number;
	
	@Override
	public boolean matches(String keyword) {
		for (int i = 0; i < pathList.size(); i++) {
			if (keyword.contentEquals(pathList.get(i)) && keyword.contentEquals(dateList.get(i))) {
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
		split(information);
	}

	@Override
	public void print() {
		System.out.printf("[%d]", number);
		for (int i = 0; i < pathList.size(); i++) {
			System.out.print(pathList.get(i) + "/" + dateList.get(i));
			if (i == pathList.size() - 1) {
				break;
			}
			System.out.print(" => ");
		}
		System.out.println();
	}

	private void split (String information) {
		String[] registerdArray = null;
		String[] temp = null;
		
		registerdArray = information.split("-");
		for (String string: registerdArray) {
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
}
