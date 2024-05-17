package com.smart.inventory.Controller;


import com.smart.inventory.DTO.ProfileDTO;
import com.smart.inventory.Entity.Item;
import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Mapper.ProfileMapper;
import com.smart.inventory.Service.ProfileService;
import com.smart.inventory.System.Result;
import com.smart.inventory.System.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    private final ProfileMapper profileMapper;

    public ProfileController(ProfileService profileService, ProfileMapper profileMapper) {
        this.profileService = profileService;
        this.profileMapper = profileMapper;
    }

    // Requirement 1: Get all profiles for a user.
    @GetMapping("/users/{userId}")
    public Result findAllProfiles(@PathVariable Long userId) {
        List<Profile> foundProfile = this.profileService.getAllProfilesForUser(userId);

        List<ProfileDTO> profileDtos = foundProfile.stream()
                .map(this.profileMapper::convertToDto)
                .toList();

        return new Result(true, StatusCode.SUCCESS, "Find All Success", profileDtos);
    }


    // Requirement 1: Get a profile by id.
    @GetMapping("/{profileId}")
    public Result findProfileById(@PathVariable Long profileId) {
        Profile foundProfile = this.profileService.getProfileById(profileId);
        ProfileDTO profileDto = this.profileMapper.convertToDto(foundProfile);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", profileDto);
    }

    // Requirement 1: Get a profile by user id.
//    @GetMapping("/users/{userId}")
    public Result findProfileByUserId(@PathVariable Long userId, @PathVariable Long profileId) {
        Profile foundProfile = this.profileService.getProfileByUserId(userId, profileId);
        ProfileDTO profileDto = this.profileMapper.convertToDto(foundProfile);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", profileDto);
    }

    // Requirement 1: Add a profile for a user.
    @PostMapping("/add")
    public Result addProfile(@RequestParam("userId") Long userId, @RequestBody ProfileDTO profileDTO) {
        Profile newProfile = this.profileMapper.convertToEntity(profileDTO);
        Profile savedProfile = this.profileService.createProfile(userId, newProfile);
        ProfileDTO savedProfileDto = this.profileMapper.convertToDto(savedProfile);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedProfileDto);
    }

    // Requirement 1: Update a profile.
    @PutMapping("/{profileId}")
    public Result updateProfile(@PathVariable Long profileId, @RequestBody ProfileDTO profileDto) {
        Profile update = this.profileMapper.convertToEntity(profileDto);
        Profile updatedProfile = this.profileService.updatedProfile(profileId, update);
        ProfileDTO updatedProfileDto = this.profileMapper.convertToDto(updatedProfile);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedProfileDto);
    }

    // Requirement 1:  Delete a profile.
    @DeleteMapping("/delete/{profileId}")
    public Result deleteProfile(@PathVariable Long profileId) {
        this.profileService.delete(profileId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success", null);
    }


}
