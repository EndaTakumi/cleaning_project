package jp.co.sss.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.co.sss.project.entity.Info;

public interface InfoRepository extends JpaRepository<Info, Integer>{
	
	@Query("SELECT i FROM Info i WHERE current_date BETWEEN keisaiFrom AND keisaiTo")
	List<Info> findByKeisaiDateBetween();
}
