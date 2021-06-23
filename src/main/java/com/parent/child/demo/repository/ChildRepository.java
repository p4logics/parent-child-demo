package com.parent.child.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.parent.child.demo.model.Child;

@Repository
public interface ChildRepository extends MongoRepository<Child, String> {

	List<Child> findByParentId(String id);

	
}
