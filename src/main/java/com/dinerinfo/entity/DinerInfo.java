package com.dinerinfo.entity;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
=======

import javax.persistence.OneToMany;

import javax.persistence.Lob;

>>>>>>> branch 'nanjian' of https://github.com/sunchen22/JavaG4.git
import javax.persistence.Table;

<<<<<<< HEAD
import com.advertisement.entity.Advertisement;

=======
import com.dinerratingcomment.entity.DinerRatingComment;
>>>>>>> branch 'nanjian' of https://github.com/sunchen22/JavaG4.git

@Entity
@Table(name="DinerInfo")
public class DinerInfo {
	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.IDENTITY)
=======

	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	
>>>>>>> branch 'nanjian' of https://github.com/sunchen22/JavaG4.git
	@Column(name="dinerID", updatable = false)
	private Integer dinerID;
//	@OneToMany(mappedBy = "dinerinfo" , cascade = CascadeType.ALL)
//	private Set<DinerRatingComment> dinerID;
	
	
	
	@Column(name="dinerName")
	private String dinerName;
	
	@Column(name="dinerPassword")
	private String dinerPassword;
	
	@Column(name="dinerRegisterTime")
	private Timestamp dinerRegisterTime;
	
	@Column(name="dinerTaxID")
	private String dinerTaxID;
	
	@Column(name="dinerContact")
	private String dinerContact;
	
	@Column(name="dinerPhone")
	private String dinerPhone;
	
	@Column(name="dinerEmail")
	private String dinerEmail;
	
	@Column(name="dinerAddress")
	private String dinerAddress;
	
	@Column(name="dinerBank")
	private String dinerBank;
	
	@Column(name="dinerAccount")
	private String dinerAccount;
	
	@Column(name="dinerAccountName")
	private String dinerAccountName;
	
	@Column(name="dinerType")
	private String dinerType;
	
	@Column(name="dinerStatus")
	private String dinerStatus;
	
	@Column(name="dinerOrderThreshold")
	private Integer dinerOrderThreshold;

	@Column(name="dinerBlob",columnDefinition="longblob")
	private byte[] dinerBlob;

	
<<<<<<< HEAD
//	@OneToMany(mappedBy = "advertisement",cascade = CascadeType.ALL)
//	@OrderBy("dinerID asc")
//	private Set<Advertisement> advertisements;
=======
	
	public DinerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DinerInfo(Integer dinerID, String dinerName, String dinerPassword, Timestamp dinerRegisterTime,
			String dinerTaxID, String dinerContact, String dinerPhone, String dinerEmail, String dinerAddress,
			String dinerBank, String dinerAccount, String dinerAccountName, String dinerType, String dinerStatus,
			Integer dinerOrderThreshold, byte[] dinerBlob) {
		super();
		this.dinerID = dinerID;
		this.dinerName = dinerName;
		this.dinerPassword = dinerPassword;
		this.dinerRegisterTime = dinerRegisterTime;
		this.dinerTaxID = dinerTaxID;
		this.dinerContact = dinerContact;
		this.dinerPhone = dinerPhone;
		this.dinerEmail = dinerEmail;
		this.dinerAddress = dinerAddress;
		this.dinerBank = dinerBank;
		this.dinerAccount = dinerAccount;
		this.dinerAccountName = dinerAccountName;
		this.dinerType = dinerType;
		this.dinerStatus = dinerStatus;
		this.dinerOrderThreshold = dinerOrderThreshold;
		this.dinerBlob = dinerBlob;
	}
