package example.person;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
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
        String host = (args.length > 0) ? args[0] : null;
        int port = 5678;
        if (host == null) {
            System.out.println("Uso: java PersonServer <ip-servidor>");
            return;
        }
        try {
            LocateRegistry.createRegistry(port);
            PersonServer server = new PersonServer();
            String url = "rmi://" + host + ":" + port + "/PersonService";
            Naming.rebind(url, server);
            System.out.println("PersonServer ready at " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
