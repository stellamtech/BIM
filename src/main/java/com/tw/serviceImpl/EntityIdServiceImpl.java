package com.tw.serviceImpl;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tw.entity.Customer;
import com.tw.entity.EntityIdRep;
import com.tw.entity.Sale;
import com.tw.generics.Code;
import com.tw.generics.Response;
import com.tw.repository.CustomerRepository;
import com.tw.repository.EntityIdRepository;
import com.tw.repository.SaleRepository;
import com.tw.service.EntityIdService;
import com.tw.utility.Constants;

@Service
@Transactional
public class EntityIdServiceImpl implements EntityIdService {

	@Autowired
	private EntityIdRepository entityIdRepository;

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private CustomerRepository contactRepository;

	@Override
	public ResponseEntity<?> entityidrep(String type) {

		EntityIdRep repo = entityIdRepository.findOneByModuleName(type);
		Long srNo = 1L;
		if (repo == null) {
			repo = new EntityIdRep();
			repo.setLastsrno(srNo);
			repo.setModuleName(Constants.SALE_INVOICE);
			repo.setPrefix("SIN-");
			repo.setCreated(Calendar.getInstance());
			repo.setModified(Calendar.getInstance());
			entityIdRepository.save(repo);
			return Response.build(Code.OK, "SIN-1");

		} else {
			String ret = "";

			if (type.equals(Constants.SALE_INVOICE)) {
				Optional<Sale> si = saleRepository
						.findBySaleno(String.join("", repo.getPrefix(), String.valueOf(repo.getLastsrno())));
				if (si.isPresent()) {
					srNo += repo.getLastsrno();
					repo.setLastsrno(srNo);
					entityIdRepository.save(repo);
					ret = String.join("", repo.getPrefix(), String.valueOf(srNo));
				} else {
					ret = String.join("", repo.getPrefix(), String.valueOf(repo.getLastsrno()));
				}
			} else if (type.equals(Constants.CUSTOMER_NO)) {
				Optional<Customer> si = contactRepository
						.findByAccno(String.join("", repo.getPrefix(), String.valueOf(repo.getLastsrno())));
				if (si.isPresent()) {
					srNo += repo.getLastsrno();
					repo.setLastsrno(srNo);
					entityIdRepository.save(repo);
					ret = String.join("", repo.getPrefix(), String.valueOf(srNo));
				} else {
					ret = String.join("", repo.getPrefix(), String.valueOf(repo.getLastsrno()));
				}
			}

			return Response.build(Code.OK, ret);
		}
	}

	@Override
	public String getvocher(String type) {

		EntityIdRep repo = entityIdRepository.findOneByModuleName(type);
		Long srNo = 1L;
		if (repo == null) {
			repo = new EntityIdRep();
			repo.setLastsrno(srNo);
			repo.setModuleName(Constants.SALE_INVOICE);
			repo.setPrefix("SIN-");
			repo.setCreated(Calendar.getInstance());
			repo.setModified(Calendar.getInstance());
			entityIdRepository.save(repo);
			return "SIN-1";

		} else {
			String ret = "";

			if (type.equals(Constants.SALE_INVOICE)) {
				Optional<Sale> si = saleRepository
						.findBySaleno(String.join("", repo.getPrefix(), String.valueOf(repo.getLastsrno())));
				if (si.isPresent()) {
					srNo += repo.getLastsrno();
					repo.setLastsrno(srNo);
					entityIdRepository.save(repo);
					ret = String.join("", repo.getPrefix(), String.valueOf(srNo));
				} else {
					ret = String.join("", repo.getPrefix(), String.valueOf(repo.getLastsrno()));
				}
			} else if (type.equals(Constants.CUSTOMER_NO)) {
				Optional<Customer> si = contactRepository
						.findByAccno(String.join("", repo.getPrefix(), String.valueOf(repo.getLastsrno())));
				if (si.isPresent()) {
					srNo += repo.getLastsrno();
					repo.setLastsrno(srNo);
					entityIdRepository.save(repo);
					ret = String.join("", repo.getPrefix(), String.valueOf(srNo));
				} else {
					ret = String.join("", repo.getPrefix(), String.valueOf(repo.getLastsrno()));
				}
			}
			return ret;
		}
	}
}
