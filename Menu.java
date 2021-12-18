package homework;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Menu extends JFrame implements ActionListener, ChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6928102053211469864L;
	private final GraphicsOfBoard grb;
	private final JPanel base, blankpanel1, blankpanel2, rulesPanel, speedOfGamePanel, startStopPanel, saveLoadPanel;
	private final JButton startButton, stopButton, stepButton, resetButton, saveButton, loadButton;
	private final ArrayList<JToggleButton> surviveJToggleButton, birthJToggleButton;
	private final JTextField speedSliderTextField, surviveJTextField, birthJTextField;
	private final JSlider speedSlider;
	private Thread game;
	private static final String[] TEXTS = {"1", "2", "3", "4", "5","6", "7", "8", "9"};
	
	public Menu()
	{
		
		setTitle("Game");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 800);
		BorderLayout lm=new BorderLayout();
		setLayout(lm);
		
		////Maga a map
		grb = new GraphicsOfBoard();
		add(grb, BorderLayout.CENTER);
		////
		
		////Main panel
		base=new JPanel(new GridLayout(7,1));
		
		///Blank Panel1
		blankpanel1 = new JPanel();
		base.add(blankpanel1);
		///
		
		///Rules Panel
		rulesPanel = new JPanel(new GridLayout(2,9));
		surviveJToggleButton = new ArrayList<JToggleButton>();
		birthJToggleButton = new ArrayList<JToggleButton>();
		birthJTextField = new JTextField("A Dead Cell births upon these rules");
		birthJTextField.setEditable(false);
		birthJTextField.setHighlighter(null);
		birthJTextField.setFocusable(false);
		surviveJTextField = new JTextField("A Living Cell survives upon these rules:");
		surviveJTextField.setEditable(false);
		surviveJTextField.setHighlighter(null);
		surviveJTextField.setFocusable(false);
		
		for (String text : TEXTS)
		{
			surviveJToggleButton.add(new JToggleButton(text));
			birthJToggleButton.add(new JToggleButton(text));
		}
		
		//rulesPanel.add(birthJTextField);
		for(Integer i = 1; i <= birthJToggleButton.size(); i++)
		{
			
			birthJToggleButton.get(i-1).setText(i.toString());
			birthJToggleButton.get(i-1).addActionListener(this);
			birthJToggleButton.get(i-1).setFocusable(false);
			rulesPanel.add(birthJToggleButton.get(i-1));
		}
		
		// rulesPanel.add(surviveJTextField);
		for(Integer i = 1; i <= surviveJToggleButton.size(); i++)
		{
			
			surviveJToggleButton.get(i-1).setText(i.toString());
			surviveJToggleButton.get(i-1).addActionListener(this);
			surviveJToggleButton.get(i-1).setFocusable(false);
			rulesPanel.add(surviveJToggleButton.get(i-1));
		}
		
		
		base.add(rulesPanel);
		///
		
		blankpanel2 = new JPanel();
		base.add(blankpanel2);
		
		
		///Speed of Game Panel
		speedOfGamePanel = new JPanel(new FlowLayout());
		speedSliderTextField = new JTextField("Speed of the game:");
		speedSliderTextField.setEditable(false);
		speedSliderTextField.setHighlighter(null);
		speedSliderTextField.setFocusable(false);
		speedOfGamePanel.add(speedSliderTextField);
		speedSlider = new JSlider(new DefaultBoundedRangeModel(7, 0, 1, 13));
		grb.setSpeed(speedSlider.getValue());
		speedOfGamePanel.add(speedSlider);
		
		speedSlider.addChangeListener(this);
		base.add(speedOfGamePanel);
		///
		
		///Start-stop Buttonok Panel
		startStopPanel = new JPanel(new FlowLayout());
		//Start Button
		startButton = new JButton();
		startButton.setText("Start");
		startButton.addActionListener(this);
		startButton.setFocusable(false);
		startStopPanel.add(startButton);
		//
		//Stop Button
		stopButton = new JButton();
		stopButton.setText("Stop");
		stopButton.addActionListener(this);
		stopButton.setFocusable(false);
		startStopPanel.add(stopButton);
		//
		//Step Button
		stepButton = new JButton();
		stepButton.setText("Step");
		stepButton.addActionListener(this);
		stepButton.setFocusable(false);
		startStopPanel.add(stepButton);
		//
		//Reset Button
		resetButton = new JButton();
		resetButton.setText("Reset");
		resetButton.addActionListener(this);
		resetButton.setFocusable(false);
		startStopPanel.add(resetButton);
		//
		base.add(startStopPanel);
		///
		
		///Save-Load Panel
		saveLoadPanel = new JPanel(new FlowLayout());
		saveButton = new JButton();
		saveButton.setText("Save");
		saveButton.addActionListener(this);
		saveButton.setFocusable(false);
		
		loadButton = new JButton();
		loadButton.setText("Load");
		loadButton.addActionListener(this);
		loadButton.setFocusable(false);
		
		saveLoadPanel.add(saveButton);
		saveLoadPanel.add(loadButton);
		
		base.add(saveLoadPanel);
		///
		
		add(base, BorderLayout.EAST);
		////
		
		setBaseRules();
		setResizable(true);
		setVisible(true);
	}
	
	public void setBaseRules()
	{
		birthJToggleButton.get(2).doClick();
		surviveJToggleButton.get(1).doClick();
		surviveJToggleButton.get(2).doClick();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals(startButton))
		{
			game = new Thread(grb);
			game.start();
			grb.setGameIsRunning();
			startButton.setEnabled(false);
			stepButton.setEnabled(false);
			resetButton.setEnabled(false);
			saveButton.setEnabled(false);
			loadButton.setEnabled(false);
			for(Integer i = 0; i < birthJToggleButton.size(); i++)
				birthJToggleButton.get(i).setEnabled(false);
			for(Integer i = 0; i < surviveJToggleButton.size(); i++)
				surviveJToggleButton.get(i).setEnabled(false);
		}
		else if(e.getSource().equals(stopButton))
		{
			if(game != null)
			{
				grb.setGameIsRunning();
			}
			startButton.setEnabled(true);
			stepButton.setEnabled(true);
			resetButton.setEnabled(true);
			saveButton.setEnabled(true);
			loadButton.setEnabled(true);
			
			
			for(Integer i = 0; i < birthJToggleButton.size(); i++)
				birthJToggleButton.get(i).setEnabled(true);
			for(Integer i = 0; i < surviveJToggleButton.size(); i++)
				surviveJToggleButton.get(i).setEnabled(true);
		}
		else if(e.getSource().equals(stepButton))
		{
			if(game == null || !game.isAlive())
				grb.gameStep();
		}
		else if(e.getSource().equals(resetButton))
		{
			if(game == null || !game.isAlive())
					grb.resetBoard();
		}
		else if(e.getSource().equals(saveButton))
		{
			grb.getBoard().saveGameBoard();
		}
		else if(e.getSource().equals(loadButton))
		{
			grb.getBoard().loadGameBoard();
			grb.repaint();
		}
		
		for(Integer i = 0; i < birthJToggleButton.size(); i++)
		{
			if(e.getSource().equals(birthJToggleButton.get(i)) && (game == null || !game.isAlive()))
			{
				grb.getBoard().getRules().setRulesBirths(i+1);
			}
		}
		
		for(Integer i = 0; i < surviveJToggleButton.size(); i++)
		{
			if(e.getSource().equals(surviveJToggleButton.get(i)) && (game == null || !game.isAlive()))
			{
				grb.getBoard().getRules().setRulesSurvives(i+1);
			}
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent e) 
	{
		if(e.getSource().equals(speedSlider))
			grb.setSpeed(speedSlider.getValue());
		grb.grabFocus();
	}
	
	public GraphicsOfBoard getGOB()
	{
		return grb;
	}
}