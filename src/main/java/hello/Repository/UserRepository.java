package hello.Repository;

import hello.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findAll();
    List<User> findByName(String name);
    List<User> findByNameLike(String name);
    User findById(int id);



//    @Modifying
//    @Query("UPDATE User u SET u.name = :name and u.email = :email WHERE u.id = :id")
//    int updatNameAndEmail(@Param("id") int id,@Param("name")String name, @Param("email") String email);

}
