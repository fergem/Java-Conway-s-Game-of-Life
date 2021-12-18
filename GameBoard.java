package homework;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentHashMap;


public class GameBoard
{
	
	private ConcurrentHashMap<Point, Integer> liveCells;
	private ConcurrentHashMap<Point, Integer> neighbours;
	private Rules rule;
	
	public GameBoard()
	{
		liveCells = new ConcurrentHashMap<Point, Integer>();
		neighbours = new ConcurrentHashMap<Point, Integer>();
		rule = new Rules();
	}
	
	public void setField(Point p)
	{
		if(liveCells.get(p) != null)
		{
			removeLiveCell(p);
		}
		else
		{
			storeLiveCell(p);
			storeNeighbours();
			updateLiveCells();
			updateNeighbours();
		}
	}
	
	public void shiftFields(int x, int y)
	{
		ConcurrentHashMap<Point, Integer> tempLiveCells = new ConcurrentHashMap<Point, Integer>();
		ConcurrentHashMap<Point, Integer> tempNeighbours = new ConcurrentHashMap<Point, Integer>();
		
		liveCells.forEach((k,v) ->
		{
			tempLiveCells.put(new Point(k.getX() + x, k.getY() + y), v);
		});
		
		neighbours.forEach((k,v) ->
		{
			tempNeighbours.put(new Point(k.getY() + x, k.getY() + y), v);
		});
		
		liveCells.clear();
		neighbours.clear();
		liveCells = tempLiveCells;
		neighbours = tempNeighbours;
	}
	
	
	public void removeLiveCell(Point p)
	{
		Point[] neighbourPoints = p.neighbours();
		for(int i = 0; i < neighbourPoints.length; i++)
		{
			if(neighbours.get(neighbourPoints[i]) == 1)
				neighbours.remove(neighbourPoints[i]);
			else
			{	
				neighbours.put(neighbourPoints[i], neighbours.get(neighbourPoints[i]) - 1);
			}
		}
		liveCells.remove(p);
	}
	
	public void storeLiveCell(Point p)
	{
		int baseneighbourCount = 0;
		liveCells.put(p, baseneighbourCount);
	}
	
	public void updateLiveCells()
	{
		ConcurrentHashMap<Point, Integer> tempLiveCells = new ConcurrentHashMap<Point, Integer>();
		liveCells.forEach((k,v)->
		{
			Point[] neighbourPoints = k.neighbours();
			int neighbourCount = 0;
			for(int i = 0; i < neighbourPoints.length; i++)
			{
				if(liveCells.get(neighbourPoints[i]) != null)
					neighbourCount++;
			}
			tempLiveCells.put(k,neighbourCount);
		});
		liveCells = tempLiveCells;
	}
	
	public void storeNeighbours()
	{
		liveCells.forEach((k,v) ->
		{
			Point[] neighbourPoints = k.neighbours();
			int baseneighbourCount = 0;
			for(int i = 0; i < neighbourPoints.length; i++)
			{
				neighbours.put(neighbourPoints[i], baseneighbourCount);
			}
		});
	}
	
	
	public void updateNeighbours()
	{
		ConcurrentHashMap<Point, Integer> tempNeighbours = new ConcurrentHashMap<Point, Integer>();
		neighbours.forEach((k,v) ->
		{
			Point[] neighbourPoints = k.neighbours();
			int neighbourCount = 0;
			for(int i = 0; i < neighbourPoints.length; i++)
			{
				if(liveCells.get(neighbourPoints[i]) != null)
					neighbourCount++;
			}
			tempNeighbours.put(k, neighbourCount);
		});
		neighbours = tempNeighbours;
	}
	
	public void step()
	{
		ConcurrentHashMap<Point, Integer> tempLiveCells = new ConcurrentHashMap<Point, Integer>();
		neighbours.forEach((k,v) ->
		{
			for(Integer i: rule.getBirths())
			{
				if(neighbours.get(k) == i)
					tempLiveCells.put(k, v);
			}
		});
		
	
		liveCells.forEach((k, v) ->
		{
			for(Integer i: rule.getSurvives())
			{
				if(liveCells.get(k) == i)
				{
					tempLiveCells.put(k, v);
				}
			}
		});
		
		liveCells = tempLiveCells;
		updateLiveCells();
		storeNeighbours();
		updateNeighbours();
	}

	public void resetBoard()
	{
		liveCells.clear();
		neighbours.clear();
	}
	
	public Rules getRules()
	{
		return rule;
	}
	
	public void saveGameBoard()
	{
		try 
		{
            FileOutputStream myFileOutStream
                = new FileOutputStream("save.txt");
  
            ObjectOutputStream myObjectOutStream
                = new ObjectOutputStream(myFileOutStream);
  
            myObjectOutStream.writeObject(liveCells);
  
            myObjectOutStream.close();
            myFileOutStream.close();
        }
        catch (IOException e)
		{
            e.printStackTrace();
        }
	}
	
	public void loadGameBoard()
	{
		liveCells.clear();
		try 
		{
            FileInputStream fileInput = new FileInputStream("save.txt");
  
            ObjectInputStream objectInput
                = new ObjectInputStream(fileInput);
  
            liveCells = (ConcurrentHashMap<Point,Integer>)objectInput.readObject();
            storeNeighbours();
            
            objectInput.close();
            fileInput.close();
        }
  
        catch (IOException obj1) 
		{
            obj1.printStackTrace();
            return;
        }
  
        catch (ClassNotFoundException obj2) 
		{
            System.out.println("Class not found");
            obj2.printStackTrace();
            return;
        }
	}
	
	public ConcurrentHashMap<Point,Integer> getLiveCells()
	{
		return liveCells;
	}
	
	public ConcurrentHashMap<Point,Integer> getNeighbours()
	{
		return neighbours;
	}
}