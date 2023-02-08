package ru.mipt.hsse.course1.jpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Table(name="order_table")
@Entity
@Data
@ToString(exclude="user")
public class Order {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne
	@JsonBackReference
	private User user;

	@ManyToOne
	private Item item;

	private Integer count;
}
