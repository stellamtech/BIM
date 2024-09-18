package com.tw.serviceImpl;

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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tw.conv.ItemCovertor;
import com.tw.dto.ItemDto;
import com.tw.dto.ItemSpecDto;
import com.tw.dto.PageDto;
import com.tw.entity.Item;
import com.tw.entity.Stock;
import com.tw.exception.ItemAlreadyExistsException;
import com.tw.exception.ItemCodeAlreadyExistsException;
import com.tw.generics.Code;
import com.tw.generics.Messages;
import com.tw.generics.Response;
import com.tw.repository.ItemRepository;
import com.tw.repository.StockRepository;
import com.tw.service.ItemService;
import com.tw.spec.ItemSpec;

@Service
public class ItemServiceImpl implements ItemService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private StockRepository stockRepository;

	@SuppressWarnings("deprecation")
	@Override
	public ResponseEntity<?> saveItem(ItemDto dto) throws ItemAlreadyExistsException, ItemCodeAlreadyExistsException {

		String message = "created success!";
		Item item = new Item();
		Optional<Item> optItem;
		if (dto.getId() != null && dto.getId() > 0) {
			item = itemRepo.getById(dto.getId());
			if (!dto.getItemName().equals(item.getItemName())) {
				optItem = itemRepo.findOneByItemName(dto.getItemName());
				if (optItem.isPresent()) {
					logger.info("Product already Exist");
					throw new ItemAlreadyExistsException(Messages.ITEM_AlREADY);
				}
			}
			if (!dto.getItemCode().equals(item.getItemCode())) {
				optItem = itemRepo.findOneByItemCode(dto.getItemCode());

				if (optItem.isPresent()) {
					logger.info("Item Code already Exist");
					throw new ItemCodeAlreadyExistsException(Messages.ITEM_CODE_AlREADY);
				}
			}
			item.setId(dto.getId());
			item.setModified(Calendar.getInstance());
			message = "updated success!";
		} else {

			optItem = itemRepo.findOneByItemName(dto.getItemName());

			if (optItem.isPresent()) {
				logger.info("Product already Exist");
				throw new ItemAlreadyExistsException(Messages.ITEM_AlREADY);
			}

			optItem = itemRepo.findOneByItemCode(dto.getItemCode());

			if (optItem.isPresent()) {
				logger.info("Product Code already Exist");
				throw new ItemCodeAlreadyExistsException(Messages.ITEM_CODE_AlREADY);
			}
			item.setModified(Calendar.getInstance());
			item.setCreated(Calendar.getInstance());
		}

		item.setItemCode(dto.getItemCode());
		item.setItemName(dto.getItemName());
		item.setItemPrice(dto.getItemPrice());

		itemRepo.save(item);
		return Response.build(Code.OK, message);
	}

	@Override
	public ResponseEntity<?> getItems(ItemSpecDto spectDto) {
		logger.info("fetching List of Products !");
		PageRequest pg = PageRequest.of(spectDto.getPage() - 1, spectDto.getSize(), Direction.DESC,
				com.tw.generics.AppConstants.MODIFIED);

		Page<Item> items = itemRepo.findAll(new ItemSpec(spectDto.getItemName(), spectDto.getItemCode(), spectDto.getType()),
				pg);
		List<ItemDto> list = items.stream().map(new ItemCovertor()).collect(Collectors.toList());
		for (ItemDto i : list) {
			Optional<Stock> stockop = stockRepository.findOneByItemId(i.getId());
			if (stockop.isPresent()) {
				Stock s = stockop.get();
				i.setStockinhand(s.getStockqty());
			}
		}
		PageDto pageDto = new PageDto(list, items.getTotalElements());
		return Response.build(Code.OK, pageDto);
	}

	@Override
	public ResponseEntity<?> getItemById(Long id) {
		Optional<Item> obj = itemRepo.findById(id);
		ItemDto itemDto = new ItemDto();
		BeanUtils.copyProperties(obj.get(), itemDto);
		return Response.build(Code.OK, itemDto);
	}

	@Override
	public ResponseEntity<?> deleteItem(Long id) {
		@SuppressWarnings("deprecation")
		Item one = itemRepo.getOne(id);
		one.setDeleted(true);
		itemRepo.save(one);
		return Response.build(Code.OK, "Deleted successfully!");
	}

	@Override
	public ResponseEntity<?> getlistItems() {
		List<Item> list = itemRepo.findAll();

		List<ItemDto> dtoList = list.stream().map(item -> {
			ItemDto itemDto = new ItemDto();
			BeanUtils.copyProperties(item, itemDto);
			Optional<Stock> stockop = stockRepository.findOneByItemId(item.getId());
			if (stockop.isPresent()) {
				Stock s = stockop.get();
				itemDto.setStockinhand(s.getStockqty());
			}
			return itemDto;
		}).collect(Collectors.toList());

		return Response.build(Code.OK, dtoList);

	}

}
