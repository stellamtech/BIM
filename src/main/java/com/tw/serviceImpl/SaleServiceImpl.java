package com.tw.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tw.commands.RemoteControl;
import com.tw.commands.SaleStock;
import com.tw.commands.account.RemoteControlAccounts;
import com.tw.commands.account.SaleAccounts;
import com.tw.commands.history.RemoteControlHistory;
import com.tw.commands.history.SaleStockHistory;
import com.tw.conv.SaleConverter;
import com.tw.dto.AccountHistoryDto;
import com.tw.dto.ItemDto;
import com.tw.dto.PageDto;
import com.tw.dto.SaleDto;
import com.tw.dto.SaleEditDto;
import com.tw.dto.SaleItemDto;
import com.tw.dto.SaleListDto;
import com.tw.dto.StockDto;
import com.tw.dto.StockHistoryDto;
import com.tw.dto.StockInOutDto;
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
import com.tw.service.StockService;
import com.tw.spec.SaleSpecDto;
import com.tw.spec.SaleSpecification;
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
	
	@Autowired
	private SaleAccounts saleAccounts;
	
	@Autowired
	private RemoteControlAccounts remoteControlAccounts;
	
	@Autowired
	private StockService stockService;

	@SuppressWarnings("deprecation")
	@Override
	public ResponseEntity<?> addSale(SaleDto dto) {
		logger.info("Adding The Sale Invoice::");
		Sale sale = new Sale();
		if (dto.getId() != null && dto.getId() > 0) {
			sale = saleRepository.getOne(dto.getId());
			sale.setSaleno(dto.getSaleno());
			sale.setModified(Calendar.getInstance());
		} else {
			String voucherNo = entityIdService.getvocher(Constants.SALE_INVOICE);
			sale.setSaleno(voucherNo);
			sale.setCreated(Calendar.getInstance());
			sale.setModified(Calendar.getInstance());
		}

		Optional<Customer> customer = java.util.Optional.empty() ;
		if (dto.getCustomerId() != null && dto.getCustomerId() > 0) {
			customer= customerRepo.findById(dto.getCustomerId());
		}
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
			String voucherNo = entityIdService.getvocher(Constants.CUSTOMER_NO);
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
			if (i.getId() != null && i.getId() > 0) {
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
		
		AccountHistoryDto a=new AccountHistoryDto();
		a.setSaleId(sale1.getId());
		a.setType(Constants.SALE_INVOICE);
		a.setVoucherno(sale1.getSaleno());
		a.setNetAmt(sale1.getNetAmt());
		a.setPaidAmt(sale1.getPaidAmt());
		a.setRemainingAmt(sale1.getRemainingAmt());
		remoteControlAccounts.addaccounts(saleAccounts, a);

		return Response.build(Code.CREATED, Messages.SALE_INVOICE_ADDED);
	}

	@Override
	@SuppressWarnings("deprecation")
	public ResponseEntity<?> getSale(Long id) {
		logger.info("Fetch Sale Invoice By Id !");
		Sale s = saleRepository.getOne(id);
		SaleEditDto dto = new SaleEditDto();
		BeanUtils.copyProperties(s, dto);
		return Response.build(Code.OK, dto);
	}

	@Override
	@SuppressWarnings("deprecation")
	public ResponseEntity<?> deleteSale(Long id) {
		logger.info("Fetch Sale Invoice By Id !");
		Sale s = saleRepository.getOne(id);
		s.setDeleted(Constants.SOFT_DELETE);
		saleRepository.save(s);
		return Response.build(Code.OK, Messages.DELETED);
	}

	@Override
	public ResponseEntity<?> getbyCustomer(Long id) {
		logger.info("Fetching Purchase vendor List :: ");
		List<Sale> list = saleRepository.findBycustomerIdAndPaidflagAndStatus(id, false, Constants.AUTHORIZED);
		List<SaleListDto> dtoList = list.stream().map(sale -> {
			SaleListDto saleDto = new SaleListDto();
			BeanUtils.copyProperties(sale, saleDto);
			return saleDto;
		}).collect(Collectors.toList());

		return Response.build(Code.OK, dtoList);
	}

	@Override
	@SuppressWarnings("deprecation")
	public ResponseEntity<?> deleteitem(Long id) {
		logger.info("Delleting Sale items Softly !");
		SaleItem sqt = saleItemRepository.getOne(id);
		sqt.setDeleted(Constants.SOFT_DELETE);
		saleItemRepository.save(sqt);
		return Response.build(Code.OK, Messages.DELETED);
	}

	@Override
	public ResponseEntity<?> getPrint(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getSaleList(SaleSpecDto spectDto) {

		logger.info("fetching List of Sales !");
		PageRequest pg = PageRequest.of(spectDto.getPage() - 1, spectDto.getSize(), Direction.DESC,
				com.tw.generics.AppConstants.MODIFIED);
		
		Specification<Sale> spec = SaleSpecification.buildSpecification(spectDto);
		Page<Sale> sales = saleRepository.findAll(spec, pg);

		List<SaleListDto> list = sales.stream().map(new SaleConverter()).collect(Collectors.toList());

		PageDto pageDto = new PageDto(list, sales.getTotalElements());
		return Response.build(Code.OK, pageDto);

	}

	@SuppressWarnings("deprecation")
	@Override
	public ResponseEntity<?> salePayment(AccountHistoryDto dto) {

		Sale sale = new Sale();
		if (dto.getSaleId() != null && dto.getSaleId() > 0) {
			sale = saleRepository.getOne(dto.getSaleId());
			sale.setModified(Calendar.getInstance());
			sale.setRemainingAmt(dto.getRemainingAmt());

			double paidAmt = sale.getPaidAmt();
			sale.setPaidAmt(paidAmt + dto.getPaidAmt());
			if (dto.getRemainingAmt() <= 0) {
				sale.setPaidflag(Boolean.TRUE);
			}
			if(dto.getStockReturn().equals("Y") && !sale.getStockflag()){
					
				for (SaleItem item : sale.getSaleItem()) {
					StockInOutDto in=new StockInOutDto();
					in.setDate(Calendar.getInstance());
					in.setItemId(item.getItem().getId());
					in.setQty(item.getQty());
					in.setStockin(item.getQty());
					in.setType("in");
					
					stockService.adjustStock(in);
				}
				sale.setStockflag(Boolean.TRUE);	
			}
			Sale sale1 = saleRepository.save(sale);
			AccountHistoryDto a = new AccountHistoryDto();
			a.setSaleId(sale1.getId());
			a.setType(Constants.SALE_PAYMENT);
			a.setVoucherno(sale1.getSaleno());
			a.setNetAmt(dto.getNetAmt());
			a.setPaidAmt(dto.getPaidAmt());
			a.setRemainingAmt(dto.getRemainingAmt());
			remoteControlAccounts.addaccounts(saleAccounts, a);
			return Response.build(Code.CREATED, Messages.SALE_PAYMENT_ADDED);
		} else {
			return Response.build(Code.INTERNAL_SERVER_ERROR, Messages.SALE_INVOICE_NOT_AVLB);
		}

	}

}
