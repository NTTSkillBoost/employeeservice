package br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.mapper;

import br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.dto.EmployeeRequest;
import br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.dto.EmployeeResponse;
import br.com.nttdata.nttskillboost.employeeservice.domain.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toDomain(EmployeeRequest dto) {
        return Employee.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .documentId(dto.getDocumentId())
                .employeeCompanyId(dto.getEmployeeCompanyId())
                .employeePosition(dto.getEmployeePosition())
                .employeeStatus(dto.getEmployeeStatus())
                .addressId(dto.getAddressId())
                .employeeStatus(dto.getEmployeeStatus())
                .employeePosition(dto.getEmployeePosition())
                .hiringDate(dto.getHiringDate())
                .build();
    }

    public EmployeeResponse toResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .documentId(employee.getDocumentId())
                .employeeCompanyId(employee.getEmployeeCompanyId())
                .employeePosition(employee.getEmployeePosition())
                .employeeStatus(employee.getEmployeeStatus())
                .addressId(employee.getAddressId())
                .hiringDate(employee.getHiringDate())
                .build();

    }
}
