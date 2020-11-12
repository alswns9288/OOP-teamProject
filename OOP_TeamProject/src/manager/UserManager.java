package manager;

import java.util.ArrayList;

import coronaMap.User;

public class UserManager {
	ArrayList<User> userList = new ArrayList<>();

	public void addList(User user) {
		userList.add(user);
	}
}
