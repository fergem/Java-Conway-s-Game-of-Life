package homework;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


public class GraphicsOfBoard extends JPanel implements  MouseInputListener, ComponentListener
{
	private Dimension d_gameBoardSize = null;
	GameBoard g;
	private static final int BLOCK_SIZE = 20;
	
	GraphicsOfBoard()
	{
		g = new GameBoard();
		addMouseListener(this);
		addComponentListener(this);
		addMouseMotionListener(this);
	}
	
	public GameBoard getBoard()
	{
		return g;
	}
	
	public void drawField(MouseEvent e) 
	{
		int x = e.getPoint().x/BLOCK_SIZE-1;
        int y = e.getPoint().y/BLOCK_SIZE-1;
        
        if ((x >= 0) && (x < d_gameBoardSize.width) && (y >= 0) && (y < d_gameBoardSize.height)) 
        {
        	drawField(x,y);
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
        	
            g.getHashMap().forEach((k,v) -> 
            {gr.setColor(Color.blue);
            gr.fillRect(BLOCK_SIZE + (BLOCK_SIZE*k.x), BLOCK_SIZE + (BLOCK_SIZE*k.y), BLOCK_SIZE, BLOCK_SIZE);});
        } catch (ConcurrentModificationException cme) {}
        //
        gr.setColor(Color.BLACK);
        for (int i=0; i<=d_gameBoardSize.width; i++) {
            gr.drawLine(((i*BLOCK_SIZE)+BLOCK_SIZE), BLOCK_SIZE, (i*BLOCK_SIZE)+BLOCK_SIZE, BLOCK_SIZE + (BLOCK_SIZE*d_gameBoardSize.height));
        }
        for (int i=0; i<=d_gameBoardSize.height; i++) {
            gr.drawLine(BLOCK_SIZE, ((i*BLOCK_SIZE)+BLOCK_SIZE), BLOCK_SIZE*(d_gameBoardSize.width+1), ((i*BLOCK_SIZE)+BLOCK_SIZE));
        }
    }
     
	 @Override
	public void componentResized(ComponentEvent e) 
	{
		d_gameBoardSize = new Dimension(getWidth()/BLOCK_SIZE-2, getHeight()/BLOCK_SIZE-2);
	}
	
	 
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//drawField(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		drawField(e);
		
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
	public void mouseDragged(MouseEvent e) {
		//drawField(e);
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
}