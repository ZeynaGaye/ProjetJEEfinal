package ucad.sn.master2.repository;


import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucad.sn.master2.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {


}
