package demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class Person {

    private final Long id;

    private final String name;

    @JsonCreator
    public Person(@JsonProperty("id") Long id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Person person = (Person) other;
        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Person {id=" + this.id + ", name='" + this.name + "'}";
    }
}

@RestController
@RequestMapping("/api")
public class RemoteServiceController {
    private static final Map<Long, Map<String, Object>> PERSON_DATA;

    static {
        PERSON_DATA = new HashMap<>();
        addPerson(1L, "Amanda");
        addPerson(2L, "Brittany");
        addPerson(3L, "Christopher");
        addPerson(4L, "Elizabeth");
        addPerson(5L, "Hannah");
        addPerson(6L, "Joshua");
        addPerson(7L, "Kayla");
        addPerson(8L, "Lauren");
        addPerson(9L, "Matthew");
        addPerson(10L, "Megan");
    }

    private static void addPerson(Long id, String name) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", id);
        map.put("name", name);
        PERSON_DATA.put(id, map);
    }

    @GetMapping("/personsnew")
    public  Flux<Map> getPersons() {
        return Flux.fromIterable(PERSON_DATA.values());
    }

}
