package br.com.nttdata.nttskillboost.employeeservice.ports.out;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeeRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRoleRepositoryPort {
    EmployeeRole save(EmployeeRole employeeRole);
    EmployeeRole findById(UUID id);
    List<EmployeeRole> findAll();
    EmployeeRole findByRoleName(String role);
}
