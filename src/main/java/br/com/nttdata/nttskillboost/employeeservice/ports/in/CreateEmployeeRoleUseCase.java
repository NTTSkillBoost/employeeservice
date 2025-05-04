package br.com.nttdata.nttskillboost.employeeservice.ports.in;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeeRole;

public interface CreateEmployeeRoleUseCase {
    EmployeeRole create(EmployeeRole employeeRole);
}
