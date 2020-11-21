package GUI;

import java.awt.*;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import coronaMap.*;
import manager.*;


import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class PathCompareGUI extends JPanel {
	PeopleManagement pmanage = PeopleManagement.getInstance();
	UserManager uManage = UserManager.getInstance();
	int dangerRate = 1;
	LocalDate myDay = null;
	String myName = null;
	String myCity = "Yountonggu";
	ArrayList<String> myPlace = new ArrayList<String>();
	ArrayList<LocalTime> myTime = new ArrayList<LocalTime>();
	ArrayList<Integer> everyDanger = new ArrayList<Integer>();
	
	public PathCompareGUI() {
		setLayout(new BorderLayout());
		setMyPath();
		setPositivesPath();
		setResult();
	}
	
	private void setResult() {
		computeResultDanger(everyDanger);
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
		myDay = FirstGUI.getDate();
		for (int i = 0; i<NumOfMyPath; i++) {
			split(uManage.userList.get(i).information);
		}
	}

	private void split(ArrayList<String> information) {
		String[] temp = null;
		for (String string : information) {
			temp = string.split("/");
			myPlace.add(temp[0]);
			myTime.add(LocalTime.parse(temp[1]));
		}
	}

	private void setMyPath() {
		setMys();
		String header[] = {"Me", "Day", "Place","Time"};
		String contents[][] = new String[myPlace.size()][4];
		contents[0][0] = myCity;
		contents[0][1] = myName;
		contents[0][2] = myPlace.get(0);
		contents[0][2] = myTime.get(0)+"";
		int i = 1;
		while(i<myPlace.size()) {
			contents[i][0] = null;
			contents[i][1] = null;
			contents[i][2] = myPlace.get(i);
			contents[i][3] = myTime.get(i)+"";
			i++;
		}
		
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 300));
		add(jscrollPane, BorderLayout.CENTER);
	}

	private void setPositivesPath() {
		int size = 0;
		String header[] = {"City", "Danger Rate", "Place", "Time"};
		String contents[][] = new String[size][size]; //일별확진자의 수를 구하기
		//contents 데이터 구축
		for(int i = 0; i<myPlace.size(); i++) {
			contents[i][0] = null;
			contents[i][1] = computeDangerRate(i)+"";
			if(pmanage.search(myPlace.get(i))) {
				int j = 0;
				for(String s: pmanage.matchPath(myPlace.get(i)))
					if (j%2==0) 
						contents[i][2] = s;
					else
						contents[i][3] = s;
			}
		}
		contents[0][0] = myCity;
		
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 300));
		// 위험도 별로 색깔 다르게 하기
		add(jscrollPane, BorderLayout.PAGE_END);
	}
	
	private int computeDangerRate(int num) {
		int danger = 1;
		if (pmanage.search(myPlace.get(num))) {
			danger++;
			LocalTime positivesTime = null;//null값에 위에서 겹친 확진자의 시간이 들어간다.
			if(myTime.get(num).isBefore(positivesTime)) {
				danger++;
			}
			if(myTime.get(num).equals(positivesTime)) {
				danger += 3;
			}
		}
		everyDanger.add(danger);
		return danger;
	}
	
	private int computeResultDanger(ArrayList<Integer> inputDanger) {
		int result = 1;
		ArrayList<Integer> ind = new ArrayList<Integer>();
		if(inputDanger.contains(2)) 
			result = 2;
		for(int i = 0; i<inputDanger.size(); i++) {
			ind.add(inputDanger.indexOf(3));
		}
		if(ind.size()==1) 
			result = 3;
		else if(ind.size()>1)
			result = 4;
		if(inputDanger.contains(5))
			result = 5;
		
		return result;
	}
}
