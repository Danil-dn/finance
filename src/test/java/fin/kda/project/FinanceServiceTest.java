package fin.kda.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import fin.kda.project.entity.FinanceEntity;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FinanceServiceTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    public String objectToJson(final Object object) {
        return objectMapper.writeValueAsString(object);
    }


    @DisplayName("addFinanceOperation")
    @Test
    public void addFinanceOperation() throws Exception {
        MvcResult mvcResult = financeOperation(20000.0, "test desc");
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    public MvcResult financeOperation(double sum, String description) throws Exception {

        FinanceEntity financeEntity = new FinanceEntity();
        financeEntity.setSum(sum);
        financeEntity.setDescription(description);
        financeEntity.setDate(LocalDate.now());

       return mockMvc.perform(
                        post("/addFinanceOperation")
                                .contentType(APPLICATION_JSON)
                                .content(objectToJson(financeEntity))
                )
                .andExpectAll(status().isOk())
                .andReturn();

    }
}

