package com.parent.child.demo.repository;

import com.parent.child.demo.dto.PaginationDto;

public interface ParentRepositoryCustom {

	PaginationDto findByFilterWithPagination(PaginationDto paginationDto);
}
