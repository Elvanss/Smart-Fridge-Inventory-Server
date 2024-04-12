package com.smart.inventory.Mapper;

import com.smart.inventory.DTO.ProfileDTO;
import com.smart.inventory.Entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public ProfileDTO convertToDto(Profile source) {
        // We are not setting password in DTO.
        return new ProfileDTO(source.getId(),
                source.getName(),
                source.getAge(),
                source.getDietary(),
                source.getAllergies(),
                source.getDescription(),
                source.getUser().getId());
    }

    public Profile convertToEntity(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setName(profileDTO.name());
        profile.setAge(profileDTO.age());
        profile.setDietary(profileDTO.dietary());
        profile.setAllergies(profileDTO.allergies());
        profile.setDescription(profileDTO.description());
        return profile;
    }
}

