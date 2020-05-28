package com.example.demo.dao.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.entity.ProductEntity;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, String>{

	public List<ProductEntity> findByprotype(String type);
	
	@Transactional
	@Modifying
	@Query(value="update product_table pt set pt.pronum =:num where pt.proid =:id",nativeQuery = true)
	public void updateProNum(String id, int num);

	//根据id更新商品的状态
	@Transactional
	@Modifying
	@Query(value="update product_table pt set pt.protype =:type where pt.proid =:id",nativeQuery = true)
	public void updateProType(String id, String type);
}
