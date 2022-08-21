package com.card.procession.credit;

import com.card.procession.credit.entity.CreditAccount;
import com.card.procession.credit.handler.DBOperationException;
import com.card.procession.credit.repository.CreditAccountRepository;
import com.card.procession.credit.service.CreditAccountServiceImpl;
import java.math.BigDecimal;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {CreditAccountServiceImpl.class})
public class CreditAccountServiceTests {

  @MockBean
  CreditAccountRepository creditAccountRepository;

  @Autowired
  CreditAccountServiceImpl creditAccountService;

  @Test
  public void addCreditAccount_method_test() throws DBOperationException {
    CreditAccount creditAccount = new CreditAccount("79927398713","Bob",null,new BigDecimal(1000));
    Mockito.when(creditAccountRepository.save(creditAccount)).thenReturn(creditAccount);
    CreditAccount response = creditAccountService.addCreditAccount(creditAccount);
    assertEquals(response.getCardNum(),creditAccount.getCardNum());
  }

  @Test
  public void addCreditAccount_method_test_error(){
    CreditAccount creditAccount = new CreditAccount("79927398713","Bob",null,new BigDecimal(1000));
    Mockito.when(creditAccountRepository.save(creditAccount)).thenThrow(new RuntimeException("Exception"));
    assertThrows(DBOperationException.class,()->creditAccountService.addCreditAccount(creditAccount));
  }

  @Test
  public void getCreditAccount_method_test() throws DBOperationException {
    CreditAccount creditAccount = new CreditAccount("79927398713","Bob",null,new BigDecimal(1000));
    Mockito.when(creditAccountRepository.findAll()).thenReturn(Arrays.asList(creditAccount));
   assertNotNull(creditAccountService.getAllCreditAccount());
  }

  @Test
  public void getCreditAccount_method_test_error() {
    Mockito.when(creditAccountRepository.findAll()).thenThrow(new RuntimeException("Exception"));
    assertThrows(DBOperationException.class,()->creditAccountService.getAllCreditAccount());
  }
}
