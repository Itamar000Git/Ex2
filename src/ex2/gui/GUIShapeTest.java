package ex2.gui;

import ex2.ex2.ShapeCollection;
import ex2.geo.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GUIShapeTest {
    private Point_2D p1= new Point_2D(1,2);
    private Point_2D p2= new Point_2D(2,2);

    private Point_2D p3= new Point_2D(0,0);

    private Segment_2D s1=new Segment_2D(p1,p2);
    private Circle_2D c1 = new Circle_2D(p1,5);
    private Circle_2D c2 = new Circle_2D(p2,3);
    private Triangle_2D tr1=new Triangle_2D(p1,p2,p3);
    private Boolean f=true;
    private Boolean f1=false;
    private Color myColor1 = new Color(255,0,0);
    private Color myColor2 = new Color(0,0,255);
    private Color myColor3 = new Color(0,255,0);
    private GUI_Shape shape1 = new GUIShape(c1,f1,myColor1,2);
    private GUI_Shape shape2 = new GUIShape(s1,f,myColor2,1);

    private GUI_Shape shape3 = new GUIShape(tr1,f,myColor3,3);
    private GUI_Shape shape4 = new GUIShape(c2,f,myColor2,2);





    ShapeCollection col = new ShapeCollection();





    @Test
    void getShape() {
        col.add(shape1);
        col.add(shape2);
        col.add(shape3);
       assertTrue(shape1.getShape().toString().equals("1.0,2.0, 5.0"));

    }

    @Test
    void setShape() {

        //no need to test geo shape

    }

    @Test
    void isFilled() {
        assertTrue(shape2.isFilled());
        assertFalse(shape1.isFilled());
    }

    @Test
    void setFilled() {
        assertFalse(shape1.isFilled());
        shape1.setFilled(true);
        assertTrue(shape1.isFilled());

    }

    @Test
    void getColor() {
        Color co=shape1.getColor();
        Color co1=shape2.getColor();


        assertTrue(co.toString().equals("java.awt.Color[r=255,g=0,b=0]"));
        assertTrue(co1.toString().equals("java.awt.Color[r=0,g=0,b=255]"));


    }

    @Test
    void setColor() {
        Color co=shape1.getColor();
        Color co1=shape2.getColor();
        assertTrue(co.toString().equals("java.awt.Color[r=255,g=0,b=0]"));


        shape1.setColor(co1);
        co=shape1.getColor();
        assertTrue(co.toString().equals("java.awt.Color[r=0,g=0,b=255]"));

    }

    @Test
    void getTag() {
        assertEquals(shape1.getTag(),2);

    }

    @Test
    void setTag() {
        assertEquals(shape1.getTag(),2);
        shape1.setTag(3);
        assertEquals(shape1.getTag(),3);

    }

    @Test
    void copy() {
        GUIShape co =(GUIShape) shape1.copy();
        assertTrue(shape1.toString().equals(co.toString()));
    }

    @Test
    void testToString() {

        System.out.println(shape1.toString());
        assertTrue(shape1.toString().equals("GUIShape,java.awt.Color[r=255,g=0,b=0],false,2,Circle_2D,1.0,2.0, 5.0"));
    }

    @Test
    void isSelected() {
        //no need to test set by mouse clicks
    }

    @Test
    void setSelected() {
        //no need to test set by mouse clicks
    }
}