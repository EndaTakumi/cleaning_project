package jp.co.sss.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.co.sss.project.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	List<Employee> findByEmployeeIdAndEmployeePw(Integer employeeId, String employeePw);
	
	@Query("SELECT e.employeeNameSei FROM Employee e WHERE e.employeeId = :employeeId")
	String findEmployeeNameSei(Integer employeeId);
	
	@Query("SELECT e.employeeNameMei FROM Employee e WHERE e.employeeId = :employeeId")
	String findEmployeeNameMei(Integer employeeId);
}
