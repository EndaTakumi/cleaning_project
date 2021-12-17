package jp.co.sss.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.project.entity.Info;

public interface InfoRepository extends JpaRepository<Info, Integer>{
	
}
