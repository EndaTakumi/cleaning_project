package jp.co.sss.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.project.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{
	
}
