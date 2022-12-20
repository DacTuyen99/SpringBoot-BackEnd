package com.example.springboot.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Employee;
import com.example.springboot.repository.EmployeeRepository;
import com.example.springboot.service.EmployeeService;
@Service

public class EmployeeServiceImpl implements EmployeeService{
	private EmployeeRepository employeeRepository;
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		Optional<Employee> employee=employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}else {
			throw new ResourceNotFoundException("Employee", "Id", id);
		}
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// TODO Auto-generated method stub
		// we need to check whether employee with given id is exit in db or not
		Employee existingEmployee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "ID", id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}
	@Override
	public void deleteEmployee(long id) {
		// TODO Auto-generated method stub
		employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "ID", id));
		
		employeeRepository.deleteById(id);
		
	}
	
}
