package renderer;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import been.User;

/**
 * 
 * @author Williyam
 * 
 */
public class FriendListRenderer extends JLabel implements ListCellRenderer<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	public FriendListRenderer() {
        setOpaque(true);
	}


	@Override
	public Component getListCellRendererComponent(JList<? extends User> list, User user, int index, boolean isSelected,boolean cellHasFocus) {

		String imageName = user.getIs_logged_in() == 1 ? "GreenCircle"  :  "RedCircle";
		
		setText(user.getName());

        ImageIcon imageIcon = new ImageIcon(FriendListRenderer.class.getResource("/resources/"+imageName+".png")); 
        
        setIcon(imageIcon); 
        
        if (cellHasFocus || isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

		return this;
	}

}
