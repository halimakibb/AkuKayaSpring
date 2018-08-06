package main.java.akuKaya.services.implementations;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import main.java.akuKaya.services.interfaces.PaginationService;

@Service
public class PaginationServiceImpl implements PaginationService {

	public <T> Page<T> findPaginatedTransaction(Pageable pageable, List<T> lists) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<T> list;

		if (lists.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, lists.size());
			list = lists.subList(startItem, toIndex);
		}

		Page<T> listPage = new PageImpl<T>(list, PageRequest.of(currentPage, pageSize), lists.size());

		return listPage;
	}

	public <T> List<Integer> findPageNumber(Page<T> page) {
		int totalPages = page.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			return pageNumbers;
		}
		
		return null;
	}
}
