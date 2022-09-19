import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Rectangle extends Remote{
    public void rotate() throws RemoteException;
    public void scale(int factor) throws RemoteException;
}
