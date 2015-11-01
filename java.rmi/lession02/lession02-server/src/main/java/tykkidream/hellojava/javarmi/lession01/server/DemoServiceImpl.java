package tykkidream.hellojava.javarmi.lession01.server;

import tykkidream.hellojava.javarmi.lession01.api.DemoService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Tykkidream on 2015/10/31.
 */
public class DemoServiceImpl extends UnicastRemoteObject implements DemoService {
    // 这个实现必须有一个显式的构造函数，并且要抛出一个RemoteException异常
    protected DemoServiceImpl() throws RemoteException {
        super();
    }

    public String sayHelloJava(String mesage) throws RemoteException {
        return "Hello " + mesage + " ^_^ ";
    }
}
