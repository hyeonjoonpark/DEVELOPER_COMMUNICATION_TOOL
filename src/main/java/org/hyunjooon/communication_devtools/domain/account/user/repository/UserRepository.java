package org.hyunjooon.communication_devtools.domain.account.user.repository;

import org.hyunjooon.communication_devtools.domain.account.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAll();
    User findByUserName(String username);
    Optional<User> findByEmail(String email);
}
