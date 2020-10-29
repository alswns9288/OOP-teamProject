package coronaMap;

import java.io.BufferedWriter;
import java.util.Scanner;

public interface Manageable {
	boolean matches(String keyword);
	void readFile(Scanner scanFile);
	void print();
	void addInformation(BufferedWriter writeFile, Scanner scan);
}