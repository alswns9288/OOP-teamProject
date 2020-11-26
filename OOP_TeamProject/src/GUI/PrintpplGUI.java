package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import coronaMap.PeopleManagement;
import coronaMap.Person;
import manager.Manageable;
import static GUI.FirstGUI.date;

public class PrintpplGUI extends JPanel {
	PeopleManagement pmanage = PeopleManagement.getInstance();
	public ArrayList<String> path = new ArrayList<>();
	public ArrayList<LocalTime> time = new ArrayList<>();
	ArrayList<String> data = new ArrayList<String>();
	int num = pmanage.searchNumber(date.format(DateTimeFormatter.ofPattern("M/d")));;

	public PrintpplGUI() {
		setTable();
	}

	private void setTable() { // 1 화포식당 영통점/12:20-경기대/13:30
		String header[] = { "확진자", "위치/시간" };
		String contents[][] = new String[num][2];
		DefaultTableModel myTable = new DefaultTableModel(contents, header);

		for (Manageable m : pmanage.objectList) {
			Person p = (Person) m;
			path = p.pathList;
			time = p.timeList;

		}

		JTable shownTable = new JTable(myTable);
		JScrollPane jscrollPane = new JScrollPane(shownTable);
		jscrollPane.setPreferredSize(new Dimension(370, 480));
		add(jscrollPane, BorderLayout.CENTER);
	}
}
