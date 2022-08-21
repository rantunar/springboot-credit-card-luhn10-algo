package com.card.procession.credit.controllers;

import com.card.procession.credit.entity.CreditAccount;
import com.card.procession.credit.handler.DBOperationException;
import com.card.procession.credit.service.CreditAccountService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit-card")
@CrossOrigin
@Slf4j
public class CreditCardController {

  @Autowired
  CreditAccountService creditAccountService;

  @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CreditAccount> addCard(@Valid @RequestBody CreditAccount creditAccount)
      throws DBOperationException {
    log.info("credit account add invoked for > ",creditAccount.getCardNum());
    return new ResponseEntity<>(creditAccountService.addCreditAccount(creditAccount), HttpStatus.OK);
  }

  @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CreditAccount>> getCards() throws DBOperationException {
    log.info("credit account get-all invoked.");
    return new ResponseEntity<>(creditAccountService.getAllCreditAccount(),HttpStatus.OK);
  }
}
