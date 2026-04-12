package example.person;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class PersonServer extends UnicastRemoteObject implements PersonService {
    protected PersonServer(int port) throws RemoteException {
        super(port);
    }

    @Override
    public Person createPerson(String name, int age) throws RemoteException {
        Person p = new Person(name, age);
        System.out.println("Pessoa criada: " + p);
        return p;
    }

    public static void main(String[] args) {
        try {
            String hostname = System.getProperty("java.rmi.server.hostname");
            if (hostname != null) {
                System.err.println("Binding RMI server to hostname: " + hostname);
            }
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            PersonServer server = new PersonServer(5678);
            Naming.rebind("PersonService", server);
            System.err.println("PersonServer ready");
        } catch (Exception e) {
            System.err.println("PersonServer exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
