package com.parent.child.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.parent.child.demo.model.Parent;

@Repository
public interface ParentRepository extends MongoRepository<Parent, String> {

	//Long countByUserId(String id);

	//Page<Parent> findByUserId(String id, Pageable pageable);

}
