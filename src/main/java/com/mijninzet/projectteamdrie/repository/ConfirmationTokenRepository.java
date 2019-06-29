package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
