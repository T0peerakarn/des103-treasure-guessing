
package Style;

import javax.swing.*;
import java.awt.*;

public class CenterTitle extends JLabel
{
    public CenterTitle(String txt, int fontSize)
    {
        super(txt, SwingConstants.CENTER);
        this.setFont(new Font("Comic Sans MS", Font.PLAIN, fontSize));
        this.setBounds(100, 100, 600, 100);
    }
}
