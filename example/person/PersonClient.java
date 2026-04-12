package example.person;

import java.rmi.Naming;

public class PersonClient {
    public static void main(String[] args) {
        String name = null;
        int age = -1;
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
            }
        }
        if (name == null || age < 0) {
            System.out.println("Uso: java PersonClient -n {nome} -a {idade}");
            return;
        }
        try {
            PersonService service = (PersonService) Naming.lookup("rmi://localhost/PersonService");
            Person p = service.createPerson(name, age);
            System.out.println("Received from server: " + p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
