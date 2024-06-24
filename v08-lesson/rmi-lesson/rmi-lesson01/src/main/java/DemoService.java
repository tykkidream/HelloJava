import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Tykkidream on 2015/10/31.
 */
public interface DemoService extends Remote {
    String sayHelloJava(String mesage) throws RemoteException;
}
