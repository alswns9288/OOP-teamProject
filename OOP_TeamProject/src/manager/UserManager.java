package manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
	private String memberListFile;
	private boolean admin;

	private UserManager() {}
	
	public static UserManager getInstance() {
		if(instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	public boolean login(String ID, String password) {
		System.out.println("2");
		System.out.println(memberList.size());
		for (Member member: memberList) {
			System.out.println(member.name);
			System.out.println(ID);
			if (member.name.contentEquals(ID) && member.password.contentEquals(password)) {
				userID = ID;
				if (ID.contentEquals("admin")) {
					member.setAdmin();
					admin = true;
				}
				return true;
			}
		}
		return false;
	}
	

	public void run(String fileName) {
		memberListFile = fileName;
		readMembers();
	}
	
	public boolean signUp(String newID, String newPassword) {
		BufferedWriter writeFile = (BufferedWriter) openFile(memberListFile, false);
		BufferedWriter newFile = (BufferedWriter) openFile(newID + ".txt", false);
		
		for (Member member : memberList) {
			if (member.name.contentEquals(newID)) {
				return false;
			}
		}
		
		try {
			newFile.flush();
			writeFile.newLine();
			writeFile.write(newID + " " + newPassword);
			writeFile.close();
			readMembers();
			return true;
		} catch (IOException e) {
			System.out.println("Fail! PoepleManagement.addInformation");
			return false;
		}
	}

	public boolean isAdmin() {
		return admin;
	}
	
	public String getID() {
		return userID;
	}
	
	public void setID(String string) {
		admin = false;
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
	
	public void readMembers() {
		Scanner scanFile = (Scanner) openFile(memberListFile, true);
		Member member = null;
		String userID = null;
		String password = null;

		memberList.clear();
		while (scanFile.hasNext()) {
			userID = scanFile.next().trim();
			password = scanFile.nextLine().trim();
			
			member = new Member(userID, password);
			memberList.add(member);
		}
		scanFile.close();
	}

	protected Object openFile(String fileName, boolean type) {
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
			System.out.println(person.number);
			value = user.calcRisk(person);
			riskByPerson.put(person.number, value);
		}
		
		return riskByPerson;
	}

	public void addInformation(String date) {
		Member member = null;
		for (Member m : memberList) {
			if (userID.contentEquals(m.name)) {
				member = m;
				break;
			}
		}
		for (User user : userList) {
			if (user.date.contentEquals(date)) {
				BufferedWriter writeFile = (BufferedWriter) openFile(userID + ".txt", false);
				member.addInformation(user.getList(), date, writeFile);
				System.out.println(user.getList().size());
				break;
			}
		}
	}
}