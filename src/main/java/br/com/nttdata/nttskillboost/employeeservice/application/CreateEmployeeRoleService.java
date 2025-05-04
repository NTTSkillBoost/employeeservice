package br.com.nttdata.nttskillboost.employeeservice.application;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeeRole;
import br.com.nttdata.nttskillboost.employeeservice.ports.in.CreateEmployeeRoleUseCase;
import br.com.nttdata.nttskillboost.employeeservice.ports.out.EmployeeRoleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEmployeeRoleService implements CreateEmployeeRoleUseCase {

    private final EmployeeRoleRepositoryPort employeeRepositoryPort;

    @Override
    public EmployeeRole create(EmployeeRole employeeRole) {
        if (employeeRepositoryPort.findByRoleName(employeeRole.getRole()) != null) {
            throw new IllegalArgumentException("Já existe funcionário com este email.");
        }
        return employeeRepositoryPort.save(employeeRole);
    }
}
