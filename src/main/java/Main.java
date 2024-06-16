import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Person p = new Person("John Doe", 30);
        String filename = "person.ser";

        try {
            Person.serialize(p, filename);

            Person deserializedPerson = Person.deserialize(filename);
            System.out.println("Deserialized Person: " + deserializedPerson.getName() + ", " + deserializedPerson.getAge());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");
        EntityManager em = emf.createEntityManager();

        PersonRepository repository = new PersonRepository(em);

        repository.addPerson(new Person("John Doe", 30));

        repository.updatePerson(1L, "Jane Doe", 25);

        repository.deletePerson(1L);

        em.close();
        emf.close();
    }
}
