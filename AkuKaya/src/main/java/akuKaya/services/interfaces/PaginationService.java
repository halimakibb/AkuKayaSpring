package main.java.akuKaya.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service()
public interface PaginationService {
    <T> Page<T> findPaginatedTransaction(Pageable pageable, List<T> lists);
    <T> List<Integer> findPageNumber(Page<T> page); 
}
