package farm.hec.data.repository;

import farm.hec.data.entity.Discover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscoverRepository extends JpaRepository<Discover, Long> {

}
