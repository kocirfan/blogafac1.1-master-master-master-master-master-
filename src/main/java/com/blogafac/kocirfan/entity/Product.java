package com.blogafac.kocirfan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.jackson.JsonComponent;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 2185691613351663804L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    String name;

    String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Category category;




    @Temporal(TemporalType.TIME)
    Date createDate;

//    @Column(name = "product_images")
//    int imageId;

    /* //@Valid
    //@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)*/
//    @JsonManagedReference
//    @OneToMany
    @OneToMany
    Map<ProductImage, Product> productImages;

    @Column
    Double price;

}
