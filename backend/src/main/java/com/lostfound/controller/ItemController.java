package com.lostfound.controller;
import com.lostfound.dto.ItemRequest; import com.lostfound.model.*; import com.lostfound.repository.*;
import com.lostfound.service.CurrentUser; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/items")
public class ItemController{
 private final ItemRepository items; private final CurrentUser current; private final UserRepository users;
 public ItemController(ItemRepository i,CurrentUser c,UserRepository u){items=i;current=c;users=u;}
 @GetMapping public List<Item> search(@RequestParam(defaultValue="") String q,@RequestParam(required=false) ItemType type){return items.search(q==null?"":q,type);}
 @GetMapping("/{id}") public Item one(@PathVariable Long id){return items.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<?> create(@Valid @RequestBody ItemRequest r){
  User u=current.get(); Item i=new Item(); fill(i,r);i.setReportedBy(u);return ResponseEntity.ok(items.save(i));
 }
 @PutMapping("/{id}") public Item update(@PathVariable Long id,@Valid @RequestBody ItemRequest r){
  Item i=items.findById(id).orElseThrow(); User u=current.get();
  if(!i.getReportedBy().getId().equals(u.getId())&&!current.isAdmin())throw new IllegalArgumentException("Not allowed");
  fill(i,r);return items.save(i);
 }
 @DeleteMapping("/{id}") public Map<String,String> delete(@PathVariable Long id){
  Item i=items.findById(id).orElseThrow();User u=current.get();
  if(!i.getReportedBy().getId().equals(u.getId())&&!current.isAdmin())throw new IllegalArgumentException("Not allowed");
  i.setStatus(ItemStatus.REMOVED);items.save(i);return Map.of("message","Report removed");
 }
 @PatchMapping("/{id}/status") public Item status(@PathVariable Long id,@RequestParam ItemStatus status){
  if(!current.isAdmin())throw new IllegalArgumentException("Admin access required");Item i=items.findById(id).orElseThrow();i.setStatus(status);return items.save(i);
 }
 private void fill(Item i,ItemRequest r){i.setName(r.name());i.setDescription(r.description());i.setLocation(r.location());i.setDate(r.date());i.setType(r.type());i.setCategory(r.category());i.setContact(r.contact());i.setImageUrl(r.imageUrl());}
}
