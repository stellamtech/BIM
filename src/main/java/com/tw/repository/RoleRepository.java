package com.tw.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tw.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public List<Role> findByRoleIn(Collection<String> roles);

	public Optional<Role> findByRole(String role);

}
