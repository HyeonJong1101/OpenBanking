package kr.ac.kopo.biz.transaction;

import java.util.Date;

public class TransactionVO {
	
	private int transactionID;
	private String accountNum;
	private String accountNum2;
	private String transactiontype;
	private int amount;
	private Date regdate;
	private String bankCode;
	private String bankCode_receive;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankCode_receive() {
		return bankCode_receive;
	}
	public void setBankCode_receive(String bankCode_receive) {
		this.bankCode_receive = bankCode_receive;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getAccountNum2() {
		return accountNum2;
	}
	public void setAccountNum2(String accountNum2) {
		this.accountNum2 = accountNum2;
	}
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "TransactionVO [transactionID=" + transactionID + ", accountNum=" + accountNum + ", accountNum2="
				+ accountNum2 + ", transactiontype=" + transactiontype + ", amount=" + amount + ", regdate=" + regdate
				+ "]";
	}
	
	
	
}
