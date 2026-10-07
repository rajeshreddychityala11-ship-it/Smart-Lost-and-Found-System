package com.lostfound.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="users")
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private String email;
 @Column(nullable=false) private String name;
 @Column(nullable=false) private String password;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role=Role.USER;
 @Column(nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
 public User(){}
 public Long getId(){return id;} public String getEmail(){return email;} public String getName(){return name;}
 public String getPassword(){return password;} public Role getRole(){return role;} public LocalDateTime getCreatedAt(){return createdAt;}
 public void setEmail(String v){email=v;} public void setName(String v){name=v;} public void setPassword(String v){password=v;} public void setRole(Role v){role=v;}
}
