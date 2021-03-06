package com.zl.pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 收款
 *
 * @author root
 */
@Component
@Scope("prototype")
public class Payee implements Serializable {
    /**
     * 贷方账号
     */
    private String creditorAcc;
    /**
     * 借方账号
     */
    private String debtor;
    /**
     * 借贷金额
     */
    private BigDecimal fund;
    /**
     * 贷方用户名
     */
    private String creditorName;
    /**
     * 借方用户名
     */
    private String debtorName;

    public String getCreditorAcc() {
        return creditorAcc;
    }

    public void setCreditorAcc(String creditorAcc) {
        this.creditorAcc = creditorAcc;
    }

    public String getDebtor() {
        return debtor;
    }

    public void setDebtor(String debtor) {
        this.debtor = debtor;
    }

    public BigDecimal getFund() {
        return fund;
    }

    public void setFund(BigDecimal fund) {
        this.fund = fund;
    }

    public String getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    @Override
    public String toString() {
        return "Payee{" +
                "creditorAcc='" + creditorAcc + '\'' +
                ", debtor='" + debtor + '\'' +
                ", fund=" + fund +
                ", creditorName='" + creditorName + '\'' +
                ", debtorName='" + debtorName + '\'' +
                '}';
    }
}
