package br.com.nttdata.nttskillboost.employeeservice.ports.in;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Employee;

import java.util.UUID;

public interface UpdateEmployeeUseCase {
    Employee update(UUID id, Employee employee);
}
