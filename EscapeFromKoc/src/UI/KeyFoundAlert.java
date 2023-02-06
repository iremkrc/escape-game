package UI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class KeyFoundAlert {
	public boolean alert(int index) {
		if(index == 5) {
		    return true;
		}else {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int result = JOptionPane.showConfirmDialog(null, "Key is found! You can continue with the next building.\n If No, you can surrender and continue suffering in Koc!",null, dialogButton);
		    if (result == JOptionPane.YES_OPTION) {
		    	return true;
		    }else {
		    	return false;
		    }  
		}
		
	}
}
