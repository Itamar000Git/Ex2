package ex2.ex2;

import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


/**
 * This class represents a collection of GUI_Shape.
 * Ex2: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements GUI_Shape_Collection {
	private ArrayList<GUI_Shape> _shapes;
	
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}
	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}


	/**
	 * Removes selected shapes.
	 * @param i - the index of the element to be removed.
	 * @return
	 */
	@Override
	public GUI_Shape removeElementAt(int i) {
		_shapes.remove(i);
		return null;
	}

	/**
	 * Adding shape in specific place in the array
	 * @param s - the gui_shape
	 * @param i - the location (index) in which s should be added
	 */
	@Override
	public void addAt(GUI_Shape s, int i) {			///done
		_shapes.add(i,s);
	}

	/**
	 * Adding shape at the end of the array
	 * @param s - the gui_shape
	 */
	@Override
	public void add(GUI_Shape s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}

	/**
	 * Make a copy of the shape collection
	 * @return
	 */
	@Override
	public GUI_Shape_Collection copy() {
		ShapeCollection SC = new ShapeCollection();
		for (int i=0;i<_shapes.size();i++){
			SC.add(_shapes.get(i).copy());
		}
		return SC;
	}

	/**
	 * Implement ALL sorting elements that can be done on shapes.
	 * Using Babble Sort method.
	 * @param comp a linear order over gui_shapes as defined in java.util.Comparator
	 */

	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		int n = _shapes.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (comp.compare(_shapes.get(j), _shapes.get(j + 1)) > 0) {
					// Swap elements at index j and j+1
					GUI_Shape temp = _shapes.get(j);
					_shapes.set(j, _shapes.get(j + 1));
					_shapes.set(j + 1, temp);
				}
			}
		}
	}


	/**
	 * Implement the CLEAR option, and removing all shapes.
	 */
	@Override
	public void removeAll() {
		int size=_shapes.size();
		for(int i=0;i<size;i++){
			_shapes.remove(0);
		}
	}

	/**
	 * This function takes the string and replace the color part with the color encoding
	 * @param replacement
	 * @param t
	 * @return
	 */
	public String modifyString( String replacement,String[] t) {
		String temp="";

		for (int i=4;i<t.length;i++){
			temp+=t[i]+",";
		}
		String m= t[0]+","+replacement+","+temp;
		return m;
	}

	/**
	 * Convert every shape to string and Save file after changing the color value.
	 * got help from YouTube and chatGPT.
	 * @param file - the file name in which this collection will be saved.
	 */
	@Override
	public void save(String file) {
		String[] s = file.split(",");		//split to array by ","
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(s[0]))) {		//declare on BufferedWriter
				for (GUI_Shape shape: _shapes) {			// for each shape
					String modifiedString = shape.toString();

					if (modifiedString.contains("java.awt.Color")) {			//replacing the color encoding
						String[] t= modifiedString.split(",");
						int colorCode = colorEncoding(shape.getColor());
						modifiedString = modifyString(String.valueOf(colorCode),t);
					}
					writer.write(modifiedString);
					writer.newLine(); // Write a new line for the next shape
				}
			} catch (IOException e) {
				throw new RuntimeException("Error writing to file: " + e.getMessage());
			}
	}

	/**
	 * Boaz code, the formula for getting the color code
	 * @param c
	 * @return
	 */
	public static int colorEncoding(Color c) {
		int r = c.getRed();
		int b = c.getBlue();
		int g = c.getGreen();
		int ce = r*256*256 + g*256 + b;
		return ce;
	}

	@Override
/**
 * Takes text file and convert every line to string,this string sends to GUIShape class for convert.
 * 	 * got help from YouTube and chatGPT.
 */
	public void load(String file) {
		try {
			File myObj = new File(file);		//create new File object
			Scanner myReader = new Scanner(myObj);	//create scanner
			_shapes.clear();						// clear all the shapes

			while (myReader.hasNextLine()) {		//while the is next line
				String data = myReader.nextLine();		//the string inserted to data
				GUIShape shape = new GUIShape(data);	//the gui shape takes this string and inr guishape class divide it as wanted
				_shapes.add(shape);						//adding the shape to the arraylist
				System.out.println(data);
			}
			myReader.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}


	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
}
