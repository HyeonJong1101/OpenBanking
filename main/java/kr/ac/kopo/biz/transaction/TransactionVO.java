package kr.ac.kopo.biz.transaction;

public class TransactionVO {
	
	private int transactionID;
	private int accountNum;
	private int accountNum2;
	private String transactiontype;
	private int amount;
	private String regdate;
	
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public int getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	public int getAccountNum2() {
		return accountNum2;
	}
	public void setAccountNum2(int accountNum2) {
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
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "TransactionVO [transactionID=" + transactionID + ", accountNum=" + accountNum + ", accountNum2="
				+ accountNum2 + ", transactiontype=" + transactiontype + ", amount=" + amount + ", regdate=" + regdate
				+ "]";
	}
	
	
	
}
