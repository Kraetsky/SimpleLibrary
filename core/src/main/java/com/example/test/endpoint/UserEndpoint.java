package com.example.test.endpoint;

import com.example.test.domain.User;
import com.example.test.domain.api.Response;
import com.example.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserEndpoint {

    private final UserService userService;

    @GetMapping("/current")
    public Response getCurrentUser() {
        return Response.from(userService::getCurrentUser);
    }

    @PostMapping
    public Response create(@RequestBody User user) {
        return Response.from(() -> userService.create(user));
    }

    @PutMapping
    public Response update(@RequestBody User user) {
        return Response.from(() -> userService.update(user));
    }

}
