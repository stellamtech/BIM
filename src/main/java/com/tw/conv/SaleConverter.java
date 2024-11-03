package com.tw.conv;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.tw.dto.SaleItemDto;
import com.tw.dto.SaleListDto;
import com.tw.entity.Sale;
import com.tw.entity.SaleItem;

public class SaleConverter implements Function<Sale, SaleListDto> {

	@Override
	public SaleListDto apply(Sale t) {
		SaleListDto dto=new SaleListDto();
		
		dto.setCustomerMobile(t.getCustomerMobile());
		dto.setCustomerName(t.getCustomerName());
		dto.setDiscount(t.getDiscount());
		dto.setGrossAmt(t.getGrossAmt());
		dto.setId(t.getId());
		dto.setNetAmt(t.getNetAmt());
		dto.setPaidAmt(t.getPaidAmt());
		dto.setRemainingAmt(t.getRemainingAmt());
		dto.setSaledate(t.getSaledate());
		dto.setSaleno(t.getSaleno());
		dto.setStatus(t.getStatus());
		dto.setPaidflag(t.getPaidflag());
		dto.setStockflag(t.getStockflag());
		
		List<SaleItemDto> list=new ArrayList<>();
		for (SaleItem saleItem : t.getSaleItem()){
			
			SaleItemDto s=new SaleItemDto();
			s.setAmount(saleItem.getAmount());
			s.setDiscount(saleItem.getDiscount());
			s.setDiscountPerc(saleItem.getDiscountPerc());
			s.setId(saleItem.getId());
			s.setItemId(saleItem.getItem().getId());
			s.setItemName(saleItem.getItem().getItemName());
			s.setQty(saleItem.getQty());
			s.setReference(saleItem.getReference());
			s.setTotal(saleItem.getTotal());
			s.setUnitPrice(saleItem.getUnitPrice());	
			list.add(s);
		}
		
		dto.setSaleItemDto(list);
		return dto;
	}

}
