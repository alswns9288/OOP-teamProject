package GUI;

import java.awt.event.*;
import javax.swing.*;


public class MyMouseListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if(e.getSource() == button) {
			if(button.getText().equals("Ȯ���� ���")) {
				PrintpplGUI ppl = new PrintpplGUI();
				ppl.createAndShowGUI();
			}
				
		}
	}
}
