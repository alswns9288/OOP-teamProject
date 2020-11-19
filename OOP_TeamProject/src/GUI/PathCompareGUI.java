package GUI;

import java.awt.*;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import coronaMap.*;
import manager.*;


import java.awt.event.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class PathCompareGUI extends JPanel {
	PeopleManagement manage = new PeopleManagement();
	UserManager uManage = new UserManager();
	int dangerRate = 1;
	String myDay = null;
	String myName = null;
	String myCity = "Yountonggu";
	ArrayList<String> myPlace = new ArrayList<String>();
	ArrayList<String> myTime = new ArrayList<String>();
	
	public PathCompareGUI() {
		setLayout(new BorderLayout());
		setMyPath();
		setPositivesPath();
		setResult();
	}
	
	private void setResult() {
		String result = myDay + "Danger Rate : "+dangerRate; 
		String necessary = null;
		switch (dangerRate) {
		case 1:
			necessary += " No Exam Required, Only City Match"; 
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
			necessary += " Exam Required, Exactly Macth";
			break;
		}
		String resultToAdd = "<html>"+result+"<br>"+necessary+"</html>";
		JLabel resultLabel = new JLabel(resultToAdd);
		resultLabel.setForeground(Color.red);
		resultLabel.setHorizontalAlignment(JLabel.CENTER);
		resultLabel.setPreferredSize(new Dimension(400, 20));		
		add(resultLabel, BorderLayout.PAGE_START);
	}
	
	private void setMys() {
		int NumOfMyPath = uManage.userList.size();
		for (int i = 0; i<NumOfMyPath; i++) {
			myDay = uManage.userList.get(i).date;
			split(uManage.userList.get(i).information);
		}
	}

	private void split(ArrayList<String> information) {
		String[] temp = null;
		for (String string : information) {
			temp = string.split("/");
			myPlace.add(temp[0]);
			myTime.add(temp[1]);
		}
	}

	private void setMyPath() {
		setMys();
		String header[] = {"Me", "Day", "Place","Time"};
		String contents[][] = new String[myPlace.size()][4];
		for(int i = 0; i<myPlace.size(); i++) {
			contents[i][0] = myCity;
			contents[i][1] = myName;
			contents[i][2] = myPlace.get(i);
			contents[i][3] = myTime.get(i);
		}
		
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 300));
		add(jscrollPane, BorderLayout.CENTER);
	}

	private void setPositivesPath() {
		String header[] = {"City", "Danger Rate", "Place", "Time"};
		String contents[][] = null;
		//contents 데이터 구축
		/*
		for (int i = 0; i<myPlace.size(); i++) {
			int rate = 1;
			String placeTmp = myPlace.get(i);
			if (manage.search(placeTmp)) {
				String dayTmp = myDay.get(i);
				if(manage.search(dayTmp)) {
					String timeTmp = myTime.get(i); // LocalTime에서 비교기능이 있는 메소드를 찾기
					if(manage.search(timeTmp)) {
						rate = 5;
					}
					else
						 rate = 3;
				}
				else
					rate = 2;
				contents[i][0] = rate+"";
				// 검색기능을 통해 content 데이터 구축!!
				
			}
		}*/
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 300));
		add(jscrollPane, BorderLayout.PAGE_END);
	}
	//최종 위험도 알려주는 알고리즘 구축
}
