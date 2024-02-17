package ex2.ex2;

import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


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

//		_shapes.addLast(s);
//		int size=_shapes.size();
//
//		for (int j=(size-1);j>=i;j--){
//			_shapes.add(_shapes.get(j));
//			_shapes.remove(_shapes.get(j));
//		}
//
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
		GUI_Shape co=new GUIShape(_shapes.toString());

		return (GUI_Shape_Collection) co.copy();
		//////////////////////////////////////////
	}

	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		//////////add your code below ///////////

		int a;
		for (int i =0; (i<_shapes.size()-1);i++){

			a= comp.compare(this._shapes.get(i),this._shapes.get(i+1));
			if (a>=1){
				this._shapes.add(_shapes.get(i+1));
				this._shapes.remove(_shapes.get(i+1));

				this._shapes.add(_shapes.get(i));
				this._shapes.remove(_shapes.get(i));
			}
		}

//		System.out.println(a);
//		if (a==1){
//			this._shapes.add(_shapes.get(0));
//			this._shapes.remove(_shapes.get(0));
//		}
		
		//////////////////////////////////////////
	}

	@Override
	public void removeAll() {				//done
		//////////add your code below ///////////
		int size=_shapes.size();
		for(int i=0;i<size;i++){
			_shapes.remove(0);
		}
		//////////////////////////////////////////
	}

	@Override
	public void save(String file) {
		//////////add your code below ///////////
		int cEncode;
		String ModifiedString , newString;

		String[] s = file.split(",");

		for (int i = 0; i < _shapes.size(); i++) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(s[0]));
				cEncode = colorEncoding(this._shapes.get(i).getColor());
				newString = cEncode + "";
				System.out.println(cEncode);
				ModifiedString=file;

				for (int j = 1; j < s.length; j++) {
					if (s[j].contains("java.awt.Color")){
						ModifiedString=stringFix(ModifiedString,j,newString);
						s=ModifiedString.split(",");
					}
					if (j == (s.length - 1) && i==(_shapes.size()-1)) {
						writer.write(s[j]);
					} else {
						writer.write(s[j]+","); // Write the last item without a comma
					}
				}
				writer.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}


		//////////////////////////////////////////
	}
	public String stringFix(String str , int k,String myColor){
		String oldString;
		String[] t = str.split(",");

		oldString = t[k]+"," + t[k+1]+"," + t[k+2];
		System.out.println("trying to replace: " + oldString + " with: " + myColor);
		str=str.replace(oldString,myColor);

		System.out.println(str);

	return str;
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
//	public void load(String file) {
//
//		////////// add your code below ///////////
//		int langth;
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//			String line;
//			while ((line=reader.readLine())!=null) {
//			new ex2.gui.GUIShape(line);
//
//				langth=line.split(",").length;
//				s=new String[langth];
//				s=line.split(",");
//
//			}
//				reader.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        //////////////////////////////////////////
//	}
	public void load(String file) {
		//ArrayList<String> arrayList = new ArrayList<>();
		try {
			File myObj = new File(file);
			Scanner myReader = new Scanner(myObj);
			_shapes.clear();

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				//arrayList.add(data);
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
