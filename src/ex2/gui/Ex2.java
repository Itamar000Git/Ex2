package ex2.gui;

import ex2.ex2.Ex2_Const;
import ex2.ex2.GUI_Shape_Collection;
import ex2.ex2.ShapeCollection;
import ex2.geo.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * This class is a simple "interlayer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a
 * "Singleton-like" implementation.
 *
 * Notes:
 * 1. Make sure to add a detailed explanation of assignment 2 below:
 * 2. Your code will be run by our "main" based on the interfaces api given to you.
 *
 * Itamar Babai - 206847584
 *
 * Explanation:
 ***This program implements gui platform for geometric shapes. The shapes are Segment, Circle, Triangle,
 * Rectangle and Polygon. Every shape have few properties: color, size, full/empty. we can choose a specific shape or all the shapes together.
 *
 *
 * Detailed explanation:
 *** In every shape we implement few methods for that creat the wanted shape.
 * 1.Builders - for creating 2_D points and build the full shape (spacial builder is the rectangle,
 *              that builds by 2 points and parallel to x-axis and y-axis).
 *
 * 2.Contains - Tells if a point is inside the shape or not.
 *
 * For example in the polygon the method is:
 * a. Found point that is outside the polygon, we are looking for the max value of "x" and "y" and adding 1 for each.
 * b. We create segment between the suspect point to the outside point.
 * c. With help from method that finds the orientation and another method that finds if this segment cross one side of the polygon.
 * d. We count the number of crossing and if odd the point inside.
 *
 * 3.Area - Tells as the area value of the shape, we use the area for diced if a point is inside a shape (in Triangle and Rectangle),
 *          also for sorting the shaped by areas.
 *
 * 4.Perimeter - Tells as the perimeter of the shape, helps us to sort by perimeter.
 *
 * 5.Translate - Moves the shape by chosen vector.
 *
 * 6.Copy - Makes exact copy of the selected shape/s. helps as with testing.
 *
 * 7.Scale - Increasing / Decreasing shape area by 10%.
 *
 * 8.Rotate - Rotate the shape by chosen angle around chosen center.
 *
 * 9.ToString - Converts all the information to strings values for save , load and print.
 *
 *
 *** This part of the gui platform compares between shapes and sorting them by the following parameters:
 *
 * 1.Area / AntiArea: Sort the shapes by the size of their areas / the opposite.
 *
 * 2.Perimeter / AntiPerimeter: Sort the shapes by the size of their perimeters / the opposite.
 *
 * 3.ToString / AntiToString: Sort by string length / the opposite
 *
 * 4. Tag / AntiTag: Sort shapes by there tag number / the opposite.
 *
 *
 *** The last part of the program is to save and load shape collection from the gui:
 * 1. Clear - Delete all shapes from the gui platform.
 *
 * 2. Remove - Delete selected shapes from the gui platform.
 *
 * 2. Save - Convert the shape collection (row by row - shape by shape) to string. We need to replace the color string by color encoding.
 *          After that write the string splits by "," to text file, ready for load.
 *
 * 3. Load -Loads text file with string represent geometric shapes,
 *          sends row by row (shape by shape) to different function that convert every word to shape property.
 *
 * @author boaz.ben-moshe
 */
public class Ex2 implements Ex2_GUI {
    private GUI_Shape_Collection _shapes = new ShapeCollection();
    private GUI_Shape _gs;
    private Polygon_2D _pp;
    private Color _color = Color.blue;
    private boolean _fill = false;
    private String _mode = "";
    private Point_2D _p1;

    private static Ex2 _winEx2 = null;

    private Ex2() {
        init(null);
    }

    public void init(GUI_Shape_Collection s) {
        if (s == null) {
            _shapes = new ShapeCollection();
        } else {
            _shapes = s;
        }
        GUI_Shape _gs = null;
        Polygon_2D _pp = null;
        _color = Color.blue;
        _fill = false;
        _mode = "";
        Point_2D _p1 = null;
    }

    public void show(double d) {
        StdDraw_Ex2.setScale(0, d);
        StdDraw_Ex2.show();
        drawShapes();
    }

    public static Ex2 getInstance() {
        if (_winEx2 == null) {
            _winEx2 = new Ex2();
        }
        return _winEx2;
    }

