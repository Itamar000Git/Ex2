package ex2.geo;

import ex2.ex2.ShapeCollection;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ShapeCompTest {


    private Point_2D p1= new Point_2D(1,2);
    private Point_2D p2= new Point_2D(2,2);
    private Segment_2D s1=new Segment_2D(p1,p2);
    private Circle_2D c1 = new Circle_2D(p1,5);
    private Boolean f=true;
    private Color myColor1 = new Color(255,0,0);
    private Color myColor2 = new Color(0,0,255);
    private GUI_Shape shape1 = new GUIShape(c1,f,myColor1,2);
    private GUI_Shape shape2 = new GUIShape(s1,f,myColor2,1);


    ShapeCollection col = new ShapeCollection();


    @Test           //done
    void compareTest() {
        String[] strCol1,strCol2;
        col.add(shape1);
        col.add(shape2);

        String strCol = col.toString();
        strCol1= strCol.split(",");

        assertEquals(strCol1[6],"Circle_2D");   //first in
        System.out.println(strCol);

         col.sort(ShapeComp.CompByArea);        //by area

        strCol = col.toString();
        strCol2= strCol.split(",");

        System.out.println(strCol);
        assertEquals(strCol2[6],"Segment_2D");     //segment area=0

        col.sort(ShapeComp.CompByAntiArea);
        assertEquals(strCol1[6],"Circle_2D");       //circle area>0

        col.sort(ShapeComp.CompByAntiPerimeter);
        assertEquals(strCol2[6],"Segment_2D");

        col.sort(ShapeComp.CompByPerimeter);
        assertEquals(strCol1[6],"Circle_2D");

        col.sort(ShapeComp.CompByAntiToString);
        assertEquals(strCol2[6],"Segment_2D");

        col.sort(ShapeComp.CompByToString);
        assertEquals(strCol1[6],"Circle_2D");

        col.sort(ShapeComp.CompByTag);
        assertEquals(strCol2[6],"Segment_2D");

        col.sort(ShapeComp.CompByAntiTag);
        assertEquals(strCol1[6],"Circle_2D");
    }
}