package tykkidream.hellojava.javarmi.lession03.client;

import tykkidream.hellojava.javarmi.lession03.api.FileService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Tykkidream on 2015/11/1.
 */
public class DemoClient {
    private static String ip = "127.0.0.1:1099";
    private static String filename = "demo.txt";
    private static String savepath = "";

    private static String rmiAddress = null;

    public static void main(String[] args) {
        if (args.length == 3) {
            ip = args[0];
            filename = args[1];
            savepath = args[2];
            // System.exit(0);
        }

        System.out.println("第一个参数，RMI服务的IP地址：" + ip);
        System.out.println("第二个参数，要下载的文件名：" + filename);
        System.out.println("第三个参数，要文件保存位置：" + savepath);

        rmiAddress = "rmi://" + ip + "/file";

        try {
            FileService service = (FileService) Naming.lookup(rmiAddress);

            // 下载文件
            byte[] filedata = service.downloadFile(filename);

            if(filedata == null){
                System.out.println("错误：文件不存在！");
                System.exit(0);
            }

            // 开始保存文件
            File file = new File(args[2]);

            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath()));
            output.write(filedata, 0, filedata.length);
            output.flush();
            output.close();

            System.out.println("消息：文件保存完毕！");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
