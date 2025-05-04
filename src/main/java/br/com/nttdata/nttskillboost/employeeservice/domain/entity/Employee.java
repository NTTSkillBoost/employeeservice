package br.com.nttdata.nttskillboost.employeeservice.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_employee")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "Employee Entity", description = "Operações relacionadas a o funcionário")
public class Employee extends AuditDomain {

    @Schema(description = "ID do Employee", example = "123e4567-e89b-12d3-a456-426614174000")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Schema(description = "Nome do funcionário", example = "João da Silva")
    @Column(name = "full_name")
    private String name;

    @Schema(description = "Email do funcionário", example = "root@localhost")
    @Email
    private String email;

    @Schema(description = "Telefone do funcionário", example = "(11) 91234-5678")
    private String phone;

    @Schema(description = "CPF do funcionário", example = "123.456.789-00")
    @Column(name = "document_id")
    private String documentId;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    @Column(name = "employee_company_id")
    private Integer employeeCompanyId;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    @Column(name = "address_id")
    private UUID addressId;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    @Column(name = "employee_position")
    @Enumerated(EnumType.STRING)
    private EmployeePosition employeePosition;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    @Column(name = "employee_status")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    @Column(name = "hiring_date")
    private LocalDate hiringDate;
}