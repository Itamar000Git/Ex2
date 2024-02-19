package ex2.gui;

import ex2.ex2.GUI_Shape_Collection;
import ex2.ex2.ShapeCollection;
import ex2.geo.Circle_2D;
import ex2.geo.Point_2D;
import ex2.geo.Segment_2D;
import ex2.geo.Triangle_2D;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Ex2Test {
    private Point_2D p1= new Point_2D(1,2);
    private Point_2D p2= new Point_2D(2,2);

    private Point_2D p3= new Point_2D(0,0);

    private Segment_2D s1=new Segment_2D(p1,p2);
    private Circle_2D c1 = new Circle_2D(p1,5);
    private Triangle_2D tr1=new Triangle_2D(p1,p2,p3);
    private Boolean f=true;
    private Color myColor1 = new Color(255,0,0);
    private Color myColor2 = new Color(0,0,255);
    private Color myColor3 = new Color(0,255,0);
    private GUI_Shape shape1 = new GUIShape(c1,f,myColor1,2);
    private GUI_Shape shape2 = new GUIShape(s1,f,myColor2,1);

    private GUI_Shape shape3 = new GUIShape(tr1,f,myColor3,3);


    private ShapeCollection col = new ShapeCollection();
    private GUI_Shape_Collection GuiCol= new ShapeCollection();



    @Test
    void initTset() {
        //init
    }

    @Test
    void show() {
        //set dimension size
    }

    @Test
    void getInstance() {

    }

    @Test
    void drawShapes() {
    }

    @Test
    void actionPerformed() {
    }

    @Test
    void mouseClicked() {

    }

    @Test
    void mouseRightClicked() {
    }

    @Test
    void mouseMoved() {
    }

    @Test
    void getShape_Collection() {
    }

    @Test
    void testShow() {
    }

    @Test
    void getInfo() {
    }
}