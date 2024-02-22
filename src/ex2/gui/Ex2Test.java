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
    private Point_2D p1= new Point_2D(0.5,0.6);
    private Point_2D p2= new Point_2D(0.5,0.5);

    private Point_2D p3= new Point_2D(0.2,0.2);
    private Point_2D p4= new Point_2D(0.4,0.5);

    private Segment_2D s1=new Segment_2D(p4,p2);
    private Circle_2D c1 = new Circle_2D(p1,1.5);
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
    void drawShapes() {
        Ex2 ex2 = Ex2.getInstance();
        GuiCol.add(shape3); // Triangle - added first [0]
        GuiCol.add(shape2); // Segment - added second [1]
        ex2.init(GuiCol);

        for (int i=0;i<500;i++) {
            ex2.drawShapes();
        }


    }

    @Test
    void actionPerformed() {
        Ex2 ex2 = Ex2.getInstance();
        GuiCol.add(shape3); // Triangle - added first [0]
        GuiCol.add(shape2); // Segment - added second [1]
        ex2.init(GuiCol);
        String p3="ByArea", p4= "";


        for (int i=0;i<500;i++){
        ex2.actionPerformed(p4);
        }


        System.out.println(ex2.getInfo());

        for (int i=0;i<500;i++){
            ex2.actionPerformed(p3);
        }

        System.out.println(ex2.getInfo());


    }

    @Test
    void getShape_Collection() {
        Ex2 ex2 = Ex2.getInstance();
        GuiCol.add(shape1);

        ex2.init(GuiCol);

        assertTrue(  shape1.equals(ex2.getShape_Collection().get(0)));
    }


    @Test
    void getInfo() {
        Ex2 ex2 = Ex2.getInstance();
        GuiCol.add(shape1);

        ex2.init(GuiCol);

        String str = ex2.getInfo().toString();
        String str1 = shape1.toString();


        System.out.println( str1);
        System.out.println(str);

    }
    @Test
    void save(){
        //checked in shape collection test
    }
    @Test
    void load(){
        //checked in shape collection test
    }
}