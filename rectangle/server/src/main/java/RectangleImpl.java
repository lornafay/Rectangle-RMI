import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RectangleImpl implements Rectangle {

    public static void main (String[] args){
        try {
            // create a remote rectangle object using the RMI registry
            Registry registry = LocateRegistry.createRegistry(1099);
            Rectangle rectangle = (Rectangle) UnicastRemoteObject.exportObject(new RectangleImpl(), 0);
            registry.bind("rectangle", rectangle);
            while(true);

        } catch (AccessException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // declare instance variables
    private int width;
    private int height;

    // no-arg constructor that uses default measurements
    public RectangleImpl() {
        this.width = 2;
        this.height = 1;
    }

//    // constructor using user-assigned measurements
//    public RectangleImpl(int w, int h) {
//        this.width = w;
//        this.height = h;
//    }

    // setters and getters for width and height
    public int getWidth() {
        return width;
    }

    public int getHeight() { return height; }

    public void setWidth(int newW) {
        this.width = newW;
    }

    public void setHeight(int newH) {
        this.height = newH;
    }

    // area and perimeter getters
    public int getArea() {
        return width * height;
    }

    public int getPerimeter() {
        return 2 * (width + height);
    }

    // returns string representation of rectangle
    public String describe() {
        return "The rectangle has a height of " + this.height + " and a width of " + this.width;
    }

    // scales rectangle by chosen factor
    public void scale(int factor) {
        // only scale if factor greater than 0 (would like to add downward scaling later)
        if (factor > 0) {
            this.width = this.width * factor ;
            this.height = this.height * factor;
        } else {
            System.out.println("Invalid scaling value. Must be greater than 0.");
        }
    }

    // rotate rectangle by 90 degrees
    public void rotate() {
        // set temporary placeholder to hold onto the value of width
        int temporaryWidth = this.width;

        // reassign instance variables
        this.width = this.height;
        this.height = temporaryWidth;
    }

    // the following string repeating functions found at answer below by user Andi
    // https://stackoverflow.com/questions/2255500/can-i-multiply-strings-in-java-to-repeat-sequences
    public static String repeat(int count, String with) {
        return new String(new char[count]).replace("\0", with);
    }

    public static String repeat(int count) {
        return repeat(count, " ");
    }

    // draws the rectangle in stdout using "o o o" and "o   o" type strings
    // opting for 'o' chars as it is more consistent for rotation
    public String[] draw() {

        // the string will be printed line by line to draw the rectangle so will be
        // stored as a string array
        int stringCount = this.height + 3;
        String[] lines = new String[stringCount];

        // first line will always be " --- " style
        lines[0] = "Drawing rectangle:";
        lines[1] = "o " + repeat(this.width, " o ") + " o";
        // loop through rest of the lines to make "o   o" until we reach the bottom and make another "o o o"
        for (int i = 2; i < stringCount; i++){
            // check for last line or not
            if (i!=stringCount-1){
                lines[i] = "o " + repeat(this.width * 3) + " o";
            } else {
                lines[i] = "o " + repeat(this.width, " o ") + " o";
            }
        }
        return lines;
    }

}
