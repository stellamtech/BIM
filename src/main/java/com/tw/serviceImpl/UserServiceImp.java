package com.tw.serviceImpl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tw.conv.UserDtoConvertor;
import com.tw.conv.UserListConvertor;
import com.tw.conv.UserSimpleListConvertor;
import com.tw.dto.UserDto;
import com.tw.dto.UserListDto;
import com.tw.dto.UserSpecDto;
import com.tw.entity.Role;
import com.tw.entity.User;
import com.tw.enums.StatusType;
import com.tw.exception.UserAlreadyExistsException;
import com.tw.generics.AppConstants;
import com.tw.generics.Code;
import com.tw.generics.Messages;
import com.tw.generics.Response;
import com.tw.repository.RoleRepository;
import com.tw.repository.UserRepository;
import com.tw.service.UserService;
import com.tw.spec.UserSpecification;



@Service
public class UserServiceImp implements UserService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> saveUser(UserDto dto) throws UserAlreadyExistsException {

		logger.info("Creating user: " + dto);

		Optional<User> userOpt = userRepository.findOneByUserName(dto.getUserName());

		if (userOpt.isPresent()) {
			logger.info("UserName already exists. ");
			throw new UserAlreadyExistsException();
		}
		User user = new UserDtoConvertor().apply(dto);
		List<Role> roles = roleRepository.findByRoleIn(Arrays.asList(dto.getRoles()));
		user.setRoles(roles);

		user.setPassword(encoder.encode(dto.getPassword()));
		user.setCreated(Calendar.getInstance());
		user.setModified(Calendar.getInstance());
		user.setJoinDate(new Date());
		User v=userRepository.save(user);

		logger.info(String.format("User %s has been created successfully", user.getUserName()));
		return Response.build(Code.OK, Messages.USER_CREATED_MSG);

	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		User user = new User();
		if (id != null) {
			user = userRepository.getOne(id);
			user.setDeleted(true);
			userRepository.save(user);
		}
		return Response.build(Code.OK, Messages.DELETED);
	}

	@Override
	public ResponseEntity<?> findById(Long id) {
		User user = userRepository.findOneById(id);
		UserDto dto = new UserDto();
		
		 if (user.getRoles() != null && !user.getRoles().isEmpty()) {
	            String[] rolesArray = user.getRoles().stream()
	                    .map(role -> role.getRole()) // Assuming Role has a method to get the role name
	                    .toArray(String[]::new);
	            dto.setRoles(rolesArray);
	        }
		
		BeanUtils.copyProperties(user, dto);
		return Response.build(Code.OK, dto);
	}

	@Override
	public ResponseEntity<?> editUser(UserDto dto) throws UserAlreadyExistsException {
		logger.info("editing user " + dto);
		User user = userRepository.findOneById(dto.getId());
		if (dto.getUserName().equals(user.getUserName())) {

		} else {
			Optional<User> userOpt = userRepository.findOneByUserName(dto.getUserName());
			if (userOpt.isPresent()) {
				logger.info("UserName already exists.");
				throw new UserAlreadyExistsException();
			}
		}
		user.setUserName(dto.getUserName());
		user.setMobileNo(dto.getMobileNo());
		user.setEmail(dto.getEmail());
		user.setPassword(encoder.encode(dto.getPassword()));
		List<Role> roles = roleRepository.findByRoleIn(Arrays.asList(dto.getRoles()));
		user.setRoles(roles);

		userRepository.save(user);
		return Response.build(Code.OK, Messages.EDIT_USER, user);
	}

	@Override
	public ResponseEntity<?> resetPassword(UserDto dto) {
		logger.info("reseting password " + dto);
		logger.info("password :", dto.getPassword());

		User user = userRepository.findOneById(dto.getId());

		user.setPassword(encoder.encode(dto.getPassword()));
		userRepository.save(user);

		return Response.build(Code.ACCEPTED, Messages.RESET_PASSWORD, user);
	}

	@Override
	public ResponseEntity<?> deactivateUser(Long id) {
		logger.info("deactivating user for id:-" + id);
		User user = userRepository.findOneById(id);

		if (user.getStatus().equals(StatusType.Active.toString())) {
			user.setStatus(StatusType.Deactive.toString());
			return Response.build(Code.ACCEPTED, Messages.USER_STATUS_DEACTIVE, user.getStatus());
		} else {
			user.setStatus(StatusType.Active.toString());
			return Response.build(Code.ACCEPTED, Messages.USER_STATUS_ACTIVE, user.getStatus());
		}
	}

	@Override
	public ResponseEntity<?> listUser(UserSpecDto dto) {
		logger.info("showing list of users", dto);

		PageRequest pg = PageRequest.of(dto.getPage() - 1, dto.getSize(), Direction.ASC, AppConstants.MODIFIED);

		Specification<User> speci = UserSpecification.buildSpecification1(dto);

		Page<User> users = userRepository.findAll(speci, pg);

		List<UserDto> list = users.stream().map(s -> new UserListConvertor().apply(s)).collect(Collectors.toList());

		//PageDto pageDto = new PageDto(list, users.getTotalElements());
		PageDto pageDto = new PageDto();

		return Response.build(Code.OK, users);
	}

	@Override
	public ResponseEntity<?> listRole() {
		List<Role> list = roleRepository.findAll();
		return Response.build(Code.OK, list);
	}

	@Override
	public ResponseEntity<?> listUserSimple() {
		List<User> users = userRepository.findAll();

		List<UserListDto> list = users.stream().map(s -> new UserSimpleListConvertor().apply(s))
				.collect(Collectors.toList());

		return Response.build(Code.OK, list);
	}

}
