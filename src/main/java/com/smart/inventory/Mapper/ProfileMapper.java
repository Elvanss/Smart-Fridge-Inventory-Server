package com.smart.inventory.Mapper;

import com.smart.inventory.DTO.ProfileDTO;
import com.smart.inventory.Entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    private final UserMapper userMapper;

    public ProfileMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ProfileDTO convertToDto(Profile source) {
        // We are not setting password in DTO.
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(source.getId());
        profileDTO.setName(source.getName());
        profileDTO.setAge(source.getAge());
        profileDTO.setDietary(source.getDietary());
        profileDTO.setAllergies(source.getAllergies());
        profileDTO.setDescription(source.getDescription());
        return profileDTO;
    }

    public Profile convertToEntity(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setName(profileDTO.getName());
        profile.setAge(profileDTO.getAge());
        profile.setDietary(profileDTO.getDietary());
        profile.setAllergies(profileDTO.getAllergies());
        profile.setDescription(profileDTO.getDescription());
        return profile;
    }
}

