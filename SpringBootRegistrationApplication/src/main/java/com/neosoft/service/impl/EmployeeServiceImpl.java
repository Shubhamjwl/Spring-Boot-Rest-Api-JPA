package com.neosoft.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.neosoft.dao.EmployeeRepository;
import com.neosoft.dao.EmployeeSortRepository;
import com.neosoft.exception.EmployeeNotFoundException;
import com.neosoft.model.Employee;
import com.neosoft.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private EmployeeSortRepository empSortRepo;

	// register employee
	@Override
	public Integer registerEmployee(Employee employee) {

		employee.setActiveFlag(1);
		Employee emp = empRepo.save(employee);

		return emp.getEmployeeId();
	}

	// get one employee details
	@Override
	public Employee getOneEmployee(Integer empId) {

		Optional<Employee> opt = empRepo.findOneEmployee(empId);

		if (opt.isPresent())
			return opt.get();
		else {
			throw new EmployeeNotFoundException("Employee not exist");
		}
	}

	// update employee details
	@Override
	public Employee updateEmployee(Employee employee) {

		Employee existEmp = getOneEmployee(employee.getEmployeeId());
		BeanUtils.copyProperties(employee, existEmp);
		existEmp.setActiveFlag(1);
		return empRepo.save(existEmp);

	}

	// get all employees
	@Override
	public List<Employee> getAllEmployee() {

		List<Employee> allEmployee = empRepo.findAllEmployee();

		return allEmployee;
	}

	// perform soft delete
	@Override
	public void softDeleteEmployee(Integer empId) {

		Employee employee = getOneEmployee(empId);
		employee.setActiveFlag(0);
		empRepo.save(employee);

	}

	// perform hard delete
	@Override
	public void deleteOneEmployee(Integer empId) {

		Optional<Employee> oneEmployee = empRepo.findById(empId);
		if (oneEmployee.isPresent())
			empRepo.deleteById(oneEmployee.get().getEmployeeId());
		else
			throw new EmployeeNotFoundException("Employee not exist");
	}

	// search employees by first name
	@Override
	public List<Employee> findByFirstName(String firstName) {
		List<Employee> listEmployee = empRepo.findByFirstName(firstName);

		if (!listEmployee.isEmpty())
			return listEmployee;
		else {
			throw new EmployeeNotFoundException("Employee not exist");
		}

	}

	// search employees by sur name
	@Override
	public List<Employee> findBySurName(String surName) {
		List<Employee> listEmployee = empRepo.findBySurName(surName);

		if (!listEmployee.isEmpty())
			return listEmployee;
		else {
			throw new EmployeeNotFoundException("Employee not exist");
		}

	}

	// search employees by pincode
	@Override
	public List<Employee> findByPincode(Integer pincode) {
		List<Employee> listEmployees = empRepo.findByPincode(pincode);

		if (!listEmployees.isEmpty())
			return listEmployees;
		else {
			throw new EmployeeNotFoundException("Employee not exist");
		}

	}

	// sort employees by DOB
	@Override
	public List<Employee> sortEmployeeByDob(boolean asc) {

		Iterable<Employee> sortedEmployees = empSortRepo
				.findAll(Sort.by(asc ? Direction.ASC : Direction.DESC, "employeeDob"));

		return (List<Employee>) sortedEmployees;

	}

	// sort employees by DOJ
	@Override
	public List<Employee> sortEmployeeByDoj(boolean asc) {
		Iterable<Employee> sortedEmployees = empSortRepo
				.findAll(Sort.by(asc ? Direction.ASC : Direction.DESC, "employeeDoj"));

		return (List<Employee>) sortedEmployees;
	}
}
