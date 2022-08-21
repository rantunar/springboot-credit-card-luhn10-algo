package com.card.procession.credit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.card.procession.credit.controllers.CreditCardController;
import com.card.procession.credit.entity.CreditAccount;
import com.card.procession.credit.service.CreditAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

@SpringJUnitConfig(classes = {CreditCardController.class})
@WebMvcTest
public class CreditCardControllerTests {

  ObjectMapper objectMapper = new ObjectMapper();

  @MockBean
  CreditAccountService creditAccountService;

  @Autowired
  MockMvc mockMvc;

  @Test
  public void add_credit_account_success() throws Exception {
    CreditAccount creditAccount = new CreditAccount("79927398713","Bob",null,new BigDecimal(1000));

    Mockito.when(creditAccountService.addCreditAccount(creditAccount)).thenReturn(creditAccount);

    mockMvc.perform(post("/credit-card/add")
        .content(objectMapper.writeValueAsString(creditAccount))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void add_credit_account_invalid_luhn10_cardnum() throws Exception {
    CreditAccount creditAccount = new CreditAccount("111111111","Bob",null,new BigDecimal(1000));

    Mockito.when(creditAccountService.addCreditAccount(creditAccount)).thenReturn(creditAccount);

    mockMvc.perform(post("/credit-card/add")
            .content(objectMapper.writeValueAsString(creditAccount))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void add_credit_account_cardnum_not_numeric() throws Exception {
    CreditAccount creditAccount = new CreditAccount("AD13212","Bob",null,new BigDecimal(1000));

    Mockito.when(creditAccountService.addCreditAccount(creditAccount)).thenReturn(creditAccount);

    mockMvc.perform(post("/credit-card/add")
            .content(objectMapper.writeValueAsString(creditAccount))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void add_credit_account_cardnum_morethan_19char() throws Exception {
    CreditAccount creditAccount = new CreditAccount("79927398713346755346","Bob",null,new BigDecimal(1000));

    Mockito.when(creditAccountService.addCreditAccount(creditAccount)).thenReturn(creditAccount);

    mockMvc.perform(post("/credit-card/add")
            .content(objectMapper.writeValueAsString(creditAccount))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void get_credit_account_success() throws Exception {
    CreditAccount creditAccount = new CreditAccount("79927398713346755346","Bob",null,new BigDecimal(1000));

    Mockito.when(creditAccountService.getAllCreditAccount()).thenReturn(Arrays.asList(creditAccount));

    mockMvc.perform(get("/credit-card/get-all"))
        .andExpect(status().isOk());
  }
}
