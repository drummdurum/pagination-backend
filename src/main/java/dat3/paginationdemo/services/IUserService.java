package dat3.paginationdemo.services;




import dat3.paginationdemo.entity.User;

import java.util.List;

public interface IUserService extends ICrudService<User,Long>{
    List<User> findByName(String name);
}
