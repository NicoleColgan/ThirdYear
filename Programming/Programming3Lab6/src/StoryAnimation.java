import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
/**
 * class for animating a story
 * @author nicole colgan 19345826
 *
 */
public class StoryAnimation extends JFrame implements ActionListener{

	private JComboBox<String> storyNamesComboBox;
	private Story story1,story2;
	private JLabel label;
	private JTextArea descriptionTextArea, descriptionTextArea2;
	private JRadioButton s1b1,s1b2,s1b3,s2b1,s2b2,s2b3,s2b4;
	private String storyNames[] = {"Stick man story","Animal story"};
	private JButton addStoryButton;
	private Container container;
	private Component[] componentList;
	private GridBagLayout layout;
	private boolean story1Shown=true,story2Shown=false;
	private GridBagConstraints constraints;
	
	/**
	 * set up GUI
	 */
	public StoryAnimation() {
		super("My stories");
		
		//get container and set up gridBagLayout
		container = getContentPane();
		layout = new GridBagLayout();
		container.setLayout(layout);
		
		//instantiate grid bag constraints
		constraints = new GridBagConstraints();
		
		//Stories will hold an arrayList of images, a title and a description. 
		//this helps to keep the program more organised
		story1 = new Story("Stick man story", "Story of 3 stick men #stick #yolo");
		story1.addImage("person.png");
		story1.addImage("person2.jpg");
		story1.addImage("person3.jpg");
		story2 = new Story("animal story", "Some wild animals in the rain forest.");
		story2.addImage("animal2.jpg");
		story2.addImage("animal1.png");
		story2.addImage("animal3.png");
		story2.addImage("animal4.png");
		
		//initially show story1 and the first picture
		label = new JLabel(story1.getImages().get(0));
		label.setIcon(story1.getImages().get(0));
		
		//comboBox with story names
		storyNamesComboBox = new JComboBox<>(storyNames);
		//show both stories when you try to select a new one
		storyNamesComboBox.setMaximumRowCount(2);
		
		storyNamesComboBox.addItemListener(
				//anonymous inner class to handle JComboEvents
				new ItemListener() {

					/*
					 * if the user selects a new story, display diff contents on screen
					 */
					@Override
					public void itemStateChanged(ItemEvent e) {
						
						if(e.getStateChange() == ItemEvent.SELECTED) {//it was either selected or deselected
							if(storyNamesComboBox.getSelectedIndex() == 0 && !story1Shown) {
								//change layout if story 1 is selected and not already being displayed
								createGUI(story1);
								story1Shown=true;
								story2Shown=false;
							}
							else if(storyNamesComboBox.getSelectedIndex() == 1 && !story2Shown) {
								//change layout if story 2 is selected and not already being displayed
								componentList=container.getComponents();
								createGUI(story2);
								story2Shown=true;
								story1Shown=false;
							}
						}
						
					}
					
				});
		
		//text area
		descriptionTextArea = new JTextArea(story1.getDescription());
		descriptionTextArea.setEditable(false);	//user shouldnt be able to edit the description
		descriptionTextArea2 = new JTextArea(story2.getDescription());
		descriptionTextArea2.setEditable(false);
		
		//radio buttons
		s1b1 = new JRadioButton("1",true);
		s1b2 = new JRadioButton("2",false);
		s1b3 = new JRadioButton("3",false);
		s2b1 = new JRadioButton("1",true);
		s2b2 = new JRadioButton("2",false);
		s2b3 = new JRadioButton("3",false);
		s2b4 = new JRadioButton("4",false);
		RadioButtonHandler handler = new RadioButtonHandler();
		s1b1.addItemListener(handler);
		s1b2.addItemListener(handler);
		s1b3.addItemListener(handler);
		s2b1.addItemListener(handler);
		s2b2.addItemListener(handler);
		s2b3.addItemListener(handler);
		s2b4.addItemListener(handler);
		
		//button for adding a new story - just prints to console
		addStoryButton = new JButton("Add Story");
		addStoryButton.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {	//dont need to do anything specific
						System.out.println("Create new story button pressed");
						
					}
					
				});
		
		//displaying gui items (default is story1)
		createGUI(story1);
		
		setSize(500,500);	//window size
		setVisible(true);	//show window
		
	}
	
	/**
	 * Method for creating the gui
	 * @param story - story from which we will get the buttons and description
	 */
	public void createGUI(Story story) {
		
		componentList=container.getComponents();	//get rid of old components
		for(Component c: componentList) {
			remove(c);
		}
		
		//set layout to be story pic
		label.setIcon(story.getImages().get(0));
		
		//GUI
		//story name combo box
		constraints.fill = GridBagConstraints.HORIZONTAL;
		addComponent(storyNamesComboBox,0,0,10,1);
		//image
		constraints.fill = GridBagConstraints.BOTH;
		addComponent(label,1,1,6,6);
		//radio buttons
		constraints.fill = GridBagConstraints.HORIZONTAL;
		if(story.getTitle().equals("Stick man story")) {
			//add story1 radio buttons
			addComponent(s1b1,7,1,1,1);
			addComponent(s1b2,7,2,1,1);
			addComponent(s1b3,7,3,1,1);
			
			//story1 description
			addComponent(descriptionTextArea,9,0,10,2);
		}
		else if(story.getTitle().equals("animal story")) {
			//add story2 radio buttons
			addComponent(s2b1,7,1,1,1);
			addComponent(s2b2,7,2,1,1);
			addComponent(s2b3,7,3,1,1);
			addComponent(s2b4,7,4,1,1);
			
			//story2 description
			addComponent(descriptionTextArea2,9,0,10,2);
		}
		
		//add story button
		addComponent(addStoryButton,12,5,1,1);
		
		revalidate();	//called when new components are added or old ones are removed
	    repaint();	//repaint components
	}
	
	/**
	 * inner class for handling selection of radio buttons
	 * @author nicole
	 *
	 */
	private class RadioButtonHandler implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			
			//change to the correct picture depending on which radio button was pressed
			//the event could be an item being unselected too, so check if it is being selected
			//then unselect other radio buttons
			if(e.getSource() == s1b1 && e.getStateChange() == ItemEvent.SELECTED) {
				label.setIcon(story1.getImages().get(0));
				//unselect other radio buttons
				s1b2.setSelected(false);
				s1b3.setSelected(false);
				System.out.println("s1b1");
			}
			else if(e.getSource() == s1b2 && e.getStateChange() == ItemEvent.SELECTED) {
				label.setIcon(story1.getImages().get(1));
				s1b1.setSelected(false);
				s1b3.setSelected(false);
				System.out.println("s1b2");
			}
			else if(e.getSource() == s1b3 && e.getStateChange() == ItemEvent.SELECTED) {
				label.setIcon(story1.getImages().get(2));
				s1b2.setSelected(false);
				s1b1.setSelected(false);
				System.out.println("s1b2");
			}
			else if(e.getSource() == s2b1 && e.getStateChange() == ItemEvent.SELECTED) {
				label.setIcon(story2.getImages().get(0));
				s2b2.setSelected(false);
				s2b3.setSelected(false);
				s2b4.setSelected(false);
				System.out.println("s2b1");
			}
			else if(e.getSource() == s2b2 && e.getStateChange() == ItemEvent.SELECTED) {
				label.setIcon(story2.getImages().get(1));
				s2b1.setSelected(false);
				s2b3.setSelected(false);
				s2b4.setSelected(false);
				System.out.println("s2b2");
			}
			else if(e.getSource() == s2b3 && e.getStateChange() == ItemEvent.SELECTED) {
				label.setIcon(story2.getImages().get(2));
				s2b2.setSelected(false);
				s2b1.setSelected(false);
				s2b4.setSelected(false);
				System.out.println("s2b3");
			}
			else if(e.getSource() == s2b4) {
				label.setIcon(story2.getImages().get(3));
				s2b1.setSelected(false);
				s2b2.setSelected(false);
				s2b3.setSelected(false);
				System.out.println("s2b4");
			}
		}
		
	}
	
	//execute application
	public static void main(String args[]) {
		StoryAnimation app = new StoryAnimation();
		
		//Application terminated when window is closed.
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * add components to the container
	 * @param component - to add
	 * @param row - which row to start 
	 * @param column - which column to start
	 * @param width - horizontal space to occupy
	 * @param height - vertical space to occupy
	 */
	private void addComponent(Component component, int row, int column, int width, int height) {
		//set grid x and grid y
		constraints.gridx = column;
		constraints.gridy = row;
		
		//set grid width and height
		constraints.gridwidth = width;
		constraints.gridheight = height;
		
		//set constraints and add components
		//set constraints and add components
		layout.setConstraints(component, constraints);
		container.add(component);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//dont need this method
		
	}

}
