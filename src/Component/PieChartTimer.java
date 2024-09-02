
package Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PieChartTimer extends JPanel implements ActionListener
{
    float time_full;
    float time_now;
    float arcScale = 360;
    float greenScale = 255;
    float tickSpeed = 250;
    Timer PieChartTimer = new Timer((int) tickSpeed, this);

    public PieChartTimer(float minute)
    {
        this.time_full = minute * 60 * 1000;
        this.time_now = time_full;

        PieChartTimer.start();
    }

    public void paintComponent(Graphics g)
    {  
        g.setColor(new Color(240,248,255));        
        g.fillRect(0, 0, 300, 300);

        g.setColor(new Color((int) (255 - greenScale), (int) greenScale, 0));
        g.fillArc(50, 50, 200, 200, 90, (int) -arcScale);
    }

    public void actionPerformed(ActionEvent e)
    {
        time_now -= tickSpeed;
        arcScale = (float) (360.0 * (time_now / time_full));
        greenScale = (float) (255.0 * (time_now / time_full));
        repaint();

        if(time_now == 0) PieChartTimer.stop();
    }
}
