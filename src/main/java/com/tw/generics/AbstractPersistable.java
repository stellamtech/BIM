package com.tw.generics;

import java.util.Calendar;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractPersistable implements IEntity<Long>, Cloneable {
	
	private static final long serialVersionUID = 7660632849184900495L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "created", nullable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar created;
	
	@Column(name = "modified", nullable = false)
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar modified;
	
	@Column(name = "deleted", nullable = false)
	private Boolean deleted = false;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	@Override
	public boolean isNew() {
		return this.id == null;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public Calendar getModified() {
		return modified;
	}

	public void setModified(Calendar modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return String.format("Entity of type %s with rowID: %s", this.getClass().getName(), getId());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (this == obj) { return true; }
		if (!getClass().equals(obj.getClass())) { return false; }
		AbstractPersistable rhs = (AbstractPersistable) obj;
		return this.id == null ? false : this.id.equals(rhs.id);
	}
	
	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += (this.id == null) ? 0 : this.id.hashCode() * 31;
		return hashCode;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}

