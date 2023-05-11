package com.vo.application.common.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 페이징 처리 공통 Util 
 */
public class PageableUtil {
	
	private static int DEFALT_SIZE = 10;
	private static int DEFALT_PAGE = 1;
	
    public static Pageable createPageRequest(int page, int size) {
    	
    	if( size == 0 ) {
    		size = DEFALT_SIZE;
    	}
    	
    	if( page == 0 ) {
    		page = DEFALT_PAGE;
    	}
    	
        return PageRequest.of(page, size);
    }

    public static Pageable createPageRequest(int page, int size, Sort.Direction direction, String... properties) {
        return PageRequest.of(page, size, direction, properties);
    }
}
