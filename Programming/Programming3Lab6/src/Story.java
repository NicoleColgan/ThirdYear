import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 * class to store story data
 * @author nicole colgan 19345826
 *
 */
public class Story {

	private String description, title;
	private ArrayList<Icon> images;
	
	//constructor
	public Story(String title, String des) {
		this.setDescription(des);
		this.setTitle(title);
		images = new ArrayList<>();
	}

	/**
	 * get description for story
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * set story description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * get title for story
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * set title for story
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * add images to array list of images for the story
	 * @param name - image name
	 */
	public void addImage(String name) {
		try {
			Icon i = new ImageIcon(name);
			images.add(i);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * get a list of all images assosiated with a story
	 * @return
	 */
	public ArrayList<Icon> getImages() {
		return images;
	}
}
