package com.tw.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tw.commands.RemoteControl;
import com.tw.commands.SaleStock;
import com.tw.commands.history.RemoteControlHistory;
import com.tw.commands.history.SaleStockHistory;
import com.tw.dto.ItemDto;
import com.tw.dto.SaleDto;
import com.tw.dto.SaleItemDto;
import com.tw.dto.StockDto;
import com.tw.dto.StockHistoryDto;
import com.tw.entity.Customer;
import com.tw.entity.Item;
import com.tw.entity.Sale;
import com.tw.entity.SaleItem;
import com.tw.generics.Code;
import com.tw.generics.Messages;
import com.tw.generics.Response;
import com.tw.repository.CustomerRepository;
import com.tw.repository.ItemRepository;
import com.tw.repository.SaleItemRepository;
import com.tw.repository.SaleRepository;
import com.tw.service.EntityIdService;
import com.tw.service.SaleService;
import com.tw.utility.Constants;
import com.tw.utility.DecimalNumber;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private EntityIdService entityIdService;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private SaleItemRepository saleItemRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private RemoteControl remoteControl;

	@Autowired
	private RemoteControlHistory remoteControlHistory;

	@Autowired
	private SaleStock saleStock;

	@Autowired
	private SaleStockHistory saleStockHistory;

	@SuppressWarnings("deprecation")
	@Override
	public ResponseEntity<?> addSale(SaleDto dto) {
		logger.info("Adding The Sale Invoice::");
		Sale sale = new Sale();
		if (dto.getId() != null && dto.getId()>0) {
			sale = saleRepository.getOne(dto.getId());
			sale.setSaleno(dto.getSaleno());
			sale.setModified(Calendar.getInstance());
		} else {
			String voucherNo = entityIdService.getvocher(Constants.SALE_INVOICE);
			sale.setSaleno(voucherNo);
			sale.setCreated(Calendar.getInstance());
			sale.setModified(Calendar.getInstance());
		}

		if (dto.getCustomerId() != null && dto.getCustomerId() > 0) {

			Optional<Customer> customer = customerRepo.findById(dto.getCustomerId());
			if (customer.isPresent()) {
				Customer ct = customer.get();
				ct.setCustomerName(dto.getCustomerName() != null ? dto.getCustomerName() : ct.getCustomerName());
				ct.setMobileNo(dto.getCustomerMobile() != null ? dto.getCustomerMobile() : ct.getMobileNo());
				ct.setAddress(dto.getAddress() != null ? dto.getAddress() : ct.getAddress());
				ct.setModified(Calendar.getInstance());
				
				sale.setCustomerName(dto.getCustomerName() != null ? dto.getCustomerName() : ct.getCustomerName());
				sale.setCustomerMobile(dto.getCustomerMobile() != null ? dto.getCustomerMobile() : ct.getMobileNo());
				sale.setAddress(dto.getAddress() != null ? dto.getAddress() : ct.getAddress());
				
				customerRepo.save(ct);
				sale.setCustomer(ct);
			} else {
				String voucherNo = entityIdService.getvocher(Constants.CUSTOMER_CONTACT);
				Customer ct = new Customer();
				ct.setCustomerName(dto.getCustomerName());
				ct.setMobileNo(dto.getCustomerMobile());
				ct.setAddress(dto.getAddress());
				ct.setAccno(voucherNo);
				ct.setCreated(Calendar.getInstance());
				ct.setModified(Calendar.getInstance());

				sale.setCustomerName(dto.getCustomerName());
				sale.setCustomerMobile(dto.getCustomerMobile());
				sale.setAddress(dto.getAddress());
				customerRepo.save(ct);
				sale.setCustomer(ct);
			}

		}

		double discount = 0;
		double grossamt = 0;
		double netamt = 0;
		double paidamt = 0;

		double advamt = 0;
		if (dto.getDiscount() > 0) {
			discount = DecimalNumber.converttoDecimal(dto.getDiscount());
		}
		if (dto.getGrossAmt() > 0) {
			grossamt = DecimalNumber.converttoDecimal(dto.getGrossAmt());
		}
		if (dto.getNetAmt() > 0) {
			netamt = DecimalNumber.converttoDecimal(dto.getNetAmt());
		}
		if (dto.getPaidAmt() > 0) {
			paidamt = DecimalNumber.converttoDecimal(dto.getPaidAmt());
		}

		if (dto.getAdvanceAmt() > 0) {
			advamt = DecimalNumber.converttoDecimal(dto.getAdvanceAmt());
		}

		sale.setDiscount(discount);
		sale.setGrossAmt(grossamt);
		sale.setNetAmt(netamt);
		sale.setPaidAmt(paidamt);
		sale.setRemainingAmt(netamt - paidamt);
		sale.setAdvanceAmt(advamt);

		sale.setNaration(dto.getNaration());
		sale.setSaledate(dto.getSaledate());
		sale.setStatus(dto.getStatus());

		List<SaleItem> saleItem = new ArrayList<>();
		List<StockDto> stock = new ArrayList<StockDto>();
		for (SaleItemDto i : dto.getSaleItem()) {
			SaleItem s = new SaleItem();
			if (i.getId() != null && i.getId()>0) {
				s = saleItemRepository.getOne(i.getId());
			}
			s.setAmount(i.getAmount());
			s.setDiscount(i.getDiscount());
			s.setDiscountPerc(i.getDiscountPerc());
			s.setQty(i.getQty());
			s.setReference(i.getReference());
			s.setSale(sale);
			s.setTotal(i.getTotal());
			s.setUnitPrice(i.getUnitPrice());
			s.setModified(Calendar.getInstance());
			s.setCreated(Calendar.getInstance());

			Item item = itemRepository.getOne(i.getItemId());
			s.setItem(item);
			saleItem.add(s);

			StockDto st = new StockDto();

			ItemDto it = new ItemDto();
			BeanUtils.copyProperties(item, it);
			st.setSaleqty(i.getQty());
			st.setItem(it);
			stock.add(st);
		}
		sale.setSaleItem(saleItem);

		Sale sale1 = saleRepository.save(sale);
		remoteControl.addstock(saleStock, stock);

		StockHistoryDto t = new StockHistoryDto();
		t.setForengId(sale1.getId());
		remoteControlHistory.addstock(saleStockHistory, t);

		return Response.build(Code.CREATED, Messages.SALE_INVOICE_ADDED);
	}

	@Override
	public ResponseEntity<?> getSale(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteSale(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getbyCustomer(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteitem(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getPrint(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getIds(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
