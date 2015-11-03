package tykkidream.hellojava.javarmi.lession03.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Tykkidream on 2015/11/1.
 *
 * 定义一个远程接口，必须继承Remote接口。
 */
public interface FileService extends Remote {
    /**
     * 数据对象要进行远程传输，所以必须实现Serializable。
     * 而远程调用的方法必须抛出RemoteException异常。
     *
     * @param fileName
     * @return
     * @throws RemoteException
     */
    byte[] downloadFile(String fileName) throws RemoteException;
}
