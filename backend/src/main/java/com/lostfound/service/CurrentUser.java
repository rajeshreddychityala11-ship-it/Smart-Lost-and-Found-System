package com.lostfound.service;
import com.lostfound.model.User; import com.lostfound.repository.UserRepository; import com.lostfound.security.TokenService;
import org.springframework.stereotype.Service; import org.springframework.web.context.request.*;
@Service
public class CurrentUser {
 private final TokenService tokens; private final UserRepository users;
 public CurrentUser(TokenService t,UserRepository u){tokens=t;users=u;}
 public User get(){ String h=((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("Authorization");
  if(h==null||!h.startsWith("Bearer ")) throw new IllegalArgumentException("Login required");
  Long id=tokens.userId(h.substring(7)); if(id==null)throw new IllegalArgumentException("Invalid or expired session");
  return users.findById(id).orElseThrow(()->new IllegalArgumentException("User not found"));
 }
 public boolean isAdmin(){return get().getRole().name().equals("ADMIN");}
}
