package finalproject.tableandprofile;

import finalproject.tableandprofile.model.Users1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users1,Integer> {
    Users1  findByUsername(String username);
}
