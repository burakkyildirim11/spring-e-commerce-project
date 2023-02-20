//package com.springCommerce.commerce.user.repository;
//
//import com.springCommerce.commerce.user.model.Advertisement;
//import org.springframework.data.elasticsearch.annotations.Query;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface AdvertisementRepository
//        extends ElasticsearchRepository<Advertisement, String> {
//
//    @Query("{\"bool\": {\"must\": [{\"match\": {\"advertisement.title\": \"?1\"}}]}}")
//    List<Advertisement> getByTitle(String search);
//}
//
