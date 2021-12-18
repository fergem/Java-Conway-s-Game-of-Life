package homework;

import java.io.Serializable;

public class Point implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3819524065934836442L;
	private final int x, y;
    
	
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    //For testing
    public Point()
    {
    	this.x = (int) Math.random();
    	this.y = (int) Math.random();
    }
       
    @Override
    public final boolean equals (Object obj)    
    {
        Point p = (Point) obj;
        return x == p.x && y == p.y;
    }
    
    @Override
    public final int hashCode()
    {
    	int result = 373; // Constant can vary, but should be prime
    	result = 37 * result + x;
    	return result = 37 * result + y;
    }
    
    
    @Override
    public String toString ()
    {
        return "(" + x + "," + y + ")";
    }

    public Point[] neighbours ()
    {
        return new Point[] {
            new Point (x-1, y-1),
            new Point (x-1, y),
            new Point (x-1, y+1),
            new Point (x, y-1),
            new Point (x, y+1),
            new Point (x+1, y-1),
            new Point (x+1, y),
            new Point (x+1, y+1)
        };
    }
    
    public int getX()
    {
    	return x;
    }
    
    public int getY()
    {
    	return y;
    }
}
