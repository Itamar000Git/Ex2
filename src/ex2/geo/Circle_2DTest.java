package ex2.geo;

import ex2.ex2.Ex2_Const;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Circle_2DTest {
    private Point_2D p1= new Point_2D(0,0);
    private Circle_2D c1= new Circle_2D(p1,5);

    private Point_2D p2= new Point_2D(5,9);
    private Circle_2D c2= new Circle_2D(p2,7);


    @Test
    void getRadius() {//?

        assertEquals(c1.getRadius(),5);
        assertEquals(c2.getRadius(),7);

    }

    @Test
    void getCenter() {
        assertEquals(c1.getCenter(),p1);
        assertEquals(c2.getCenter(),p2);
    }

    @Test
    void testToString() {//?
    }

    @Test
    void contains() {

        Point_2D p2 = new Point_2D(3,0);
        boolean b1=c1.contains(p2);
        assertTrue(b1);

        p2= new Point_2D(7,3);
        b1=c1.contains(p2);
        assertFalse(b1);
    }

    @Test
    void area() {                       //done
        double e= c1.area(); //25*PI
        double e1= 25* Math.PI;
        assertEquals(e,e1, Ex2_Const.EPS);

        e=c2.area();//49*PI
        e1= 49* Math.PI;
        assertEquals(e,e1, Ex2_Const.EPS);
    }

    @Test
    void perimeter() {                          //done
        double p=c1.perimeter();    //10*PI
        double p1=10*Math.PI;
        assertEquals(p,p1,Ex2_Const.EPS);

        p=c2.perimeter();           //14*PI
        p1=14*Math.PI;
        assertEquals(p,p1,Ex2_Const.EPS);
    }

    @Test
    void translate() {                                  //done
        Point_2D vec= new Point_2D(5,7);
        Point_2D vec2= new Point_2D(-5,-7);

        Circle_2D t0=new Circle_2D(c1);
        Circle_2D t1=new Circle_2D(c2);

        t0.translate(vec);
        assertNotEquals(c1.getCenter(),t0.getCenter());
        t0.translate(vec2);
        assertEquals(c1.getCenter(),t0.getCenter());


        t1.translate(vec);
        assertNotEquals(c2.getCenter(),t1.getCenter());
        t1.translate(vec2);
        assertEquals(c2.getCenter(),t1.getCenter());

    }

    @Test
    void copyTest() {
        Circle_2D co=(Circle_2D) c1.copy();
        assertEquals(c1.getCenter(),co.getCenter());

        co = (Circle_2D) c2.copy();
        assertEquals(c2.getCenter(),co.getCenter());

        assertNotEquals(c1.getCenter(),co.getCenter());


    }

    @Test
    void scaleTest() {      //done
        double r=c1.getRadius();
        double ratio1=90.0/100, ratio2=100.0/90;

        c1.scale(p1,ratio1);
        c2.scale(p2,ratio1);
        assertNotEquals(r,c1.getRadius(),Ex2_Const.EPS);

        c1.scale(p1,ratio2);
        c2.scale(p2,ratio2);
        assertEquals(r,c1.getRadius(),Ex2_Const.EPS);
    }

    @Test
    void rotateTest() {     //done

        Circle_2D co=(Circle_2D) c2.copy();
        Circle_2D co1=(Circle_2D) c1.copy();
        Point_2D cen=new Point_2D(0.5,0.5);

        c1.rotate(cen,30);
        c2.rotate(cen,30);

        assertNotEquals(c2.getCenter().x(),co.getCenter().x());
        assertNotEquals(c2.getCenter().y(),co.getCenter().y());

        assertNotEquals(c1.getCenter().x(),co1.getCenter().x());
        assertNotEquals(c1.getCenter().y(),co1.getCenter().y());

        c1.rotate(cen,-30);
        c2.rotate(cen,-30);

        assertEquals(c2.getCenter().x(),co.getCenter().x(),Ex2_Const.EPS);
        assertEquals(c2.getCenter().y(),co.getCenter().y(),Ex2_Const.EPS);

        assertEquals(c1.getCenter().x(),co1.getCenter().x(),Ex2_Const.EPS);
        assertEquals(c1.getCenter().y(),co1.getCenter().y(),Ex2_Const.EPS);

    }
}