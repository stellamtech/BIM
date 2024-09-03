package com.tw.utility;

public class Constants {
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "devglan123r";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final boolean SOFT_DELETE = true;
    
    public static final String STOCKIN = "in";
	public static final String STOCKOUT = "out";
	public static final String DIRECT = "Direct";
	
	public static final String SALE_ENQUIRY = "SALE ENQUIRY";
	public static final String SALE_QUOTATION = "SALE QUOTATION";
	public static final String SALE_ORDER = "SALE ORDER";
	public static final String DELIVERY_NOTE = "DELIVERY NOTE";
	public static final String SALE_INVOICE = "SALE INVOICE";
	public static final String PURCHASE_ORDER = "PURCHASE ORDER";
	public static final String PURCHASE_INVOICE = "PURCHASE INVOICE";
	public static final String CREDIT_NOTE = "CREDIT NOTE";
	public static final String DEBIT_NOTE = "DEBIT NOTE";
	public static final String CASH_PAYMENT = "CASH PAYMENT";
	public static final String BANK_PAYMENT = "BANK PAYMENT";
	public static final String CASH_RECEIPT = "CASH RECEIPT";
	public static final String BANK_RECEIPT = "BANK RECEIPT";
	public static final String JOURNAL_VOUCHER = "JOURNAL_VOUCHER";
	public static final String OPENING_BALANCE = "OPENING_BALANCE";
	
	public static final String CUSTOMER = "Customer";
	public static final String VENDOR = "Vendor";
	
	public static final String PAYMENTTYPE_CASH= "Cash";
	public static final String PAYMENTTYPE_BANK= "Bank";
	public static final String ROLE_SALE="ROLE_SALE";
	
	//Status
	public static final String SAVED="Saved";
	public static final String PENDING_APPROVAL_BY_MANAGER="Pending_Approval_by_Manager";
	public static final String PENDING_APPROVAL_BY_DIRECTOR="Pending_Approval_by_Director";
	public static final String AUTHORIZED="Authorized";
	public static final String UNAUTHORIZED_BY_MANAGER="Unauthorized_by_Manager";
	public static final String UNAUTHORIZED_BY_DIRECTOR="Unauthorized_by_Director";
	
	//Stage
	public static final String PO_STAGE="PURCHASE ORDER";
	public static final String GRN_STAGE="PURCHASE";
	public static final String SALE_ENQUIRY_STAGE = "SALE ENQUIRY";
	public static final String SALE_QUOTATION_STAGE = "SALE QUOTATION";
	public static final String SALE_ORDER_STAGE = "SALE ORDER";
	public static final String DELIVERY_NOTE_STAGE = "DELIVERY NOTE";
	public static final String SALE_INVOICE_STAGE = "SALE INVOICE";
	
	public static final String VENDOR_CONTACT="VENDOR_CONTACT";
	public static final String CUSTOMER_CONTACT="CUSTOMER_CONTACT";
	
	public static final String SALE_STOCK="SALE_INVOICE";
	public static final String PURCHASE_STOCK="PURCHASE_INVOICE";
	public static final String SALE_RETURN_STOCK="SALE_RETURN";
	public static final String PURCHASE_RETURN="PURCHASE_RETURN";
	public static final String OPENING_STOCK="OPENING_STOCK";
	public static final String STOCK_IN="STOCK_IN";
	public static final String STOCK_OUT="STOCK_OUT";
	public static final String ACCOUNTS="Accounts";
	
	public static final String CREDIT="Credit";
	public static final String DEBIT="Debit";
	public static final String JOURNAL_VOUCHER_CREDIT = "JOURNAL_VOUCHER_CREDIT";
	public static final String JOURNAL_VOUCHER_DEBIT = "JOURNAL_VOUCHER_DEBIT";
	public static final String OPENING_BALANCE_CREDIT = "OPENING_BALANCE_CREDIT";
	public static final String OPENING_BALANCE_DEBIT = "OPENING_BALANCE_DEBIT";
}
