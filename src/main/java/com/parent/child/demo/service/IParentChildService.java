package com.parent.child.demo.service;

import org.springframework.stereotype.Service;

import com.parent.child.demo.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.parent.child.demo.dto.PaginationDto;
import com.parent.child.demo.model.Child;
import com.parent.child.demo.model.Parent;

@Service
public interface IParentChildService {

	void addParent(Parent company, ApiResponseDtoBuilder apiResponseDtoBuilder);

	void addChild(Child child, ApiResponseDtoBuilder apiResponseDtoBuilder);

	void getParentWithPagination(PaginationDto pagination, ApiResponseDtoBuilder apiResponseDtoBuilder);

	void getChildByParentId(String parentId, ApiResponseDtoBuilder apiResponseDtoBuilder);

	void deleteParentById(String id, ApiResponseDtoBuilder apiResponseDtoBuilder);

	void deleteChildById(String id, ApiResponseDtoBuilder apiResponseDtoBuilder);

	void updateParent(Parent parent, ApiResponseDtoBuilder apiResponseDtoBuilder);

	void updateChild(Child child, ApiResponseDtoBuilder apiResponseDtoBuilder);


}
