package com.lostfound.dto;
import com.lostfound.model.ItemType;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
public record ItemRequest(@NotBlank String name,@NotBlank String description,@NotBlank String location,
 @NotNull LocalDate date,@NotNull ItemType type,String category,String contact,String imageUrl){}
