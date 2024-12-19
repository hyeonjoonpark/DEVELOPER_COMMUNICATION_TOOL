package org.hyunjooon.communication_devtools.domain.auth.repository;

import org.hyunjooon.communication_devtools.domain.auth.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

}
