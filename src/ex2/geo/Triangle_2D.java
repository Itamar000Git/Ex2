package ex2.geo;

import ex2.ex2.Ex2_Const;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex2: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle_2D implements GeoShape{
	////// add your code here //////
	private Point_2D _p1,_p2,_p3;

	////////////////////////////////
	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {				//from point to triangle
		////// add your code here //////
	_p1=new Point_2D(p1);
	_p2=new Point_2D(p2);
	_p3=new Point_2D(p3);

		////////////////////////////////
	}
	public Triangle_2D(Triangle_2D t1) {					//from triangle to points
		////// add your code here //////
		_p1=new Point_2D(t1._p1);
		_p2=new Point_2D(t1._p2);
		_p3=new Point_2D(t1._p3);
		////////////////////////////////
	}


	public Point_2D[] getAllPoints() {				//done
		////// add your code here //////
		Point_2D [] alltp = {new Point_2D(_p1),new Point_2D(_p2),new Point_2D(_p3)};

		return alltp;
		////////////////////////////////
	}

	public Point_2D getP1() {				//done
		////// add your code here //////
		Point_2D p1 = new Point_2D(_p1);

		return p1;
		////////////////////////////////
	}
	public Point_2D getP2() {				//done
		////// add your code here //////
		Point_2D p2 = new Point_2D(_p2);

		return p2;
		////////////////////////////////
	}
	public Point_2D getP3() {				//done
		////// add your code here //////
		Point_2D p3 = new Point_2D(_p3);

		return p3;
		////////////////////////////////
	}

	/**
	 * For knowing if suspect point is inside the triangle we creat 3 new triangles
	 * with the suspect point and 2 othe =r point from the triangle.
	 * If the sum of the three new triangle areas is match to the original triangle area, the suspect point is inside.
	 * @param ot - a query 2D point
	 * @return
	 */
	@Override
	public boolean contains(Point_2D ot) {// create 3 new triangles with the checked point, and compares the areas.---done
		Triangle_2D tr=new Triangle_2D(_p1,_p2,_p3);
		Triangle_2D tr1= new Triangle_2D(ot,_p2,_p3);
		Triangle_2D tr2=new Triangle_2D(_p1,ot,_p3);
		Triangle_2D tr3=new Triangle_2D(_p1,_p2,ot);
		double fullArea=tr.area();
		double a1,a2,a3, s;
		boolean co=false;
		a1=tr1.area();
		a2=tr2.area();
		a3=tr3.area();

		s=a1+a2+a3;
		co = s < fullArea + Ex2_Const.EPS;

		return co;
	}

	/**
	 * Heron's formula
	 * @return
	 */
	@Override
	public double area() {
		double a,b,c,s,temp;
		a=_p1.distance(_p2);
		b=_p2.distance(_p3);
		c=_p3.distance(_p1);
		s= (a+b+c)/2;
		temp=s*(s-a)*(s-b)*(s-c);

		double Tarea=Math.sqrt(temp);

		return Tarea;
	}

	@Override
	public double perimeter() {
		double a,b,c;
		a=_p1.distance(_p2);
		b=_p2.distance(_p3);
		c=_p3.distance(_p1);

		return (a+b+c);
	}

	@Override
	public void translate(Point_2D vec) {
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
	}

	@Override
	public GeoShape copy() {
		return new Triangle_2D(_p1,_p2,_p3);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		_p1.scale(center,ratio);
		_p2.scale(center,ratio);
		_p3.scale(center,ratio);

	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_p1.rotate(center,angleDegrees);
		_p2.rotate(center,angleDegrees);
		_p3.rotate(center,angleDegrees);

	}

	public String toString()
	{
		return(_p1.toString()+","+_p2.toString()+","+_p3.toString());
	}

}
