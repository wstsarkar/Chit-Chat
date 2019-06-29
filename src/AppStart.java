import java.awt.EventQueue;

import view.LoginFrame;
import view.TabPanFrame;

/**
 * 
 * @author Williyam
 * 
 */
public class AppStart {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
