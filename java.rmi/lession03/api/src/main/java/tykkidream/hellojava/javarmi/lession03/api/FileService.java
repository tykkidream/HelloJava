package tykkidream.hellojava.javarmi.lession03.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Tykkidream on 2015/11/1.
 */
public interface FileService extends Remote {
    byte[] downloadFile(String fileName) throws RemoteException;
}
