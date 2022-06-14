package com.blogafac.kocirfan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductImage  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;



//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int id;

    //private ImageType imageType;
    private  String url;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @Column( name = "product_id")
//    private Product product;

//    public enum ImageType{
//        FEATURE, NORMAL;
//    }
}
