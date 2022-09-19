import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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

    // width getter
    public double getWidth() {
        return width;
    }

    // height getter
    public double getHeight() {
        return height;
    }

    // width setter
    public void setWidth(int newW) {
        this.width = newW;
    }

    // height setter
    public void setHeight(int newH) {
        this.height = newH;
    }

    // area calculator
    public double getArea() {
        return width * height;
    }

    // perimeter calculator
    public double getPerimeter() {
        return 2 * (width + height);
    }

    // returns string representation of rectangle
    @Override
    public String toString() {
        String representation = "The rectangle has a width of " + width + " and a height of " + height;
        return representation;
    }

    // scales rectangle by chosen factor
    public void scale(int factor) {

        // only make change if factor greater than 0
        if (factor > 0) {
            // scale height and width by factor
            this.width = this.width * factor ;
            this.height = this.height * factor;
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

}
