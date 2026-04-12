package example.hello;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImplem extends UnicastRemoteObject implements Hello {

    public HelloImplem(int port) throws RemoteException {
        super(port);
    }

    public String sayHello() throws RemoteException {
        return "Hello, world!";
    }

    public int soma (int a, int b) throws RemoteException {
        return a + b;
    }

    public String getServerIP() throws RemoteException {
        String ip;
        try{
            ip = java.net.InetAddress.getLocalHost().getHostAddress();
    } catch (Exception e) {
            System.err.println("Error getting server IP: " + e.toString());
            e.printStackTrace();
            ip = "";
        }
        return ip;
    }

    public String getClientIP() throws RemoteException {
        String clientIP = "";
        try {
            clientIP = java.rmi.server.RemoteServer.getClientHost();
            System.out.println("Passou aqui");
        } catch (Exception e) {
            System.err.println("Error getting client IP: " + e.toString());
            e.printStackTrace();
        }
        return clientIP;
    }
}
