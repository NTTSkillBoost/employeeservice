package br.com.nttdata.nttskillboost.employeeservice.ports.out;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepositoryPort {
    Optional<Employee> findByEmail(String email);
    Employee save(Employee employee);
    Employee findById(UUID id);
    List<Employee> findAll();
}
