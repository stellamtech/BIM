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
import org.springframework.transaction.annotation.Transactional;

import com.tw.conv.CustomerCovertor;
import com.tw.dto.CustomerDto;
import com.tw.dto.CustomerSpecDto;
import com.tw.entity.Customer;
import com.tw.generics.AppConstants;
import com.tw.generics.Code;
import com.tw.generics.Messages;
import com.tw.generics.Response;
import com.tw.repository.CustomerRepository;
import com.tw.service.CustomerService;
import com.tw.spec.CustomerSpec;
import com.tw.utility.Constants;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public ResponseEntity<?> saveCustomer(CustomerDto dto) {

		logger.info("Adding The Customer data::");
		Customer customer = new Customer();

		if (dto.getId() != null && dto.getId() > 0) {
			customer = customerRepository.getOne(dto.getId());
			customer.setModified(Calendar.getInstance());
		} else {
			customer.setModified(Calendar.getInstance());
			customer.setCreated(Calendar.getInstance());
		}
		customer.setAddress(dto.getAddress());
		customer.setCustomerName(dto.getCustomerName());
		customer.setFirstName(dto.getFirstName());
		customer.setLastName(dto.getLastName());
		customer.setMobileNo(dto.getMobileNo());
		customer.setRemark(dto.getRemark());

		customerRepository.save(customer);
		if (dto.getId() != null && dto.getId() > 0) {
			return Response.build(Code.CREATED, Messages.UPDATED_MSG);
		} else {
			return Response.build(Code.CREATED, Messages.CREATED_MSG);
		}
	}

	@Override
	public ResponseEntity<?> getCustomerById(Long id) {
		logger.info("Fetch Customer By Id !");
		Optional<Customer> obj = customerRepository.findById(id);
		CustomerDto dto = new CustomerDto();
		BeanUtils.copyProperties(obj.get(), dto);
		return Response.build(Code.OK, dto);
	}

	@Override
	public ResponseEntity<?> deleteCustomerById(Long id) {
		logger.info("Delleting Customer By Id!");
		Customer c = customerRepository.getById(id);
		c.setDeleted(Constants.SOFT_DELETE);
		customerRepository.save(c);
		return Response.build(Code.OK, Messages.DELETED);
	}

	@Override
	public ResponseEntity<?> getCustomerList(CustomerSpecDto dto) {
	    logger.info("Fetching Customer List :: ");
	    PageRequest pg = PageRequest.of(dto.getPage() - 1, dto.getSize(), Direction.DESC, AppConstants.MODIFIED);

	    Page<Customer> customer = customerRepository.findAll(new CustomerSpec(dto.getCustomerName(), dto.getMobileNo()), pg);
	    List<CustomerDto> list = customer.stream().map(new CustomerCovertor()).collect(Collectors.toList());
	    return Response.build(Code.OK, list);
	}

}
