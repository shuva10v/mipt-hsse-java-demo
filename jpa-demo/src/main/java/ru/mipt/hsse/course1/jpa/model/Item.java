package ru.mipt.hsse.course1.jpa.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(unique=true)
	private String name;
}
