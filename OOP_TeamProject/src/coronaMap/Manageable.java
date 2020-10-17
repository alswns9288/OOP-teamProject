package coronaMap;

import java.util.Scanner;

public interface Manageable {
	boolean matches(String keyword);
	void readFile(Scanner scanFile);
	void print();
}
