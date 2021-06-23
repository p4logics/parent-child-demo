package com.parent.child.demo.utility;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.parent.child.demo.dto.PaginationDataDto;
import com.parent.child.demo.dto.PaginationDto;

/**
 * utility class for common solution in entire project
 * 
 * @author anil
 *
 */
public class Utility {
	static Logger logger = Logger.getLogger(Utility.class);

	/**
	 * method for get pagination dataDto
	 * 
	 * @param totalCounts
	 * @param paginationDto
	 * @return
	 */
	public static PaginationDataDto getPaginationData(long totalCounts, PaginationDto paginationDto) {
		PaginationDataDto paginationDataDto = new PaginationDataDto();
		int totalPages = (int) Math.ceil((double) totalCounts / (double) paginationDto.getPerPage());
		int from = paginationDto.getCurrentPage() - 1;
		int to = paginationDto.getPerPage();
		paginationDataDto.setTotalPages(totalPages);
		paginationDataDto.setFrom(from);
		paginationDataDto.setTo(to);
		return paginationDataDto;
	}

}
