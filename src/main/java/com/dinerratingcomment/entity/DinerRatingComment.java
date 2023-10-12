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

@Entity
@Table(name="DinerRatingComment")
public class DinerRatingComment {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "commentID")
	private Integer commentID;
	
//	@ManyToOne
//	@JoinColumn(name= "dinerID" , referencedColumnName = "dinerID")
	@Column(name = "dinerID")
	private Integer dinerID;
//	private DinerInfo dinerinfo;
	
	@Column(name = "userID")
	private Integer userID;
	
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

	public DinerRatingComment() {
		super();
		 
	}

	public DinerRatingComment(Integer commentID, Integer dinerID, Integer userID, Integer dinerRating,
			String userCommentContent, Timestamp userCommentTime, String dinerReplyContent, Timestamp dinerReplyTime) {
		super();
		this.commentID = commentID;
		this.dinerID = dinerID;
		this.userID = userID;
		this.dinerRating = dinerRating;
		this.userCommentContent = userCommentContent;
		this.userCommentTime = userCommentTime;
		this.dinerReplyContent = dinerReplyContent;
		this.dinerReplyTime = dinerReplyTime;
	}

	public Integer getCommentID() {
		return commentID;
	}

	public void setCommentID(Integer commentID) {
		this.commentID = commentID;
	}

	public Integer getDinerID() {
		return dinerID;
	}

	public void setDinerID(Integer dinerID) {
		this.dinerID = dinerID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
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

	

	

	
	
	

}
