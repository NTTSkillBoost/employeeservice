package br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.dto;

import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeePosition;
import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    @Schema(description = "ID do funcionário", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Nome do funcionário", example = "João da Silva")
    private String name;

    @Schema(description = "Email do funcionário", example = "root@localhost")
    @Email
    private String email;

    @Schema(description = "Telefone do funcionário", example = "(11) 91234-5678")
    private String phone;

    @Schema(description = "CPF do funcionário", example = "123.456.789-00")
    private String documentId;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    private Integer employeeCompanyId;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID addressId;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    @Enumerated(EnumType.STRING)
    private EmployeePosition employeePosition;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    private LocalDate hiringDate;
}
