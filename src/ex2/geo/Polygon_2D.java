package ex2.geo;
import java.util.ArrayList;

public class Polygon_2D implements GeoShape{
	////// add your code here //////
	private ArrayList<Point_2D> Apoly;

	////////////////////////////////
	public Polygon_2D() {					//done
		////// add your code here //////
			this.Apoly=new ArrayList<>();
		////////////////////////////////
	}
	public Polygon_2D(Polygon_2D po) {		//done
		////// add your code here //////
		this.Apoly=new ArrayList<>();

		for (Point_2D p : po.Apoly){
			this.Apoly.add(new Point_2D(p.x(),p.y()));
		}
		////////////////////////////////
	}
	public Point_2D[] getAllPoints() {		//done
		//////// add your code here //////
		return Apoly.toArray(new Point_2D[0]);
		////////////////////////////////
	}

	public void add(Point_2D p) {			//done
		////// add your code here //////
		Apoly.add(new Point_2D(p));
		////////////////////////////////
	}
	@Override
	public String toString() {				//done
		////// add your code here //////
		String polySrt="";
		for (Point_2D p : this.Apoly){
			polySrt+=p.x()+","+p.y()+",";
		}
		return polySrt;
		////////////////////////////////
	}
	@Override
	public boolean contains(Point_2D ot) {
		////// add your code here //////
		boolean cross, contain=false;

		int counter=0;
		Point_2D outsideP=outSidePoint();
		for (int i=0; i<Apoly.size();i++){
			if (i==Apoly.size()-1){
				cross=doSegmentsIntersect(ot,outsideP,Apoly.get(i),Apoly.get(0));
				if (cross){counter++;}
			}
			else {
				cross=doSegmentsIntersect(ot,outsideP,Apoly.get(i),Apoly.get(i+1));
				if (cross){counter++;}
			}
	}
		if (counter % 2 == 1) {
			contain=true;
		}
		return contain;
		////////////////////////////////
	}

	/**
	 * Find point that with no doubt outside the polygon by finding the highest X,Y and add 1 for each.
	 * @return
	 */

	public Point_2D outSidePoint(){			//workes like magic

		Point_2D outsideP;
		Double max_X=Apoly.get(0).x(),max_Y= Apoly.get(0).y();

		for (int i=0;i<(Apoly.size()-1);i++){
			if (max_X<Apoly.get(i+1).x()){
				max_X= Apoly.get(i+1).x();
			}
			if (max_Y<Apoly.get(i+1).y()){
				max_Y= Apoly.get(i+1).y();
			}
		}
		outsideP=new Point_2D(max_X+1,max_Y+1);
		return outsideP;
	}

	/**
	 * Ths=is function founds if two segments are intersect.
	 * If the orientations of (checkP, outsideP) and (p1, p2) are different,
	 * and the orientations of (checkP, p1) and (checkP, p2) are also different, then the line segments intersect.
	 * @param checkP
	 * @param outsideP
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static boolean doSegmentsIntersect(Point_2D checkP, Point_2D outsideP, Point_2D p1, Point_2D p2) {
		int o1 = orientation(checkP, outsideP, p1);
		int o2 = orientation(checkP, outsideP, p2);
		int o3 = orientation(p1, p2, checkP);
		int o4 = orientation(p1, p2, outsideP);

		return (o1 != o2 && o3 != o4);
	}

	/**
	 * Tells us what is the orientation of segment.
	 * @param p1
	 * @param p2
	 * @param p3
	 * @return
	 */
	public static int orientation(Point_2D p1, Point_2D p2, Point_2D p3) {
		double val = (p2.y() - p1.y()) * (p3.x() - p2.x()) -
				(p2.x() - p1.x()) * (p3.y() - p2.y());

		if (val == 0) {
			return 0;
		} else if (val>0) {		// Clockwise or Counterclockwise
			return 1;

		}else return 2;
	}

	/**
	 * Polygon area formula.
	 * @return
	 */
	@Override
	public double area() {
		////// add your code here //////
			int numVertices = Apoly.size();
			double area = 0;

			for (int i = 0; i < numVertices; i++) {

				Point_2D currentVertex = Apoly.get(i);
				Point_2D nextVertex = Apoly.get((i + 1) % numVertices);
				area += currentVertex.x()* nextVertex.y();
				area -= currentVertex.y() * nextVertex.x();
			}
			area /= 2.0;
			return Math.abs(area);
		////////////////////////////////
	}
	@Override
	public double perimeter() {				//done
		////// add your code here //////
		double per=0;
		for (int i=0 ;i<Apoly.size();i++){
			if (i==(Apoly.size()-1)){
				per += Apoly.get(i).distance(Apoly.get(0));
			}
			else {
				per += Apoly.get(i).distance(Apoly.get(i + 1));
			}
		}
		return per;
		////////////////////////////////
	}
	@Override
	public void translate(Point_2D vec) {
		////// add your code here //////

		for (int i=0 ;i<Apoly.size();i++){
			Apoly.get(i).move(vec);
		}

		////////////////////////////////
	}
	@Override
	public GeoShape copy() {			//done
		////// add your code here //////
		return new Polygon_2D(this);
		////////////////////////////////
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		////// add your code here //////
		for (int i=0 ;i<Apoly.size();i++){
			Apoly.get(i).scale(center,ratio);
		}


		////////////////////////////////
	}
	@Override
	public void rotate(Point_2D center, double angleDegrees) {		//done
		////// add your code here //////
		for (int i=0 ;i<Apoly.size();i++){
			Apoly.get(i).rotate(center,angleDegrees);
		}
		////////////////////////////////
	}

}