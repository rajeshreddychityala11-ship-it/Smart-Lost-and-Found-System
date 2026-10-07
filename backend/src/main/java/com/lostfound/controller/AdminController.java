package com.lostfound.controller;
import com.lostfound.model.*; import com.lostfound.repository.*;
import com.lostfound.service.CurrentUser; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/admin")
public class AdminController{
 private final CurrentUser current;private final ItemRepository items;private final UserRepository users;
 public AdminController(CurrentUser c,ItemRepository i,UserRepository u){current=c;items=i;users=u;}
 @GetMapping("/dashboard") public Map<String,Object> dashboard(){
  if(!current.isAdmin())throw new IllegalArgumentException("Admin access required");
  return Map.of("users",users.count(),"items",items.count(),"active",items.countByStatus(ItemStatus.ACTIVE),
   "resolved",items.countByStatus(ItemStatus.RESOLVED),"removed",items.countByStatus(ItemStatus.REMOVED));
 }
 @GetMapping("/items") public List<Item> all(){if(!current.isAdmin())throw new IllegalArgumentException("Admin access required");return items.findAllByOrderByCreatedAtDesc();}
}
