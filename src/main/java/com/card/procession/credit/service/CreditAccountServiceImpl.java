package com.card.procession.credit.service;

import com.card.procession.credit.entity.CreditAccount;
import com.card.procession.credit.handler.DBOperationException;
import com.card.procession.credit.repository.CreditAccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreditAccountServiceImpl implements CreditAccountService{

  @Autowired
  CreditAccountRepository creditAccountRepository;

  @Override
  public CreditAccount addCreditAccount(CreditAccount creditAccount) throws DBOperationException {
    CreditAccount acc = null;
    try {
      creditAccount.setBalance(new BigDecimal(0));
      acc = creditAccountRepository.save(creditAccount);
    } catch (Exception e){
      log.error("error in adding credit account > ",creditAccount.getCardNum());
      throw new DBOperationException(e.getMessage());
    }
    log.info("credit account saved success > ",creditAccount.getCardNum());
    return acc;
  }

  @Override
  public List<CreditAccount> getAllCreditAccount() throws DBOperationException {
    List<CreditAccount> creditAccounts = null;
    try {
      creditAccounts = creditAccountRepository.findAll();
    }catch (Exception e){
      log.error("error in fetching credit account > ");
      throw new DBOperationException(e.getMessage());
    }
    log.info("credit accounts fetch success");
    return creditAccounts;
  }
}
