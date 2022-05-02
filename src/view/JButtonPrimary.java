package view;

import javax.swing.*;
import java.awt.*;

public class JButtonPrimary extends JButton {
	
	public static final Color INDIGO_COLOR = new Color(101, 88, 245);

    private static final long serialVersionUID = 90062794964789095L;

    public JButtonPrimary(String text) {
        super(text);
        super.setBackground(INDIGO_COLOR);
        super.setForeground(Color.WHITE);
        super.setMargin(new Insets(5, 15, 5, 15));
    }
}