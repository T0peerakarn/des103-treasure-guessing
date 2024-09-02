package Style;

import javax.swing.*;

import Component.ReturnButton;

import java.awt.*;

public class TemplatePanel extends JPanel
{
    public ReturnButton ReturnToMainButton = new ReturnButton();

    public TemplatePanel()
    {
        this.setLayout(null);
        this.setBackground(new Color(240,248,255));

        this.add(ReturnToMainButton);
    }
}
