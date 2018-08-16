package com.games.company;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Delayed;

@SuppressWarnings("serial")
public class PlayWithCPU extends Frame {

	Set<Point> zeros = new HashSet<Point>();
	Set<Point> crosses = new HashSet<Point>();
	Rectangle[][] squares = new Rectangle[3][3];
	int[][] tictactoe = new int[3][3];
	int[][][] coordinates;
	int flag = 0;
	Point[] winLine = new Point[2];
	Set<Integer> possible = new HashSet<Integer>();
	boolean result = false;

	public PlayWithCPU() {
		super("tic-tac-toe");
		coordinates = new int[][][] { { { 50, 100 }, { 150, 100 }, { 250, 100 } },
				{ { 50, 200 }, { 150, 200 }, { 250, 200 } }, { { 50, 300 }, { 150, 300 }, { 250, 300 } } };

		possible.add(0);possible.add(1);possible.add(3);possible.add(4);
		possible.add(5);possible.add(6);possible.add(7);possible.add(8);
		possible.add(2);
		prepareGUI();

		
	}

	public static void main(String[] args) {
		PlayWithCPU awtGraphicsDemo = new PlayWithCPU();
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
				int posPoint;
				int i1=0,j1=0;
				int found = 0;
				while(!possible.isEmpty() && result==false)
				{
					
					for(int i = 0;i<3;i++)
					{
						for(int j = 0;j<3;j++)
						{
							if (squares[i][j].contains(pos))
							{
								i1=j;
								j1=i;
								found=1;
								break;
							}
						}
						if(found==1)
						break;
					}
					

						if (tictactoe[i1][j1] != 0)
							return;
						pos = new Point(coordinates[i1][j1][0], coordinates[i1][j1][1]);
                        posPoint = (i1*3)+j1;
                        System.out.println("-------------------------------------------");
                		System.out.println("My Remove :" + posPoint);
                        possible.remove(posPoint);
						zeros.add(pos);
						tictactoe[i1][j1] = 1;
						System.out.println("tictactoe--i:"+i1+"j:"+j1);
						result = checkWin(i1, j1);
						repaint();
						if(!possible.isEmpty() && result==false)
						{
							playCPUWithIntel(possible);
						repaint();
						}
						
						

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

	protected boolean checkWin(int i, int j) {

		int value = tictactoe[i][j];
		if (tictactoe[i][0] == value && tictactoe[i][1] == value && tictactoe[i][2] == value) {
			winLine[0] = new Point(0, (i + 1) * 100);
			winLine[1] = new Point(300, (i + 1) * 100);
			return true;

		} else if (tictactoe[0][j] == value && tictactoe[1][j] == value && tictactoe[2][j] == value) {
			winLine[0] = new Point((j + 1) * 100 - 50, 50);
			winLine[1] = new Point((j + 1) * 100 - 50, 350);
			return true;

		} else if (tictactoe[0][0] == value && tictactoe[1][1] == value && tictactoe[2][2] == value) {
			winLine[0] = new Point(0, 50);
			winLine[1] = new Point(300, 350);
			return true;

		} else if (tictactoe[0][2] == value && tictactoe[1][1] == value && tictactoe[2][0] == value) {
			winLine[0] = new Point(300, 50);
			winLine[1] = new Point(0, 350);
			return true;

		}

		return false;
		
	}
	
	protected boolean checkWinTest(int i, int j) {

		int value = tictactoe[i][j];
		if (tictactoe[i][0] == value && tictactoe[i][1] == value && tictactoe[i][2] == value) {
			return true;

		} else if (tictactoe[0][j] == value && tictactoe[1][j] == value && tictactoe[2][j] == value) {
			return true;

		} else if (tictactoe[0][0] == value && tictactoe[1][1] == value && tictactoe[2][2] == value) {
			return true;

		} else if (tictactoe[0][2] == value && tictactoe[1][1] == value && tictactoe[2][0] == value) {
			return true;

		}

		return false;
		
	}

	private void playCPU(Set<Integer> possibles) {
		int min = 0;
		int max = 8;
		int ran = (int) ((Math.random() * ((max - min) + 1)) + min);
		while(!possibles.contains(ran))
		{
			ran = (int) ((Math.random() * ((max - min) + 1)) + min);	
		}
		System.out.println("-------------------------------------------");
		System.out.println("CPU Remove :" + ran);
		possibles.remove(ran);

		int i = ran%3;
		int j = ran/3;
		Point pos = new Point(50+i*100,100+j*100);
		crosses.add(pos);
		tictactoe[j][i] = 2;
		System.out.println("tictactoe--i:"+j+"j:"+i);
		result = checkWin(j, i);

	}
	
	private void playCPUWithIntel(Set<Integer> possibles) {
		
		int count = 0;
		int possCount = possibles.size();
		
		if(possibles.contains(4))
			{
			int ran = 4;
			int i = ran%3;
    		int j = ran/3;
    		System.out.println("-------------------------------------------");
			System.out.println("CPU Remove :" + ran);
			Point pos = new Point(50+i*100,100+j*100);
			crosses.add(pos);
			tictactoe[j][i] = 2;
			possibles.remove(ran);
			System.out.println("tictactoe--i:"+j+"j:"+i);
			result = checkWin(j, i);
			count = 1;
			return;
			
			}
		
        for(int ran : possibles)
        {
        	int i = ran%3;
    		int j = ran/3;
    		
    		tictactoe[j][i] = 2;
    		if(checkWinTest(j,i))
    		{
    			System.out.println("-------------------------------------------");
    			System.out.println("CPU Remove :" + ran);
    			Point pos = new Point(50+i*100,100+j*100);
    			crosses.add(pos);
    			tictactoe[j][i] = 2;
    			possibles.remove(ran);
    			System.out.println("tictactoe--i:"+j+"j:"+i);
    			result = checkWin(j, i);
    			count = 1;
    			break;
    		}
    		tictactoe[j][i] = 1;
    		if(checkWinTest(j,i))
    		{
    			System.out.println("-------------------------------------------");
    			System.out.println("CPU Remove :" + ran);
    			Point pos = new Point(50+i*100,100+j*100);
    			crosses.add(pos);
    			tictactoe[j][i] = 2;
    			possibles.remove(ran);
    			System.out.println("tictactoe--i:"+j+"j:"+i);
    			result = checkWin(j, i);
    			count = 1;
    			break;
    		}
    			tictactoe[j][i] = 0;
        }
        if(count==0)
        	playCPU(possibles);

		
		

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
		if(possible.isEmpty())
		{
			g.drawLine(winLine[0].x, winLine[0].y, winLine[1].x, winLine[1].y);
			g.setColor(Color.MAGENTA);
			Font font = new Font("Serif", Font.PLAIN, 100);
			g.setFont(font);
			g.drawString("its a Draw!", 150, 250);
		}
		if (winLine != null) {
			g.drawLine(winLine[0].x, winLine[0].y, winLine[1].x, winLine[1].y);
			g.setColor(Color.MAGENTA);
			Font font = new Font("Serif", Font.PLAIN, 100);
			g.setFont(font);
			g.drawString("Game Over!", 150, 250);
			
		}
		

	}
}
