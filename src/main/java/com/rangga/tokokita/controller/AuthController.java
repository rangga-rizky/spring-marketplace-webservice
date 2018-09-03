package com.rangga.tokokita.controller;

import com.rangga.tokokita.model.Cart;
import com.rangga.tokokita.model.User;
import com.rangga.tokokita.payload.AuthResponse;
import com.rangga.tokokita.payload.request.LoginRequest;
import com.rangga.tokokita.payload.common.PostResponse;
import com.rangga.tokokita.payload.UserResponse;
import com.rangga.tokokita.service.CartService;
import com.rangga.tokokita.service.UserService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Api(tags = "auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    private PostResponse response;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public PostResponse signup(@Valid @ApiParam("Signup User") @RequestBody User user) {
        User newUser = userService.signup(user);
        Cart newCart = new Cart();
        newCart.setUser_id(newUser.get_id());
        cartService.create(newCart);
        response = new PostResponse(false, "Pendaftaran Berhasil");
        return response;
    }

    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public PostResponse login( @Valid @ApiParam("logins") @RequestBody LoginRequest user) {
        String token = userService.signin(user.getUsername(), user.getPassword());
        response = new AuthResponse(false,"Login Berhasil",token);
        return response;
    }

    @GetMapping(value = "/me")
    @ApiOperation(value = "${UserController.me}", response = UserResponse.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserResponse getCurrentUser( HttpServletRequest req) {
        return modelMapper.map(userService.getCurrentUser(req), UserResponse.class);
    }


}
