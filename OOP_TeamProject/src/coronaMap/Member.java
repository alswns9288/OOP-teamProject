package coronaMap;

import java.io.*;
import java.time.LocalTime;
import java.util.*;
import manager.*;

public class Member {
	public ArrayList<String> information = new ArrayList<>();
	public ArrayList<String> date = new ArrayList<>();
	public String name;
	
	public Member(String userID) {
		name = userID;
	}

	public void readFile(String fileName) {
		Scanner scanFile = openFile(fileName);
		
		while (scanFile.hasNext()) {
			String tmp = null;
			
			date.add(scanFile.next());
			tmp = (scanFile.nextLine()).trim();
			information.add(tmp);
		}
		scanFile.close();
		
		for (int i = 0; i < date.size(); i++) {
			System.out.print(date.get(i) + " ");
			System.out.println(information.get(i));
		}
	}
	
	private Scanner openFile(String fileName) {
		Scanner scanFile = null;

		try {
			scanFile = new Scanner(new File(fileName));
		} catch (IOException e) {
			System.out.println("Fail! scanFile " + fileName);
			System.exit(0);
		}
		return scanFile;
	}
}
