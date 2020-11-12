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
			String[] inputDay, String[] inputTime) { // �ܺηκ���  ���, ��¥, �ð��� �迭 ���·� ���޹���
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
		String result = "Danger Rate : "+dangerRate; // "���赵 "+dangerRate+" �ڷγ� �˻�"
		switch (dangerRate) {
		case 1:
			result += " No Exam Required, No Match with Positive"; //"���ʿ�, Ȯ������ ��ο� ��ġ�ϴ� �κ��� ����"
			break;
		case 2:
			result += " No Exam Required, Visit On Another Day"; //"���ʿ�, Ȯ������ ��ο� ��Ұ� ��ġ������ �ٸ� ���� �湮"
			break;
		case 3:
			result += " Exam Required, Match Place and Day"; //"�ʿ�, Ȯ������ ��ο� ��¥, ��Ұ� ��ġ!!"
			break;
		case 4:
			result += " Exam Required, Match Multiple Place and Day"; //"�ʿ�, Ȯ������ ��ο� �������� ��¥�� ��Ұ� ��ġ!!"
			break;
		default:
			result += " Exam Required, Exactly Macth"; //"�ʿ�, Ȯ������ ��ο� ��¥�� �ð�, ��Ұ� ��ġ!!"
			break;
		}
		JLabel resultLabel = new JLabel(result);
		resultLabel.setPreferredSize(new Dimension(400, 40));
		add(resultLabel, BorderLayout.PAGE_END);
	}

	private void setMyPath() {
		String header[] = {"Me", "Place", "Day","Time"}; // "��", "��ġ", "��¥", "�ð�"
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
		String header[] = {"No. of postives", "Place", "Day", "Time"}; // "Ȯ���� ��ȣ", "��ġ", "��¥", "�ð�"
																
		// �˻������ ���� content ������ ����
		/*
		PlaceManagement manage = new PlaceManagement();
		int overlapNo = 0; //��ġ�� ����� �� 
		String contents[][] = new String[overlapNo][4];
		for (int i = 0; i<overlapNo; i++) {
			//���� �߰�
		}
		*/
		String contents[][] = {
				{"No1", "Demo Place1", "Demo Day1", "Demo Time1"}, //"��ȣ1", "������ġ1", "����¥1", "����ð�1"
				{"No2", "Demo Place2", "Demo Day2", "Demo Time2"},
				{"No3", "Demo Place3", "Demo Day3", "Demo Time3"}
		};
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 300));
		add(jscrollPane, BorderLayout.CENTER);
	}
}
