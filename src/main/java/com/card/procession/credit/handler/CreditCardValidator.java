package com.card.procession.credit.handler;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreditCardValidator implements ConstraintValidator<CreditCard, String> {

  @Override
  public boolean isValid(String cardNum, ConstraintValidatorContext constraintValidatorContext) {
    if(cardNum == null) return false;
    char[] num = cardNum.toCharArray();
    Integer sum = 0;
    boolean isSecond = false;
    for(int i= num.length-1; i>=0; i--){
      int d = num[i]- '0';
      if(isSecond){
        d = d * 2;
      }
      sum += d/10;
      sum += d%10;
      isSecond = !isSecond;
    }

    return (sum%10==0);
  }
}
