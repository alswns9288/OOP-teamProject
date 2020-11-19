package manager;

import java.util.ArrayList;

import coronaMap.User;

public class UserManager {
	public ArrayList<User> userList = new ArrayList<>();

	public void addList(User user) {
		userList.add(user);
	}
	
	public User fineUser(String keyword) {
		for (User user: userList) {
			if (keyword.contentEquals(user.date)) {
				return user;
			}
		}
		return null;
	}
}
