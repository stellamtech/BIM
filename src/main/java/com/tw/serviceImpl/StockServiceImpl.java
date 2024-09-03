package com.tw.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tw.commands.RemoteControl;
import com.tw.commands.StockInOutCommand;
import com.tw.conv.StockInOutCovertor;
import com.tw.dto.ItemDto;
import com.tw.dto.ItemSpecDto;
import com.tw.dto.PageDto;
import com.tw.dto.StockDto;
import com.tw.dto.StockInOutDto;
import com.tw.entity.Item;
import com.tw.entity.Stock;
import com.tw.entity.StockInOut;
import com.tw.generics.Code;
import com.tw.generics.Messages;
import com.tw.generics.Response;
import com.tw.repository.ItemRepository;
import com.tw.repository.StockInOutRepository;
import com.tw.repository.StockRepository;
import com.tw.service.StockService;
import com.tw.spec.StockinOutSpec;
import com.tw.utility.Constants;

@Service
@Transactional
public class StockServiceImpl implements StockService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private StockInOutCommand stockInOutCommand;

	@Autowired
	private RemoteControl remoteControl;

	@Autowired
	private StockInOutRepository stockInOutRepository;

	@Autowired
	private StockRepository stockRepository;

	@SuppressWarnings("deprecation")
	@Override
	public ResponseEntity<?> adjustStock(StockInOutDto dto) {

		logger.info("Stock Details! ");

		StockInOut sio = new StockInOut();
		if (dto.getId() != null && dto.getId() > 0) {
			
			sio = stockInOutRepository.getById(dto.getId());
			sio.setModified(Calendar.getInstance());
		}else {
			sio.setModified(Calendar.getInstance());
			sio.setCreated(Calendar.getInstance());
		}
		
		Optional<Item> obj = itemRepository.findById(dto.getItemId());
		Item i=obj.get();
		sio.setItem(i);
		
		List<StockDto> stock = new ArrayList<>();
		for (int j = 0; j < 1; j++) {
			StockDto stockdto = new StockDto();
			ItemDto it = new ItemDto();

			BeanUtils.copyProperties(i, it);
			if (StringUtils.isNotEmpty(dto.getType())) {
				if (dto.getType().equals(Constants.STOCKIN)) {
					sio.setStockin(dto.getQty());
					sio.setQty(dto.getQty());
					sio.setType(dto.getType());
					stockdto.setStockin(dto.getQty());

				} else {
					sio.setStockout(dto.getQty());
					sio.setQty(dto.getQty());
					sio.setType(dto.getType());
					stockdto.setStockout(dto.getQty());
				}
				stockdto.setItem(it);
			}
			stock.add(stockdto);
		}
		sio.setDate(dto.getDate());
		sio.setReason(dto.getReason());
		sio.setRefno(dto.getRefno());

		stockInOutRepository.save(sio);
		remoteControl.addstock(stockInOutCommand, stock);
		return Response.build(Code.CREATED, Messages.STOCK_UPDATED);
	}

	@Override
	public ResponseEntity<?> byItemId(Long id) {
		logger.info("Stock By Item! ");
		Stock stock = stockRepository.findByItemId(id);
		StockDto dto = new StockDto();
		BeanUtils.copyProperties(stock, dto);
		return Response.build(Code.OK, dto);
	}

	@Override
	public ResponseEntity<?> byStockInOut(Long id) {
		logger.info("Stock By Stock Id! ");
		StockInOut stock = stockInOutRepository.getOne(id);
		StockInOutDto dto = new StockInOutDto();
		BeanUtils.copyProperties(stock, dto);
		return Response.build(Code.OK, dto);
	}

	@Override
	public ResponseEntity<?> stockList(ItemSpecDto spectDto) {
		logger.info("fetching List of Products !");
		PageRequest pg = PageRequest.of(spectDto.getPage() - 1, spectDto.getSize(), Direction.DESC,
				com.tw.generics.AppConstants.MODIFIED);
		Page<StockInOut> stock = stockInOutRepository.findAll(new StockinOutSpec(spectDto.getName(), spectDto.getCode()),
				pg);

		List<StockInOutDto> list = stock.stream().map(new StockInOutCovertor()).collect(Collectors.toList());
		PageDto pageDto = new PageDto(list, stock.getTotalElements());
		return Response.build(Code.OK, pageDto);
	}

}