>>>>>>> branch 'nanjian' of https://github.com/sunchen22/JavaG4.git

	public Integer getDinerID() {
		return dinerID;
	}

	public void setDinerID(Integer dinerID) {
		this.dinerID = dinerID;
	}

	public String getDinerName() {
		return dinerName;
	}

	public void setDinerName(String dinerName) {
		this.dinerName = dinerName;
	}

	public String getDinerPassword() {
		return dinerPassword;
	}

	public void setDinerPassword(String dinerPassword) {
		this.dinerPassword = dinerPassword;
	}

	public Timestamp getDinerRegisterTime() {
		return dinerRegisterTime;
	}

	public void setDinerRegisterTime(Timestamp dinerRegisterTime) {
		this.dinerRegisterTime = dinerRegisterTime;
	}

	public String getDinerTaxID() {
		return dinerTaxID;
	}

	public void setDinerTaxID(String dinerTaxID) {
		this.dinerTaxID = dinerTaxID;
	}

	public String getDinerContact() {
		return dinerContact;
	}

	public void setDinerContact(String dinerContact) {
		this.dinerContact = dinerContact;
	}

	public String getDinerPhone() {
		return dinerPhone;
	}

	public void setDinerPhone(String dinerPhone) {
		this.dinerPhone = dinerPhone;
	}

	public String getDinerEmail() {
		return dinerEmail;
	}

	public void setDinerEmail(String dinerEmail) {
		this.dinerEmail = dinerEmail;
	}

	public String getDinerAddress() {
		return dinerAddress;
	}

	public void setDinerAddress(String dinerAddress) {
		this.dinerAddress = dinerAddress;
	}

	public String getDinerBank() {
		return dinerBank;
	}

	public void setDinerBank(String dinerBank) {
		this.dinerBank = dinerBank;
	}

	public String getDinerAccount() {
		return dinerAccount;
	}

	public void setDinerAccount(String dinerAccount) {
		this.dinerAccount = dinerAccount;
	}

	public String getDinerAccountName() {
		return dinerAccountName;
	}

	public void setDinerAccountName(String dinerAccountName) {
		this.dinerAccountName = dinerAccountName;
	}

	public String getDinerType() {
		return dinerType;
	}

	public void setDinerType(String dinerType) {
		this.dinerType = dinerType;
	}

	public String getDinerStatus() {
		return dinerStatus;
	}

	public void setDinerStatus(String dinerStatus) {
		this.dinerStatus = dinerStatus;
	}

	public Integer getDinerOrderThreshold() {
		return dinerOrderThreshold;
	}

	public void setDinerOrderThreshold(Integer dinerOrderThreshold) {
		this.dinerOrderThreshold = dinerOrderThreshold;
	}

	public byte[] getDinerBlob() {
		return dinerBlob;
	}

	public void setDinerBlob(byte[] dinerBlob) {
		this.dinerBlob = dinerBlob;
	}
<<<<<<< HEAD

//	public Set<Advertisement> getAdvertisements() {
//		return advertisements;
//	}
//
//	public void setAdvertisements(Set<Advertisement> advertisements) {
//		this.advertisements = advertisements;
//	}

	public DinerInfo() {
		super();
	}

	public DinerInfo(Integer dinerID, String dinerName, String dinerPassword, Timestamp dinerRegisterTime,
			String dinerTaxID, String dinerContact, String dinerPhone, String dinerEmail, String dinerAddress,
			String dinerBank, String dinerAccount, String dinerAccountName, String dinerType, String dinerStatus,
			Integer dinerOrderThreshold, byte[] dinerBlob) {
		super();
		this.dinerID = dinerID;
		this.dinerName = dinerName;
		this.dinerPassword = dinerPassword;
		this.dinerRegisterTime = dinerRegisterTime;
		this.dinerTaxID = dinerTaxID;
		this.dinerContact = dinerContact;
		this.dinerPhone = dinerPhone;
		this.dinerEmail = dinerEmail;
		this.dinerAddress = dinerAddress;
		this.dinerBank = dinerBank;
		this.dinerAccount = dinerAccount;
		this.dinerAccountName = dinerAccountName;
		this.dinerType = dinerType;
		this.dinerStatus = dinerStatus;
		this.dinerOrderThreshold = dinerOrderThreshold;
		this.dinerBlob = dinerBlob;

	}

	@Override
	public String toString() {
		return "DinerInfo [dinerID=" + dinerID + ", dinerName=" + dinerName + ", dinerPassword=" + dinerPassword
				+ ", dinerRegisterTime=" + dinerRegisterTime + ", dinerTaxID=" + dinerTaxID + ", dinerContact="
				+ dinerContact + ", dinerPhone=" + dinerPhone + ", dinerEmail=" + dinerEmail + ", dinerAddress="
				+ dinerAddress + ", dinerBank=" + dinerBank + ", dinerAccount=" + dinerAccount + ", dinerAccountName="
				+ dinerAccountName + ", dinerType=" + dinerType + ", dinerStatus=" + dinerStatus
				+ ", dinerOrderThreshold=" + dinerOrderThreshold + ", dinerBlob=" + Arrays.toString(dinerBlob)
				+ ", advertisements=" + "]";
	}
=======
	
	
	
	
>>>>>>> branch 'nanjian' of https://github.com/sunchen22/JavaG4.git
	

	
}
