package jp.co.lincraft.kensyu.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 投稿エンティティ
 */
@Entity
@Data
@Table(name = "posts")
public class Post {
	
	/**
	 * コメントid
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * ユーザID
	 */
	@Column(name = "userId")
	private String userId;

	/**
	 * タイトル
	 */
	@NotEmpty(message = "タイトルを入力してください。")
	@Column(name = "title")
	private String title;

	/**
	 * 投稿内容
	 */
	@NotEmpty(message = "投稿内容を入力してください。")
	@Column(name = "postText")
	private String postText;

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
	public int getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
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
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title セットする title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return postText
	 */
	public String getPostText() {
		return postText;
	}

	/**
	 * @param postText セットする postText
	 */
	public void setPostText(String postText) {
		this.postText = postText;
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
