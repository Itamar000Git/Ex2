package ex2.geo;

import ex2.ex2.Ex2_Const;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Polygon_2DTest {


    Point_2D cen=new Point_2D(1,1);
    Point_2D vec1=new Point_2D(5,5);
    Point_2D vec2=new Point_2D(-5,-5);

    Polygon_2D po = new Polygon_2D();


    @Test
    void getAllPoints() {           //done
        Point_2D p1=new Point_2D(0,0);
        Point_2D p2=new Point_2D(0,1);
        Point_2D p3=new Point_2D(0,2);
        Point_2D p4=new Point_2D(2,2);
        Point_2D p5=new Point_2D(2,0);
        po.add(p1);
        po.add(p2);
        po.add(p3);
        po.add(p4);
        po.add(p5);

        assertEquals(p1,po.getAllPoints()[0]);
        assertEquals(p2,po.getAllPoints()[1]);
        assertEquals(p3,po.getAllPoints()[2]);
        assertEquals(p4,po.getAllPoints()[3]);
        assertEquals(p5,po.getAllPoints()[4]);



    }

    @Test
    void add() {
        Point_2D p1=new Point_2D(0,0);
        Point_2D p2=new Point_2D(0,1);
        Point_2D p3=new Point_2D(0,2);
        Point_2D p4=new Point_2D(2,2);
        Point_2D p5=new Point_2D(2,0);
        po.add(p1);
        po.add(p2);
        po.add(p3);
        po.add(p4);
        po.add(p5);
        assertEquals(po.getAllPoints().length,5);
        getAllPoints();
    }

    @Test
    void testToString() {
        Point_2D p1=new Point_2D(0,0);
        Point_2D p2=new Point_2D(0,1);
        Point_2D p3=new Point_2D(0,2);
        Point_2D p4=new Point_2D(2,2);
        Point_2D p5=new Point_2D(2,0);
        po.add(p1);
        po.add(p2);
        po.add(p3);
        po.add(p4);
        po.add(p5);
        String[] str=new String[po.toString().length()];

            str[0] = po.toString();
            System.out.println(str[0]);

            assertEquals(str[0],po.toString());
    }

    @Test
    void contains() {
        Point_2D p1=new Point_2D(0,0);
        Point_2D p2=new Point_2D(-1,1);
        Point_2D p3=new Point_2D(0.5,2);
        Point_2D p4=new Point_2D(2,3);
        Point_2D p5=new Point_2D(3,0.5);
        po.add(p1);
        po.add(p2);
        po.add(p3);
        po.add(p4);
        po.add(p5);

        Point_2D point = new Point_2D(0,12);

        System.out.println(po.contains(point));

    }

    @Test
    void area() {
        Point_2D p1=new Point_2D(0,0);
        Point_2D p2=new Point_2D(0,1);
        Point_2D p3=new Point_2D(0,2);
        Point_2D p4=new Point_2D(2,2);
        Point_2D p5=new Point_2D(2,0);
        po.add(p1);
        po.add(p2);
        po.add(p3);
        po.add(p4);
        po.add(p5);
        double a= po.area();

        assertEquals(a,4);
    }

    @Test
    void perimeter() {
        Point_2D p1=new Point_2D(0,0);
        Point_2D p2=new Point_2D(0,1);
        Point_2D p3=new Point_2D(0,2);
        Point_2D p4=new Point_2D(2,2);
        Point_2D p5=new Point_2D(2,0);
        po.add(p1);
        po.add(p2);
        po.add(p3);
        po.add(p4);
        po.add(p5);

       double d = po.perimeter();
       assertEquals(8,d);
    }

    @Test
    void translate() {
        Point_2D p1=new Point_2D(0,0);
        Point_2D p2=new Point_2D(0,1);
        Point_2D p3=new Point_2D(0,2);
        Point_2D p4=new Point_2D(2,2);
        Point_2D p5=new Point_2D(2,0);
        po.add(p1);
        po.add(p2);
        po.add(p3);
        po.add(p4);
        po.add(p5);

        Polygon_2D co= (Polygon_2D) po.copy();
        po.translate(vec1);

        for (int i=0;i<po.getAllPoints().length;i++){
            assertNotEquals(po.getAllPoints()[i].x(),co.getAllPoints()[i].x());
            assertNotEquals(po.getAllPoints()[i].y(),co.getAllPoints()[i].y());
        }
        po.translate(vec2);
        for (int i=0;i<po.getAllPoints().length;i++){
            Point_2D temp=po.getAllPoints()[i];
            assertEquals(temp.x(),co.getAllPoints()[i].x(),Ex2_Const.EPS);
            assertEquals(temp.y(),co.getAllPoints()[i].y(),Ex2_Const.EPS);
        }
    }
    @Test
    void copy() {
        Point_2D p1=new Point_2D(0,0);
        Point_2D p2=new Point_2D(0,1);
        Point_2D p3=new Point_2D(0,2);
        Point_2D p4=new Point_2D(2,2);
        Point_2D p5=new Point_2D(2,0);
        po.add(p1);
        po.add(p2);
        po.add(p3);
        po.add(p4);
        po.add(p5);

        Polygon_2D co= (Polygon_2D) po.copy();

        for (int i=0;i<po.getAllPoints().length;i++){
            Point_2D temp=po.getAllPoints()[i];
            assertEquals(temp.x(),co.getAllPoints()[i].x(),Ex2_Const.EPS);
            assertEquals(temp.y(),co.getAllPoints()[i].y(),Ex2_Const.EPS);
        }

    }

    @Test
    void scale() {
        Point_2D p1=new Point_2D(0,0);
        Point_2D p2=new Point_2D(0,1);
        Point_2D p3=new Point_2D(0,2);
        Point_2D p4=new Point_2D(2,2);
        Point_2D p5=new Point_2D(2,0);
        po.add(p1);
        po.add(p2);
        po.add(p3);
        po.add(p4);
        po.add(p5);

        Polygon_2D co= (Polygon_2D) po.copy();
        double r1=90.0/100, r2=100.0/90;
        double a;
        a=po.area();

        po.scale(cen,r1);
        assertNotEquals(a,po.area());

        po.scale(cen,r2);
        assertEquals(a,po.area(),Ex2_Const.EPS);
    }

    @Test
    void rotate() {
        Point_2D p1=new Point_2D(0,0);
        Point_2D p2=new Point_2D(0,1);
        Point_2D p3=new Point_2D(0,2);
        Point_2D p4=new Point_2D(2,2);
        Point_2D p5=new Point_2D(2,0);

        po.add(p1);
        po.add(p2);
        po.add(p3);
        po.add(p4);
        po.add(p5);

        Polygon_2D co= (Polygon_2D) po.copy();
        po.rotate(cen,30);

        for (int i=0;i<po.getAllPoints().length;i++){
            assertNotEquals(po.getAllPoints()[i].x(),co.getAllPoints()[i].x());
            assertNotEquals(po.getAllPoints()[i].y(),co.getAllPoints()[i].y());
        }

        po.rotate(cen,-30);
        for (int i=0;i<po.getAllPoints().length;i++){
            Point_2D temp=po.getAllPoints()[i];
            assertEquals(temp.x(),co.getAllPoints()[i].x(),Ex2_Const.EPS);
            assertEquals(temp.y(),co.getAllPoints()[i].y(),Ex2_Const.EPS);
        }
    }

    @Test
    void outSidePointTest(){
        Point_2D p1=new Point_2D(0,0);
        Point_2D p2=new Point_2D(-1,1);
        Point_2D p3=new Point_2D(0.5,2);
        Point_2D p4=new Point_2D(2,3);
        Point_2D p5=new Point_2D(3,0.5);
        po.add(p1);
        po.add(p2);
        po.add(p3);
        po.add(p4);
        po.add(p5);
        Point_2D outSideP= po.outSidePoint();
        System.out.println(outSideP);

        double max_X=outSideP.x(),max_Y=outSideP.y();

        for (int i=0;i<po.getAllPoints().length;i++){
            assertTrue(max_X>po.getAllPoints()[i].x());
            assertTrue(max_Y>po.getAllPoints()[i].y());

        }
    }
    @Test
    void equalsTest(){
        Point_2D p1=new Point_2D(0,0);
        Point_2D p2=new Point_2D(-1,1);
        Point_2D p3=new Point_2D(0.5,2);
        Point_2D p4=new Point_2D(2,3);
        Point_2D p5=new Point_2D(3,0.5);
        po.add(p1);
        po.add(p2);
        po.add(p3);
        po.add(p4);
        po.add(p5);
         Polygon_2D pol2 =(Polygon_2D) po.copy();
        assertTrue(po.equals(pol2));

        Polygon_2D pol3 = new Polygon_2D();
        Point_2D p6=new Point_2D(2,0);
        Point_2D p7=new Point_2D(3,1);
        Point_2D p8=new Point_2D(3,1);
        Point_2D p9=new Point_2D(3,1);
        Point_2D p10=new Point_2D(3,1);

        pol3.add(p6);
        pol3.add(p7);
        pol3.add(p8);
        pol3.add(p9);
        pol3.add(p10);

        assertFalse(po.equals(pol3));

    }
}