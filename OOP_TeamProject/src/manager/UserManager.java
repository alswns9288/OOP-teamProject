package manager;

import java.time.LocalDate;
import java.util.ArrayList;

import coronaMap.PeopleManagement;
import coronaMap.User;

public class UserManager {
	private static UserManager instance;
	public ArrayList<User> userList = new ArrayList<>();
	private String userID;

	private UserManager() {}
	
	public static UserManager getInstance() {
		if(instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	public void login(String ID) {
		userID = ID;
	}
	
	public String getID() {
		return userID;
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
}