    /* private static void drawGrid(int x, int y, int delta) {
         for(int i=0;i<x;i+=delta) {
             StdDraw_Ex2.line(i, 0, i, y);
         }
         for(int i=0;i<y;i+=delta) {
             StdDraw_Ex2.line(0, i, x, i);
         }
    } */
    public void drawShapes() {
        StdDraw_Ex2.clear();
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape sh = _shapes.get(i);

            drawShape(sh);
        }
        if (_gs != null) {
            drawShape(_gs);
        }
        StdDraw_Ex2.show();
    }

    private static void drawShape(GUI_Shape g) {
        StdDraw_Ex2.setPenColor(g.getColor());
        if (g.isSelected()) {
            StdDraw_Ex2.setPenColor(Color.gray);
        }
        GeoShape gs = g.getShape();
        boolean isFill = g.isFilled();
        if (gs instanceof Circle_2D) {
            Circle_2D c = (Circle_2D) gs;
            Point_2D cen = c.getCenter();
            double rad = c.getRadius();
            if (isFill) {
                StdDraw_Ex2.filledCircle(cen.x(), cen.y(), rad);
            } else {
                StdDraw_Ex2.circle(cen.x(), cen.y(), rad);
            }
        }
        if (gs instanceof Segment_2D) {
            Segment_2D c = (Segment_2D) gs;
            Point_2D m0 = c.get_p1();
            Point_2D m1 = c.get_p2();
            StdDraw_Ex2.line(m0.x(), m0.y(), m1.x(), m1.y());

        }
        Point_2D[] ps = null;
        if (gs instanceof Polygon_2D) {
            ps = ((Polygon_2D) gs).getAllPoints();
        }

        if (gs instanceof Rect_2D) {
            ps = ((Rect_2D) gs).getAllPoints();
        }


        if (gs instanceof Triangle_2D) {                        //added
            ps = ((Triangle_2D) gs).getAllPoints();
            Point_2D m0 = ps[0];        //+
            Point_2D m1 = ps[1];        //+
            Point_2D m2 = ps[2];        //+
            StdDraw_Ex2.line(m0.x(), m0.y(), m1.x(), m1.y());        //+
            StdDraw_Ex2.line(m1.x(), m1.y(), m2.x(), m2.y());        //+
            StdDraw_Ex2.line(m2.x(), m2.y(), m0.x(), m0.y());        //+
        }
        if (ps != null) {
            double[] xx = new double[ps.length];
            double[] yy = new double[ps.length];
            for (int i = 0; i < xx.length; i++) {
                xx[i] = ps[i].x();
                yy[i] = ps[i].y();
            }
            if (isFill) {
                StdDraw_Ex2.filledPolygon(xx, yy);
            } else {
                StdDraw_Ex2.polygon(xx, yy);
            }

        }




    }

    private void setColor(Color c) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                s.setColor(c);
            }
        }
    }


    private void setFill() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                s.setFilled(_fill);
            }
        }
    }

    private void remove() {
        for (int i = _shapes.size() - 1; i >= 0; i--) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                _shapes.removeElementAt(i);
            }
        }
    }

    public void actionPerformed(String p) {
        _mode = p;
        if (p.equals("Blue")) {
            _color = Color.BLUE;
            setColor(_color);
        }
        if (p.equals("Red")) {
            _color = Color.RED;
            setColor(_color);
        }
        if (p.equals("Green")) {
            _color = Color.GREEN;
            setColor(_color);
        }
        if (p.equals("White")) {
            _color = Color.WHITE;
            setColor(_color);
        }
        if (p.equals("Black")) {
            _color = Color.BLACK;
            setColor(_color);
        }
        if (p.equals("Yellow")) {
            _color = Color.YELLOW;
            setColor(_color);
        }
        if (p.equals("Fill")) {
            _fill = true;
            setFill();
        }
        if (p.equals("Empty")) {
            _fill = false;
            setFill();
        }
        if (p.equals("Remove")) {
            remove();
        }
        if (p.equals("Save")) {
            save();
        }
        if (p.equals("Load")) {
            load();
        }
        if (p.equals("ByArea")) {
            _shapes.sort(ShapeComp.CompByArea);     //done
        }
        if (p.equals("ByAntiArea")) {                //done
            _shapes.sort(ShapeComp.CompByAntiArea);
        }
        if (p.equals("ByPerimeter")) {                //done
            _shapes.sort(ShapeComp.CompByPerimeter);
        }
        if (p.equals("ByAntiPerimeter")) {                //done
            _shapes.sort(ShapeComp.CompByAntiPerimeter);
        }
        if (p.equals("ByToString")) {                //done
            _shapes.sort(ShapeComp.CompByToString);
        }
        if (p.equals("ByAntiToString")) {                //done
            _shapes.sort(ShapeComp.CompByAntiToString);
        }
        if (p.equals("ByTag")) {                //done
            _shapes.sort(ShapeComp.CompByTag);
        }
        if (p.equals("ByAntiTag")) {                //done
            _shapes.sort(ShapeComp.CompByAntiTag);
        }

        if (p.equals("Clear")) {
            _shapes.removeAll();
        }
        if (p.equals("None")) {
            selectNone();
        }
        if (p.equals("All")) {
            selectAll();
        }
        if (p.equals("Anti")) {
            selectAnti();
        }
        if (p.equals("Info")) {
            System.out.println(getInfo());
        }

        drawShapes();

    }

    private void save() {                   //added try and catch
        FileDialog chooser = new FileDialog(StdDraw_Ex2.getFrame(), "Save to Text file", FileDialog.SAVE);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            try {
                _shapes.save(chooser.getDirectory() + File.separator + chooser.getFile() +","+ getInfo());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void load() {
        FileDialog chooser = new FileDialog(StdDraw_Ex2.getFrame(), "Load from Text file", FileDialog.LOAD);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            _shapes.load(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }

    public void mouseClicked(Point_2D p) {
        System.out.println("Mode: " + _mode + "  " + p);
        if (_mode.equals("Rect") || _mode.equals("Circle") || _mode.equals("Segment")) {
            if (_gs == null) {
                _p1 = new Point_2D(p);
            } else {
                if(_mode.equals("Segment")){ _gs.setTag(1);}
                if(_mode.equals("Circle")){ _gs.setTag(2);}
                if(_mode.equals("Rect")){ _gs.setTag(4);}
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;

            }
        }
        if (_mode.equals("Move")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                _p1 = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
                move();
                _p1 = null;
            }
        }
        if (_mode.equals("Copy")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                _p1 = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
                copy();
                _p1 = null;
            }
        }
        if (_mode.equals("Rotate")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                rotate(p);
                _p1 = null;
            }
        }


        if (_mode.equals("Polygon")) {
            if (_pp == null) {
                _pp = new Polygon_2D();
            }
            _p1 = new Point_2D(p);
            _pp.add(p);
        }

        if (_mode.equals("Triangle")) {
            if (_pp == null) {
                _pp = new Polygon_2D();
            }
            _p1 = new Point_2D(p);
            _pp.add(p);
            if (_pp.getAllPoints().length == 3) {

                Point_2D[] pp = _pp.getAllPoints();
                Triangle_2D tt = new Triangle_2D(pp[0], pp[1], pp[2]);
                GUI_Shape s = new GUIShape(tt, _fill, _color, 5);

                _shapes.add(s);
                _pp = null;
                _p1 = null;
                _gs = null;
            }
        }
        if (_mode.equals("Point")) {
            select(p);
        }
        if (_mode.equals("Scale_90%")) {
            scale(p, 0.9);
        }
        if (_mode.equals("Scale_110%")) {
            scale(p, 1.10);
        }
        drawShapes();
    }

    private void scale(Point_2D p, double ratio) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                g.scale(p, ratio);
            }
        }
    }

    private void select(Point_2D p) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (g != null && g.contains(p)) {
                s.setSelected(!s.isSelected());
            }
        }
    }

    private void move() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                g.translate(_p1);
            }
        }
    }

    private void copy() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                GUI_Shape s1 = s.copy();
                GeoShape g = s1.getShape();
                g.translate(_p1);
                _shapes.add(s1);
            }
        }
    }

    private void rotate(Point_2D ang) {
        double dx = ang.x() - _p1.x();
        double dy = ang.y() - _p1.y();
        double da = Math.atan2(dy, dx);
        da = Math.toDegrees(da);
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected()) {
                g.rotate(_p1, da);
            }
        }
    }

    private void selectAll() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            s.setSelected(true);
        }
    }

    //printInfo
    private void printInfo() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                System.out.println(i + ") " + s.toString());
            }
        }
    }

    private void selectNone() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            s.setSelected(false);
        }
    }

    private void selectAnti() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            s.setSelected(!s.isSelected());
        }
    }

    public void mouseRightClicked(Point_2D p) {
        if (_mode.equals("Polygon") && _pp != null) {
            GUIShape s = new GUIShape(_pp, _fill, _color, 0);
            _shapes.add(s);
        }
        _pp = null;
        _gs = null;
        _p1 = null;
        drawShapes();


    }

    public void mouseMoved(MouseEvent e) {
        if (_p1 != null) {
            double x1 = StdDraw_Ex2.mouseX();
            double y1 = StdDraw_Ex2.mouseY();
            GeoShape gs = null;
            //	System.out.println("M: "+x1+","+y1);
            Point_2D p = new Point_2D(x1, y1);
            if (_mode.equals("Circle")) {
                double r = _p1.distance(p);
                gs = new Circle_2D(_p1, r);
            }
            if (_mode.equals("Rect")) {
                gs=new Rect_2D(_p1,p);
                //gs = new Rect_2D(_p1, p);
            }
            if (_mode.equals("Segment")) {
                gs = new Segment_2D(_p1, p);
            }

            if (_mode.equals("Polygon") || _mode.equals("Triangle")) {
                if (_pp == null) {
                    _pp = new Polygon_2D();
                    _pp.add(_p1);
                }
                Polygon_2D gg = new Polygon_2D(_pp);
                gg.add(p);
                gs = gg;
            }
            _gs = new GUIShape(gs, false, Color.pink, 0);
            drawShapes();
        }
    }

    @Override
    public GUI_Shape_Collection getShape_Collection() {
        // TODO Auto-generated method stub
        return this._shapes;
    }

    @Override
    public void show() {
        show(Ex2_Const.DIM_SIZE);
    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        String ans = "";
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            ans += s.toString() + "\n";
            //	ans +=s.toString()+"  area: "+s.getShape().area()+"  per: "+s.getShape().perimeter()+"\n";
        }
        return ans;
    }
}
