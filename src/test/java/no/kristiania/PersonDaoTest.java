package no.kristiania;

public class PersonDaoTest {


    @Test
    void shouldRetriveSavedPersonFromDatabase(){
        PersonDao dao = new PersonDao(creatDataSource());

        Person person = examplePerson();
        dao.save(person);

        assertThat(dao.retrive(person.getId))

        ;
    }
}
