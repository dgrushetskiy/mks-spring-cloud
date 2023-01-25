package ru.gothmog.ws.auth.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.gothmog.ws.auth.core.model.auth.UserBill;

@Repository
public interface UserBillRepository extends JpaRepository<UserBill, Long>, JpaSpecificationExecutor<UserBill> {
}
