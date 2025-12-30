package com.cjc.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cjc.app.model.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
 
	List<Manager> findByfirstname(String firstname);
	
	@Query(
		"SELECT m FROM Manager m " +
	    "Where (:type IS NULL OR m.type = :type) " +
		"AND (:minSalary IS NULL OR m.salary >= :minSalary) " +
	    "AND (:minSalary IS NULL OR m.salary <= :maxSalary)"
	)
	List<Manager> filterManagers(@Param("type") String type,
			                     @Param("minSalary") Double minSalary,
			                     @Param("maxSalary") Double maxSalary
	);
}
