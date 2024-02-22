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
			return _center.toString()+", "+_radius;
		}

	/**
	 * checks if a point is inside the circle by comparing the distance to the center and the radios.
	 * @param ot - a query 2D point
	 * @return
	 */
	@Override
	public boolean contains(Point_2D ot) {
		return (ot.distance(_center)<_radius);
	}

	/**
	 * circle area formula
	 * @return
	 */
	@Override
	public double area() {
		double e=Math.PI * Math.pow(_radius,2);
		return e;
	}

	/**
	 * circle perimeter formula
	 * @return
	 */
	@Override
	public double perimeter() {
		double p= 2* Math.PI *_radius;
		return p;
	}

	/**
	 * moving the center point by vector.
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		_center.move(vec);
	}
	@Override
	public GeoShape copy() {
		return new Circle_2D(this);
	}

	/**
	 * scale the circle by increasing or decreasing the radios at the same ratio.
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		_center.scale(center,ratio);
		_radius*=ratio;
	}
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_center.rotate(center,angleDegrees);
	}

	@Override
	public boolean equals(Object c1) {

		if((c1==null || !(c1 instanceof Circle_2D))) {return false;}

		Circle_2D c2 = (Circle_2D)c1;

		return (_center.equals(c2._center)&& (_radius==c2._radius));
	}


}
