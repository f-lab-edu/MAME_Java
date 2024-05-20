package com.flab.mame.user;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public void signup(@RequestBody @Valid final UserSignupRequest request) {
		userService.signup(request);
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable final Long id) {
		return userService.getUserById(id);
	}

	/*@PatchMapping("/{id}")
	public void updateUser(@PathVariable final Long id, @RequestBody UserUpdateReqeust request) {
		userService.updateUser(id, request);
	}
*/
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable final Long id) {
		userService.deleteUser(id);
	}

}
