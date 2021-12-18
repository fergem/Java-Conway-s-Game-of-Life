package homework;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ConcurrentModificationException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelListener;


public class GraphicsOfBoard extends JPanel implements  MouseInputListener, ComponentListener, Runnable, KeyListener,  MouseWheelListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5140975939553356775L;
	private Dimension gameBoardDimensions;
	private GameBoard g;
	private int cellSize;
	private int speed;
	private boolean gameIsRunning;
	private boolean mouseDragged;
	
	
	public GraphicsOfBoard()
	{
		g = new GameBoard();
		cellSize = 30;
		addMouseListener(this);
		addComponentListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		addMouseWheelListener(this);
		setFocusable(true);
		gameIsRunning = false;
		mouseDragged = false;
	}
	
	public GameBoard getBoard()
	{
		return g;
	}
	
	public void setSpeed(int x)
	{
		speed = x;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public void setGameIsRunning()
	{
		if(gameIsRunning)
			gameIsRunning = false;
		else
			gameIsRunning = true;
	}
	public void drawField(MouseEvent e) 
	{
		if(!gameIsRunning) 
		{
			int x = e.getPoint().x/cellSize-1;
	        int y = e.getPoint().y/cellSize-1;
	        
	        if ((x >= 0) && (x < gameBoardDimensions.width) && (y >= 0) && (y < gameBoardDimensions.height)) 
	        {
	        	drawField(x,y);
	        }
		}
	}
	
	public void drawField(int x, int y)
	{
    	g.setField(new Point(x,y));
		repaint();
	}
	
	@Override
    public void paintComponent(Graphics gr) 
	{
        super.paintComponent(gr);
        try {
        	gr.setColor(Color.BLUE);
            g.getLiveCells().forEach((k,v) -> 
            {	
            	if ((k.getX() >= 0) && (k.getX() < gameBoardDimensions.width) && (k.getY() >= 0) && (k.getY() < gameBoardDimensions.height)) 
            	{
            		gr.setColor(Color.blue);
            		gr.fillRect(cellSize + (cellSize*k.getX()), cellSize + (cellSize*k.getY()), cellSize, cellSize);
            	}
            	
            });
        } catch (ConcurrentModificationException cme) {}
        
        //
        gr.setColor(Color.BLACK);
        for (int i=0; i<=gameBoardDimensions.width; i++) {
            gr.drawLine(((i*cellSize)+cellSize), cellSize, (i*cellSize)+cellSize, cellSize + (cellSize*gameBoardDimensions.height));
        }
        for (int i=0; i<=gameBoardDimensions.height; i++) {
            gr.drawLine(cellSize, ((i*cellSize)+cellSize), cellSize*(gameBoardDimensions.width+1), ((i*cellSize)+cellSize));
        }
    }
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		if(!gameIsRunning)
		{
			int x = e.getPoint().x/cellSize-1;       
			int y = e.getPoint().y/cellSize-1;		
			if(!g.getLiveCells().containsKey(new Point(x,y)))
        	drawField(e);
			mouseDragged = true;
		}
	}
	
	@Override
	public void run()
	{
		g.step();
        repaint();
        
        while(gameIsRunning)
        {
        	try 
        	{
        		Thread.sleep(1000/speed);
                run();
            }
        	catch (InterruptedException ex) {}
        }
            
	}
	
	 @Override
	public void componentResized(ComponentEvent e) 
	{
		gameBoardDimensions = new Dimension(getWidth()/cellSize - 2, getHeight()/cellSize - 2);
	}
	
	public void gameStep()
	{
		g.step();
		repaint();
	}
	
	public void resetBoard()
	{
		g.resetBoard();
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
	    if (e.getKeyCode() == KeyEvent.VK_A && !mouseDragged)
	    {
	    	g.shiftFields(1, 0);
	    	repaint();
	    }

	    else if (e.getKeyCode() == KeyEvent.VK_D && !mouseDragged)
	    {
	    	g.shiftFields(-1, 0);
	    	repaint();
	    }

	    else if (e.getKeyCode() == KeyEvent.VK_W && !mouseDragged) 
	    {
	    	g.shiftFields(0, 1);
	    	repaint();
	    }

	    else if (e.getKeyCode() == KeyEvent.VK_S && !mouseDragged) 
	    {
	    	g.shiftFields(0, -1);
	    	repaint();
	    }
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) 
	{
		if (e.getWheelRotation() < 0 && cellSize < 50) 
	    {
			cellSize += 1;
	        gameBoardDimensions = new Dimension(getWidth()/cellSize-2, getHeight()/cellSize-2);
	        repaint();
	    } 
	    else if(cellSize > 10)
	    {
	        cellSize -= 1;
	        gameBoardDimensions = new Dimension(getWidth()/cellSize-2, getHeight()/cellSize-2);
	        repaint();
	    }
	  }

	@Override
	public void mousePressed(MouseEvent e) 
	{
		drawField(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		mouseDragged = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
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
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}