package com.smart.inventory.Controller;


import com.smart.inventory.DTO.ProfileDTO;
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

    @GetMapping("/users/{userId}")
    public Result findAllProfiles(@PathVariable Long userId) {
        List<Profile> foundProfile = this.profileService.getAllProfilesForUser(userId);

        List<ProfileDTO> profileDtos = foundProfile.stream()
                .map(this.profileMapper::convertToDto)
                .toList();

        return new Result(true, StatusCode.SUCCESS, "Find All Success", profileDtos);
    }


    @GetMapping("/{profileId}")
    public Result findProfileById(@PathVariable Long profileId) {
        Profile foundProfile = this.profileService.getProfileById(profileId);
        ProfileDTO profileDto = this.profileMapper.convertToDto(foundProfile);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", profileDto);
    }

    @PostMapping("/{userId}")
    public Result addProfile(@PathVariable Long userId, @RequestBody Profile newProfile) {
        Profile savedProfile = this.profileService.createProfile(userId, newProfile);
        ProfileDTO savedProfileDto = this.profileMapper.convertToDto(savedProfile);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedProfileDto);
    }

    @PutMapping("/{profileId}")
    public Result updateProfile(@PathVariable Long profileId, @RequestBody ProfileDTO profileDto) {
        Profile update = this.profileMapper.convertToEntity(profileDto);
        Profile updatedProfile = this.profileService.updatedProfile(profileId, update);
        ProfileDTO updatedProfileDto = this.profileMapper.convertToDto(updatedProfile);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedProfileDto);
    }

    @DeleteMapping("/{profileId}")
    public Result deleteProfile(@PathVariable Long profileId) {
        this.profileService.delete(profileId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success", null);
    }


}
