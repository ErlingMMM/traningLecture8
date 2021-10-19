package no.kristiania;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.SQLException;

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
        return new Person();
    }

    private DataSource creatDataSource() {
        return null;
    }
}
