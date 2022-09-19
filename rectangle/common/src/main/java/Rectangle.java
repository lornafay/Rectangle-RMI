import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Rectangle extends Remote{
    public void setWidth(int newW) throws RemoteException;
    public void setHeight(int newH) throws RemoteException;
    public int getWidth() throws RemoteException;
    public int getHeight() throws RemoteException;
    public int getArea() throws RemoteException;
    public int getPerimeter() throws RemoteException;
    public String describe() throws RemoteException;
    public String[] draw() throws RemoteException;
    public void rotate() throws RemoteException;
    public void scale(int factor) throws RemoteException;


}
