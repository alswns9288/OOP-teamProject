package manager;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;

import coronaMap.User;
import coronaMap.Member;

public class UserManager {
	private static UserManager instance;
	public ArrayList<User> userList = new ArrayList<>();
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
	
	public User fineUser(LocalDate date) {
		for (User user: userList) {
			if (date.toString().contentEquals(user.date.toString())) {
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
		for (Member m: memberList) {
			if (m.name.contentEquals(userID)) {
				member = m;
				member.readFile(userID + ".txt");
				break;
			}
		}
		setUser(member);
	}

	private void setUser(Member member) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
		HashMap<String, String> tmp = new HashMap<>();
		User user = null;
		String tmpDate = null;
		LocalDate date = null;
		
		userList.clear(); // 로그아웃했다가 다시 로그인하면 정보 비우기
		
		
		for (Entry<String, String> entry: tmp.entrySet()) {
			date = LocalDate.parse(entry.getKey(), formatter);
			
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
	
	public ArrayList<String> getPath(String Date) {
		
		return null;
	}

}
