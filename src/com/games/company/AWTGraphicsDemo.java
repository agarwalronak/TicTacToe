package com.games.company;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class AWTGraphicsDemo extends Frame {

	Set<Point> zeros = new HashSet<Point>();
	Set<Point> crosses = new HashSet<Point>();
	Rectangle[][] squares = new Rectangle[3][3];
	int[][] tictactoe = new int[3][3];
	int[][][] coordinates;
	int flag = 0;
	Point[] winLine = new Point[2]; 

	public AWTGraphicsDemo() {
		super("tic-tac-toe");
		coordinates = new int[][][] {
				{ { 50, 100 }, { 150, 100 }, { 250, 100 } },
				{ { 50, 200 }, { 150, 200 }, { 250, 200 } },
				{ { 50, 300 }, { 150, 300 }, { 250, 300 } } };
		prepareGUI();
	}

	public static void main(String[] args) {
		AWTGraphicsDemo awtGraphicsDemo = new AWTGraphicsDemo();
		awtGraphicsDemo.setVisible(true);
	}

	private void prepareGUI() {
		setSize(800, 400);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent evt) {

				Point pos = evt.getPoint();
				int fl = 0;
				for(int i = 0; i<3; i++)
				{
					for(int j=0; j<3; j++)
					{
					if(squares[i][j].contains(pos))
					{
						
					   if(tictactoe[j][i]!=0)
						   return;
					   pos = new Point(coordinates[j][i][0],coordinates[j][i][1]);
					   if (flag == 0) {
							zeros.add(pos);
							tictactoe[j][i]  = 1;
							repaint();
							checkWin(j,i);
							flag = 1;
						}
						else if (flag == 1) {
							crosses.add(pos);
							tictactoe[j][i]  = 2;
							repaint();
							checkWin(j,i);
							flag = 0;
						}
					   fl=1;
					   break;
					   
					}
					}
					if(fl==1)
						break;
				}
				

			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	protected void checkWin(int i, int j) {
		
	    int value = tictactoe[i][j];
		if(tictactoe[i][0]==value  && tictactoe[i][1]== value && tictactoe[i][2]== value)
		{
			winLine[0] = new Point(0,(i+1)*100);
			winLine[1] = new Point(300,(i+1)*100);
			repaint();
		}
		else if(tictactoe[0][j]==value  && tictactoe[1][j]== value && tictactoe[2][j]== value)
		{
			winLine[0] = new Point((j+1)*100-50,50);
			winLine[1] = new Point((j+1)*100-50,350);
			repaint();
		}
		else if(tictactoe[0][0]==value  && tictactoe[1][1]== value && tictactoe[2][2]== value)
		{
			winLine[0] = new Point(0,50);
			winLine[1] = new Point(300,350);
			repaint();
		}
		else if(tictactoe[0][2]==value  && tictactoe[1][1]== value && tictactoe[2][0]== value)
		{
			winLine[0] = new Point(300,50);
			winLine[1] = new Point(0,350);
			repaint();
		}
		
		
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		Font font = new Font("DialogInput", Font.PLAIN, 24);
		g.setFont(font);
		// g.drawString("Welcome to the world of graphics", 50, 150);
		g.drawLine(100, 50, 100, 350);
		g.drawLine(200, 50, 200, 350);
		g.drawLine(0, 150, 300, 150);
		g.drawLine(0, 250, 300, 250);

		int d1 = 0, d2 = 50;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				squares[i][j] = new Rectangle(d1, d2, 100, 100);
				d2 = d2 + 100;
			}
			d1 = d1 + 100;
			d2 = 50;
		}

		// int[][][] coordinates = new
		// int[][][]{{{50,75},{150,75},{250,75}},{{50,175},{150,175},{250,175}},{{50,275},{150,275},{250,275}}};
		// int[][][] coordinates = new
		// int[][][]{{{50,100},{150,100},{250,100}},{{50,200},{150,200},{250,200}},{{50,300},{150,300},{250,300}}};
		//
		// //play(coordinates);
		//
		// for(int i = 0; i<3; i++)
		// {
		// for(int j = 0; j<3; j++)
		// {
		// g.drawChars(new
		// char[]{'x'},0,1,coordinates[i][j][0],coordinates[i][j][1]);
		// }
		// }

		play(g);

	}

	private void play(Graphics g) {
		// TODO Auto-generated method stub
		for (Point pt : zeros) {
			g.setColor(Color.RED);
			Font font = new Font("Serif", Font.BOLD, 40);
			g.drawChars(new char[] { 'o' }, 0, 1, pt.x, pt.y);
		}
		for (Point pt : crosses) {
			g.setColor(Color.BLUE);
			Font font = new Font("Serif", Font.BOLD, 40);
			g.drawChars(new char[] { 'x' }, 0, 1, pt.x, pt.y);
		}
		if(winLine != null)
		{
			g.drawLine(winLine[0].x, winLine[0].y, winLine[1].x, winLine[1].y);
			g.setColor(Color.MAGENTA);
		      Font font = new Font("Serif", Font.PLAIN, 100);
		      g.setFont(font);
		      g.drawString("Game Over!", 150, 250);     
		}

	}
}
