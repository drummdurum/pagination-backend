package dat3.paginationdemo.repositories;


import dat3.paginationdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUsername(String name);
    //List<User> findUserByPasswordContains(String passwordPart);
}
