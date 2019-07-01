package renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import been.MSG;
import been.User;

/**
 * 
 * @author Williyam
 * 
 */
public class MsgListRenderer extends JLabel implements ListCellRenderer<MSG> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User me;
	
	public MsgListRenderer(User me) {
		this.me = me;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends MSG> arg0, MSG msg, int index, boolean isSelected,boolean cellHasFocus) {

		setText(msg.getMessage());
		setHorizontalAlignment(msg.getReceiver_user_id() == me.getUser_id() ? SwingConstants.LEFT : SwingConstants.RIGHT);		
		return this;
	}

}
