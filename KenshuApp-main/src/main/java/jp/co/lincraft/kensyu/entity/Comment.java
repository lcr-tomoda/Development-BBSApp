package jp.co.lincraft.kensyu.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * コメントエンティティ
 */
@Entity
@Data
@Table(name = "comments")
public class Comment {

	/**
	 * ユーザー情報
	 */
	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User user;

	/**
	 * ユーザー情報を取得する
	 * @return
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user ユーザー情報
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * コメントid
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	/**
	 * ユーザID
	 */
	@Column(name = "userId", insertable = true, updatable = true)
	private String userId;

	/**
	 * 投稿ID
	 */
	@Column(name = "postId")
	@NotEmpty(message = "不正なリクエストです。")
	private String postId;

	/**
	 * 投稿内容
	 */
	@Column(name = "commentText")
	@NotEmpty(message = "コメントを入力してください。")
	private String commentText;

	/**
	 * 作成日時
	 */
	@Column(name = "createdAt")
	private Timestamp createdAt;

	/**
	 * 更新日時
	 */
	@Column(name = "updatedAt")
	private Timestamp updatedAt;

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return postId
	 */
	public String getPostId() {
		return postId;
	}

	/**
	 * @param postId セットする postId
	 */
	public void setPostId(String postId) {
		this.postId = postId;
	}

	/**
	 * @return commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText セットする commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return createdAt
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt セットする createdAt
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return updatedAt
	 */
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt セットする updatedAt
	 */
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}