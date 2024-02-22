package ex2.geo;

import ex2.ex2.Ex2_Const;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;


class Segment_2DTest {
    private Segment_2D s1=new Segment_2D(Point_2D.ORIGIN,new Point_2D(0,1));
    private Point_2D p1=new Point_2D(1,0);
    private Point_2D p2=new Point_2D(5,3);
    private Segment_2D s2=new Segment_2D(p1,p2);




    @org.junit.jupiter.api.Test
    void containsTest() {

        Point_2D q0= new Point_2D(0,0.4);
        boolean b1=s1.contains(q0);
        assertTrue(b1);

        q0= new Point_2D(0,1.3);
        b1=s1.contains(q0);
        assertFalse(b1);

        q0= new Point_2D(3,1.5);
        b1=s2.contains(q0);
        assertTrue(b1);

        q0= new Point_2D(2.8,1.5);
        b1=s2.contains(q0);
        assertFalse(b1);
    }

    @org.junit.jupiter.api.Test
    void perimeterTest() {
        double p=s1.perimeter();//2
        assertEquals(p,2, Ex2_Const.EPS);

         p=s2.perimeter();//10
        assertEquals(p,10, Ex2_Const.EPS);
    }

    @org.junit.jupiter.api.Test
    void translateTest() {
        Point_2D vec= new Point_2D(5,7);
        Point_2D vec2= new Point_2D(-5,-7);
        Segment_2D q0= new Segment_2D(s2);

        s2.translate(vec);                          //after translate
        assertNotEquals(s2.get_p1(),q0.get_p1());
        assertNotEquals(s2.get_p2(),q0.get_p2());

        s2.translate(vec2);                         //after translate back
        assertEquals(s2.get_p1(),q0.get_p1());
        assertEquals(s2.get_p2(),q0.get_p2());

    }

    @org.junit.jupiter.api.Test
    void copy() {

        Segment_2D co= (Segment_2D) s1.copy(); //copy returns "GeoShape" that need to cast as segment
        assertEquals(s1.get_p1(),co.get_p1());
        assertEquals(s1.get_p2(),co.get_p2());

        co= (Segment_2D) s2.copy();
        assertEquals(s2.get_p1(),co.get_p1());
        assertEquals(s2.get_p2(),co.get_p2());

    }

    @org.junit.jupiter.api.Test
    void scaleTest() {
        double sc= (double) 90 /100, sc1= (double) 100 /90;
        double d1,d2;

        Point_2D ce= new Point_2D(0.5,0.5);

       d1= s1.get_p1().distance(s1.get_p2());

        s1.scale(ce,sc);
       d2= s1.get_p1().distance(s1.get_p2());

        assertNotEquals(d1,d2);
        s1.scale(ce,sc1);

        d2= s1.get_p1().distance(s1.get_p2());
        assertEquals(d1,d2);
    }

    @org.junit.jupiter.api.Test
    void rotate() {
        Point_2D ce= new Point_2D(8,0.5);

        Segment_2D co= (Segment_2D) s1.copy();

        s1.rotate(ce,30);
        assertNotEquals(s1.get_p1().x(),co.get_p1().x());
        assertNotEquals(s1.get_p1().y(),co.get_p1().y());
        assertNotEquals(s1.get_p2().x(),co.get_p2().x());
        assertNotEquals(s1.get_p2().y(),co.get_p2().y());

        s1.rotate(ce,-30);

        assertEquals(s1.get_p1().x(),co.get_p1().x(),Ex2_Const.EPS);
        assertEquals(s1.get_p1().y(),co.get_p1().y(),Ex2_Const.EPS);
        assertEquals(s1.get_p2().x(),co.get_p2().x(),Ex2_Const.EPS);
        assertEquals(s1.get_p2().y(),co.get_p2().y(),Ex2_Const.EPS);
    }

    @org.junit.jupiter.api.Test
    void toStringTest(){
        System.out.println(s1.toString());

        String a=s1.toString();
        String b="0.0,0.0,0.0,1.0";

        assertTrue(a.equals(b));
    }
    @org.junit.jupiter.api.Test
    void equalsTest(){
        Segment_2D co =(Segment_2D) s2.copy();

        assertTrue(s2.equals(co));

    }



}