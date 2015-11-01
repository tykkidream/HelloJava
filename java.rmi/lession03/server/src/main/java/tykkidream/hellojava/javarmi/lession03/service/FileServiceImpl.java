package tykkidream.hellojava.javarmi.lession03.service;

import tykkidream.hellojava.javarmi.lession03.api.FileService;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Tykkidream on 2015/11/1.
 */
public class FileServiceImpl extends UnicastRemoteObject implements FileService {

    public FileServiceImpl()  throws RemoteException{
        super();
    }

    public byte[] downloadFile(String fileName) throws RemoteException {
        System.out.println("消息：收到了来自远程的请求，请求的文件为" + fileName);

        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("消息：不存在名为" + fileName + "的文件！");
            return null;
        }

        byte buffer[] = new byte[(int) file.length()];

        int size = buffer.length;

        System.out.println("消息：下载文件的大小为" + size + "b");

        //限制文件大小不能超过10M，文件太大可能导制内存溢出！
        if (size > 1024 * 1024 * 10) {
            throw new RemoteException("错误：这个文件太大，不能下载！");
        }

        BufferedInputStream input = null;
        try {
            input = new BufferedInputStream(new FileInputStream(fileName));

            input.read(buffer, 0, buffer.length);

            input.close();

            System.out.println("消息：文件已经被成功下载！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer;
    }
}
