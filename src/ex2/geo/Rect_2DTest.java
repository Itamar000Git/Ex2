package ex2.geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rect_2DTest {

    Point_2D p1=new Point_2D(0,2);
    Point_2D p2=new Point_2D(2,0);
    Point_2D p3=new Point_2D(0,0);
    Point_2D p4=new Point_2D(2,2);

    Rect_2D rec1=new Rect_2D(p1,p2);

    Point_2D p5=new Point_2D(1,4);
    Point_2D p6=new Point_2D(4,3);
    Point_2D p7=new Point_2D(1,3);
    Point_2D p8=new Point_2D(4,4);

    Rect_2D rec2=new Rect_2D(p5,p6);


    @Test
    void get_p1Test() {     //done

        Point_2D r1=rec1.get_p1();
        Point_2D r2=rec1.get_p2();
        Point_2D r3=rec1.get_p3();
        Point_2D r4=rec1.get_p4();

        assertEquals(r1,p1);
        assertEquals(r2,p2);
        assertEquals(r3,p3);
        assertEquals(r4,p4);

        }

    @Test
    void contains() {                           //done
        Point_2D c0=new Point_2D(1,1);
        Point_2D c1=new Point_2D(5,1);
        boolean a;
        a=rec1.contains(c0);
        assertTrue(a);

        a=rec1.contains(c1);
        assertFalse(a);
    }

    @Test
    void area() {
        double s=rec1.area(); //4
        assertEquals(s,4);

        s=rec2.area();//
        assertEquals(s,3);

    }

    @Test
    void perimeter() {
        double p=rec1.perimeter();
        assertEquals(p,8);

        p=rec2.perimeter();
        assertEquals(p,8);
    }

    @Test
    void translate() {
        Point_2D vec= new Point_2D(5,7);
        Point_2D vec2= new Point_2D(-5,-7);
        Rect_2D q1=new Rect_2D(rec1);
        Rect_2D q2=new Rect_2D(rec2);

        rec1.translate(vec);
        rec2.translate(vec);

        assertNotEquals(rec1.get_p1(),q1.get_p1());
        assertNotEquals(rec1.get_p2(),q1.get_p2());
        assertNotEquals(rec1.get_p3(),q1.get_p3());
        assertNotEquals(rec1.get_p4(),q1.get_p4());

        assertNotEquals(rec2.get_p1(),q2.get_p1());
        assertNotEquals(rec2.get_p2(),q2.get_p2());
        assertNotEquals(rec2.get_p3(),q2.get_p3());
        assertNotEquals(rec2.get_p4(),q2.get_p4());

        rec1.translate(vec2);
        rec2.translate(vec2);
        assertEquals(rec1.get_p1(),q1.get_p1());
        assertEquals(rec1.get_p2(),q1.get_p2());
        assertEquals(rec1.get_p3(),q1.get_p3());
        assertEquals(rec1.get_p4(),q1.get_p4());

        assertEquals(rec2.get_p1(),q2.get_p1());
        assertEquals(rec2.get_p2(),q2.get_p2());
        assertEquals(rec2.get_p3(),q2.get_p3());
        assertEquals(rec2.get_p4(),q2.get_p4());

    }

    @Test
    void copy() {

        Rect_2D r1=(Rect_2D)rec1.copy();
        assertEquals(r1.get_p1(),rec1.get_p1());
        assertEquals(r1.get_p2(),rec1.get_p2());
        assertEquals(r1.get_p3(),rec1.get_p3());
        assertEquals(r1.get_p4(),rec1.get_p4());

        r1=(Rect_2D) rec2.copy();
        assertEquals(r1.get_p1(),rec2.get_p1());
        assertEquals(r1.get_p2(),rec2.get_p2());
        assertEquals(r1.get_p3(),rec2.get_p3());
        assertEquals(r1.get_p4(),rec2.get_p4());

    }

    @Test
    void scale() {
        double ratio1=(90.0/100), ratio2=(100.0/90);
        double a1,a2;
        Rect_2D co=(Rect_2D)rec1.copy();
        Point_2D p=new Point_2D(1,1);

        a1=rec1.area();
        rec1.scale(p,ratio1);

        assertNotEquals(a1,rec1.area());

        rec1.scale(p,ratio2);

        assertEquals(a1,rec1.area());
    }

    @Test
    void rotate() {
    }
}