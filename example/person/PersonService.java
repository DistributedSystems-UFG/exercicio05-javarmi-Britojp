package example.person;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PersonService extends Remote {
    Person createPerson(String name, int age) throws RemoteException;
}
