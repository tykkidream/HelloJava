import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Tykkidream on 2015/10/31.
 */
public class DemoServiceImpl extends UnicastRemoteObject implements DemoService {
    // ���ʵ�ֱ�����һ����ʽ�Ĺ��캯��������Ҫ�׳�һ��RemoteException�쳣
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

            System.out.print("�������");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
