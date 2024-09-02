
package Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinkHoverableButton extends JButton implements MouseListener
{
    public boolean locked = false;

    Color HoverButtonColor = new Color(255,105,180);
    Color HoverTextColor = Color.WHITE;
    Color UnhoverButtonColor = new Color(255,192,203);
    Color UnhoverTextColor = Color.BLACK;

    public PinkHoverableButton(String title)
    {
        super(title);
        this.setBackground(UnhoverButtonColor);
        this.setForeground(UnhoverTextColor);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        this.addMouseListener(this);
    }

    public void reset()
    {
        this.setBackground(UnhoverButtonColor);
        this.setForeground(UnhoverTextColor);
    }

    public void mouseEntered(MouseEvent e)
    {
        if(locked) return ;

        if(e.getSource() instanceof JButton)
        {
            ((JComponent)e.getSource()).setBackground(HoverButtonColor);
            ((JComponent)e.getSource()).setForeground(HoverTextColor);
        }
    }

    public void mouseExited(MouseEvent e)
    {
        if(locked) return ;
        
        if(e.getSource() instanceof JButton)
        {
            ((JComponent)e.getSource()).setBackground(UnhoverButtonColor);
            ((JComponent)e.getSource()).setForeground(UnhoverTextColor);
        }
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
