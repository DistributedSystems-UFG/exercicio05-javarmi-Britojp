package example.hello;

import java.rmi.Naming;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        System.out.println("Initiating client");
        
        String host = (args.length < 1) ? null : args[0];
        try {
            //Registry registry = LocateRegistry.getRegistry(host);
            //System.out.println("Registry has been located");
            //Hello stub = (Hello) registry.lookup("Hello");

            Hello stub = (Hello) Naming.lookup("rmi://" + host + "/MyHello"); 
            System.out.println("Found server");
            String response = stub.sayHello();
            System.out.println("Response: " + response);

            int result = stub.soma(100,1000);
            
            System.out.println("Response from soma: " + result);
            String ipServer = stub.getServerIP();
            System.out.println("Server IP: " + ipServer);

            String ipClient = stub.getClientIP();
            System.out.println("Client IP: " + ipClient);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
