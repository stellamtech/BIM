package com.tw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.Where;

import com.tw.generics.AbstractPersistable;

@SuppressWarnings("deprecation")
@Entity
@Table(name ="entity_id_repository")
@Where(clause = "deleted=false")
@Data
@EqualsAndHashCode(callSuper=false)
public class EntityIdRep extends AbstractPersistable {
	private static final long serialVersionUID = -8667731991640624887L;
	
	@Column(name ="last_sr_no", nullable = false)
	private Long lastsrno;
	
	@Column(name ="module_name", nullable = true)
	private String moduleName;
	
	@Column(name ="prefix", nullable = false)
	private String prefix;
	
	@Column(name ="suffix", nullable = true)
	private String suffix;
	
	@Column(name ="description", nullable = true)
	private String description;

	@Column(name ="value_", nullable = true)
	private String value;
	
	@Column(name ="key_", nullable = true)
	private String key;

}
