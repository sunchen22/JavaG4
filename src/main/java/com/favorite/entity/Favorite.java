package com.favorite.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.userInfo.entity.UserInfo;

public class Favorite {

	public Favorite() {	
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="favoriteID", updatable = false)
	private Integer favoriteID;
	
	@ManyToOne
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	private UserInfo userInfo;
	
	@ManyToOne
	@JoinColumn(name = "dinerID", referencedColumnName = "dinerID")
	private DinerInfo dinerInfo;
	
	@Column(name="favoriteTime")
	private Timestamp favoriteTime;
	
	public Favorite(Integer favoriteID, UserInfo userInfo, DinerInfo dinerInfo , Timestamp favoriteTime) {
		super();
		this.favoriteID = favoriteID;
		this.userInfo = userInfo;
		this.dinerInfo = dinerInfo;
		this.favoriteTime = favoriteTime;
	}

	public Integer getFavoriteID() {
		return favoriteID;
	}

	public void setFavoriteID(Integer favoriteID) {
		this.favoriteID = favoriteID;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public DinerInfo getDinerInfo() {
		return dinerInfo;
	}

	public void setDinerInfo(DinerInfo dinerInfo) {
		this.dinerInfo = dinerInfo;
	}

	public Timestamp getFavoriteTime() {
		return favoriteTime;
	}

	public void setFavoriteTime(Timestamp favoriteTime) {
		this.favoriteTime = favoriteTime;
	}

}
