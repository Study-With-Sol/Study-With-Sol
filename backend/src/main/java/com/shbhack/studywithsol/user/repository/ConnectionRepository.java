package com.shbhack.studywithsol.user.repository;

import com.shbhack.studywithsol.user.domain.Connection;
import com.shbhack.studywithsol.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    List<Connection> findAllByParentAndIsConnect(User parent, Boolean isConnect);

    Connection findByParentAndChildren(User parent, User child);

    List<Connection> findAllByChildrenAndIsConnect(User child, Boolean isConnect);
}
