package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

public class PathCompareGUI extends JPanel {
	int matchNum;
	String myName;
	String myPlace;
	// String myDay;
	String myTime;

//	public PathCompareGUI(String myName, String myPlace, /*String myDay, */ String myTime) {
//		this.myName = myName;
//		this.myPlace = myPlace;
//		//this.myDay = myDay;
//		this.myTime = myTime;
//	}

	public PathCompareGUI() {
		setLayout(new BorderLayout());
		setTop();
		setTable();
		setResult();
	}

	private void setResult() {
		String result = matchNum + "contacts expectation, "; // "총 "+matchNum+"명 접촉 예상, 코로나 검사"
		if (matchNum == 0) {
			result += "No Exam Required, Keep Social Distancing"; // "불필요, 자가격리 후 경과를 지켜보세요"
		} else {
			result += "Exam Required, Visit Selective Care Center"; // "필요, 가까운 선별진료소를 방문하세요!!"
		}
		JLabel resultLabel = new JLabel(result);
		resultLabel.setPreferredSize(new Dimension(400, 40));
		add(resultLabel, BorderLayout.PAGE_END);
	}

	private void setTop() {
		String header[] = { "Me", "Place", /* "Day", */ "Time" }; // 아직 날짜는 데이터 파일에 없다., "나", "위치", /*"날짜",*/ "시간"
		String contents[][] = { { myName, myPlace, /* myDay, */ myTime } };
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 40));
		add(jscrollPane, BorderLayout.PAGE_START);
	}

	private void setTable() {
		String header[] = { "No. of posttives", "Place", /* "Day", */ "Time" }; // 아직 날짜는 데이터 파일에 없다., "확진자 번호", "위치",
																				// /*"날짜",*/ "시간"
		// 검색기능을 통해 content 데이터 구축
		String contents[][] = { { "No1", "Demo Place1", "Demo Time1" }, // "번호1", "데모위치1", "데모시간1"
				{ "No2", "Demo Place2", "Demo Time2" }, { "No3", "Demo Place3", "Demo Time3" } };
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 600));
		add(jscrollPane, BorderLayout.CENTER);
	}
}
