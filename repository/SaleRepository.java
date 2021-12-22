package jp.co.sss.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.co.sss.project.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Integer>{
	@Query("SELECT s.discount FROM Sale s WHERE current_date BETWEEN saleFrom AND saleTo")
	int findDiscountBetween();
}
