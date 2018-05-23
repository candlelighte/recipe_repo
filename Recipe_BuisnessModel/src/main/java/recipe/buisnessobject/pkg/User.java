package recipe.buisnessobject.pkg;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "User", uniqueConstraints = { @UniqueConstraint(columnNames = { "IdUser" }) })
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1845296961773664204L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdUser", nullable = false, unique = true, length = 11)
	private Long id;

	@Column(name = "Login", nullable = false, unique = true, length = 255)
	private String login;

	@Column(name = "Password", nullable = false, length = 255)
	private String password;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + "]";
	}

}
