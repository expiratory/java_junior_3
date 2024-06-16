import javax.persistence.EntityManager;

public class PersonRepository {
    private final EntityManager entityManager;

    public PersonRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addPerson(Person person) {
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();
    }

    public void updatePerson(Long id, String newName, int newAge) {
        entityManager.getTransaction().begin();
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            person.setName(newName);
            person.setAge(newAge);
        }
        entityManager.getTransaction().commit();
    }

    public void deletePerson(Long id) {
        entityManager.getTransaction().begin();
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            entityManager.remove(person);
        }
        entityManager.getTransaction().commit();
    }
}