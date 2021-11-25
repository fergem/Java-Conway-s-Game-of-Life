package homework;

public class Cell
{
	private boolean state;
	private boolean nextstate;
	
	Cell()
	{
		state = false;
		nextstate = false;
	}
	
	public void SetNewState(boolean b)
	{
		nextstate = b;
	}
	
	public void SetState(boolean b)
	{
		state = b;
	}
	
	public void UpdateState()
	{
		state = nextstate;
	}
	
	public boolean GetState()
	{
		return state;
	}
	
	public boolean GetNextState()
	{
		return nextstate;
	}
}