package com.plvtarch.UsersTest.repositories;

import com.plvtarch.UsersTest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long>
{
    List<User> findByName(String name);
    List<User> findByEmail(String email);
    List<User> findByLogin(String login);
}
