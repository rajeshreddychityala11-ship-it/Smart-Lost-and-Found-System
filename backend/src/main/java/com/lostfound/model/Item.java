package com.lostfound.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity @Table(name="items")
public class Item {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name;
 @Column(nullable=false,length=2000) private String description;
 @Column(nullable=false) private String location;
 @Column(nullable=false) private LocalDate date;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ItemType type;
 @Column private String category;
 @Column private String contact;
 @Column private String imageUrl;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ItemStatus status=ItemStatus.ACTIVE;
 @Column(nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
 @ManyToOne(fetch=FetchType.EAGER,optional=false) private User reportedBy;
 public Item(){}
 public Long getId(){return id;} public String getName(){return name;} public String getDescription(){return description;}
 public String getLocation(){return location;} public LocalDate getDate(){return date;} public ItemType getType(){return type;}
 public String getCategory(){return category;} public String getContact(){return contact;} public String getImageUrl(){return imageUrl;}
 public ItemStatus getStatus(){return status;} public LocalDateTime getCreatedAt(){return createdAt;} public User getReportedBy(){return reportedBy;}
 public void setName(String v){name=v;} public void setDescription(String v){description=v;} public void setLocation(String v){location=v;}
 public void setDate(LocalDate v){date=v;} public void setType(ItemType v){type=v;} public void setCategory(String v){category=v;}
 public void setContact(String v){contact=v;} public void setImageUrl(String v){imageUrl=v;} public void setStatus(ItemStatus v){status=v;}
 public void setReportedBy(User v){reportedBy=v;}
}
