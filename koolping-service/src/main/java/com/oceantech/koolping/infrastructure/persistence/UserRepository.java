package com.oceantech.koolping.infrastructure.persistence;


import com.oceantech.koolping.domain.model.identity.User;
import com.oceantech.koolping.domain.model.identity.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    Page<User> findByUserName(String username, Pageable pageable);

    Page<User> findByUserId(UserId userId, Pageable pageable);

    User findByUserName(String username);

    Page<User> findAll(Pageable pageable);

    Page<User> findByUserNameLike(String username, Pageable pageable);

    Page<User> findByFirstNameLike(String firstName, Pageable pageable);

    Page<User> findByLastNameLike(String lastName, Pageable pageable);

    Page<User> findByFirstNameLikeAndLastNameLike(String firstName, String lastName, Pageable pageable);

    @Query("select u from User u where u.role.role = :role")
    Page<User> findByRole(@Param("role") Integer role, Pageable pageable);
}
