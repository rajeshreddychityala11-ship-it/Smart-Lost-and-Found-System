package com.lostfound.config;
import com.lostfound.model.*; import com.lostfound.repository.UserRepository;
import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.Bean; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@org.springframework.context.annotation.Configuration
public class DataSeeder {
 @Bean CommandLineRunner seed(UserRepository repo){
  return args->{ if(!repo.existsByEmailIgnoreCase("admin@lostfound.local")){
   User a=new User();a.setName("System Administrator");a.setEmail("admin@lostfound.local");
   a.setPassword(new BCryptPasswordEncoder().encode("Admin@123"));a.setRole(Role.ADMIN);repo.save(a);
  }};
 }
}
