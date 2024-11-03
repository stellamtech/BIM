package com.tw.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tw.conv.CustomerConvertor;
import com.tw.dto.CustomerWiseDto;
import com.tw.dto.ItemRepSpecDto;
import com.tw.dto.ReportSpecDto;
import com.tw.dto.SaleItemReportDto;
import com.tw.entity.Sale;
import com.tw.entity.SaleItem;
import com.tw.generics.Code;
import com.tw.generics.Response;
import com.tw.repository.SaleRepository;
import com.tw.service.ReportService;
import com.tw.spec.CustomerWiseSpecification;
import com.tw.spec.ItemWiseSpecification;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

	@Autowired
	private SaleRepository saleRepository;

	@Override
	public ResponseEntity<?> customerWise(ReportSpecDto dto) {
		Specification<Sale> spec = CustomerWiseSpecification.buildSpecification(dto);
		List<Sale> r = saleRepository.findAll(spec);
		List<CustomerWiseDto> listReportDto = r.stream().map(new CustomerConvertor()).collect(Collectors.toList());
		return Response.build(Code.OK, listReportDto);
	}

	@Override
	public ResponseEntity<?> itemWise(ItemRepSpecDto dto) {
		Specification<Sale> spec = ItemWiseSpecification.buildSpecification(dto);
		List<Sale> sale = saleRepository.findAll(spec);
		List<SaleItemReportDto> res = new ArrayList<>();
		for (Sale obj : sale) {
			for (SaleItem item : obj.getSaleItem()) {
				SaleItemReportDto s = new SaleItemReportDto();
				s.setCustomerName(obj.getCustomerName());
				s.setCustomerMobile(obj.getCustomerMobile());
				s.setNetAmt(obj.getNetAmt());
				s.setPaidAmt(obj.getPaidAmt());
				s.setPaidflag(obj.getPaidflag());
				s.setRemainingAmt(obj.getRemainingAmt());
				s.setSaleDate(obj.getSaledate());
				s.setSaleNo(obj.getSaleno());
				s.setStatus(obj.getStatus());
				s.setStockflag(obj.getStockflag());

				s.setItemName(item.getItem().getItemName());
				s.setItemAmount(item.getAmount());
				s.setQty(item.getQty());
				s.setUnitPrice(item.getUnitPrice());
				res.add(s);
			}
		}

		return Response.build(Code.OK, res);
	}

}
