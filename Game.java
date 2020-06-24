import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;;

    
public class Game extends JPanel implements ActionListener, MouseListener
{
    JFrame frame;
    JMenuBar menuBar;
    JMenu game, icons, instructions, empty;
    JMenuItem beginner, intermediate, expert, classic, pokemon, dbz, stuff;
    JLabel showFlags, showTime;
    JButton reset;
    JPanel gridPanel;
    JToggleButton[][] gridArray;
    int gridH, gridW, numMines, seconds, numFlags, timePassed;
    Font mineFont;
    boolean firstClick, i1, i2, i3, gameEnd;
    ImageIcon mineIcon, flagIcon, voltorb, pikachu, buu, goku, smiley, frown, dragonball, pokeball;
    Timer timer;

    public Game()
    {
        try{
            mineFont = Font.createFont(Font.TRUETYPE_FONT, new File("mineFont.ttf")).deriveFont(24f);
        }catch(Exception e)
        {
        }

        frame = new JFrame("Minesweeper");
        frame.add(this);

        gridH = 9;
        gridW = 9;
        numMines = 10;

        firstClick = true;
        i1 = true;
        i2 = false;
        i3 = false;
        
        gameEnd = false;

        timer = new Timer();
        timePassed = 0;
        showTime = new JLabel("" + timePassed);
        timer.schedule(new TimerTask(){
        
            @Override
            public void run() {
                if(!gameEnd && !firstClick){
                    timePassed++;
                    showTime.setText("" + timePassed);
                }
            }
        }, 0, 1000);

        menuBar = new JMenuBar();

        game = new JMenu("Game");
        icons = new JMenu("Icons");
        instructions = new JMenu("Info");
        empty = new JMenu("  |  ");
        empty.setEnabled(false);

        numFlags = 0;
        showFlags = new JLabel("" + numMines);

        beginner = new JMenuItem("Beginner");
        beginner.addActionListener(this);
        intermediate = new JMenuItem("Intermediate");
        intermediate.addActionListener(this);
        expert = new JMenuItem("Expert");
        expert.addActionListener(this);

        classic = new JMenuItem("Classic");
        classic.addActionListener(this);
        pokemon = new JMenuItem("Pokemon");
        pokemon.addActionListener(this);
        dbz = new JMenuItem("DBZ");
        dbz.addActionListener(this);

        stuff = new JMenuItem("Changing the icons will change the appearance of the mine, flag, and smiley icons to its respective category. The zeroes indicate an empty space where there are no mines around.");

        game.add(beginner);
        game.add(intermediate);
        game.add(expert);

        icons.add(classic);
        icons.add(pokemon);
        icons.add(dbz);

        instructions.add(stuff);

        smiley = new ImageIcon("smiley.png");
        reset = new JButton();
        reset.setIcon(smiley);
        reset.addActionListener(this);

        menuBar.add(game);
        menuBar.add(icons);
        menuBar.add(instructions);
        menuBar.add(reset);
        menuBar.add(showFlags);
        menuBar.add(empty);
        menuBar.add(showTime);

        makeGrid();

        mineIcon = new ImageIcon("mine.png");
        flagIcon = new ImageIcon("flagged.png");
        voltorb = new ImageIcon("voltorb.png");
        pikachu = new ImageIcon("pikachu.png");
        buu = new ImageIcon("buu.png");
        goku = new ImageIcon("goku.png");
        frown = new ImageIcon("frown.png");
        pokeball = new ImageIcon("pokeball.png");
        dragonball = new ImageIcon("dragonball.png");

        frame.add(menuBar, BorderLayout.NORTH);
        frame.setVisible(true);
        frame.addMouseListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void expand(int row, int col)
    {
        if(!gridArray[row][col].isSelected())
        {
            gridArray[row][col].setSelected(true);
        }

        int state = Integer.parseInt("" + gridArray[row][col].getClientProperty("state"));

        if(state > 0)
        {
            writeText(row, col, state);
        }
        else
        {
            writeText(row, col, state);
            for(int r = row-1; r <= row+1; r++)
            {
                for(int c = col-1; c <= col+1; c++)
                {
                        try
                        {
                            if(!gridArray[r][c].isSelected())
                            {
                                expand(r, c);
                            }
                            
                        }catch(Exception e)
                        {
                        }
                }
            }
        }

    }

    public void writeText(int row, int col, int state)
    {
        switch(state)
        {
            case 0: gridArray[row][col].setForeground(Color.BLACK);
                break;
            case 1: gridArray[row][col].setForeground(Color.BLUE);
                break;
            case 2: gridArray[row][col].setForeground(Color.GREEN);
                break;
            case 3: gridArray[row][col].setForeground(Color.RED);
                break;
            case 4: gridArray[row][col].setForeground(new Color(128, 0, 128));
                break;
            case 5: gridArray[row][col].setForeground(new Color(128, 0, 0));
                break;
            case 6: gridArray[row][col].setForeground(Color.CYAN);
                break;
            case 7: gridArray[row][col].setForeground(Color.BLACK);
                break;
            case 8: gridArray[row][col].setForeground(Color.GRAY);
                break;
            case 9:
                if(i1) 
                    gridArray[row][col].setIcon(mineIcon);
                else if(i2)
                    gridArray[row][col].setIcon(voltorb);
                else if(i3)
                    gridArray[row][col].setIcon(buu);
                break;  
        }
        if(state != 9)
        {
            gridArray[row][col].setText("" + state);
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }

    public void makeGrid()
    {
        if(gridPanel != null)
            frame.remove(gridPanel);
        
        gridArray = new JToggleButton[gridH][gridW];
        gridPanel = new JPanel(new GridLayout(gridH, gridW));

        for(int r = 0; r < gridH; r++)
        {
            for(int c = 0; c < gridW; c++)
            {
                gridArray[r][c] = new JToggleButton();
                gridArray[r][c].putClientProperty("column", c);
                gridArray[r][c].putClientProperty("row", r);
                gridArray[r][c].putClientProperty("state", 0);
                gridArray[r][c].putClientProperty("flagged", false);

                gridArray[r][c].setFocusPainted(false);
                gridArray[r][c].addMouseListener(this);
                gridArray[r][c].setFont(mineFont);
                gridArray[r][c].setBackground(Color.LIGHT_GRAY);
                gridArray[r][c].setBorder(BorderFactory.createBevelBorder(1));

                gridPanel.add(gridArray[r][c]);
            }
        }

        frame.setSize(40 * gridW, 40 * gridH);
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.revalidate();
    }

    public void dropMines(int currentRow, int currentCol)
    {
        int count = numMines;
        while(count > 0)
        {
            int row = (int)(Math.random() * gridH);
            int col = (int)(Math.random() * gridW);
            int state = Integer.parseInt("" + gridArray[row][col].getClientProperty("state"));

            if(state == 0 && (Math.abs(row-currentRow) > 1|| Math.abs(col-currentCol) > 1))
            {
                gridArray[row][col].putClientProperty("state", 9);
                count--;
            }
        }

        for(int r = 0; r < gridH; r++)
        {
            for(int c = 0; c < gridW; c++)
            {
                count = 0;
                int currentState = Integer.parseInt("" + gridArray[r][c].getClientProperty("state"));

                if(currentState != 9)
                {
                    for(int x = r-1; x <= r+1; x++)
                    {
                        for(int y = c-1; y <= c+1; y++)
                        {
                            try
                            {
                                int buttonState = Integer.parseInt("" + gridArray[x][y].getClientProperty("state"));
                                if(buttonState == 9)
                                    count++;

                            }catch (Exception e)
                            {
                            }
                        }
                    }
                    gridArray[r][c].putClientProperty("state", count);
                }
            }
        }

    }

    public void checkWin()
    {
        int totalSpaces = gridH * gridW;
        int count = 0;

        for(int r = 0; r < gridH; r++)
        {
            for(int c = 0; c < gridW; c++)
            {
                int state = Integer.parseInt("" + gridArray[r][c].getClientProperty("state"));
                if(gridArray[r][c].isSelected() && state != 9)
                {
                    count++;
                }
            }
        }

        if(numMines == (totalSpaces- count))
        {
            gameEnd = true;
            JOptionPane.showMessageDialog(null, "You Win!");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }


    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(!gameEnd)
        {
            JToggleButton button = (JToggleButton)e.getComponent();
            int row = Integer.parseInt(""+button.getClientProperty("row"));
            int col = Integer.parseInt(""+button.getClientProperty("column"));
            
            if(e.getButton() == MouseEvent.BUTTON1 && !e.isControlDown())
            {
                if(firstClick)
                {
                    dropMines(row, col);
                    firstClick = false;
                }

                int state = Integer.parseInt(""+button.getClientProperty("state"));

                if(state == 9)
                {
                    gridArray[row][col].setSelected(true);

                    if(i1)
                        reset.setIcon(frown);

                    writeText(row, col, state);
                    gameEnd = true;
                    JOptionPane.showMessageDialog(null, "You Lose :(");
                }
                else
                {
                    expand(row, col);
                    checkWin();
                }

            }
            else if(e.getButton() == MouseEvent.BUTTON3 || e.isControlDown())
            {
                if(!((boolean)gridArray[row][col].getClientProperty("flagged")))
                {

                    if(i1)
                        gridArray[row][col].setIcon(flagIcon);
                    else if(i2)
                        gridArray[row][col].setIcon(pikachu);
                    else if(i3)
                        gridArray[row][col].setIcon(goku);

                        numFlags++;
                        gridArray[row][col].putClientProperty("flagged", true);
                }
                else
                {
                    gridArray[row][col].setIcon(null);
                    gridArray[row][col].putClientProperty("flagged", false);
                    numFlags--;
                }
                showFlags.setText("" + (numMines-numFlags));
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == beginner)
        {
            gridW = 9;
            gridH = 9;
            numMines = 10;
            gameEnd = false;
            firstClick = true;
            timePassed = 0;
            showTime.setText("" + timePassed);
            numFlags = 0;
            showFlags.setText(""+(numMines-numFlags));
            makeGrid();
        }

        else if(e.getSource() == intermediate)
        {
            gridW = 16;
            gridH = 16;
            numMines = 40;
            gameEnd = false;
            firstClick = true;
            timePassed = 0;
            showTime.setText("" + timePassed);
            numFlags = 0;
            showFlags.setText(""+(numMines-numFlags));
            makeGrid();
        }

        else if(e.getSource() == expert)
        {
            gridW = 30;
            gridH = 16;
            numMines = 99;
            gameEnd = false;
            firstClick = true;
            timePassed = 0;
            showTime.setText("" + timePassed);
            numFlags = 0;
            showFlags.setText(""+(numMines-numFlags));
            makeGrid();
        }

        else if(e.getSource() == reset)
        {
            if(i1)
                reset.setIcon(smiley);
            gameEnd = false;
            firstClick = true;
            timePassed = 0;
            showTime.setText("" + timePassed);
            numFlags = 0;
            showFlags.setText(""+(numMines-numFlags));
            makeGrid();
        }

        else if (e.getSource() == classic)
        {
            i1 = true;
            i2 = false;
            i3 = false;
            reset.setIcon(smiley);
        }

        else if (e.getSource() == pokemon)
        {
            i1 = false;
            i2 = true;
            i3 = false;
            reset.setIcon(pokeball);
        }

        else if (e.getSource() == dbz)
        {
            i1 = false;
            i2 = false;
            i3 = true;
            reset.setIcon(dragonball);
        }
        revalidate();
    }

    public static void main(String[] args)
    {
        Game app = new Game();
    }
}