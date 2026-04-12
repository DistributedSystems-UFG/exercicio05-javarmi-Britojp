package example.hello;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public Server() {}
    
    private static void getUserIp() {
        try {
            String ip = java.net.InetAddress.getLocalHost().getHostAddress();
            System.out.println("Server IP: " + ip);
        } catch (Exception e) {
            System.err.println("Error getting server IP: " + e.toString());
            e.printStackTrace();
        }
    }


    public static void main(String args[]) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Registry registry = LocateRegistry.getRegistry("localhost");
            
            HelloImplem obj = new HelloImplem(5678);
            //Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 5678);

            // Bind the remote object's stub in the registry
            Naming.rebind("MyHello", obj);


            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
