package GUI;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;

import coronaMap.*;
import manager.*;

import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PathCompareGUI extends JPanel {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
	PeopleManagement pmanage = PeopleManagement.getInstance();
	UserManager uManage = UserManager.getInstance();
	JTable myTable;
	JTable shownTable;
	LocalDate myDay;
	String myName;
	String myCity = "영통구";
	ArrayList<String> myPlace = new ArrayList<String>();
	ArrayList<LocalTime> myTime = new ArrayList<LocalTime>();
	ArrayList<Integer> everyDanger = new ArrayList<Integer>();

	public PathCompareGUI() {
		setLayout(new BorderLayout());
		setMyPath();
		setPositivesArea();
		setResult();
	}

	private void setResult() {
		int resultDanger = computeResultDanger(everyDanger);
		String result = myDay + " Danger Rate : " + resultDanger;
		String necessary = null;
		
		switch (resultDanger) {
		case 1:
			necessary += " No Exam Required, Only City Matched";
			break;
		case 2:
			necessary += " No Exam Required, Visited Before Positive's visit";
			break;
		case 3:
			necessary += " Exam Required, Visited After Positive's visit";
			break;
		case 4:
			necessary += " Exam Required, Visited After Positive's visit more than 2";
			break;
		default:
			necessary += " Exam Required, Exactly Macthed";
			break;
		}
		String resultToAdd = "<html>" + result + "<br>" + necessary + "</html>";
		JLabel resultLabel = new JLabel(resultToAdd);
		resultLabel.setForeground(Color.red);
		resultLabel.setHorizontalAlignment(JLabel.CENTER);
		resultLabel.setPreferredSize(new Dimension(400, 100));
		add(resultLabel, BorderLayout.NORTH);
	}

	private void setMys() {
		User user;
		myName = uManage.getID();
		myDay = FirstGUI.getDate();

		if (myName == null) {
			myName = "Non-member";
		}
		user = uManage.fineUser(myDay.format(formatter));

		split(user.getPathAndTime());
	}

	private void split(ArrayList<String> information) {
		String[] temp = null;
		for (String string : information) {
			temp = string.split("/");
			myPlace.add(temp[0]);
			myTime.add(LocalTime.parse(temp[1]));
			System.out.println(temp[0] + " " + temp[1]);
		}
	}

	private void setMyPath() {
		setMys();
		String header[] = { "Me", "City", "Place", "Time" };
		String contents[][] = new String[myPlace.size()][4];
		contents[0][0] = myName;
		contents[0][1] = myCity;
		contents[0][2] = myPlace.get(0);
		contents[0][3] = myTime.get(0) + "";
		int i = 1;
		while (i < myPlace.size()) {
			contents[i][0] = null;
			contents[i][1] = null;
			contents[i][2] = myPlace.get(i);
			contents[i][3] = myTime.get(i) + "";
			i++;
		}

		myTable = new JTable(contents, header);
		setTableDateCenter(myTable);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 300));
		add(jscrollPane, BorderLayout.CENTER);
	}

	private void setPositivesArea() {
		String header[] = { "Risk/Number", "City", "Place", "Time" };
		String contents[][] = {};
		DefaultTableModel model = new DefaultTableModel(contents, header);
		
		shownTable = new JTable(model);
		JScrollPane jscrollPane = new JScrollPane(shownTable);
		jscrollPane.setPreferredSize(new Dimension(400, 400));
		// 위험도 별로 색깔 다르게 하기
		add(jscrollPane, BorderLayout.SOUTH);
		
		model = (DefaultTableModel) shownTable.getModel();
		
		setPositivesPath(model);
		setTableDateCenter(shownTable);
	}

	private void setPositivesPath(DefaultTableModel model) {
		LinkedHashMap<Integer, Integer> riskByPerson = uManage.getRiskByPerson(myDay.format(formatter)); // 확진자 번호, 위험도
		for (Entry<Integer, Integer> entry : riskByPerson.entrySet()) {
			boolean first = true;
			Person person;
			Object[] row = new Object[4];
			everyDanger.add(entry.getValue());
			row[0] = entry.getValue() + " / " + entry.getKey();
			row[1] = myCity;

			person = pmanage.findByNumber(entry.getKey() + "");
			
			for (int j = 0; j < person.pathList.size(); j++) {
				if (first) {
					row[2] = person.pathList.get(j);
					row[3] = person.timeList.get(j);
					first = false;
				} else {
					row[0] = null;
					row[1] = null;
					row[2] = person.pathList.get(j);
					row[3] = person.timeList.get(j);
				}
				model.addRow(row);
			}
		}
	}

	private int computeResultDanger(ArrayList<Integer> inputDanger) {
		if (inputDanger.contains(5)) {
			return 5;
		}

		if (inputDanger.contains(4)) {
			return 4;
		}
		
		if (inputDanger.contains(3)) {
			int count = 0;

			for (int result : inputDanger) {
				if (result == 3) {
					count++;
				}
			}
			if (count > 1) {
				return 4;
			} else {
				return 3;
			}
		}
		
		if (inputDanger.contains(2)) {
			return 2;
		}
		return 1;
	}

	private void setTableDateCenter(JTable table) {
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();

		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
	}
}
