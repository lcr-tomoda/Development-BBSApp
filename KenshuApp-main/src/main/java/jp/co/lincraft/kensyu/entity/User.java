package jp.co.lincraft.kensyu.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * ユーザーエンティティ
 */
@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ユーザーID
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	/**
	 * メールアドレス（ログインID）
	 */
	@NotEmpty(message = "メールアドレス（ログインID）を入力してください。")
	@Email(message = "メールアドレス（ログインID）はメールアドレスの形式で入力してください")
	@Column(name = "email")
	private String email;

	/**
	 * ユーザー名
	 */
	@NotEmpty(message = "ユーザー名を入力してください。")
	@Column(name = "userName")
	private String userName;

	/**
	 * パスワード
	 */
	@NotEmpty(message = "パスワードを入力してください。")
	@Column(name = "password")
	private String password;

	/**
	 * アイコン
	 */
	@Range(min=1, max=16, message = "アイコンを選択してください。")
	@Column(name = "icon")
	private Integer icon;
	
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
     * 更新日時
     */
	@Column(name = "deletedAt")
    private Timestamp deletedAt;

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
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email セットする email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName セットする userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return password
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return icon
	 */
	public Integer getIcon() {
		return icon;
	}

	/**
	 * @param icon セットする icon
	 */
	public void setIcon(Integer icon) {
		this.icon = icon;
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

	/**
	 * @return deletedAt
	 */
	public Timestamp getDeletedAt() {
		return deletedAt;
	}

	/**
	 * @param deletedAt セットする deletedAt
	 */
	public void setDeletedAt(Timestamp deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new GrantedAuthority() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return "USER";
			}
		});
		
		
		return grantedAuthorities;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
