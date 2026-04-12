package example.person;

import java.rmi.Naming;

public class PersonClient {
    public static void main(String[] args) {
        String name = null;
        int age = -1;
        String host = "localhost";
        for (int i = 0; i < args.length; i++) {
            if ("-n".equals(args[i]) && i + 1 < args.length) {
                name = args[++i];
            } else if ("-a".equals(args[i]) && i + 1 < args.length) {
                try {
                    age = Integer.parseInt(args[++i]);
                } catch (NumberFormatException e) {
                    System.err.println("Idade inválida: " + args[i]);
                    return;
                }
            } else if ("-h".equals(args[i]) && i + 1 < args.length) {
                host = args[++i];
            }
        }
        if (name == null || age < 0) {
            System.out.println("Uso: java PersonClient -n {nome} -a {idade} [-h {host}]");
            return;
        }
        try {
            String url = "rmi://" + host + ":5678/PersonService";
            PersonService service = (PersonService) Naming.lookup(url);
            Person p = service.createPerson(name, age);
            System.out.println("Received from server: " + p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
