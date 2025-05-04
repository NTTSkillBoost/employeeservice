package br.com.nttdata.nttskillboost.employeeservice.ports.in;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeeRole;

import java.util.List;
import java.util.UUID;

public interface GetEmployeeRoleUseCase {
    EmployeeRole findById(UUID id);
    List<EmployeeRole> findAll();
}
