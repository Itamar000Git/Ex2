package ex2.geo;

import ex2.ex2.Ex2_Const;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class Point_2DTest {

    private Point_2D p1 = new Point_2D(1.2,2.5);
    private Point_2D p2 = new Point_2D(0.8,0.5);
    private Point_2D p3 = new Point_2D(0,0.5);
    private Point_2D p4 = new Point_2D(1.2,8.5);


    @Test
    void x() {
        assertEquals(p1.x(),1.2);

    }

    @Test
    void y() {
        assertEquals(p1.y(),2.5);
    }

    @Test
    void ix() {
        assertEquals(p1.x(),1.2);
    }

    @Test
    void iy() {
        assertEquals(p1.y(),2.5);
    }

    @Test
    void add() {

        Point_2D a= new Point_2D(2,3);

        assertEquals(p1.add(p2),a);

    }

    @Test
    void testToString() {

        assertTrue(p1.toString().equals("1.2,2.5"));
    }

    @Test
    void distance() {

        assertEquals(p3.distance(),0.5, Ex2_Const.EPS);
    }

    @Test
    void testDistance() {
        assertEquals(p1.distance(p4),6);
    }

    @Test
    void testEquals() {
        Point_2D co=new Point_2D(p1);
        assertTrue(p1.equals(co));
        assertFalse(p1.equals(p2));

    }

    @Test
    void close2equals() {
        Point_2D e=new Point_2D((Ex2_Const.EPS/2),(Ex2_Const.EPS/2));
        Point_2D co=new Point_2D(p1);
        p1.add(e);
       assertTrue(p1.close2equals(co,Ex2_Const.EPS));
    }

    @Test
    void vector() {
        Point_2D tar = new Point_2D(0.2,0.5);
        Point_2D test = new Point_2D(-1,-8);
        assertEquals(p4.vector(tar),test);
    }

    @Test
    void move() {
        Point_2D vec = new Point_2D(0.2,0.5);
        Point_2D test = new Point_2D(1.4,9);
        p4.move(vec);
         assertEquals(p4,test);
    }

    @Test
    void scale() {
        Point_2D cen = new Point_2D(5,5);
        Double r1=100.0/90 , r2=90.0/100;
        Point_2D co=new Point_2D(p1);

        p1.scale(cen,r1);
        assertNotEquals(p1,co);

        p1.scale(cen,r2);

        assertEquals(p1.x(),co.x(),Ex2_Const.EPS);
        assertEquals(p1.y(),co.y(),Ex2_Const.EPS);
    }

    @Test
    void rotate() {
        Point_2D cen = new Point_2D(5,5);
        int r1=30, r2=-30;
        Point_2D co=new Point_2D(p1);

        p1.rotate(cen,r1);
        assertNotEquals(p1,co);

        p1.rotate(cen,r2);
        assertEquals(p1.x(),co.x(),Ex2_Const.EPS);
        assertEquals(p1.y(),co.y(),Ex2_Const.EPS);

    }
}