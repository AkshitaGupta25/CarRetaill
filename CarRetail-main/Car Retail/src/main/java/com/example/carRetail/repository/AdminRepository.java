package com.example.carRetail.repository;

import com.example.carRetail.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    List<User> findAllByDeleteStatus(boolean deleteStatus);

    List<User> findAllByDeleteStatusAndRoleId(boolean deleteStatus, Long roleId);

    boolean existsByEmail(String email);

    boolean existsByEmailAndDeleteStatus(String email, boolean deleteStatus);

    @Modifying
    @Transactional
    @Query(value = "update user set delete_status=false where email=:email", nativeQuery = true)
    void updateIfUserExist(String email);
}