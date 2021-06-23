package com.parent.child.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parent.child.demo.dto.ApiResponseDto;
import com.parent.child.demo.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.parent.child.demo.dto.PaginationDto;
import com.parent.child.demo.model.Child;
import com.parent.child.demo.model.Parent;
import com.parent.child.demo.service.IParentChildService;

@CrossOrigin(origins = "*", maxAge = 360000000)
@RestController
@RequestMapping("/api/v1/demo")
/**
 * parent child controller
 * 
 * @author anil
 *
 */
public class ParentChildController {

	@Autowired
	private IParentChildService parentChildService;

	/**
	 * api for add Parent
	 * 
	 * @param parent
	 * @return
	 */
	@RequestMapping(value = "/addParent", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addParent(@Validated @RequestBody Parent parent) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		parentChildService.addParent(parent, apiResponseDtoBuilder);
		return apiResponseDtoBuilder.build();
	}

	/**
	 * api for add child
	 * 
	 * @param child
	 * @return
	 */
	@RequestMapping(value = "/addChild", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addChild(@Validated @RequestBody Child child) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		parentChildService.addChild(child, apiResponseDtoBuilder);
		return apiResponseDtoBuilder.build();
	}

	/**
	 * api for get Parent with pagination it includes sum of all child's payment
	 * 
	 * @param pagination
	 * @return
	 */
	@RequestMapping(value = "/getParentWithPagination", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto getParentWithPagination(@RequestBody PaginationDto pagination) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		parentChildService.getParentWithPagination(pagination, apiResponseDtoBuilder);
		return apiResponseDtoBuilder.build();
	}

	/**
	 * 
	 * api for get child by parent id it includes assigned parent details as well
	 */
	@RequestMapping(value = "/getChildByParentId", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto getChildByParentId(@RequestParam String parentId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		parentChildService.getChildByParentId(parentId, apiResponseDtoBuilder);
		return apiResponseDtoBuilder.build();

	}

	/**
	 * api for update parent
	 * 
	 * @param parent
	 * @return
	 */
	@RequestMapping(value = "/updateParent", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ApiResponseDto updateParent(@Validated @RequestBody Parent parent) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		parentChildService.updateParent(parent, apiResponseDtoBuilder);
		return apiResponseDtoBuilder.build();
	}

	/**
	 * api for update child
	 * 
	 * @param child
	 * @return
	 */
	@RequestMapping(value = "/updateChild", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ApiResponseDto updateChild(@Validated @RequestBody Child child) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		parentChildService.updateChild(child, apiResponseDtoBuilder);
		return apiResponseDtoBuilder.build();
	}

	/**
	 * api for delete parent by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteParentById", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public ApiResponseDto deleteParentById(@RequestParam String id) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		parentChildService.deleteParentById(id, apiResponseDtoBuilder);
		return apiResponseDtoBuilder.build();

	}

	/**
	 * api for delete child by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteChildById", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public ApiResponseDto deleteChildById(@RequestParam String id) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		parentChildService.deleteChildById(id, apiResponseDtoBuilder);
		return apiResponseDtoBuilder.build();

	}

}
