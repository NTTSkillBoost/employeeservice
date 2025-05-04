package br.com.nttdata.nttskillboost.employeeservice.application;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Employee;
import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Status;
import br.com.nttdata.nttskillboost.employeeservice.ports.in.DeleteEmployeeUseCase;
import br.com.nttdata.nttskillboost.employeeservice.ports.out.EmployeeRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteEmployeeService implements DeleteEmployeeUseCase {

    private final EmployeeRepositoryPort employeeRepositoryPort;

    @Override
    public boolean delete(UUID id) {
        Employee byId = employeeRepositoryPort.findById(id);
        if (byId != null) {
            byId.setStatus(Status.DELETED);
            employeeRepositoryPort.save(byId);
            return true;
        } else {
            return false;
        }
    }
}
