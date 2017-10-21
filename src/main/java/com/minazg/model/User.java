package com.minazg.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Data
public class User implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(unique=true, nullable=false)
	private String ssoId;
	
	@NotEmpty
	@Column(nullable=false)
	private String password;
		
	@NotEmpty
	@Column(nullable=false)
	private String firstName;

	@NotEmpty
	@Column(nullable=false)
	private String lastName;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "User_UserRole",
				joinColumns = {@JoinColumn(name = "user_id")},
				inverseJoinColumns = {@JoinColumn(name = "userRole_name")})
	private Set<UserRole> userRoles = new HashSet<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
