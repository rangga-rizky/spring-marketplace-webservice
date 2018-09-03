package com.rangga.tokokita.controller;

import com.rangga.tokokita.model.User;
import com.rangga.tokokita.payload.ProfilePayload;
import com.rangga.tokokita.payload.UserResponse;
import com.rangga.tokokita.payload.common.PostResponse;
import com.rangga.tokokita.service.UserService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
@Api(tags = "profile")
public class ProfileController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "")
    @ApiOperation(value = "${ProfileController.me}", response = UserResponse.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ProfilePayload getCurrentUser(HttpServletRequest req) {
        return modelMapper.map(userService.getCurrentUser(req), ProfilePayload.class);
    }
    @PutMapping("")
    @ApiOperation(value = "${ProfileController.edit}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public PostResponse update(@Valid @ApiParam("Update User") @RequestBody ProfilePayload user,
                               HttpServletRequest req) {
        User currentProfile = userService.getCurrentUser(req);
        currentProfile.setAddresses(user.getAddresses());
        currentProfile.setCards(user.getCards());
        currentProfile.setEmail(user.getEmail());
        userService.save(currentProfile);
        PostResponse response = new PostResponse(false, "Profile Berhasil di update");
        return response;
    }


}
