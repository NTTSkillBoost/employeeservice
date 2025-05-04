package br.com.nttdata.nttskillboost.employeeservice.application;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeeRole;
import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Status;
import br.com.nttdata.nttskillboost.employeeservice.ports.in.DeleteEmployeeRoleUseCase;
import br.com.nttdata.nttskillboost.employeeservice.ports.out.EmployeeRoleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteEmployeeRoleService implements DeleteEmployeeRoleUseCase {

    private final EmployeeRoleRepositoryPort employeeRoleRepositoryPort;

    @Override
    public boolean delete(UUID id) {
        EmployeeRole byId = employeeRoleRepositoryPort.findById(id);
        if (byId != null) {
            byId.setStatus(Status.DELETED);
            employeeRoleRepositoryPort.save(byId);
            return true;
        } else {
            return false;
        }
    }
}
