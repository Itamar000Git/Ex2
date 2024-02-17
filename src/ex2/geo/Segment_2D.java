package ex2.geo;

import ex2.ex2.Ex2_Const;

/**
 * This class represents a 2D segment on the plane, 
 * Ex2: you should implement this class!
 * @author I2CS
 *
 */
public class Segment_2D implements GeoShape{//if we use geo shape we have to use all is functions
	private Point_2D _p1 ,_p2;
	public Segment_2D(Point_2D a, Point_2D b) {//this is the builder and this is the tow points that build the segment---done
		////// add your code here //////
	_p1= new Point_2D(a);
	_p2= new Point_2D(b);
		////////////////////////////////
	}
	public Segment_2D(Segment_2D t1) { 		// done, build the segment from tow points
		////// add your code here //////
		_p1= new Point_2D(t1._p1);
		_p2= new Point_2D(t1._p2);
		////////////////////////////////
	}

	public Point_2D get_p1() { 					//done
		////// add your code here //////
		return new Point_2D(_p1); //duplicate
		////////////////////////////////
	}
	public Point_2D get_p2() {					//done
		////// add your code here //////
		return new Point_2D(_p2);
		////////////////////////////////
	}

	@Override
	public boolean contains(Point_2D ot) { //true if point id inside the segment --- done
		boolean ans = false;

		double dist=_p1.distance(_p2);
		double d1= _p1.distance(ot);
		double d2= ot.distance(_p2);
		ans = (d1+d2) < dist+ Ex2_Const.EPS;

		//ans = (d1+d2)==(dist+Ex2_Const.EPS);  //mt idea need to check

		return ans;
	}

	@Override
	public double area() {				// there is no area in segment --- done
		return 0;
	}

	@Override
	public double perimeter() {			// is 2 times the segment distance --- done
		double d=_p1.distance(_p2);
		return (2*d);
	}

	@Override
	public void translate(Point_2D vec) { //move the segment on some vector ---done
		_p1.move(vec);
		_p2.move(vec);

	}

	@Override
	public GeoShape copy() { 			//done
		return new Segment_2D(_p1,_p2);
	}

	@Override							//done
	public void scale(Point_2D center, double ratio) {
		_p1.scale(center,ratio);
		_p2.scale(center,ratio);
	}

	@Override							//problem for later
	public void rotate(Point_2D center, double angleDegrees) {
		_p1.rotate(center,angleDegrees);
		_p2.rotate(center,angleDegrees);

	}

	public String toString()
	{
		return(_p1.toString()+","+_p2.toString());
	}
}