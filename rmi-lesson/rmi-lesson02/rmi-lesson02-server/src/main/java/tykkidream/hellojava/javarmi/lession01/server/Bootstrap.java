package tykkidream.hellojava.javarmi.lession01.server;

import tykkidream.hellojava.javarmi.lession01.api.DemoService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Tykkidream on 2015/11/1.
 */
public class Bootstrap {
    public static void main(String[] args) {
        try {
            DemoService service = new DemoServiceImpl();

            //加上此程序，就可以不要在控制台上开启RMI的注册程序，1099是RMI服务监视的默认端口
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://127.0.0.1:1099/demo", service);

            System.out.print("启动完成");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
