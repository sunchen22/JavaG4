package com.userinfo.entity;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.*;

import com.buildinginfo.entity.BuildingInfo;
import com.favorite.entity.Favorite;



@Entity
@Table(name="userInfo")
public class UserInfo implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userID", updatable = false)
	private Integer userID;
	
	@Column(name="userAccount")
	private String userAccount;
	
	@Column(name="userPassword")
	private String userPassword;

	@Column(name="userPhone")
	private String userPhone;

	@Column(name="userName")
	private String userName;

	@Column(name="userRegisterTime")
	private Timestamp userRegisterTime;

	@Column(name="userNickName")
	private String userNickName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "buildingID", referencedColumnName = "buildingID")
	private BuildingInfo buildinginfo;
//	@Column(name="buildingID")
//	private Integer buildingID;

	@Column(name="userBirthday")
	private Date userBirthday;
	
	@Column(name = "userBlob", columnDefinition = "longblob")
    private byte[] userBlob;
	
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	public String getUserName() {
		return userName;
	}
	
	@OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
	@OrderBy("favoriteTime")
	private Set<Favorite> favoriteSet;
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Timestamp getUserRegisterTime() {
		return userRegisterTime;
	}
	public void setUserRegisterTime(Timestamp userRegisterTime) {
		this.userRegisterTime = userRegisterTime;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	
	public BuildingInfo getBuildinginfo() {
		return buildinginfo;
	}
	public void setBuildinginfo(BuildingInfo buildinginfo) {
		this.buildinginfo = buildinginfo;
	}
	
	public Date getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}
	public byte[] getUserBlob() {
		return userBlob;
	}
	public void setUserBlob(byte[] userBlob) {
		this.userBlob = userBlob;
	}
	
	@Override
	public String toString() {
		return "UserInfo [userID=" + userID + ", userAccount=" + userAccount + ", userPassword=" + userPassword
				+ ", userPhone=" + userPhone + ", userName=" + userName + ", userRegisterTime=" + userRegisterTime
				+ ", userNickName=" + userNickName + ", buildinginfo=" + buildinginfo + ", userBirthday=" + userBirthday
				+ ", userBlob=" + Arrays.toString(userBlob) + "]";
	}

	public UserInfo() {
	}
	
	public UserInfo(Integer userID, String userAccount, String userPassword, String userPhone, String userName,
			Timestamp userRegisterTime, String userNickName, BuildingInfo buildinginfo, Date userBirthday,
			byte[] userBlob) {
		super();
		this.userID = userID;
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userName = userName;
		this.userRegisterTime = userRegisterTime;
		this.userNickName = userNickName;
		this.buildinginfo = buildinginfo;
		this.userBirthday = userBirthday;
		this.userBlob = userBlob;
	}
	

}
