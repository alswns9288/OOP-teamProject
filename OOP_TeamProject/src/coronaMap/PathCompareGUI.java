package coronaMap;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

public class PathCompareGUI extends JFrame{
	Container container = null;
	JFrame jframe = new JFrame();
	JPanel background = null;
	int matchNum = 0;
	String myName;
	String myPlace;
	//String myDay;
	String myTime;
    
	public PathCompareGUI(String myName, String myPlace, /*String myDay, */ String myTime) {
		this.myName = myName;
		this.myPlace = myPlace;
		//this.myDay = myDay;
		this.myTime = myTime;
	}
	
	public void createAndShowGUI() {
		setTitle("Compare with Positive");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(500, 50);
		setPreferredSize(new Dimension(400, 730));
		setResizable(false);
		
		setMainGUI();
		pack();
		setVisible(true);
	}
	
	private void setMainGUI() {
		container = getContentPane();
		setTop();
		setTable();
		setResult();
	}
	
	private void setResult() {
		String result = matchNum+"contacts expectation, "; // "�� "+matchNum+"�� ���� ����, �ڷγ� �˻�"
		if (matchNum==0) {
			result += "No Exam Required, Keep Social Distancing"; //"���ʿ�, �ڰ��ݸ� �� ����� ���Ѻ�����"
		}
		else {
			result += "Exam Required, Visit Selective Care Center"; //"�ʿ�, ����� ��������Ҹ� �湮�ϼ���!!"
		}
		JLabel resultLabel = new JLabel(result);
		resultLabel.setPreferredSize(new Dimension(400, 40));
		container.add(resultLabel, BorderLayout.PAGE_END);
	}

	private void setTop() {
		String header[] = {"Me", "Place", /*"Day",*/ "Time"}; // ���� ��¥�� ������ ���Ͽ� ����., "��", "��ġ", /*"��¥",*/ "�ð�"
		String contents[][] = {
				{myName, myPlace, /*myDay, */ myTime}
		};
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 40));
		container.add(jscrollPane, BorderLayout.PAGE_START);
	}
	
	private void setTable() {
		String header[] = {"No. of posttives", "Place", /*"Day",*/ "Time"}; // ���� ��¥�� ������ ���Ͽ� ����., "Ȯ���� ��ȣ", "��ġ", /*"��¥",*/ "�ð�"
		//�˻������ ���� content ������ ����
		String contents[][] = {
				{"No1", "Demo Place1", "Demo Time1"}, //"��ȣ1", "������ġ1", "����ð�1"
				{"No2", "Demo Place2", "Demo Time2"},
				{"No3", "Demo Place3", "Demo Time3"}
		};
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 500));
		container.add(jscrollPane, BorderLayout.CENTER);
	}
	
	
	
	public void setBackImage() {
		ImageIcon image = new ImageIcon("background.png");
		background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(image.getImage(), 0, 0, null);
				super.paintComponents(g);
			}
		};
		background.setLayout(null);
		container.add(background, BorderLayout.CENTER);
	}
}
