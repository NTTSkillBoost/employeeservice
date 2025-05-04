package br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.mapper;

import br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.dto.EmployeeRoleRequest;
import br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.dto.EmployeeRoleResponse;
import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Employee;
import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeeRole;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRoleMapper {

    public EmployeeRole toDomain(EmployeeRoleRequest dto) {
        return EmployeeRole.builder()
                .role(dto.getRole())
                .employee(Employee.builder().id(dto.getEmployeeId()).build())
                .build();
    }

    public EmployeeRoleResponse toResponse(EmployeeRole employeeRole) {
        return EmployeeRoleResponse.builder()
                .id(employeeRole.getId())
                .employeeId(employeeRole.getEmployee().getId())
                .role(employeeRole.getRole())
                .build();
    }
}
