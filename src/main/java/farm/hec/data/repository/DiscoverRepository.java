package farm.hec.data.repository;

import farm.hec.data.entity.Discover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscoverRepository extends JpaRepository<Discover, Long> {

    List<Discover> findAllByCategoryId(Integer categoryId);

}
