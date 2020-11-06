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
		String result = "총 "+matchNum+"명 접촉 예상, 코로나 검사";
		if (matchNum==0) {
			result += "불필요, 자가격리 후 경과를 지켜보세요";
		}
		else {
			result += "필요, 가까운 선별진료소를 방문하세요!!";
		}
		JLabel resultLabel = new JLabel(result);
		resultLabel.setPreferredSize(new Dimension(400, 40));
		container.add(resultLabel, BorderLayout.PAGE_END);
	}

	private void setTop() {
		String header[] = {"나", "위치", /*"날짜",*/ "시간"}; // 아직 날짜는 데이터 파일에 없다.
		String contents[][] = {
				{"나", "데모 내 위치", "데모 내 시간"}
		};
		JTable myTable = new JTable(contents, header);
		JScrollPane jscrollPane = new JScrollPane(myTable);
		jscrollPane.setPreferredSize(new Dimension(400, 40));
		container.add(jscrollPane, BorderLayout.PAGE_START);
	}
	
	private void setTable() {
		String header[] = {"확진자 번호", "위치", /*"날짜",*/ "시간"}; // 아직 날짜는 데이터 파일에 없다.
		
		String contents[][] = {
				{"번호1", "데모위치1", "데모시간1"},
				{"번호2", "데모위치2", "데모시간2"},
				{"번호3", "데모위치3", "데모시간3"}
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
	
	public static void main(String[] args) {
		PathCompareGUI g = new PathCompareGUI();
		g.createAndShowGUI();
	}
}
