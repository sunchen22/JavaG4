package com.dinerratingcomment.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dinerinfo.entity.DinerInfo;



import com.userinfo.entity.UserInfo;
@Entity
@Table(name="dinerratingcomment")

public class DinerRatingComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="commentID", updatable = false)
	private Integer commentID;
	
	@ManyToOne
	@JoinColumn(name = "dinerID", referencedColumnName = "dinerID")
	private DinerInfo dinerInfo;
	
	@ManyToOne
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	private UserInfo userInfo;
	
	@Column(name = "dinerRating")
	private Integer dinerRating;
	
	@Column(name = "userCommentContent")
	private String userCommentContent ;
	
	@Column(name = "userCommentTime")
	private Timestamp userCommentTime;
	
	@Column(name = "dinerReplyContent")
	private String  dinerReplyContent;
	
	@Column(name = "dinerReplyTime")
	private Timestamp dinerReplyTime;
	
	@Column(name = "dinerRatingCommentStatus")
		private Integer dinerRatingCommentStatus ;
	
	public DinerRatingComment() {
		super(); 
	}

	public Integer getCommentID() {
		return commentID;
	}

	public void setCommentID(Integer commentID) {
		this.commentID = commentID;
	}

	public DinerInfo getDinerInfo() {
		return dinerInfo;
	}

	public void setDinerInfo(DinerInfo dinerInfo) {
		this.dinerInfo = dinerInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Integer getDinerRating() {
		return dinerRating;
	}

	public void setDinerRating(Integer dinerRating) {
		this.dinerRating = dinerRating;
	}

	public String getUserCommentContent() {
		return userCommentContent;
	}

	public void setUserCommentContent(String userCommentContent) {
		this.userCommentContent = userCommentContent;
	}

	public Timestamp getUserCommentTime() {
		return userCommentTime;
	}

	public void setUserCommentTime(Timestamp userCommentTime) {
		this.userCommentTime = userCommentTime;
	}

	public String getDinerReplyContent() {
		return dinerReplyContent;
	}

	public void setDinerReplyContent(String dinerReplyContent) {
		this.dinerReplyContent = dinerReplyContent;
	}

	public Timestamp getDinerReplyTime() {
		return dinerReplyTime;
	}

	public void setDinerReplyTime(Timestamp dinerReplyTime) {
		this.dinerReplyTime = dinerReplyTime;
	}
	
	
	
	
	public Integer getDinerRatingCommentStatus() {
		return dinerRatingCommentStatus;
	}

	public void setDinerRatingCommentStatus(Integer dinerRatingCommentStatus) {
		this.dinerRatingCommentStatus = dinerRatingCommentStatus;
	}

	@Override
	public String toString() {
		return "DinerRatingComment [commentID=" + commentID + ", dinerInfo=" + dinerInfo + ", userInfo=" + userInfo
				+ ", dinerRating=" + dinerRating + ", userCommentContent=" + userCommentContent + ", userCommentTime="
				+ userCommentTime + ", dinerReplyContent=" + dinerReplyContent + ", dinerReplyTime=" + dinerReplyTime
				+ "]";
	}


	

	

	
	
	

}
