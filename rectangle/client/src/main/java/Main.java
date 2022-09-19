import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args){
        try {
            Registry registry = LocateRegistry.getRegistry();
            Rectangle rectangle = (Rectangle) registry.lookup("rectangle");

            // now carrying out a sequence of tasks to demonstrate remote method invocation of the rectangle class
            printDescription(rectangle);
            System.out.println("Setting dimensions...");
            rectangle.setHeight(4);
            rectangle.setWidth(2);
            printDescription(rectangle);
            System.out.println("Rotating...");
            rectangle.rotate();
            printDescription(rectangle);
            System.out.println("Scaling...");
            rectangle.scale(2);
            printDescription(rectangle);
            printDrawing(rectangle);
            System.out.println("Rotating...");
            rectangle.rotate();
            printDescription(rectangle);
            printDrawing(rectangle);
            System.out.println("Scaling...");
            rectangle.scale(2);
            printDrawing(rectangle);
            System.out.println("Rotating...");
            rectangle.rotate();
            printDrawing(rectangle);


        } catch (AccessException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static void printDescription(Rectangle rectangle){
        try {
            System.out.println("String representation: " + rectangle.describe());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // take the array returned by the draw() method and create a printed representation
    private static void printDrawing(Rectangle rectangle){
        try {
            String[] arrLines = rectangle.draw();
            for (int i = 0; i < arrLines.length; i++){
                System.out.println(arrLines[i]);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
