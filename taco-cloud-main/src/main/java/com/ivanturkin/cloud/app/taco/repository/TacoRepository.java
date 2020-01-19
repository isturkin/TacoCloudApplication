package com.ivanturkin.cloud.app.taco.repository;

import com.ivanturkin.cloud.app.taco.domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
