package kau.DIBN.likeitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeItemRepository extends JpaRepository<LikeItem, Long> {


}
