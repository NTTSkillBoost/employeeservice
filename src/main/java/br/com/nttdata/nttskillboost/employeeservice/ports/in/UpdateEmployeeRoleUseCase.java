package br.com.nttdata.nttskillboost.employeeservice.ports.in;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeeRole;

import java.util.UUID;

public interface UpdateEmployeeRoleUseCase {
    EmployeeRole update(UUID id, EmployeeRole employeeRole);
}
