package kr.ac.kopo.biz.account;

public class AccountVO {
	
	private String accountNum;
	private String id;
	private int password;
	private String type;
	private int money;
	private String bankCode;
	private String productID;
	
	
	
	
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "AccountVO [accountNum=" + accountNum + ", id=" + id + ", password=" + password + ", type=" + type
				+ ", money=" + money + "]";
	}
	
	
}
