package com.neoSoft.User.Management;

import com.neoSoft.User.Management.Entity.User;
import com.neoSoft.User.Management.Exception.DuplicateEmailIdException;
import com.neoSoft.User.Management.Exception.ResourceUnAvailableException;
import com.neoSoft.User.Management.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserManagementApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserService userService;

	static List<User> userList = new ArrayList<>();


	@Test
	public void create_user_should_increase_the_user_count_by_1(){
		int current_total_users = userService.getAllUsers().size();
		User user = new User();
		user.setFirstName("Kiara");
		user.setLastName("Advani");
		user.setEmailId("kiara@gmail.com");
		user.setDob("1985-06-21");
		user.setMobileNo("7777777256");
		user.setPassword("admin");
		user.setJoiningDate(LocalDate.parse("2021-10-09"));
		user.setPinCode("450007");
		user.setAddress("7B, M.G.Road, Delhi");
		user.isActive();

		userService.createUser(user);

		assertEquals(current_total_users+1,userService.getAllUsers().size());
	}

	@Test
	public void creating_user_with_existing_emailid_should_throw_data_integrity_violation_exception() throws DataIntegrityViolationException{
		Throwable exception= assertThrows(
				DataIntegrityViolationException.class, () -> {
					User user = new User();
					user.setFirstName("Kenny");
					user.setLastName("Stouraites");
					user.setEmailId("kiara@gmail.com");
					user.setDob("2001-06-21");
					user.setMobileNo("8787878787");
					user.setPassword("admin");
					user.setJoiningDate(LocalDate.parse("2021-09-14"));
					user.setPinCode("950002");
					user.setAddress("54A, G.T.Road, Ludhiana");
					user.isActive();
					userService.createUser(user);
				}
		);
	}

	@Test
	public void searching_for_an_existing_user_should_return_the_expected_object_of_the_user() throws ResourceUnAvailableException {
		User userObject = userService.getUser(1);

		assertThat(userObject.getEmailId(),equalTo("kiara@gmail.com"));

	}

	@Test
	public void searching_for_a_non_existing_user_should_throw_the_exception() throws ResourceUnAvailableException{
		Throwable exception = assertThrows(
				ResourceUnAvailableException.class, () -> {
					userService.getUser(80);
				}
		);
	}

	@Test
	public void updating_user_should_update_the_object_in_the_database(){
		User existingUser = userService.getUser(1);
		existingUser.setPinCode("450004");

		User savedUser = userService.updateUser(existingUser,1);

		assertThat(savedUser.getPinCode(),equalTo(existingUser.getPinCode()));

	}

	@Test
	public void updating_non_existing_user_should_throw_an_exception() throws ResourceUnAvailableException{
		assertThrows(ResourceUnAvailableException.class, () -> {

			User user = new User();
			user.setFirstName("Kiara");
			user.setLastName("Advani");
			user.setEmailId("kiara@gmail.com");
			user.setDob("1985-06-21");
			user.setMobileNo("7777777256");
			user.setPassword("admin");
			user.setJoiningDate(LocalDate.parse("2021-10-09"));
			user.setPinCode("450003");
			user.setAddress("7B, M.G.Road, Delhi");
			user.isActive();

			User savedUser = userService.updateUser(user,80);
		});
	}

	@Test
	public void removing_existing_user_should_decrease_the_user_count_by_1() {

		User existingUserToBeDeleted = userService.getUser(1);

		User deletedUser = userService.deleteUser(1);

		assertEquals(existingUserToBeDeleted,deletedUser);

	}

	@Test
	public void removing_non_existing_user_should_throw_an_exception() throws ResourceUnAvailableException{

		assertThrows(
				ResourceUnAvailableException.class, () -> {
					userService.deleteUser(80);
				}
		);
	}


	@Test
	public void searching_users_with_firstname_or_lastname_or_pincode_should_return_a_list_of_users() {


		User user1 = new User(2,"Shahrukh","Khan","1984-11-11","8888888888","srk@gmail.com","admin",LocalDate.parse("2021-07-09"),"Mannat, Mumbai","400001",false);
		User user2 = new User(3,"Kareena","Kapoor","1982-05-10","6666666666","kareena@gmail.com","admin",LocalDate.parse("2021-05-10"),"Mumbai","400002",false);
		User user3 = new User(4,"Siddhartha","Malhotra","1981-25-12","8888888878","sid@gmail.com","admin",LocalDate.parse("2021-04-19"),"London","400003",false);

		userList.add(userService.createUser(user1));
		userList.add(userService.createUser(user2));
		userList.add(userService.createUser(user3));

		List<User> filteredUsers = userService.getUserByFirstNameOrLastNameOrPinCode("Shahrukh","Kapoor","400003");

		assertEquals(userList,filteredUsers);

	}

	@Test
	public void sorting_users_based_on_their_joining_date_should_return_the_sorted_list_of_users_by_joining_date() {

		userList.add(userService.getUser(2));
		userList.add(userService.getUser(3));
		userList.add(userService.getUser(4));

		List<User> sortedList = userService.getAllUsersSortedByJoiningDate();

		assertEquals(userList,sortedList);


	}

	@Test
	public void sorting_users_based_on_their_dob_should_return_the_sorted_list_of_users_by_dob() {
		userList.add(userService.getUser(2));
		userList.add(userService.getUser(3));
		userList.add(userService.getUser(4));

		List<User> sortedList = userService.getAllUsersSortedByDob();

		assertEquals(userList,sortedList);


	}



}
