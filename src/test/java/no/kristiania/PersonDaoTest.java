package no.kristiania;

import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonDaoTest {

    private final PersonDao dao = new PersonDao(creatDataSource());




    @Test
    void shouldRetrieveSavedPersonFromDatabase() throws SQLException {
        Person person = examplePerson();
        dao.save(person);

        assertThat(dao.retrieve(person.getId()))
                .usingRecursiveComparison()
                .isEqualTo(person);
    }


    @Test
    void shouldListPeopleByLastName() throws SQLException {
        Person matchingPerson = examplePerson();
        matchingPerson.setLastName("Testperson");
        dao.save(matchingPerson);

        Person anotherMatchingPerson = examplePerson();
        anotherMatchingPerson.setLastName(matchingPerson.getLastName());
        dao.save(anotherMatchingPerson);

        Person nonMatchingPerson = examplePerson();
        dao.save(nonMatchingPerson);

        assertThat(dao.listByLastName(matchingPerson.getLastName()))
        .extracting(Person::getId)
        .contains(matchingPerson.getId(), anotherMatchingPerson.getId())
        .doesNotContain(nonMatchingPerson.getId());
    }

    private Person examplePerson() {
        Person person = new Person();
        person.setFirstName(pickOne("Johannes", "Juliett", "James", "Janet", "Janice"));
        person.setLastName(pickOne("Persson", "Olsen", "Johnsen", "Walker", "Gundersen"));
        return new Person();
    }

    private String pickOne(String ... alternatives) {
        return alternatives[new Random().nextInt(alternatives.length)];
    }

    private DataSource creatDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5432/person_db");
        dataSource.setUser("person_dbuser");
        dataSource.setPassword("99fy68_'f[Cvt%T\\6C");
        return dataSource;
    }



}
