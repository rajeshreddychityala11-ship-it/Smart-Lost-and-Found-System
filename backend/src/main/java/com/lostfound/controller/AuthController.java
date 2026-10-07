package com.lostfound.controller;
import com.lostfound.dto.*; import com.lostfound.model.User; import com.lostfound.security.*;
import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/auth")
public class AuthController{
 private final AuthService auth; private final TokenService tokens; private final com.lostfound.repository.UserRepository users;
 public AuthController(AuthService a,TokenService t,com.lostfound.repository.UserRepository u){auth=a;tokens=t;users=u;}
 @PostMapping("/register") public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest r){
  User u=auth.register(r.name(),r.email(),r.password()); return ResponseEntity.ok(Map.of("message","Registration successful","user",u.getName()));
 }
 @PostMapping("/login") public ResponseEntity<?> login(@Valid @RequestBody AuthRequest r){
  String token=auth.login(r.email(),r.password()); User u=users.findByEmailIgnoreCase(r.email()).orElseThrow();
  return ResponseEntity.ok(Map.of("token",token,"user",Map.of("id",u.getId(),"name",u.getName(),"email",u.getEmail(),"role",u.getRole().name())));
 }
 @PostMapping("/logout") public ResponseEntity<?> logout(@RequestHeader(value="Authorization",required=false) String h){
  if(h!=null&&h.startsWith("Bearer "))tokens.revoke(h.substring(7)); return ResponseEntity.ok(Map.of("message","Logged out"));
 }
}
