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
	public GUI_Shape removeElementAt(int i) {			//done
		//////////add your code below ///////////
		_shapes.remove(i);
		return null;
		//////////////////////////////////////////
	}


	@Override
	public void addAt(GUI_Shape s, int i) {			///done
		//////////add your code below ///////////
		_shapes.add(i,s);
		//////////////////////////////////////////
	}

	@Override
	public void add(GUI_Shape s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public GUI_Shape_Collection copy() {
		//////////add your code below ///////////
		ShapeCollection SC = new ShapeCollection();
		for (int i=0;i<_shapes.size();i++){
			SC.add(_shapes.get(i).copy());
		}
		return SC;
		//////////////////////////////////////////
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
	 * Implement the CLEAR option
	 */
	@Override
	public void removeAll() {				//done
		//////////add your code below ///////////
		int size=_shapes.size();
		for(int i=0;i<size;i++){
			_shapes.remove(0);
		}
		//////////////////////////////////////////
	}

	/**
	 * This function takes the string and replace the color part with the color encoding
	 * @param replacement
	 * @param t
	 * @return
	 */
	private String modifyString( String replacement,String[] t) {
		// Replace the target substring with the replacement string
		String temp="";

		for (int i=4;i<t.length;i++){
			temp+=t[i]+",";
		}
		String m= t[0]+","+replacement+","+temp;
		return m;
	}

	/**
	 * Convert every shape to string and Save file after changing the color value.
	 * @param file - the file name in which this collection will be saved.
	 */
	@Override
	public void save(String file) {
		//////////add your code below ///////////
		int cEncode;
		String ModifiedString;
		String[] s = file.split(",");

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(s[0]))) {
				for (GUI_Shape shape: _shapes) {
					String modifiedString = shape.toString();

					if (modifiedString.contains("java.awt.Color")) {
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

		//////////////////////////////////////////
	}

	public static int colorEncoding(Color c) {
		int r = c.getRed();
		int b = c.getBlue();
		int g = c.getGreen();
		int ce = r*256*256 + g*256 + b;
		return ce;
	}


	public String s[];

	@Override
/**
 * Takes text file and convert every line to string,this string sends to GUIShape class for convert.
 */
	public void load(String file) {
		try {
			File myObj = new File(file);
			Scanner myReader = new Scanner(myObj);
			_shapes.clear();

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				GUIShape shape = new GUIShape(data);
				_shapes.add(shape);
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
