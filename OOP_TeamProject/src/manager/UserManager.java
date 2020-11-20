package manager;

import java.time.LocalDate;
import java.util.ArrayList;

import coronaMap.User;

public class UserManager {
	public ArrayList<User> userList = new ArrayList<>();

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
