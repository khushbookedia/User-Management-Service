package com.neoSoft.User.Management.Service;

import com.neoSoft.User.Management.Dao.UserDao;
import com.neoSoft.User.Management.Entity.User;
import com.neoSoft.User.Management.Exception.ResourceUnAvailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Override
    public List<User> getUserByFirstNameOrLastNameOrPinCode(String firstName, String lastName, String pinCode){
        return userDao.findByFirstNameOrLastNameOrPinCode(firstName,lastName,pinCode);
    }

    @Override
    public User createUser(User user){
        try {
            return userDao.save(user);
        }
        catch(DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException(ex.getLocalizedMessage());
        }
    }

    @Override
    public User getUser(int userId){
        return Optional.
                ofNullable(userDao.findById(userId)).get()
                .orElseThrow(() -> new ResourceUnAvailableException(HttpStatus.BAD_REQUEST, "User Not Found"));

    }

    @Override
    public List<User> getAllUsers(){
        return userDao.findAll();
    }

    @Override
    public User updateUser(User user, int userId) throws ResourceUnAvailableException{
        try {
            User existingUser = getUser(userId);
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setAddress(user.getAddress());
            existingUser.setDob(user.getDob());
            existingUser.setEmailId(user.getEmailId());
            existingUser.setJoiningDate(user.getJoiningDate());
            existingUser.setMobileNo(user.getMobileNo());
            existingUser.setPassword(user.getPassword());
            existingUser.setPinCode(user.getPinCode());

            return userDao.save(existingUser);
        }
        catch (ResourceUnAvailableException ex){
            throw new ResourceUnAvailableException(HttpStatus.BAD_REQUEST,"User doesn't exist");
        }

    }

    @Override
    public User deleteUser(int userId) throws ResourceUnAvailableException{

        try {
            User savedUser = getUser(userId);
            userDao.delete(savedUser);
            return savedUser;
        }
        catch(ResourceUnAvailableException ex){
            throw new ResourceUnAvailableException(HttpStatus.BAD_REQUEST,"User doesn't exist");
        }



    }

    @Override
    public List<User> getAllUsersSortedByJoiningDate(){
        return userDao.findAllByOrderByJoiningDateDesc().stream().collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsersSortedByDob(){
        return userDao.findAllByOrderByDobDesc().stream().collect(Collectors.toList());
    }


}
