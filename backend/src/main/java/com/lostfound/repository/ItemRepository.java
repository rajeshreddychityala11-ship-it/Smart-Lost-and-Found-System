package com.lostfound.repository;
import com.lostfound.model.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface ItemRepository extends JpaRepository<Item,Long>{
 @Query("select i from Item i where i.status <> com.lostfound.model.ItemStatus.REMOVED and " +
 "(lower(i.name) like lower(concat('%',:q,'%')) or lower(i.description) like lower(concat('%',:q,'%')) " +
 "or lower(i.location) like lower(concat('%',:q,'%')) or lower(coalesce(i.category,'')) like lower(concat('%',:q,'%'))) " +
 "and (:type is null or i.type=:type) order by i.createdAt desc")
 List<Item> search(String q, ItemType type);
 List<Item> findAllByOrderByCreatedAtDesc();
 long countByStatus(ItemStatus status);
}
