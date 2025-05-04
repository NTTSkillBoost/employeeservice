package br.com.nttdata.nttskillboost.employeeservice.ports.in;

import java.util.UUID;

public interface DeleteEmployeeRoleUseCase {
    boolean delete(UUID id);
}
