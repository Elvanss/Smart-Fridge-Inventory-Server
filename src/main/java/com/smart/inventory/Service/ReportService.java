package com.smart.inventory.Service;

import com.smart.inventory.DTO.ProfileDTO;
import com.smart.inventory.DTO.ReportDTO;
import com.smart.inventory.Entity.*;
import com.smart.inventory.Repository.FridgeInventoryRepository;
import com.smart.inventory.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ProfileService profileService;
    private final UserRepository userRepository;

    public ReportService(ProfileService profileService,
                         UserRepository userRepository) {
        this.profileService = profileService;
        this.userRepository = userRepository;
    }

    public ReportDTO generateReport(Long profileId) {
        Profile profile = this.profileService.getProfileById(profileId);
        List<Item> consumedItems = this.profileService.getItemsForProfile(profileId);

        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setProfileName(profile.getName());
        reportDTO.setProfileAge(profile.getAge());
        reportDTO.setProfileDietary(profile.getDietary());

        reportDTO.setConsumedItems(consumedItems.stream()
                .map(Item::getName)
                .collect(Collectors.toList()));
        reportDTO.setTotalCaloriesConsumed(consumedItems.stream()
                .mapToDouble(Item::getCalories).sum());
        reportDTO.setTotalProteinConsumed(consumedItems.stream()
                .mapToDouble(Item::getProtein).sum());

        return reportDTO;
    }

    public List<ReportDTO> generateWeeklyProfileReport(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        List<Profile> profiles = profileService.getAllProfilesForUser(user.getId());
        List<ReportDTO> reportDtos = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();
        LocalDate lastWeekDate = currentDate.minusWeeks(1);

        for (Profile profile : profiles) {
            ReportDTO reportDto = new ReportDTO();
            reportDto.setProfileName(profile.getName());
            reportDto.setProfileAge(profile.getAge());
            reportDto.setProfileDietary(profile.getDietary());

            List<ConsumptionRecord> weeklyConsumptionRecords = profile.getConsumptionRecords().stream()
                    .filter(cr -> cr.getConsumedAt().toLocalDate().isAfter(lastWeekDate) &&
                            cr.getConsumedAt().toLocalDate().isBefore(currentDate))
                    .toList();

            reportDto.setConsumedItems(weeklyConsumptionRecords.stream()
                    .map(cr -> cr.getItem().getName())
                    .distinct()
                    .toList());

            reportDto.setTotalCaloriesConsumed(weeklyConsumptionRecords.stream()
                    .mapToDouble(cr -> cr.getItem().getCalories())
                    .sum());

            reportDto.setTotalProteinConsumed(weeklyConsumptionRecords.stream()
                    .mapToDouble(cr -> cr.getItem().getProtein())
                    .sum());

            reportDtos.add(reportDto);
        }

        return reportDtos;
    }
}
