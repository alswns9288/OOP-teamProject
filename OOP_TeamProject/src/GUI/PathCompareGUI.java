package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.ArrayList;

public class PathCompareGUI extends JPanel {
	int dangerRate = 1;
	ArrayList<String> myPlace = new ArrayList<String>();
	ArrayList<String> myDay = new ArrayList<String>();
	ArrayList<String> myTime = new ArrayList<String>();

	public PathCompareGUI( String[] inputPlace, 
			String[] inputDay, String[] inputTime) { // 외부로부터  장소, 날짜, 시간을 배열 형태로 전달받음
		for(int i = 0; i<inputPlace.length; i++) {
			myPlace.add(inputPlace[i]);
		}
		for(int i = 0; i<inputDay.length; i++) {
			myDay.add(inputDay[i]);
		}
		for(int i = 0; i<inputTime.length; i++) {
			myTime.add(inputTime[i]);
		}
	}

	public PathCompareGUI() {
		setLayout(new BorderLayout());
		setMyPath();
		setPositivesPath();
		setResult();
	}

	private void setResult() {
		String result = "Danger Rate : "+dangerRate; // "위험도 "+dangerRate+" 코로나 검사"
		switch (dangerRate) {
		case 1:
			result += " No Exam Required, No Match with Positive"; //"불필요, 확진자의 경로와 일치하는 부분이 없음"
			break;
		case 2:
			result += " No Exam Required, Visit On Another Day"; //"불필요, 확진자의 경로와 장소가 일치하지만 다른 날에 방문"
			break;
		case 3:
			result += " Exam Required, Match Place and Day"; //"필요, 확진자의 경로와 날짜, 장소가 일치!!"
			break;
		case 4:
			result += " Exam Required, Match Multiple Place and Day"; //"필요, 확진자의 경로와 복수개의 날짜와 장소가 일치!!"
			break;
		default:
			result += " Exam Required, Exactly Macth"; //"필요, 확진자의 경로와 날짜와 시간, 장소가 일치!!"
			break;
		}
		JLabel resultLabel = new JLabel(result);
		resultLabel.setPreferredSize(new Dimension(400, 40));
		add(resultLabel, BorderLayout.PAGE_END);
	}

	private void setMyPath() {
		String header[] = {"Me", "Place", "Day","Time"}; // "나", "위치", "날짜", "시간"
		String contents[][] = new String[myPlace.size()][4];
		for(int i = 0; i<myPlace.size(); i++) {
			contents[i][0] = null;
			contents[i][1] = myPlace.get(i);
			contents[i][2] = myDay.get(i);
			contents[i][3] = myTime.get(i);
		}
		
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 300));
		add(jscrollPane, BorderLayout.PAGE_START);
	}

	private void setPositivesPath() {
		String header[] = {"No. of postives", "Place", "Day", "Time"}; // "확진자 번호", "위치", "날짜", "시간"
																
		// 검색기능을 통해 content 데이터 구축
		/*
		PlaceManagement manage = new PlaceManagement();
		int overlapNo = 0; //겹치는 경로의 수 
		String contents[][] = new String[overlapNo][4];
		for (int i = 0; i<overlapNo; i++) {
			//내용 추가
		}
		*/
		String contents[][] = {
				{"No1", "Demo Place1", "Demo Day1", "Demo Time1"}, //"번호1", "데모위치1", "데모날짜1", "데모시간1"
				{"No2", "Demo Place2", "Demo Day2", "Demo Time2"},
				{"No3", "Demo Place3", "Demo Day3", "Demo Time3"}
		};
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 300));
		add(jscrollPane, BorderLayout.CENTER);
	}
}
