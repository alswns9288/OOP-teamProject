package GUI;

import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import coronaMap.*;

public class PrintpplGUI extends JPanel {
	ArrayList<Person> person = new ArrayList<Person>();
	ArrayList<String> pathList;
	ArrayList<LocalTime> timeList;
	PeopleManagement peopleManagement = PeopleManagement.getInstance();
	String date = FirstGUI.getDate().format(DateTimeFormatter.ofPattern("MM/dd"));
	DefaultTableModel model;
	JTable table;
	int num;
	boolean pre;
	

	public PrintpplGUI() {
		num = peopleManagement.searchNumber(date);
		setTable();
		setTableDateCenter();
		showRegisteredDate();
	}

	private void showRegisteredDate() {
		if (num < 1) {
			return;
		}

		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);

		person = peopleManagement.findByDate(date);
		String path = null;
		for (Person p : person) {
			pre = false;
			pathList = p.pathList;
			timeList = p.timeList;
			for (int i = 0; i < pathList.size(); i++) {
				path = "   " + pathList.get(i) + " / " + timeList.get(i);
				addTableRow(p.number, path);
				pre = true;
			}
		}
	}

	private void setTableDateCenter() {
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
	}

	private void addTableRow(int number, String path) {
		if (pre) {
			Object[] row = { null, path };
			model.addRow(row);
			return;
		}
		Object[] row = { number, path };
		model.addRow(row);
	}

	private void setTable() { // 1 화포식당 영통점/12:20-경기대/13:30
		String header[] = { "확진자 번호", "위치/시간" };
		String contents[][] = new String[0][0];

		model = new DefaultTableModel(contents, header);
		table = new JTable(model);

		JScrollPane jscrollPane = new JScrollPane(table);
		jscrollPane.setPreferredSize(new Dimension(370, 480));
		add(jscrollPane, BorderLayout.CENTER);
	}
}
