package ru.mipt.hsse.course1.jpa.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="user_table")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(unique=true)
	private String name;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Order> orders;
}
