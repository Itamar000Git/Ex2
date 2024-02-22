package ex2.ex2;
import ex2.geo.Circle_2D;
import ex2.geo.Point_2D;
import ex2.gui.Ex2;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;

import ex2.geo.*;
import ex2.gui.Ex2;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShapeCollectionTest {
    private Point_2D p1= new Point_2D(5,5);
    private Point_2D p2= new Point_2D(2,1);
    private Point_2D p3= new Point_2D(3,3);
    private Segment_2D s1=new Segment_2D(p1,p2);
    private Circle_2D c1 = new Circle_2D(p1,2);
    private Triangle_2D tr1=new Triangle_2D(p1,p2,p3);
    private Boolean f=true;
    private Color myColor1 = new Color(255,0,0);
    private Color myColor2 = new Color(0,0,255);
    private Color myColor3 = new Color(0,255,0);
    private GUI_Shape shape1 = new GUIShape(c1,false,myColor1,2);
    private GUI_Shape shape2 = new GUIShape(s1,f,myColor2,1);

    private GUI_Shape shape3 = new GUIShape(tr1,f,myColor3,3);


    ShapeCollection col = new ShapeCollection();




    @Test
    void get() {
        col.add(shape1);
        col.add(shape2);

        assertTrue(col.get(0).toString().equals(shape1.toString()));
        assertTrue(col.get(1).toString().equals(shape2.toString()));

    }

    @Test
    void size() {
        col.add(shape1);
        col.add(shape2);
        col.add(shape3);
        assertTrue(col.size()==3);
    }

    @Test
    void removeElementAt() {
        col.add(shape1);
        col.add(shape2);
        col.add(shape3);

        assertTrue(col.size()==3);

        col.removeElementAt(1);

        assertTrue(col.size()==2);

        assertTrue(col.get(0).toString().equals(shape1.toString()));
        assertTrue(col.get(1).toString().equals(shape3.toString()));

    }

    @Test
    void addAt() {
        col.add(shape1);
        col.add(shape2);

        assertTrue(col.get(1).toString().equals(shape2.toString()));

        col.addAt(shape3,1);

        assertFalse(col.get(1).toString().equals(shape2.toString()));
        assertTrue(col.get(1).toString().equals(shape3.toString()));
    }

    @Test
    void add() {
        col.add(shape1);
        col.add(shape2);
        col.add(shape3);

        assertTrue(col.get(0).toString().equals(shape1.toString()));
        assertTrue(col.get(1).toString().equals(shape2.toString()));
        assertTrue(col.get(2).toString().equals(shape3.toString()));
    }

    @Test
    void copy() {
        col.add(shape1);
        col.add(shape2);
        col.add(shape3);

        ShapeCollection copyCol =(ShapeCollection) col.copy();

        assertTrue(col.toString().equals(copyCol.toString()));

        col.add(shape3);
        assertFalse(col.toString().equals(copyCol.toString()));

    }

    @Test
    void sort() {
        ///already checked in ShapeCompTest
    }

    @Test
    void removeAll() {
        col.add(shape1);
        col.add(shape2);
        col.add(shape3);

        assertTrue(col.size()==3);
        col.removeAll();
        assertTrue(col.size()==0);
    }

    /**
     * please check the test separate.
     */
    @Test
    void save() {
        Ex2 ex2 = Ex2.getInstance();
        col.add(shape1);
        col.add(shape2);

        String file=shape1.toString()+"\n"+ shape2.toString();

       col.save(file);
        ex2.init(col);

        System.out.println(ex2.getInfo().toString());
        System.out.println(file.toString());

      for (int i=0;i<500;i++){
          ex2.show();
      }


    }

    @Test
    void modifyStringTest() {
        String file=shape1.toString();
        String  str=file;
        assertFalse(str.contains("1111"));
       str = col.modifyString("1111",file.split(","));
       assertTrue(str.contains("1111"));
    }

    @Test
    void colorEncodingTest() {
        int r= ShapeCollection.colorEncoding(myColor1);
        assertEquals(r,16711680);
    }

    /**
     * please check the test separate.
     */
    @Test
    void load() {
        Ex2 ex2 = Ex2.getInstance();
        String file = "a0.txt"; //make sure the file is your working directory.
        String[] str1 =file.split(",");

        col.load(file);

        ex2.init(col);
        String[] str2 = ex2.toString().split(",");

  for (int i=2;i<str1.length;i++){
      assertTrue(str1[i]==str2[i+2]);
  }
        for (int i=0;i<500;i++) {
            ex2.show();
        }

    }

    @Test
    void testToString() {
        col.add(shape1);
        col.add(shape2);
        col.add(shape3);

        System.out.println(col.toString());

       assertTrue(col.toString().equals("GUIShape,java.awt.Color[r=255,g=0,b=0],false,2,Circle_2D,5.0,5.0, 2.0GUIShape,java.awt.Color[r=0,g=0,b=255],true,1,Segment_2D,5.0,5.0,2.0,1.0GUIShape,java.awt.Color[r=0,g=255,b=0],true,3,Triangle_2D,5.0,5.0,2.0,1.0,3.0,3.0"));

    }
}