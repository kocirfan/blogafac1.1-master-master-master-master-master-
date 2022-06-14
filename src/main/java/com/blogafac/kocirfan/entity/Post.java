package com.blogafac.kocirfan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="post")
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;

    @Column(name = "title")
    String title;

    @Lob
    @Column(columnDefinition="text")
    String text;

    @Temporal(TemporalType.TIME)
    Date createDate;



}
