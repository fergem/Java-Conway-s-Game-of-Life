package homework;


//import java.awt.Point;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class GameBoard
{
	
	private HashMap<Point, Cell> board;
	
	GameBoard()
	{
		board = new HashMap<Point, Cell>();
	}
	
	
	public void setField(Point p)
	{
		Cell c = board.get(p);
		if(c != null)
		{
			board.remove(p);
		}
		else
		{
			if (board.containsKey(p)) 
			{
				board.remove(p);
			} 
			else 
			{
				c = new Cell();
				board.put(p, c);
			}
		}
	}
	
	public void deleteField(Point p)
	{
		board.remove(p);
	}
	
	public HashMap<Point, Cell> getHashMap()
	{
		return board;
	}
	
	public void printOut()
	{
		System.out.println(board);
	}
}