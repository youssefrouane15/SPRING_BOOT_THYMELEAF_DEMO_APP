package com.luv2code.springboot.thymeleaf.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.thymeleaf.demo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleaf.demo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {

		this.employeeRepository = theEmployeeRepository;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {

		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	@Transactional
	public Employee findById(int employeeId) {

		Optional<Employee> result = employeeRepository.findById(employeeId);
		Employee employee = null;

		if (result.isPresent()) {

			employee = result.get();
		} else {

			throw new RuntimeException("No Employee with id ---" + employeeId + " was found");
		}
		return employee;
	}

	@Override
	@Transactional
	public void save(Employee employee) {

		employeeRepository.save(employee);
	}

	@Override
	@Transactional
	public void deleteById(int employeeId) {

		employeeRepository.deleteById(employeeId);
	}

}
