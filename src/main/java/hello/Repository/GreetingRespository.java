package hello.Repository;

import hello.Entity.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GreetingRespository extends JpaRepository<Greeting,Integer> {

}
