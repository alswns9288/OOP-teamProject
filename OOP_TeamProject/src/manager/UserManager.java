package manager;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;

import GUI.FirstGUI;
import coronaMap.User;
import coronaMap.Member;
import coronaMap.PeopleManagement;
import coronaMap.Person;

public class UserManager {
	private static UserManager instance;
	public ArrayList<User> userList = new ArrayList<>(); // 날짜별로 유저에 저장됨
	public ArrayList<Member> memberList = new ArrayList<>();
	private String userID;

	private UserManager() {}
	
	public static UserManager getInstance() {
		if(instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	public boolean login(String ID) {
		for (Member member: memberList) {
			if (member.name.contentEquals(ID)) {
				userID = ID;
				return true;
			}
		}
		return false;
	}
	
	public String getID() {
		return userID;
	}
	
	public void setID(String string) {
		userID = string;
	}
	
	public void addList(User user) {
		userList.add(user);
	}
	
	public User fineUser(String date) {
		for (User user: userList) {
			if (date.contentEquals(user.date)) {
				return user;
			}
		}
		return null;
	}
	
	public void readMembers(String fileName) {
		Scanner scanFile = openFile(fileName);
		Member member = null;
		String userID = null;
		
		while (scanFile.hasNext()) {
			userID = scanFile.nextLine();
			
			member = new Member(userID);
			memberList.add(member);
		}
		scanFile.close();
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

	public void readMemberPath() {
		Member member = null;
		userList.clear();
		if (userID == null) {
			return;
		}
		for (Member m: memberList) {
			if (m.name.contentEquals(userID)) {
				member = m;
				member.readFile(userID + ".txt");
				break;
			}
		}
		setUser(member);
	}

	public void setUser(Member member) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
		HashMap<String, String> tmp = member.getPathByDate(FirstGUI.getDate().format(formatter));
		User user = null;
		String date = null;
		
		if (tmp == null) {
			return;
		}
		
		for (Entry<String, String> entry: tmp.entrySet()) {
			date = entry.getKey();
			
			if (fineUser(date) == null) {
				user = new User(date);
				user.addInformation(entry.getValue());
				addList(user);
			} else {
				user = fineUser(date);
				user.addInformation(entry.getValue());
			}
		}
	}
	
	public ArrayList<String> getPath(String date) {
		ArrayList<String> pathAndTime = new ArrayList<>();
		readMemberPath();
		User user = fineUser(date);
		if (user == null) {
			return null;
		}
		
		pathAndTime = user.getPathAndTime();
		
		return pathAndTime;
	}
	
	public LinkedHashMap<Integer, Integer> getRiskByPerson(String date) {
		PeopleManagement peopleManager = PeopleManagement.getInstance();
		LinkedHashMap<Integer, Integer> riskByPerson = new LinkedHashMap<Integer, Integer>();
		ArrayList<Person> personList = peopleManager.findByDate(date);
		User user = fineUser(date);
		int value = 0;
		
		if (user == null) {
			return null;
		}

		for (Person person : personList) {
			value = user.calcRisk(person);
			riskByPerson.put(person.number, value);
		}
		
		return riskByPerson;
	}
}
