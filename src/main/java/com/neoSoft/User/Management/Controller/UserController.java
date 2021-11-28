package com.neoSoft.User.Management.Controller;

import com.neoSoft.User.Management.Entity.User;
import com.neoSoft.User.Management.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @PostMapping("/create")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        try {
            return ResponseEntity.ok(userService.createUser(user));
        }
        catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException(ex.getLocalizedMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id){
        return ResponseEntity.ok(userService.updateUser(user, id));
    }

    @GetMapping("/firstnameorlastnameorpincode")
    public ResponseEntity<List<User>> getUserByFirstNameOrLastNameOrPinCode(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String pincode){
        return ResponseEntity.ok(userService.getUserByFirstNameOrLastNameOrPinCode(firstname,lastname,pincode));
    }


    @GetMapping("/joiningDate")
    public ResponseEntity<List<User>> getUsersSortedByTheirJoiningDate(){
        return ResponseEntity.ok(userService.getAllUsersSortedByJoiningDate());
    }

    @GetMapping("/dob")
    public ResponseEntity<List<User>> getUsersSortedByTheirDob(){
        return ResponseEntity.ok(userService.getAllUsersSortedByDob());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }





}
