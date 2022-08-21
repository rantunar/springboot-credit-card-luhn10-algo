package com.card.procession.credit.service;

import com.card.procession.credit.entity.CreditAccount;
import com.card.procession.credit.handler.DBOperationException;
import java.util.List;

public interface CreditAccountService {

  public CreditAccount addCreditAccount(CreditAccount creditAccount) throws DBOperationException;
  public List<CreditAccount> getAllCreditAccount() throws DBOperationException;

}
