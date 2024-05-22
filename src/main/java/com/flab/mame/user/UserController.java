package com.flab.mame.user;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.mame.global.resolvers.CurrentUser;
import com.flab.mame.user.domain.User;
import com.flab.mame.user.dto.UserSignupRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public void signup(@RequestBody @Valid final UserSignupRequest request) {
		userService.signup(request);
	}

	@GetMapping
	public User getUserById(@CurrentUser final Long id) {
		log.info("userId = {}", id);
		return userService.getUserById(id);
	}

	/*@PatchMapping("/{id}")
	public void updateUser(@PathVariable final Long id, @RequestBody UserUpdateReqeust request) {
		userService.updateUser(id, request);
	}
*/
	@DeleteMapping
	public void deleteUser(@CurrentUser final Long id) {
		log.info("userId = {}", id);
		userService.deleteUser(id);
	}

}
