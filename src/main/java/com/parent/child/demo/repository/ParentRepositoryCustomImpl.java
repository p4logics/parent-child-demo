package com.parent.child.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.parent.child.demo.dto.PaginationDataDto;
import com.parent.child.demo.dto.PaginationDto;
import com.parent.child.demo.model.Parent;
import com.parent.child.demo.utility.Utility;

/**
 * custom repository implementation for get parent details
 * @author anil
 *
 */
@Repository
public class ParentRepositoryCustomImpl implements ParentRepositoryCustom {

	@Autowired
	MongoTemplate mongoTemplate;

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * method for get parent data from database with custom query and sorting
	 */
	public PaginationDto findByFilterWithPagination(PaginationDto paginationDto) {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.ASC, "id"));
		long totalCounts = mongoTemplate.count(query, Parent.class);
		PaginationDataDto paginationDataDto = Utility.getPaginationData(totalCounts,
				paginationDto);
		Pageable pageable = PageRequest.of(paginationDataDto.getFrom(), paginationDataDto.getTo());
		query.with(pageable);
		List<Parent> opportunityList = mongoTemplate.find(query, Parent.class);
		Object object = opportunityList;
		paginationDto.setTotalCount(totalCounts);
		paginationDto.setTotalPages(paginationDataDto.getTotalPages());
		paginationDto.setData((List<Object>) object);
		return paginationDto;
	}

}
