package com.kodlamaio.hrmsDemo5.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.hrmsDemo5.entities.concretes.JobAdvertisement;

@Repository
public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {
	List<JobAdvertisement> findByActiveTrue();
	List<JobAdvertisement> findByActiveTrueOrderByReleaseDateAsc();
	List<JobAdvertisement> findByActiveTrueOrderByApplicationDeadlineAsc();
	List<JobAdvertisement> findByActiveTrueAndEmployer_Id(Integer id);
	// Hocanın örneğinde CategoryCategoryId yazılırsa olur. Bu adreste bir örnek mevcut.
	// https://thorben-janssen.com/ultimate-guide-derived-queries-with-spring-data-jpa/#Traverse_associations_in_derived_queries
	// Burada jpa employer field arıyor JobAdvertisement class'ında
	// daha sonra bulduğu employer'ın id adlı field'ını arıyor.
	// Benim employer classımdaki primary key field'ımın adı "id". 
}
