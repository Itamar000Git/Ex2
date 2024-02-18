package ex2.geo;

import ex2.ex2.Ex2_Const;

/**
 * This class represents a 2D circle in the plane. 
 * Please make sure you update it according to the GeoShape interface.
 * Ex2: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle_2D implements GeoShape{
	private Point_2D _center;
	private double _radius;
	
	public Circle_2D(Point_2D cen, double rad) {     		//builder
		this._center = new Point_2D(cen);
		this._radius = rad;
	}
	public Circle_2D(Circle_2D c) {

		this(c.getCenter(), c.getRadius());
	}
	public double getRadius() {return this._radius;}
	public Point_2D getCenter(){ return _center;}
	 @Override
	    public String toString()
	    {
			////// add your code here //////
			return _center.toString()+", "+_radius;
			////////////////////////////////
		}
	@Override
	public boolean contains(Point_2D ot) {				//done
		////// add your code here //////
		return (ot.distance(_center)<_radius);
		////////////////////////////////
	}
	
	@Override
	public double area() {
		////// add your code here //////
		double e=Math.PI * Math.pow(_radius,2);
		return e;
		////////////////////////////////
	}
	@Override
	public double perimeter() {
		////// add your code here //////
		double p= 2* Math.PI *_radius;
		return p;
		////////////////////////////////
	}
	@Override
	public void translate(Point_2D vec) {
		////// add your code here //////
		_center.move(vec);
		////////////////////////////////
	}
	@Override
	public GeoShape copy() {				//done
		////// add your code here //////
		return new Circle_2D(this);
		////////////////////////////////
	}

	@Override
	public void scale(Point_2D center, double ratio) {	//done
		////// add your code here //////
		_center.scale(center,ratio);
		_radius*=ratio;
		////////////////////////////////
	}
	@Override
	public void rotate(Point_2D center, double angleDegrees) {	//done
		////// add your code here //////
		_center.rotate(center,angleDegrees);
		////////////////////////////////
	}


}
