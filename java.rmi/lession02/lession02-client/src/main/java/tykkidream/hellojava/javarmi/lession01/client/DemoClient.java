package tykkidream.hellojava.javarmi.lession01.client;

import tykkidream.hellojava.javarmi.lession01.api.DemoService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Tykkidream on 2015/11/1.
 */
public class DemoClient {
    public static void main(String[] args) {
        try {
            DemoService service = (DemoService) Naming.lookup("rmi://localhost:1099/demo");

            String message = service.sayHelloJava("hello java rmi");
            System.out.println(message);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
