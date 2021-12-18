package homework;

import java.util.ArrayList;
import java.util.Iterator;


public class Rules
{
	private ArrayList<Integer> births;
	private ArrayList<Integer> survives;
	
	public Rules()
	{
		births = new ArrayList<>();
		survives = new ArrayList<>();
	}
	
	public void setRulesBirths(int x)
	{
		if(x > 0 && x < 10)
		{
			if(!births.contains(x))
				this.births.add(x);
			else if(births.contains(x))
			{
				births.remove(births.indexOf(x));
				Iterator<Integer> itr = births.iterator();
		        while (itr.hasNext()) {
		            Integer number = itr.next();
		            if (number == x) {
		                itr.remove();
		            }
		        }
			}
		}
	}
	
	public void setRulesSurvives(int x)
	{
		if(x > 0 && x < 10)
		{
			if(!survives.contains(x))
				this.survives.add(x);
			else if(survives.contains(x))
			{
				survives.remove(survives.indexOf(x));
				Iterator<Integer> itr = survives.iterator();
		        while (itr.hasNext()) {
		            Integer number = itr.next();
		            if (number == x) {
		                itr.remove();
		            }
		        }
			}
		}
	}
	
	public ArrayList<Integer> getBirths()
	{
		return births;
	}
	
	public ArrayList<Integer> getSurvives()
	{
		return survives;
	}
}