package homework;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class Menu
{
	private final JFrame jr;
	private final GraphicsOfBoard grb;
	private final JPanel p2;
	private final JButton b1,b2;
	
	Menu()
	{
		jr = new JFrame();
		jr.setTitle("Game");
		
		jr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jr.setSize(1000, 600);
		BorderLayout lm=new BorderLayout();
		jr.setLayout(lm);
		
		///Maga a map
		grb = new GraphicsOfBoard();
		
		jr.add(grb, BorderLayout.CENTER);
		
		///
		
		///Buttonok Panel
		p2=new JPanel(new GridLayout(5,1));
		
		//Button 1
		b1 = new JButton();
		b1.setText("Gyorsabban");
		p2.add(b1);
		//
		
		//Button 2
		b2 = new JButton();
		b2.setText("Lassabban");
		p2.add(b2);
		//
		
		jr.add(p2, BorderLayout.EAST);
		///
		
		
		jr.setResizable(true);
		jr.setVisible(true);
	}
	
	public GraphicsOfBoard getgrb()
	{
		return grb;
	}
}