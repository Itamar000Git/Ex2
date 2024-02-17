package ex2.geo;
import ex2.ex2.Ex2_Const;


public class Rect_2D implements GeoShape{

	private Point_2D _p1,_p2,_p3,_p4;



	public Rect_2D(Point_2D p1, Point_2D p2) {		        //done
		////// add your code here //////
		this._p1=new Point_2D(p1);
		this._p2=new Point_2D(p2);
		this._p3=new Point_2D(p1.x(),p2.y());
		this._p4=new Point_2D(p2.x(),p1.y());
		////////////////////////////////
	}
	public Rect_2D(Rect_2D r1) {			        //done
		////// add your code here //////
		Point_2D [] rec=r1.getAllPoints();
		_p1=new Point_2D(r1._p1) ;
		_p2=new Point_2D(r1._p2) ;
		_p3=new Point_2D(r1._p3) ;
		_p4=new Point_2D(r1._p4) ;

//        this._p1=new Point_2D(rec[0]) ;
//        this._p3=new Point_2D(rec[1]) ;
//        this._p4=new Point_2D(rec[2]) ;
//        this._p2=new Point_2D(rec[3]) ;
		// this._p1=rec[0];
		//  this._p3= rec[1];
		//  this._p4= rec[2];
		// this._p2= rec[3];
		////////////////////////////////
	}


	public Point_2D get_p1() {
		return this._p1;
	}
	public Point_2D get_p2() {
		return this._p2;
	}
	public Point_2D get_p3() {
		return this._p3;
	}
	public Point_2D get_p4() {
		return this._p4;
	}

	public Point_2D[] getAllPoints(){
		Point_2D [] points = new Point_2D[4];
//		points[0]=_p1;
//		points[1]=_p3;
//		points[2]=_p2;
//		points[3]=_p4;
		points[0]=_p4;
		points[1]=_p2;
		points[2]=_p3;
		points[3]=_p1;

		return points;
	}


	public Point_2D set_p1(Point_2D p) {
		return _p1=p;
	}
	public Point_2D set_p2(Point_2D p) {

		return _p2=p;
	}
	public Point_2D set_p3(Point_2D p) {
		return _p3=p;
	}
	public Point_2D set_p4(Point_2D p) {
		return _p4=p;
	}


	@Override
	public boolean contains(Point_2D ot) {
		double sT,sR;
		boolean a;
		Triangle_2D tr1=new Triangle_2D(_p1,_p2,ot);
		Triangle_2D tr2=new Triangle_2D(_p2,_p4,ot);
		Triangle_2D tr3=new Triangle_2D(_p3,_p4,ot);
		Triangle_2D tr4=new Triangle_2D(_p3,_p1,ot);

		Rect_2D rec=new Rect_2D(_p1,_p2);
		sT=tr1.area()+ tr2.area()+tr3.area()+tr4.area();
		sR=rec.area();
		a= sR>sT + Ex2_Const.EPS;
		return a;
	}

	@Override
	public double area() {
		double Recarea;

		Recarea= _p1.distance(_p3) *_p3.distance(_p2);

		return Recarea;
	}

	@Override
	public double perimeter() {
		double per;
		per=2*_p1.distance(_p3)+2* _p3.distance(_p2);

		return per;
	}

	@Override
	public void translate(Point_2D vec) {
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
		_p4.move(vec);
	}

	@Override
	public GeoShape copy() {
		return new Rect_2D(this);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		_p1.scale(center,ratio);
		_p2.scale(center,ratio);
		_p3.scale(center,ratio);
		_p4.scale(center,ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {          //not working yet

		this._p1.rotate(center,angleDegrees);
		this._p2.rotate(center,angleDegrees);
		this._p3.rotate(center,angleDegrees);
		this._p4.rotate(center,angleDegrees);

	}

	public String toString()
	{
		return(this._p1.toString()+","+this._p2.toString()+","+this._p3.toString()+","+this._p4.toString()+",");
	}
}
