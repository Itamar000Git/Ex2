package ex2.geo;

import ex2.ex2.Ex2_Const;

/**
 * This class represents a 2D segment on the plane, 
 * Ex2: you should implement this class!
 * @author I2CS
 *
 */
public class Segment_2D implements GeoShape{
	private Point_2D _p1 ,_p2;

	/**
	 * This is the builder and this is the tow points that build the segment
	 * @param a
	 * @param b
	 */
	public Segment_2D(Point_2D a, Point_2D b) {
	_p1= new Point_2D(a);
	_p2= new Point_2D(b);
	}
	public Segment_2D(Segment_2D t1) {
		_p1= new Point_2D(t1._p1);
		_p2= new Point_2D(t1._p2);
	}

	public Point_2D get_p1() {
		return new Point_2D(_p1);
	}
	public Point_2D get_p2() {
		return new Point_2D(_p2);
	}

	/**
	 * Checks if a point is on the segment.
	 * @param ot - a query 2D point
	 * @return
	 */
	@Override
	public boolean contains(Point_2D ot) {
		boolean ans = false;

		double dist=_p1.distance(_p2);
		double d1= _p1.distance(ot);
		double d2= ot.distance(_p2);
		ans = (d1+d2) < dist+ Ex2_Const.EPS;

		return ans;
	}

	/**
	 * There is no area in segment
	 * @return
	 */
	@Override
	public double area() {
		return 0;
	}

	/**
	 * The perimeter is 2 times the segment distance
	 * @return
	 */
	@Override
	public double perimeter() {
		double d=_p1.distance(_p2);
		return (2*d);
	}

	/**
	 * Moving the segment by vector
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		_p1.move(vec);
		_p2.move(vec);

	}

	@Override
	public GeoShape copy() {
		return new Segment_2D(_p1,_p2);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		_p1.scale(center,ratio);
		_p2.scale(center,ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_p1.rotate(center,angleDegrees);
		_p2.rotate(center,angleDegrees);

	}
	@Override
	public String toString()
	{
		return(_p1.toString()+","+_p2.toString());
	}
	@Override
	public boolean equals(Object s1) {

		if((s1==null || !(s1 instanceof Segment_2D))) {return false;}

		Segment_2D s2 = (Segment_2D)s1;

		return (_p1.equals(s2._p1)&& _p2.equals(s2._p2));
	}
}