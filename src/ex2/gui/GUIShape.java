package ex2.gui;
/**
 * This class implements the GUI_shape.
 * Ex2: you should implement this class!
 * @author I2CS
 */
import ex2.ex2.GUI_Shape_Collection;
import ex2.geo.*;
import java.awt.*;
import java.io.IOException;
import java.util.Comparator;


public class GUIShape implements GUI_Shape{
	private GeoShape _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;
	
	public GUIShape(GeoShape g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}


	/**
	 * This function receive string, split by "," and creat array with shape properties.
	 * After that we creat shapes by insert to correct string to every property of the shapes.
	 * @param s
	 */
	public GUIShape(String s) {
		// TBI (to be implemented...{
		String[] strShape = s.split(",");
		int colorV=Integer.parseInt(strShape[1]);
		boolean fi=Boolean.parseBoolean(strShape[2]);
		int t= Integer.parseInt(strShape[3]);
		String geoKind=strShape[4];

		double x1=Double.parseDouble(strShape[5]);
		double y1=Double.parseDouble(strShape[6]);
		double x2=Double.parseDouble(strShape[7]);
		double y2;


		Point_2D p1 = new Point_2D(x1,y1);
		Point_2D p2;


		double[] otherPoints = new double[strShape.length-5];
		for (int i=0;i<otherPoints.length;i++){
			otherPoints[i]= Double.parseDouble(strShape[i+5]);
		}

		GeoShape geo=null;

		switch (geoKind){
			case "Segment_2D":
				 y2=Double.parseDouble(strShape[8]);
				 p2 = new Point_2D(x2,y2);
				geo=new Segment_2D(p1,p2);
				break;

			case "Circle_2D":
				geo=new Circle_2D(p1,x2);
				break;

			case "Rect_2D":
				y2=Double.parseDouble(strShape[8]);
				p2 = new Point_2D(x2,y2);
				geo=new Rect_2D(p1,p2);
				break;

			case "Triangle_2D":
				double x3=Double.parseDouble(strShape[9]);
				double y3=Double.parseDouble(strShape[10]);
						 y2=Double.parseDouble(strShape[8]);

				 p2 = new Point_2D(x2,y2);
				Point_2D p3 = new Point_2D(x3,y3);

				geo=new Triangle_2D(p1,p2,p3);
				break;
			case "Polygon_2D":
				Polygon_2D pol = new Polygon_2D();
				for (int i=0;i<(otherPoints.length-2);i++){
					Point_2D p =new Point_2D(otherPoints[i],otherPoints[i+1]);
					pol.add(p);
					i++;
				}
				geo = pol;

				break;
		}
		_g=geo;
		_fill=fi;
		_color=new Color(colorV);
		_tag=t;
		_isSelected=false;



	}
	@Override
	public GeoShape getShape() {
		return _g;
	}

	@Override
	public void setShape(GeoShape g) {
		_g = g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;
	}
	@Override
	public GUI_Shape copy() {
		GUI_Shape cp = new GUIShape(this);
		return cp;
	}
	@Override
	public String toString() {
		String ans = ""+this.getClass().getSimpleName()+","+_color+","+_fill+","+_tag+","+this._g.getClass().getSimpleName()+","+_g.toString();
		return ans;
	}
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
}
