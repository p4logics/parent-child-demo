package com.parent.child.demo.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.parent.child.demo.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.parent.child.demo.dto.ChildDto;
import com.parent.child.demo.dto.PaginationDto;
import com.parent.child.demo.dto.ParentDto;
import com.parent.child.demo.model.Child;
import com.parent.child.demo.model.Parent;
import com.parent.child.demo.repository.ChildRepository;
import com.parent.child.demo.repository.ParentRepository;
import com.parent.child.demo.repository.ParentRepositoryCustom;
import com.parent.child.demo.service.IParentChildService;

@Service
public class ParentChildServiceImpl implements IParentChildService {

	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private ChildRepository childRepository;
	@Autowired
	private ParentRepositoryCustom parentRepositoryCustom;

	/**
	 * method for add parent entity in database
	 */
	@Override
	public void addParent(Parent parent, ApiResponseDtoBuilder apiResponseDtoBuilder) {
		parentRepository.save(parent);
		apiResponseDtoBuilder.withMessage("Parent successfully added").withStatus(HttpStatus.OK).withData(parent);

	}
    /**
     * 
     * method for update parent details 
     */
	@Override
	public void updateParent(Parent parent, ApiResponseDtoBuilder apiResponseDtoBuilder) {
		Optional<Parent> companyExist = parentRepository.findById(parent.getId());
		if (!companyExist.isPresent()) {
			apiResponseDtoBuilder.withMessage("Not Found").withStatus(HttpStatus.NOT_FOUND);
			return;

		}
		parentRepository.save(parent);
		apiResponseDtoBuilder.withMessage("Parent details successfully updated").withStatus(HttpStatus.OK)
				.withData(parent);

	}

	/**
	 * method for delete parent by its id 
	 */
	@Override
	public void deleteParentById(String id, ApiResponseDtoBuilder apiResponseDtoBuilder) {
		Optional<Parent> parentExist = parentRepository.findById(id);
		if (!parentExist.isPresent()) {
			apiResponseDtoBuilder.withMessage("Not Found").withStatus(HttpStatus.NOT_FOUND);
			return;
		}
		parentRepository.deleteById(id);
		apiResponseDtoBuilder.withMessage("parent details successfully deleted").withStatus(HttpStatus.OK);

	}

	/**
	 * method for delete child by its id
	 */
	@Override
	public void deleteChildById(String id, ApiResponseDtoBuilder apiResponseDtoBuilder) {
		Optional<Child> childExist = childRepository.findById(id);
		if (!childExist.isPresent()) {
			apiResponseDtoBuilder.withMessage("Not Found").withStatus(HttpStatus.NOT_FOUND);
			return;
		}
		childRepository.deleteById(id);
		apiResponseDtoBuilder.withMessage("child details successfully deleted").withStatus(HttpStatus.OK);

	}

	/**
	 * method for add child
	 */
	@Override
	public void addChild(Child child, ApiResponseDtoBuilder apiResponseDtoBuilder) {
		childRepository.save(child);
		apiResponseDtoBuilder.withMessage("Child details successfully added").withStatus(HttpStatus.OK).withData(child);

	}

	/**
	 * method for get parent with pagination with sum of all child payable payment 
	 */
	@Override
	public void getParentWithPagination(PaginationDto pagination, ApiResponseDtoBuilder apiResponseDtoBuilder) {

		pagination = parentRepositoryCustom.findByFilterWithPagination(pagination);
		List<Object> listParent = pagination.getData();
		List<ParentDto> parent = new LinkedList<ParentDto>();
		for (Object object : listParent) {
			ParentDto parentDto = new ParentDto();
			Double totalPayableAmountChild = 0.0;
			Parent parent1 = (Parent) object;
			List<Child> listOfchild = childRepository.findByParentId(parent1.getId());
			for (Child child : listOfchild) {
				totalPayableAmountChild += child.getPaidAmount();
			}
			parentDto.setId(parent1.getId());
			parentDto.setReceiver(parent1.getReceiver());
			parentDto.setSender(parent1.getSender());
			parentDto.setTotalAmount(parent1.getTotalAmount());
			parentDto.setTotalPaidAmount(totalPayableAmountChild);
			parent.add(parentDto);
		}
		Object object = parent;
		pagination.setData((List<Object>) object);
		apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(pagination);

	}

	/**
	 * mehtod for get child by parent id with parent details
	 */
	@Override
	public void getChildByParentId(String parentId, ApiResponseDtoBuilder apiResponseDtoBuilder) {
		List<Child> listChild = childRepository.findByParentId(parentId);
		Optional<Parent> parent = parentRepository.findById(parentId);
		List<ChildDto> child = new LinkedList<>();
		for (Child child1 : listChild) {
			ChildDto childDto = new ChildDto();
			childDto.setId(child1.getId());
			childDto.setSender(parent.get().getSender());
			childDto.setReceiver(parent.get().getReceiver());
			childDto.setTotalAmount(parent.get().getTotalAmount());
			childDto.setPaidAmount(child1.getPaidAmount());
			child.add(childDto);
		}
		apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(child);
	}

	/**
	 * method for update child
	 */
	@Override
	public void updateChild(Child child, ApiResponseDtoBuilder apiResponseDtoBuilder) {
		Optional<Child> childExist = childRepository.findById(child.getId());
		if (!childExist.isPresent()) {
			apiResponseDtoBuilder.withMessage("Not Found").withStatus(HttpStatus.NOT_FOUND);
			return;
		}
		childRepository.save(child);
		apiResponseDtoBuilder.withMessage("Child details successfully updated").withStatus(HttpStatus.OK)
				.withData(child);
	}

}
