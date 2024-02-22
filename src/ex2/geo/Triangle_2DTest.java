package ex2.geo;

import ex2.ex2.Ex2_Const;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Triangle_2DTest {

    Point_2D p1=new Point_2D(0,0);
    Point_2D p2=new Point_2D(0,2);
    Point_2D p3=new Point_2D(2,0);
    Triangle_2D tr1 = new Triangle_2D(p1,p2,p3);

    Point_2D p4=new Point_2D(0,0);
    Point_2D p5=new Point_2D(6,6);
    Point_2D p6=new Point_2D(3,0);

    Triangle_2D tr2 = new Triangle_2D(p4,p5,p6);

    Point_2D cen=new Point_2D(5,5);

    @Test
    void getAllPointsTest() {                   //done
        Point_2D [] arrp = {p1,p2,p3};
        Point_2D [] mytr= tr1.getAllPoints();
        assertArrayEquals(mytr,arrp);

        Point_2D [] arrp2 = {p4,p5,p6};
         mytr= tr2.getAllPoints();
        assertArrayEquals(mytr,arrp2);

    }

    @Test
    void containsTest() {                           //done
        Point_2D p7=new Point_2D(0.5,0.5);
        Point_2D p8=new Point_2D(2.5,0);
        Point_2D p9=new Point_2D(-2,2);
        assertTrue(tr1.contains(p7));
        assertFalse(tr1.contains(p8));

        assertTrue(tr2.contains(p8));
        assertFalse(tr2.contains(p9));
    }

    @Test
    void area() {                       //done
        double a=tr1.area();    //1

        assertEquals(a,2, Ex2_Const.EPS);

        a=tr2.area();       //9
        assertEquals(a,9,Ex2_Const.EPS);

    }

    @Test
    void perimeter() {

       double p= tr1.perimeter(); //6.828...

        assertEquals(p,4+Math.sqrt(8),Ex2_Const.EPS);

        p= tr2.perimeter();         //18.19...
        assertEquals(p,(3+Math.sqrt(72)+Math.sqrt(45)),Ex2_Const.EPS);
    }

    @Test
    void translateTest() {
        Point_2D vec= new Point_2D(5,7);
        Point_2D vec2= new Point_2D(-5,-7);

        Triangle_2D q1=(Triangle_2D) tr1.copy();
        Triangle_2D q2=(Triangle_2D) tr2.copy();

        tr1.translate(vec);
        tr2.translate(vec);
        for (int i=0;i<3;i++){
            assertNotEquals(tr1.getAllPoints()[i],q1.getAllPoints()[i]);
            assertNotEquals(tr2.getAllPoints()[i],q2.getAllPoints()[i]);
        }

        tr1.translate(vec2);
        tr2.translate(vec2);
        for (int i=0;i<3;i++) {
            assertEquals(tr1.getAllPoints()[i],q1.getAllPoints()[i]);
            assertEquals(tr2.getAllPoints()[i],q2.getAllPoints()[i]);
        }
    }

    @Test
    void copyTest() {

        Triangle_2D co=(Triangle_2D) tr1.copy();
        Triangle_2D co1=(Triangle_2D) tr2.copy();

        for (int i=0;i<3;i++) {
            assertEquals(tr1.getAllPoints()[i],co.getAllPoints()[i]);
            assertEquals(tr2.getAllPoints()[i],co1.getAllPoints()[i]);
        }
    }

    @Test
    void scaleTest() {              //done
        double r1=90.0/100, r2=100.0/90;

        double d1,d2,d3,dc1,dc2,dc3;
        double a;

        Triangle_2D co=(Triangle_2D) tr1.copy();
        Triangle_2D co2=(Triangle_2D) tr2.copy();

        dc1=co.getAllPoints()[0].distance(co.getAllPoints()[1]);
        dc2=co.getAllPoints()[1].distance(co.getAllPoints()[2]);
        dc3=co.getAllPoints()[2].distance(co.getAllPoints()[0]);

        a=tr2.area();

        tr1.scale(cen,r1);
        tr2.scale(cen,r1);

        d1=tr1.getAllPoints()[0].distance(tr1.getAllPoints()[1]);
        d2=tr1.getAllPoints()[1].distance(tr1.getAllPoints()[2]);
        d3=tr1.getAllPoints()[2].distance(tr1.getAllPoints()[0]);

        assertNotEquals(d1,dc1,Ex2_Const.EPS);
        assertNotEquals(d2,dc2,Ex2_Const.EPS);
        assertNotEquals(d3,dc3,Ex2_Const.EPS);

        assertTrue(a>tr2.area());

        tr1.scale(cen,r2);
        tr2.scale(cen,r2);

        d1=tr1.getAllPoints()[0].distance(tr1.getAllPoints()[1]);
        d2=tr1.getAllPoints()[1].distance(tr1.getAllPoints()[2]);
        d3=tr1.getAllPoints()[2].distance(tr1.getAllPoints()[0]);

        assertEquals(d1,dc1,Ex2_Const.EPS);
        assertEquals(d2,dc2,Ex2_Const.EPS);
        assertEquals(d3,dc3,Ex2_Const.EPS);

        assertEquals(a,tr2.area(), Ex2_Const.EPS);
    }

    @Test
    void rotate() {                 //done
        Triangle_2D co1=(Triangle_2D) tr1.copy();
        Triangle_2D co2=(Triangle_2D) tr2.copy();

        tr1.rotate(cen,30);
        tr2.rotate(cen,30);

        for (int i=0;i<3;i++){
            assertNotEquals(tr1.getAllPoints()[i].x(),co1.getAllPoints()[i].x());
            assertNotEquals(tr1.getAllPoints()[i].y(),co1.getAllPoints()[i].y());

            assertNotEquals(tr1.getAllPoints()[i].x(),co1.getAllPoints()[i].x());
            assertNotEquals(tr1.getAllPoints()[i].y(),co1.getAllPoints()[i].y());
        }

        tr1.rotate(cen,-30);
        tr2.rotate(cen,-30);

        for (int i=0;i<3;i++){
            assertEquals(tr1.getAllPoints()[i].x(),co1.getAllPoints()[i].x(),Ex2_Const.EPS);
            assertEquals(tr1.getAllPoints()[i].y(),co1.getAllPoints()[i].y(),Ex2_Const.EPS);

            assertEquals(tr2.getAllPoints()[i].x(),co2.getAllPoints()[i].x(),Ex2_Const.EPS);
            assertEquals(tr2.getAllPoints()[i].y(),co2.getAllPoints()[i].y(),Ex2_Const.EPS);
        }
    }
    @Test
    void toStringTest(){

        String a=tr1.toString();
        String b="0.0,0.0,0.0,2.0,2.0,0.0";

        assertTrue(a.equals(b));
    }

    @Test
   void equalsTest(){
        Triangle_2D co =(Triangle_2D) tr1.copy();

        assertTrue(tr1.equals(co));

    }


}