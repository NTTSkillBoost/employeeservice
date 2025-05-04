package br.com.nttdata.nttskillboost.employeeservice.ports.in;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Employee;

import java.util.List;
import java.util.UUID;

public interface GetEmployeeUseCase {
    Employee findById(UUID id);
    List<Employee> findAll();
}
