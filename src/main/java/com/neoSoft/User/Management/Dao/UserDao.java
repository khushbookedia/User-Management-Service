package com.neoSoft.User.Management.Dao;

import com.neoSoft.User.Management.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    List<User> findByFirstNameOrLastNameOrPinCode(String firstName, String lastName, String pinCode);

    List<User> findAllByOrderByJoiningDateDesc();

    List<User> findAllByOrderByDobDesc();

}
