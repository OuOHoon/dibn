package kau.DIBN.item;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findAllByCategory(String category);

    public List<Item> findByNameContaining(String name);
    public List<Item> findAllByNameContaining(String name);

}
