package org.hyunjooon.communication_devtools.domain.account.user.repository;

import org.hyunjooon.communication_devtools.domain.account.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
