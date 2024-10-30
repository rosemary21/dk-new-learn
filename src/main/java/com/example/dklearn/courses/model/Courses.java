package com.example.dklearn.courses.model;

import com.example.dklearn.admin.administration.model.Admin;
import com.example.dklearn.admin.staff.model.Staff;
import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Where(clause ="del_Flag='N'")
public class Courses extends AbstractEntity {

  private String courseGroup;
  private String courseCategory;
  private String courseImageUrl;
  private String author;
  private String title;
  private BigDecimal nairaPrice;
  private String description;
  private String status;
  private BigDecimal gdbPrice;
  private BigDecimal usdPrice;
  private BigDecimal euroPrice;
  private LocalDateTime timeCreated;
  private String courseVideoUrl;

  @ManyToOne
  private Staff staff;
  @ManyToOne
  private Admin admin;

  @OneToMany(fetch = FetchType.LAZY)
  private List<Section> section;

}


