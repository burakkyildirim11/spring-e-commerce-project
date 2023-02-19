//package com.springCommerce.commerce.model;
//
//import lombok.*;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.DateFormat;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.util.Date;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Document(indexName = "advertisement")
//public class Advertisement {
//
//    @Id
//    private String id;
//
//    @Field(type = FieldType.Keyword)
//    private String title;
//
//    @Field(type = FieldType.Keyword)
//    private String description;
//
//    private Double price;
//
//    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
//    private Date creationTime;
//
//    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
//    private Date lastModifiedDate;
//
//}
