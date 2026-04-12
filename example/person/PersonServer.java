package example.person;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class PersonServer extends UnicastRemoteObject implements PersonService {
    protected PersonServer() throws RemoteException {
        super();
    }

    @Override
    public Person createPerson(String name, int age) throws RemoteException {
        Person p = new Person(name, age);
        System.out.println("Pessoa criada: " + p);
        return p;
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Registry registry = LocateRegistry.getRegistry();
            PersonServer server = new PersonServer();
            Naming.rebind("PersonService", server);
            System.err.println("PersonServer ready");
        } catch (Exception e) {
            System.err.println("PersonServer exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
