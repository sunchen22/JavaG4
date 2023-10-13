package com.commentlike.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dinerratingcomment.entity.DinerRatingComment;
import com.userinfo.entity.UserInfo;
@Entity
@Table(name="commentlike")
public class CommentLike {

	public CommentLike() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="commentLikeID", updatable = false)
	private Integer commentLikeID;
	
	@ManyToOne
	@JoinColumn(name = "commentID", referencedColumnName = "commentID")
	private DinerRatingComment dinerRatingComment;
	
	@ManyToOne
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	private UserInfo userInfo;
	
	@Column(name="likeCount")
	private Integer likeCount;

	public Integer getCommentLikeID() {
		return commentLikeID;
	}

	public void setCommentLikeID(Integer commentLikeID) {
		this.commentLikeID = commentLikeID;
	}

	public DinerRatingComment getDinerRatingComment() {
		return dinerRatingComment;
	}

	public void setDinerRatingComment(DinerRatingComment dinerRatingComment) {
		this.dinerRatingComment = dinerRatingComment;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

}
