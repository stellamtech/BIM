//package com.tw.entity;
//
//import org.hibernate.annotations.NotFound;
//import org.hibernate.annotations.NotFoundAction;
//import org.hibernate.annotations.Where;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.tw.generics.AbstractPersistablewithuser;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@SuppressWarnings("deprecation")
//@Entity
//@Table(name = "stock_history")
//@Where(clause = "deleted=false")
//public class StockHistory extends AbstractPersistablewithuser {
//	private static final long serialVersionUID = -8667731991640624887L;
//
//	@Column(name = "voucherno", nullable = true)
//	private String voucherno;
//
//	@Column(name = "type", nullable = true)
//	private String type;
//
//	@Column(name = "qty", nullable = true)
//	private double qty;
//
//	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
//	@JoinColumn(name = "purchase_id")
//	@JsonIgnore
//	@NotFound(action = NotFoundAction.IGNORE)
//	private GrnItem grnitem;
//
//	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
//	@JoinColumn(name = "sale_id")
//	@JsonIgnore
//	@NotFound(action = NotFoundAction.IGNORE)
//	private SaleItem saleitem;
//
//	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
//	@JoinColumn(name = "credir_id")
//	@JsonIgnore
//	@NotFound(action = NotFoundAction.IGNORE)
//	private CreditNoteItem credititem;
//
//	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
//	@JoinColumn(name = "debit_id")
//	@JsonIgnore
//	@NotFound(action = NotFoundAction.IGNORE)
//	private DebitNoteItem debititem;
//
//	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
//	@JoinColumn(name = "opening_id")
//	@JsonIgnore
//	@NotFound(action = NotFoundAction.IGNORE)
//	private OpeningStockItem ops;
//
//	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
//	@JoinColumn(name = "stock_inout_id")
//	@JsonIgnore
//	@NotFound(action = NotFoundAction.IGNORE)
//	private StockInOut sio;
//
//	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
//	@JoinColumn(name = "item_id")
//	@JsonIgnore
//	@NotFound(action = NotFoundAction.IGNORE)
//	private Item item;
//	
//	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
//	@JoinColumn(name = "contact_id")
//	@JsonIgnore
//	@NotFound(action = NotFoundAction.IGNORE)
//	private Contacts contacts;
//	
//	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
//	@JoinColumn(name = "delivery_id")
//	@JsonIgnore
//	@NotFound(action = NotFoundAction.IGNORE)
//	private DeliveryNoteItem deveryItem;
//	
//	@Column(name = "status")
//	private String status;
//
//	public String getVoucherno() {
//		return voucherno;
//	}
//
//	public void setVoucherno(String voucherno) {
//		this.voucherno = voucherno;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public double getQty() {
//		return qty;
//	}
//
//	public void setQty(double qty) {
//		this.qty = qty;
//	}
//
//	public GrnItem getGrnitem() {
//		return grnitem;
//	}
//
//	public void setGrnitem(GrnItem grnitem) {
//		this.grnitem = grnitem;
//	}
//
//	public SaleItem getSaleitem() {
//		return saleitem;
//	}
//
//	public void setSaleitem(SaleItem saleitem) {
//		this.saleitem = saleitem;
//	}
//
//	public CreditNoteItem getCredititem() {
//		return credititem;
//	}
//
//	public void setCredititem(CreditNoteItem credititem) {
//		this.credititem = credititem;
//	}
//
//	public DebitNoteItem getDebititem() {
//		return debititem;
//	}
//
//	public void setDebititem(DebitNoteItem debititem) {
//		this.debititem = debititem;
//	}
//
//	public OpeningStockItem getOps() {
//		return ops;
//	}
//
//	public void setOps(OpeningStockItem ops) {
//		this.ops = ops;
//	}
//
//	public StockInOut getSio() {
//		return sio;
//	}
//
//	public void setSio(StockInOut sio) {
//		this.sio = sio;
//	}
//
//	public Item getItem() {
//		return item;
//	}
//
//	public void setItem(Item item) {
//		this.item = item;
//	}
//
//	public Contacts getContacts() {
//		return contacts;
//	}
//
//	public void setContacts(Contacts contacts) {
//		this.contacts = contacts;
//	}
//
//	public DeliveryNoteItem getDeveryItem() {
//		return deveryItem;
//	}
//
//	public void setDeveryItem(DeliveryNoteItem deveryItem) {
//		this.deveryItem = deveryItem;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	
//}
