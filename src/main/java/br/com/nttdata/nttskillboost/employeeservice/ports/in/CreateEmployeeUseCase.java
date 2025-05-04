package br.com.nttdata.nttskillboost.employeeservice.ports.in;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Employee;

public interface CreateEmployeeUseCase {
    Employee create(Employee employee);
}
