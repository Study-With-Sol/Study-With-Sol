package com.shbhack.studywithsol.user.repository;

import com.shbhack.studywithsol.user.domain.Connection;
import com.shbhack.studywithsol.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    List<Connection> findAllByParent(User parent);

    Connection findByParentAndChildren(User parent, User child);
}
