package com.matthew050102.wareflow.user;

import com.matthew050102.wareflow.security.CurrentUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public UserDTO fetchUserDataByUsername(@CurrentUserId String id) {
        return userService.fetchUserDataById(id);
    }
}
