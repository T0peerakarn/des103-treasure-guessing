
package Component;

import Style.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JFrame
{
    int levelState = 0;
    float time = 5;

    int[] numberOfFlipAllowed = {64, 16, 8, 1};
    
    JPanel[] screens = {new MainScreen(),
                        new SettingScreen(),
                        new HowToPlaySceen()
    };

    private class MainScreen extends TemplatePanel implements ActionListener
    {
        CenterTitle TitleLabel = new CenterTitle("Treasure Guessing", 60);

        PinkHoverableButton PlayButton = new PinkHoverableButton("Play");
        PinkHoverableButton HowToPlayButton = new PinkHoverableButton("How to play");

        MainScreen()
        {
            this.remove(ReturnToMainButton);
            this.add(TitleLabel);
            this.add(PlayButton);
            this.add(HowToPlayButton);

            PlayButton.setBounds(275, 250, 250, 50);
            PlayButton.addActionListener(this);

            HowToPlayButton.setBounds(275, 350, 250, 50);
            HowToPlayButton.addActionListener(this);

        }

        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == PlayButton)
            {
                changeScreen(1);
                PlayButton.reset();
            }
            else if(e.getSource() == HowToPlayButton)
            {
                changeScreen(2);
                HowToPlayButton.reset();
            }
        }
    }

    private class SettingScreen extends TemplatePanel implements ActionListener
    {
        String[] levels = {"Easy", "Normal", "Hard", "Impossible"};
        Color[] levelTextColors = {new Color(46, 139, 87), new Color(255, 140, 0), Color.RED, new Color(128, 0, 128)};
        
        CenterTitle TitleLabel = new CenterTitle("Please Select Mode and Discussion Time", 30);

        JLabel LevelLabel = new JLabel(levels[levelState] + ": " + numberOfFlipAllowed[levelState] + " move(s)", SwingConstants.CENTER);
        JLabel TimeLabel = new JLabel(((int)time) + " minute(s)", SwingConstants.CENTER);

        PinkHoverableButton LeftButton = new PinkHoverableButton("<");
        PinkHoverableButton RightButton =  new PinkHoverableButton(">");
        PinkHoverableButton IncreaseButton = new PinkHoverableButton("+");
        PinkHoverableButton DecreaseButton = new PinkHoverableButton("-");
        PinkHoverableButton StartButton = new PinkHoverableButton("Start");

        SettingScreen()
        {
            this.add(TitleLabel);
            this.add(LevelLabel);
            this.add(LeftButton);
            this.add(RightButton);
            this.add(TimeLabel);
            this.add(DecreaseButton);
            this.add(IncreaseButton);
            this.add(StartButton);

            LevelLabel.setBounds(250, 250, 300, 50);
            LevelLabel.setForeground(new Color(46, 139, 87));
            LevelLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

            LeftButton.setBounds(200, 250, 50, 50);
            LeftButton.addActionListener(this);

            RightButton.setBounds(550, 250, 50, 50);
            RightButton.addActionListener(this);

            TimeLabel.setBounds(250, 325, 300, 50);
            TimeLabel.setForeground(Color.BLACK);
            TimeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

            DecreaseButton.setBounds(200, 325, 50, 50);
            DecreaseButton.locked = true;
            DecreaseButton.addActionListener(this);

            IncreaseButton.setBounds(550, 325, 50, 50);
            IncreaseButton.addActionListener(this);

            StartButton.setBounds(300, 500, 200, 50);
            StartButton.addActionListener(this);

            this.ReturnToMainButton.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == LeftButton)
            {
                setLevelState(levelState, 3);
            }
            else if(e.getSource() == RightButton)
            {
                setLevelState(levelState, 1);
            }
            else if(e.getSource() == ReturnToMainButton)
            {
                setLevelState(0, 0);
                setTime(0);
                changeScreen(0);
                ReturnToMainButton.reset();
            }
            else if(e.getSource() == DecreaseButton)
            {
                if(time != 1)
                {
                    setTime(-1);

                    if(time == 1)
                    {
                        DecreaseButton.locked = true;
                        DecreaseButton.setBackground(new Color(255, 219, 233));
                        DecreaseButton.setForeground(Color.LIGHT_GRAY);
                    }

                    if(time == 9)
                    {
                        IncreaseButton.locked = false;
                        IncreaseButton.reset();
                    }
                }
            }
            else if(e.getSource() == IncreaseButton)
            {
                if(time != 10)
                {
                    setTime(1);

                    if(time == 2)
                    {
                        DecreaseButton.locked = false;
                        DecreaseButton.reset();
                    }

                    if(time == 10)
                    {
                        IncreaseButton.locked = true;
                        IncreaseButton.setBackground(new Color(255, 219, 233));
                        IncreaseButton.setForeground(Color.LIGHT_GRAY);
                    }
                }
            }
            else if(e.getSource() == StartButton)
            {
                changeScreen(4);
            }
        }
        
        private void setLevelState(int a, int b)
        {
            levelState = (a+b)%4;
            LevelLabel.setText(levels[levelState] + ": " + numberOfFlipAllowed[levelState] + " move(s)");
            LevelLabel.setForeground(levelTextColors[levelState]);
        }

        private void setTime(int a)
        {
            time = (a == 0) ? 5 : time+a;
            TimeLabel.setText(((int)time) + " minute(s)");
        }
    }

    private class HowToPlaySceen extends TemplatePanel implements ActionListener
    {
        JLabel txt1 = new JLabel("This is a two-player strategy game. There are 64 coins on a board, each coin was");
        JLabel txt2 = new JLabel("labeled with a number 0, 1, 2, …, 63. There is a treasure buried in one of the cells of the");
        JLabel txt3 = new JLabel("board. You and your friend need to cooperate to find the treasure.");
        JLabel txt4 = new JLabel("First, determine which one is a player A and player B. Player A will know the location");
        JLabel txt5 = new JLabel("of the treasure, but he cannot directly tell B, he can only flip the coins with limited moves,");
        JLabel txt6 = new JLabel("then send the modified board to B.");
        JLabel txt7 = new JLabel("Second, B has to investigate the board, then B has to answer the location of the");
        JLabel txt8 = new JLabel("treasure.");
        JLabel txt9 = new JLabel("A and B cannot communicate with each other during the game. However, there is a");
        JLabel txt10 = new JLabel("few minutes to let A and B create the strategies before the game start.");

        HowToPlaySceen()
        {
            this.ReturnToMainButton.addActionListener(this);

            this.add(txt1);
            txt1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            txt1.setBounds(80, 100, 650, 50);

            this.add(txt2);
            txt2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            txt2.setBounds(50, 120, 700, 50);

            this.add(txt3);
            txt3.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            txt3.setBounds(50, 140, 700, 50);

            this.add(txt4);
            txt4.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            txt4.setBounds(80, 170, 650, 50);

            this.add(txt5);
            txt5.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            txt5.setBounds(50, 190, 700, 50);

            this.add(txt6);
            txt6.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            txt6.setBounds(50, 210, 700, 50);

            this.add(txt7);
            txt7.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            txt7.setBounds(80, 240, 650, 50);

            this.add(txt8);
            txt8.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            txt8.setBounds(50, 260, 700, 50);

            this.add(txt9);
            txt9.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            txt9.setBounds(80, 290, 650, 50);

            this.add(txt10);
            txt10.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            txt10.setBounds(50, 310, 700, 50);
        }

        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == ReturnToMainButton)
            {
                changeScreen(0);
            }
        }
    }

    private class DiscussionScreen extends TemplatePanel implements ActionListener
    {
        CenterTitle TitleLabel = new CenterTitle("Discussion Time", 30);

        Timer DiscussionTimer = new Timer(0, this);

        PinkHoverableButton SkipButton = new PinkHoverableButton("Skip");

        DiscussionScreen()
        {
            this.remove(ReturnToMainButton);
            this.add(TitleLabel);

            PieChartTimer CountdownTimer = new PieChartTimer(time);
            CountdownTimer.setBounds(250, 200, 300, 300);
            this.add(CountdownTimer);

            this.add(SkipButton);
            SkipButton.addActionListener(this);
            SkipButton.setBounds(720, 5, 60, 50);

            DiscussionTimer = new Timer((int) time*60*1000, this);
            DiscussionTimer.start();
        }

        public void actionPerformed(ActionEvent e)
        {
            DiscussionTimer.stop();
            changeScreen(5);
        }
    }

    private class Gameplay extends TemplatePanel implements MouseListener, ActionListener
    {
        int availableFlip;
        int treasureLocation;
        int selectedCoin;
        int[][] initialCoinState = new int[8][8];
        int[][] CoinState = new int[8][8];
        int[][][] CoinCenter = new int[8][8][2];

        boolean showLocation = false;
        boolean answerPhase = false;

        Color[] NumberColor = {Color.BLACK, Color.WHITE};
        Color[] CoinColor = {Color.LIGHT_GRAY, Color.ORANGE};

        JLabel NumberOfFlipLabel = new JLabel();
        JLabel TreasureLocationLabel = new JLabel("The treasure is on **");
        JLabel AnswerAskingLabel1 = new JLabel("Please click on the coin");
        JLabel AnswerAskingLabel2 = new JLabel("that contain the treasure");

        PinkHoverableButton ShowHideButton = new PinkHoverableButton("Click to show the number");
        PinkHoverableButton ResetButton = new PinkHoverableButton("Reset");
        PinkHoverableButton FinishButton = new PinkHoverableButton("Finish");
        PinkHoverableButton SubmitButton = new PinkHoverableButton("Submit");

        Gameplay()
        {
            this.remove(ReturnToMainButton);
            this.addMouseListener(this);

            for(int i=0 ; i<8 ; i++) for(int j=0 ; j<8 ; j++)
            {
                CoinCenter[i][j][0] = 118 + 40*i;
                CoinCenter[i][j][1] = 68 + 40*j;
            }

            initialGame();

            this.add(NumberOfFlipLabel);
            NumberOfFlipLabel.setText("You have " + availableFlip + " move(s) left");
            NumberOfFlipLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            NumberOfFlipLabel.setBounds(450, 110, 300, 20);
            
            this.add(TreasureLocationLabel);
            TreasureLocationLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            TreasureLocationLabel.setBounds(470, 150, 300, 20);

            this.add(ShowHideButton);
            ShowHideButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
            ShowHideButton.setBounds(430, 190, 280, 50);
            ShowHideButton.addActionListener(this);

            this.add(ResetButton);
            ResetButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
            ResetButton.setBounds(430, 260, 280, 50);
            ResetButton.addActionListener(this);

            this.add(FinishButton);
            FinishButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
            FinishButton.setBounds(430, 330, 280, 50);
            FinishButton.addActionListener(this);
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            for(int i=0 ; i<8 ; i++) for(int j=0 ; j<8 ; j++)
            {
                g.setColor((selectedCoin == 8*i+j ? Color.RED : Color.BLACK));
                g.fillOval(CoinCenter[i][j][1] - 18, CoinCenter[i][j][0] - 18, 36, 36);
                
                g.setColor(CoinColor[CoinState[i][j]]);
                g.fillOval(CoinCenter[i][j][1] - 15, CoinCenter[i][j][0] - 15, 30, 30);

                g.setColor(NumberColor[CoinState[i][j]]);
                g.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
                g.drawString(String.valueOf(8*i+j), CoinCenter[i][j][1] + ((8*i+j < 10) ? -4 : -10), CoinCenter[i][j][0] + 5);
            }
        }

        boolean isCoin(int x, int y, int i, int j)
        {
            int dx = x - CoinCenter[i][j][1], dy = y - CoinCenter[i][j][0];
            return Math.sqrt(dx*dx + dy*dy) <= 18;
        }

        public void mouseClicked(MouseEvent e)
        {
            int x = e.getX(), y = e.getY();

            if(answerPhase)
            {                
                for(int i=0 ; i<8 ; i++) for(int j=0 ; j<8 ; j++) if(isCoin(x, y, i, j))
                {
                    if(selectedCoin == -1)
                    {
                        SubmitButton.locked = false;
                        SubmitButton.setBackground(new Color(255,192,203));
                        SubmitButton.setForeground(Color.BLACK);
                    }
                    selectedCoin = 8*i+j;
                    repaint();
                }
            }
            else
            {
                for(int i=0 ; i<8 ; i++) for(int j=0 ; j<8 ; j++) if(isCoin(x, y, i, j) && availableFlip > 0)
                {
                    availableFlip -= 1;
                    NumberOfFlipLabel.setText("You have " + availableFlip + " move(s) left.");
                    CoinState[i][j] = 1 - CoinState[i][j];
                    repaint();
                    return ;
                }
            }
        }  

        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == ShowHideButton)
            {
                showLocation ^= true;
                
                if(showLocation)
                {
                    TreasureLocationLabel.setText("The treasure is on " + treasureLocation);
                    ShowHideButton.setText("Click to hide the number");
                }
                else
                {
                    TreasureLocationLabel.setText("The treasure is on **");
                    ShowHideButton.setText("Click to show the number");
                }
            }
            else if(e.getSource() == ResetButton)
            {
                initialGame();
            }
            else if(e.getSource() == FinishButton)
            {
                this.remove(NumberOfFlipLabel);
                this.remove(TreasureLocationLabel);
                this.remove(ShowHideButton);
                this.remove(ResetButton);
                this.remove(FinishButton);

                answerPhase = true;

                this.add(AnswerAskingLabel1);
                AnswerAskingLabel1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
                AnswerAskingLabel1.setBounds(450, 200, 300, 20);

                this.add(AnswerAskingLabel2);
                AnswerAskingLabel2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
                AnswerAskingLabel2.setBounds(440, 240, 300, 20);

                this.add(SubmitButton);
                SubmitButton.addActionListener(this);
                SubmitButton.setBounds(430, 300, 280, 50);
                SubmitButton.locked = true;
                SubmitButton.setBackground(new Color(255, 219, 233));
                SubmitButton.setForeground(Color.LIGHT_GRAY);

                repaint();
            }

            if(e.getSource() == SubmitButton)
            {
                dispose();
                new ResultScreen(selectedCoin == treasureLocation);
            }
        }

        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}  
        public void mousePressed(MouseEvent e) {}  
        public void mouseReleased(MouseEvent e) {} 

        void initialGame()
        {
            selectedCoin = -1;

            availableFlip = numberOfFlipAllowed[levelState];
            NumberOfFlipLabel.setText("You have " + availableFlip + " move(s) left");

            treasureLocation = (int) (Math.random() * 64);
            TreasureLocationLabel.setText("The treasure is on **");

            showLocation = false;
            ShowHideButton.setText("Click to show the number");

            for(int i=0 ; i<8 ; i++) for(int j=0 ; j<8 ; j++) initialCoinState[i][j] = CoinState[i][j] = (int) (Math.random() * 2);
            
            repaint();
        }
    }

    private class ResultScreen extends JFrame implements ActionListener
    {
        TemplatePanel MainPanel = new TemplatePanel();

        JLabel ResultLabel = new JLabel();

        PinkHoverableButton RestartButton = new PinkHoverableButton("Restart");

        ResultScreen(boolean correct)
        {
            super("Treasure Guessing");
            this.setVisible(true);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setSize(400, 300);
            this.setResizable(false);
            this.add(MainPanel);

            MainPanel.remove(MainPanel.ReturnToMainButton);

            MainPanel.add(ResultLabel);
            ResultLabel.setText((correct ? "You win!" : "You lose!"));
            ResultLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
            ResultLabel.setBounds(120, 80, 300, 50);

            MainPanel.add(RestartButton);
            RestartButton.setBounds(150, 150, 100, 40);
            RestartButton.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            dispose();
            new Game();
        }
    }

    public Game()
    {
        super("Treasure Guessing");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        
        changeScreen(0);
    }

    public void changeScreen(int screenState)
    {
        if(screenState == 4)
        {
            this.setContentPane(new DiscussionScreen());
        }
        else if(screenState == 5)
        {
            this.setContentPane(new Gameplay());
        }
        else
        {
            this.setContentPane(screens[screenState]);
        }
        this.revalidate();
    }
}
