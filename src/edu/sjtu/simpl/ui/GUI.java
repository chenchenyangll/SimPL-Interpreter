package edu.sjtu.simpl.ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.TextUI;

import edu.sjtu.simpl.grammar.SimPL;
import edu.sjtu.simpl.grammar.SimpleNode;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public GUI(SimpleNode root) {
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		final MyPanel mp = new MyPanel(root);
		JScrollPane scrollPane=new JScrollPane();
		
		scrollPane.getViewport().add(mp);
		getContentPane().add(scrollPane);
		setVisible(true);
	}
	
	class MyPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private SimpleNode root;
		private static final int lineHeight = 20;
		private static final int itemWidth = 50;
		private static final int lineMargin = 10;
		
		
		public MyPanel(SimpleNode root) {
			super();
			setOpaque(false);
			setPreferredSize(new Dimension(1000,2000));
			this.root = root;
			this.setLayout(null);
			
			JLabel itemLabel = new JLabel();
			itemLabel.setSize(itemWidth, lineHeight);
			itemLabel.setBounds(0,0,itemWidth,lineHeight);
			itemLabel.setText("ceshi");
			itemLabel.setBorder(BorderFactory.createLineBorder(Color.black));
//			itemLabel.setOpaque(true);
//			itemLabel.setBackground(Color.black);
			this.add(itemLabel,0,0);
		}

		@Override
		public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.gray);
//            Shape[] shapes = new Shape[2];   
//            shapes[0] = new Rectangle2D.Float(25,25,70,70);   
//            shapes[1] = new Rectangle2D.Float(130+400,10,100+400,100);
//               
//            for(int i=0;i<shapes.length;i++){  
//                    g2.draw(shapes[i]);   
//            }
			
			int lineNum = 0;
			
			
		}
		
		private void paintSingleLine(ArrayList<SimpleNode> items, Graphics2D g, int ypos)
		{
			Shape[] shapes = new Shape[2];
			
		}
		
	}
	
	public static void main(String[] args) {
//		  System.out.println("Reading from standard input...");
//		  SimPL parser = new SimPL(System.in);
//		 
//		  while (true) {
//				parser.ReInit(System.in);
//				try {
//					System.out.print("SimPL> ");
//					SimpleNode root = parser.Program();
//					root.dump("---");
//					System.out.println("-------");
//					new GUI(root);
//				} catch (Exception e) {
//					System.out.println("Syntax Error!");
//					System.out.println(e.getMessage());
//				}
//			}
		new GUI(null);
	}
}


