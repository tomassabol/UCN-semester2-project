package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class JLink extends JButton {
	
	public static final Color INDIGO = new Color(101, 88, 245);
	public static final Color GREEN = new Color(32, 120, 104);
	public static final Color RED = new Color(211, 69, 91);
    public static final Color PINK = new Color(183,26,134,255);
	public static final Color PURPLE = new Color(53,63,154,255);


	private static final long serialVersionUID = -6117412042952963334L;
	
	public enum COLORS {
		INDIGO,
		GREEN,
		RED,
        PINK,
        PURPLE
	}
	
	String text;
	COLORS color;

	public JLink(String text, COLORS color) {
		super(text);
		this.text = text;
		this.color = color;
		
        setFocusPainted(false);
        setMargin(new Insets(0, 0, 0, 0));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        
		switch(color) {
			case INDIGO: this.setForeground(INDIGO);break;
			case GREEN: this.setForeground(GREEN);break;
			case RED: this.setForeground(RED);break;
            case PINK: this.setForeground(PINK);break;
			case PURPLE: this.setForeground(PURPLE);break;
			default: this.setForeground(INDIGO);break;
		}
		
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		this.addMouseListener(new MouseAdapter() {
			
			@Override
		    public void mouseEntered(MouseEvent e) {
				if (JLink.this.isEnabled()) {
			        // the mouse is on the label: underline it
					JLink.super.setText("<HTML><U>" + JLink.this.text +"</U></HTML>");
				}

		    }
		 
			@Override
		    public void mouseExited(MouseEvent e) {
				if (JLink.this.isEnabled()) {
			        // the mouse has exited the label: set back to original
			    	JLink.super.setText(JLink.this.text);
				}
		    }
		});
	}
	
	public JLink(String text) {
		this(text, COLORS.INDIGO);
	}
	
	@Override
	public void setText(String text) {
		super.setText(text);
		this.text = text;
	}

}
