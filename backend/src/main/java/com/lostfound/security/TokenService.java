package com.lostfound.security;
import com.lostfound.model.User;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class TokenService {
 private final Map<String,Long> tokens=new ConcurrentHashMap<>();
 public String issue(User u){String t=UUID.randomUUID().toString(); tokens.put(t,u.getId()); return t;}
 public Long userId(String token){return token==null?null:tokens.get(token);}
 public void revoke(String token){if(token!=null)tokens.remove(token);}
}
