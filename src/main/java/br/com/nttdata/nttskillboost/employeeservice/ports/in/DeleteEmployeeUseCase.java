package br.com.nttdata.nttskillboost.employeeservice.ports.in;

import java.util.UUID;

public interface DeleteEmployeeUseCase {
    boolean delete(UUID id);
}
