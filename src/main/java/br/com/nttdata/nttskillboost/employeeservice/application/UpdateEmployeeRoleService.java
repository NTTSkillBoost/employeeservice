package br.com.nttdata.nttskillboost.employeeservice.application;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Employee;
import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeeRole;
import br.com.nttdata.nttskillboost.employeeservice.ports.in.UpdateEmployeeRoleUseCase;
import br.com.nttdata.nttskillboost.employeeservice.ports.out.EmployeeRepositoryPort;
import br.com.nttdata.nttskillboost.employeeservice.ports.out.EmployeeRoleRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateEmployeeRoleService implements UpdateEmployeeRoleUseCase {

    private final EmployeeRoleRepositoryPort employeeRoleRepositoryPort;
    private final EmployeeRepositoryPort employeeRepositoryPort;

    @Override
    public EmployeeRole update(UUID id, EmployeeRole employeeRole) {
        EmployeeRole byId = employeeRoleRepositoryPort.findById(id);
        if (byId == null) {
            return null;
        }

        EmployeeRole employeeRoleUpdate = new EmployeeRole();

        Employee employee = employeeRepositoryPort.findById(employeeRole.getEmployee().getId());
        if (employee == null) {
            throw new EntityNotFoundException("Funcionário não encontrado");
        }

        employeeRoleUpdate.setId(byId.getId());
        employeeRoleUpdate.setRole(employeeRole.getRole());
        employeeRoleUpdate.setEmployee(employee);

        return employeeRoleRepositoryPort.save(employeeRoleUpdate);
    }
}
