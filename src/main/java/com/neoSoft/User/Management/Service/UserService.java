package com.neoSoft.User.Management.Service;

import com.neoSoft.User.Management.Entity.User;

import java.util.List;

public interface UserService {

    public User getUser(int userId);
    public List<User> getAllUsers();
    public User createUser(User user);
    public User updateUser(User user, int userId);
    public User deleteUser(int userId);
    public List<User> getAllUsersSortedByJoiningDate();
    public List<User> getAllUsersSortedByDob();
    public List<User> getUserByFirstNameOrLastNameOrPinCode(String firstName, String lastName, String pinCode);

}
