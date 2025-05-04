package br.com.nttdata.nttskillboost.employeeservice;

import br.com.nttdata.nttskillboost.employeeservice.adapters.api.controller.EmployeeController;
import br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.dto.EmployeeRequest;
import br.com.nttdata.nttskillboost.employeeservice.adapters.api.model.dto.EmployeeResponse;
import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeePosition;
import br.com.nttdata.nttskillboost.employeeservice.domain.entity.EmployeeStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerResilienceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testRetryAndFallback() {
        EmployeeRequest request = new EmployeeRequest();
        request.setName("FAIL");  // Vai acionar a falha simulada
        request.setEmail("fail@example.com");
        request.setPhone("12345678339");
        request.setDocumentId("34234434234484");
        request.setEmployeeCompanyId(1177547);
        request.setAddressId(UUID.randomUUID());
        request.setEmployeePosition(EmployeePosition.JUNIOR);
        request.setEmployeeStatus(EmployeeStatus.ACTIVE);
        request.setHiringDate(LocalDate.now());

        ResponseEntity<EmployeeResponse> response = restTemplate.postForEntity(
                "http://localhost:8881/api/v1/employees", request, EmployeeResponse.class);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Mockito.verify não funciona em chamada REST com o contexto real
    }

    @Test
    void testBulkheadWithConcurrentRequests() throws InterruptedException {
        int concurrentRequests = 15; // bulkhead está configurado para aceitar até 10
        ExecutorService executor = Executors.newFixedThreadPool(concurrentRequests);

        CountDownLatch latch = new CountDownLatch(concurrentRequests);

        for (int i = 0; i < concurrentRequests; i++) {
            int requestNumber = i + 1;
            executor.submit(() -> {
                try {
                    EmployeeRequest request = new EmployeeRequest();
                    request.setName("Concurrent " + requestNumber);
                    request.setEmail("user" + requestNumber + "@example.com");
                    request.setPhone("1234567e" + requestNumber);
                    request.setDocumentId("342344342" + requestNumber);
                    request.setEmployeeCompanyId(requestNumber);
                    request.setAddressId(UUID.randomUUID());
                    request.setEmployeePosition(EmployeePosition.JUNIOR);
                    request.setEmployeeStatus(EmployeeStatus.ACTIVE);
                    request.setHiringDate(LocalDate.now());

                    ResponseEntity<String> response = restTemplate.postForEntity(
                            "http://localhost:8881/api/v1/employees", request, String.class);

                    if (response.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                        System.out.println("Erro retornado pela API: " + response.getBody());
                    } else if (response.getStatusCode().is2xxSuccessful()) {
                        try {
                            EmployeeResponse employeeResponse = new ObjectMapper().readValue(response.getBody(), EmployeeResponse.class);
                            System.out.println("Resposta bem-sucedida: " + employeeResponse);
                        } catch (JsonProcessingException e) {
                            System.out.println("Erro ao processar JSON: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Resposta inesperada: " + response.getBody());
                    }

                    System.out.println("Request " + requestNumber + " status: " + response.getStatusCode());
                } catch (Exception e) {
                    System.out.println("Request " + requestNumber + " failed: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // Aguarda todas as requisições terminarem
        executor.shutdown();
    }
}