package com.tw.serviceImpl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tw.dto.ItemDto;
import com.tw.entity.Item;
import com.tw.generics.Code;
import com.tw.generics.Response;
import com.tw.repository.ItemRepository;
import com.tw.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepo;

	@SuppressWarnings("deprecation")
	@Override
	public ResponseEntity<?> saveItem(ItemDto dto) {

		String message = "created success!";
		Item item = new Item();
		if (dto.getId() != null && dto.getId() > 0) {
			item = itemRepo.getById(dto.getId());
			item.setId(dto.getId());
			item.setModified(Calendar.getInstance());
			message = "updated success!";
		} else {
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
	public ResponseEntity<?> getItems() {

		List<Item> list = itemRepo.findAll();

		List<ItemDto> dtoList = list.stream().map(item -> {
			ItemDto itemDto = new ItemDto();
			BeanUtils.copyProperties(item, itemDto);
			return itemDto;
		}).collect(Collectors.toList());

		return Response.build(Code.OK, dtoList);
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

}
