package com.card.procession.credit.entity;

import com.card.procession.credit.handler.CreditCard;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="credit_account")
public class CreditAccount {

  @Id
  @Column(name="card_num")
  @NotNull(message = "Card Number is missing")
  @NotEmpty(message = "Card Number is blank")
  @Pattern(regexp="-?\\d+(\\.\\d+)?", message = "must be numeric")
  @Length(min=1,max=19,message = "must have 19 digits")
  @CreditCard
  private String cardNum;

  @NotEmpty(message = "Name is missing")
  @Column(name="accholder_name")
  private String name;

  private BigDecimal balance;

  @NotNull(message = "Limit is missing")
  @Column(name="card_limit")
  private BigDecimal cardLimit;
}
