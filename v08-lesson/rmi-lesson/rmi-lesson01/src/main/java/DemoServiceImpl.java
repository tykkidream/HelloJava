import java.net.MalformedURLException;
import java.rmi.Naming;
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

    public static void main(String[] args) {
        try {
            DemoService service = new DemoServiceImpl();

            Naming.rebind("rmi://127.0.0.1:1099/demo", service);

            System.out.print("启动完成");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
