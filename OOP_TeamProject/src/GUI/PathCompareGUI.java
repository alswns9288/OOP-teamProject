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
		String result = matchNum + "contacts expectation, "; // "�� "+matchNum+"�� ���� ����, �ڷγ� �˻�"
		if (matchNum == 0) {
			result += "No Exam Required, Keep Social Distancing"; // "���ʿ�, �ڰ��ݸ� �� ����� ���Ѻ�����"
		} else {
			result += "Exam Required, Visit Selective Care Center"; // "�ʿ�, ����� ��������Ҹ� �湮�ϼ���!!"
		}
		JLabel resultLabel = new JLabel(result);
		resultLabel.setPreferredSize(new Dimension(400, 40));
		add(resultLabel, BorderLayout.PAGE_END);
	}

	private void setTop() {
		String header[] = { "Me", "Place", /* "Day", */ "Time" }; // ���� ��¥�� ������ ���Ͽ� ����., "��", "��ġ", /*"��¥",*/ "�ð�"
		String contents[][] = { { myName, myPlace, /* myDay, */ myTime } };
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 40));
		add(jscrollPane, BorderLayout.PAGE_START);
	}

	private void setTable() {
		String header[] = { "No. of posttives", "Place", /* "Day", */ "Time" }; // ���� ��¥�� ������ ���Ͽ� ����., "Ȯ���� ��ȣ", "��ġ",
																				// /*"��¥",*/ "�ð�"
		// �˻������ ���� content ������ ����
		String contents[][] = { { "No1", "Demo Place1", "Demo Time1" }, // "��ȣ1", "������ġ1", "����ð�1"
				{ "No2", "Demo Place2", "Demo Time2" }, { "No3", "Demo Place3", "Demo Time3" } };
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 600));
		add(jscrollPane, BorderLayout.CENTER);
	}
}
