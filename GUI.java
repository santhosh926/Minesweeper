import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.TreeSet;

public class GUI extends JPanel implements ActionListener, MouseListener
{

    JFrame frame;
    JMenuBar menuBar;
    JMenu font, size, color, bg, outline;
    JPanel buttonPanel, bigPanel;
    GridLayout buttonLayout, menuLayout, bigLayout;
    JButton north, south, west, east, reset;
    JMenuItem[] fontArr, sizeArr, colorArr, bgArr, outlineArr;
    JTextArea textArea;
    Color[] borderColor, textColor, outlineColor, bgColor;
    Font currentFont;
    int fontSize;
    int[] allSizes;
    Color currentTextColor, currentBG, currentBorder;
    Font[] allFonts;

    public GUI()
    {
        frame = new JFrame("GUI Program");
        frame.setLayout(new BorderLayout());
        frame.add(this);

        menuBar = new JMenuBar();
        menuLayout = new GridLayout(1, 6);
        menuBar.setLayout(menuLayout);

        font = new JMenu("Font");
        font.addActionListener(this);
        size = new JMenu("Font Size");
        size.addActionListener(this);
        color = new JMenu("Text Color");
        color.addActionListener(this);
        bg = new JMenu("Background");
        bg.addActionListener(this);
        outline = new JMenu("Outline Color");
        outline.addActionListener(this);

        fontArr = new JMenuItem[3];
        fontArr[0] = new JMenuItem("Times New Roman");
        fontArr[1] = new JMenuItem("Arial");
        fontArr[2] = new JMenuItem("Cambria");

        for(JMenuItem j: fontArr)
        {
            j.addActionListener(this);
            font.add(j);
        }

        sizeArr = new JMenuItem[3];
        sizeArr[0] = new JMenuItem("18");
        sizeArr[1] = new JMenuItem("24");
        sizeArr[2] = new JMenuItem("30");

        for(JMenuItem j: sizeArr)
        {
            j.addActionListener(this);
            size.add(j);
        }

        colorArr = new JMenuItem[3];
        colorArr[0] = new JMenuItem("Red");
        colorArr[1] = new JMenuItem("Black");
        colorArr[2] = new JMenuItem("Blue");

        for(JMenuItem j: colorArr)
        {
            j.addActionListener(this);
            color.add(j);
        }

        bgArr = new JMenuItem[3];
        bgArr[0] = new JMenuItem("White");
        bgArr[1] = new JMenuItem("Yellow");
        bgArr[2] = new JMenuItem("Orange");

        for(JMenuItem j: bgArr)
        {
            j.addActionListener(this);
            bg.add(j);
        }

        outlineArr = new JMenuItem[3];
        outlineArr[0] = new JMenuItem("Green");
        outlineArr[1] = new JMenuItem("Purple");
        outlineArr[2] = new JMenuItem("Brown");

        for(JMenuItem j: outlineArr)
        {
            j.addActionListener(this);
            outline.add(j);
        }

        textColor = new Color[3];
        textColor[0] = new Color(255, 0, 0);
        textColor[1] = new Color(0, 0, 0);
        textColor[2] = new Color(0, 0, 255);
        currentTextColor = textColor[0];

        outlineColor = new Color[3];
        outlineColor[0] = new Color(0, 255, 0);
        outlineColor[1] = new Color(128, 0, 128);
        outlineColor[2] = new Color(165, 42, 42);
        currentBorder = outlineColor[0];

        bgColor = new Color[3];
        bgColor[0] = new Color(255, 255, 255);
        bgColor[1] = new Color(255, 255, 0);
        bgColor[2] = new Color(255, 165, 0);
        currentBG = bgColor[0];

        allSizes = new int[]{18, 24, 30};
        fontSize = allSizes[0];

        allFonts = new Font[3];
        allFonts[0] = new Font("Times New Roman", Font.PLAIN, fontSize);
        allFonts[1] = new Font("Arial", Font.PLAIN, fontSize);
        allFonts[2] = new Font("Cambria", Font.PLAIN, fontSize);
        currentFont = allFonts[0];

        reset = new JButton("Reset");
        reset.setBorder(new LineBorder(currentBorder));
        reset.addActionListener(this);

        menuBar.add(font);
        menuBar.add(size);
        menuBar.add(color);
        menuBar.add(bg);
        menuBar.add(outline);
        menuBar.add(reset);

        buttonPanel = new JPanel();
        buttonLayout = new GridLayout(1, 4);
        buttonPanel.setLayout(buttonLayout);

        bigPanel = new JPanel();
        bigLayout = new GridLayout(1, 2);
        bigPanel.setLayout(bigLayout);

        north = new JButton("North");
        north.setBorder(new LineBorder(currentBorder));
        north.addActionListener(this);     

        south = new JButton("South");
        south.setBorder(new LineBorder(currentBorder));
        south.addActionListener(this);

        west = new JButton("West");
        west.setBorder(new LineBorder(currentBorder));
        west.addActionListener(this);

        east = new JButton("East");
        east.setBorder(new LineBorder(currentBorder));
        east.addActionListener(this);

        textArea = new JTextArea();
        textArea.setFont(currentFont);
        textArea.setBackground(currentBG);
        textArea.setForeground(currentTextColor);

        buttonPanel.add(north);
        buttonPanel.add(south);
        buttonPanel.add(east);
        buttonPanel.add(west);

        bigPanel.add(buttonPanel);
        bigPanel.add(menuBar);
        
        frame.add(bigPanel, BorderLayout.NORTH);
        frame.add(textArea, BorderLayout.CENTER);

        frame.setSize(1400, 800);
        frame.setVisible(true);
        frame.addMouseListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

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
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if(e.getSource() == north)
        {
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(1, 4);
            bigLayout = new GridLayout(1, 2);
            bigPanel.setLayout(bigLayout);
            bigPanel.remove(menuBar);
            bigPanel.remove(buttonPanel);
            bigPanel.add(buttonPanel);
            menuLayout = new GridLayout(1, 6);

            menuBar.remove(font);
            menuBar.remove(size);
            menuBar.remove(color);
            menuBar.remove(bg);
            menuBar.remove(outline);
            menuBar.remove(reset);

            menuBar.add(font);
            menuBar.add(size);
            menuBar.add(color);
            menuBar.add(bg);
            menuBar.add(outline);
            menuBar.add(reset);

            bigPanel.add(menuBar);
            frame.add(bigPanel, BorderLayout.NORTH);
        }
        
        else if(e.getSource() == south)
        {
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(1, 4);
            bigLayout = new GridLayout(1, 2);
            bigPanel.setLayout(bigLayout);
            bigPanel.remove(menuBar);
            bigPanel.remove(buttonPanel);
            bigPanel.add(buttonPanel);
            menuLayout = new GridLayout(1, 6);

            menuBar.remove(font);
            menuBar.remove(size);
            menuBar.remove(color);
            menuBar.remove(bg);
            menuBar.remove(outline);
            menuBar.remove(reset);

            menuBar.add(font);
            menuBar.add(size);
            menuBar.add(color);
            menuBar.add(bg);
            menuBar.add(outline);
            menuBar.add(reset);

            bigPanel.add(menuBar);
            frame.add(bigPanel, BorderLayout.SOUTH);
        }
        
        else if(e.getSource() == west)
        {
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(1, 4);
            bigLayout = new GridLayout(2, 1);
            bigPanel.setLayout(bigLayout);
            bigPanel.remove(menuBar);
            bigPanel.remove(buttonPanel);
            bigPanel.add(buttonPanel);
            menuLayout = new GridLayout(1, 6);

            menuBar.remove(font);
            menuBar.remove(size);
            menuBar.remove(color);
            menuBar.remove(bg);
            menuBar.remove(outline);
            menuBar.remove(reset);

            menuBar.add(font);
            menuBar.add(size);
            menuBar.add(color);
            menuBar.add(bg);
            menuBar.add(outline);
            menuBar.add(reset);

            bigPanel.add(menuBar);
            frame.add(bigPanel, BorderLayout.WEST);
        }

        else if(e.getSource() == east)
        {
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(1, 4);
            bigLayout = new GridLayout(2, 1);
            bigPanel.setLayout(bigLayout);
            bigPanel.remove(menuBar);
            bigPanel.remove(buttonPanel);
            bigPanel.add(buttonPanel);
            menuLayout = new GridLayout(1, 6);

            menuBar.remove(font);
            menuBar.remove(size);
            menuBar.remove(color);
            menuBar.remove(bg);
            menuBar.remove(outline);
            menuBar.remove(reset);

            menuBar.add(font);
            menuBar.add(size);
            menuBar.add(color);
            menuBar.add(bg);
            menuBar.add(outline);
            menuBar.add(reset);

            bigPanel.add(menuBar);
            frame.add(bigPanel, BorderLayout.EAST);
        }

        else if(e.getSource() == fontArr[0])
        {
            currentFont = allFonts[0];
            textArea.setFont(currentFont);
        }

        else if(e.getSource() == fontArr[1])
        {
            currentFont = allFonts[1];
            textArea.setFont(currentFont);
        }

        else if(e.getSource() == fontArr[2])
        {
            currentFont = allFonts[2];
            textArea.setFont(currentFont);
        }

        else if(e.getSource() == sizeArr[0])
        {
            fontSize = allSizes[0];
            textArea.setFont(new Font(currentFont.getName(), currentFont.getStyle(), fontSize));
        }

        else if(e.getSource() == sizeArr[1])
        {
            fontSize = allSizes[1];
            textArea.setFont(new Font(currentFont.getName(), currentFont.getStyle(), fontSize));
        }

        else if(e.getSource() == sizeArr[2])
        {
            fontSize = allSizes[2];
            textArea.setFont(new Font(currentFont.getName(), currentFont.getStyle(), fontSize));
        }

        else if(e.getSource() == colorArr[0])
        {
            currentTextColor = textColor[0];
            textArea.setForeground(currentTextColor);
        }

        else if(e.getSource() == colorArr[1])
        {
            currentTextColor = textColor[1];
            textArea.setForeground(currentTextColor);
        }

        else if(e.getSource() == colorArr[2])
        {
            currentTextColor = textColor[2];
            textArea.setForeground(currentTextColor);
        }

        else if(e.getSource() == bgArr[0])
        {
            currentBG = bgColor[0];
            textArea.setBackground(currentBG);
        }

        else if(e.getSource() == bgArr[1])
        {
            currentBG = bgColor[1];
            textArea.setBackground(currentBG);
        }

        else if(e.getSource() == bgArr[2])
        {
            currentBG = bgColor[2];
            textArea.setBackground(currentBG);
        }

        else if(e.getSource() == outlineArr[0])
        {
            currentBorder = outlineColor[0];
            north.setBorder(new LineBorder(currentBorder));
            south.setBorder(new LineBorder(currentBorder));
            west.setBorder(new LineBorder(currentBorder));
            east.setBorder(new LineBorder(currentBorder));
            reset.setBorder(new LineBorder(currentBorder));
        }

        else if(e.getSource() == outlineArr[1])
        {
            currentBorder = outlineColor[1];
            north.setBorder(new LineBorder(currentBorder));
            south.setBorder(new LineBorder(currentBorder));
            west.setBorder(new LineBorder(currentBorder));
            east.setBorder(new LineBorder(currentBorder));
            reset.setBorder(new LineBorder(currentBorder));
        }

        else if(e.getSource() == outlineArr[2])
        {
            currentBorder = outlineColor[2];
            north.setBorder(new LineBorder(currentBorder));
            south.setBorder(new LineBorder(currentBorder));
            west.setBorder(new LineBorder(currentBorder));
            east.setBorder(new LineBorder(currentBorder));
            reset.setBorder(new LineBorder(currentBorder));
        }

        else if(e.getSource() == reset)
        {
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(1, 4);
            bigLayout = new GridLayout(1, 2);
            bigPanel.setLayout(bigLayout);
            bigPanel.remove(menuBar);
            bigPanel.remove(buttonPanel);
            bigPanel.add(buttonPanel);
            menuLayout = new GridLayout(1, 6);

            menuBar.remove(font);
            menuBar.remove(size);
            menuBar.remove(color);
            menuBar.remove(bg);
            menuBar.remove(outline);
            menuBar.remove(reset);

            menuBar.add(font);
            menuBar.add(size);
            menuBar.add(color);
            menuBar.add(bg);
            menuBar.add(outline);
            menuBar.add(reset);

            fontSize = allSizes[0];
            currentFont = allFonts[0];
            currentTextColor = textColor[0];
            currentBG = bgColor[0];
            currentBorder = outlineColor[0];

            north.setBorder(new LineBorder(currentBorder));
            south.setBorder(new LineBorder(currentBorder));
            west.setBorder(new LineBorder(currentBorder));
            east.setBorder(new LineBorder(currentBorder));
            reset.setBorder(new LineBorder(currentBorder));

            textArea.setFont(currentFont);
            textArea.setForeground(currentTextColor);
            textArea.setBackground(currentBG);
            textArea.setText("");

            bigPanel.add(menuBar);
            frame.add(bigPanel, BorderLayout.NORTH);
        }

        revalidate();
    }

    public static void main(String[] args)
    {
        GUI app = new GUI();
    }
    
}