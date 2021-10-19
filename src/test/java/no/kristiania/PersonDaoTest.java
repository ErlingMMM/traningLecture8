package no.kristiania;

import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonDaoTest {


    @Test
    void shouldRetrieveSavedPersonFromDatabase() throws SQLException {
        PersonDao dao = new PersonDao(creatDataSource());

        Person person = examplePerson();
        dao.save(person);

        assertThat(dao.retrieve(person.getId()))
                .usingRecursiveComparison()
                .isEqualTo(person)
        ;
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
