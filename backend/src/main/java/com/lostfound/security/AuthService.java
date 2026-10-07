package com.lostfound.security;
import com.lostfound.model.User;
import com.lostfound.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
 private final UserRepository users; private final TokenService tokens; private final BCryptPasswordEncoder enc=new BCryptPasswordEncoder();
 public AuthService(UserRepository u,TokenService t){users=u;tokens=t;}
 public User register(String name,String email,String password){
  if(users.existsByEmailIgnoreCase(email)) throw new IllegalArgumentException("Email already registered");
  User u=new User();u.setName(name.trim());u.setEmail(email.trim().toLowerCase());u.setPassword(enc.encode(password));return users.save(u);
 }
 public String login(String email,String password){
  User u=users.findByEmailIgnoreCase(email).orElseThrow(()->new IllegalArgumentException("Invalid email or password"));
  if(!enc.matches(password,u.getPassword())) throw new IllegalArgumentException("Invalid email or password");
  return tokens.issue(u);
 }
}
