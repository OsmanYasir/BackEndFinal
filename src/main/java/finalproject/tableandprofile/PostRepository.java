package finalproject.tableandprofile;

import finalproject.tableandprofile.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    @Query(value = "Select * from post p where p.title like %:keyword%", nativeQuery = true)
    public List<Post> findByKeyword(@Param("keyword") String keyword);
}
