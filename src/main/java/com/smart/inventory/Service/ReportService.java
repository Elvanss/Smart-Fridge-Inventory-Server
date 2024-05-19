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
        List<Item> itemRemaining = this.profileService.getItemsForProfile(profileId);

        // Filter out consumption records that are not for the profile
        profile.setConsumptionRecords(profile.getConsumptionRecords().stream()
                .filter(cr -> cr.getProfile().getId().equals(profileId))
                .toList());

        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setProfileName(profile.getName());
        reportDTO.setProfileAge(profile.getAge());
        reportDTO.setProfileDietary(profile.getDietary());

        reportDTO.setConsumedItems(itemRemaining.stream()
                .map(Item::getName)
                .collect(Collectors.toList()));

        // Calculate total remaining calories, protein, and fat
        reportDTO.setTotalCaloriesRemaining(itemRemaining.stream()
                .mapToDouble(Item::getCalories).sum());
        reportDTO.setTotalProteinRemaining(itemRemaining.stream()
                .mapToDouble(Item::getProtein).sum());
        reportDTO.setTotalFatRemaining(itemRemaining.stream()
                .mapToDouble(Item::getFat).sum());

        // Calculate total consumed calories, protein, and fat
        reportDTO.setTotalCaloriesConsumed(profile.getConsumptionRecords().stream()
                .mapToDouble(cr -> cr.getQuantity() * cr.getItem().getCalories()).sum());
        reportDTO.setTotalProteinConsumed(profile.getConsumptionRecords().stream()
                .mapToDouble(cr -> cr.getQuantity() * cr.getItem().getProtein()).sum());
        reportDTO.setTotalFatConsumed(profile.getConsumptionRecords().stream()
                .mapToDouble(cr -> cr.getQuantity() * cr.getItem().getFat()).sum());


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

            // Filter out consumption records that are not for the profile
            List<ConsumptionRecord> weeklyConsumptionRecords = profile.getConsumptionRecords().stream()
                    .filter(cr -> cr.getConsumedAt().toLocalDate().isAfter(lastWeekDate) &&
                            cr.getConsumedAt().toLocalDate().isBefore(currentDate))
                    .toList();

            // Calculate total remaining calories, protein, and fat
            reportDto.setTotalCaloriesRemaining(profile.getFridgeInventory().getItems().stream()
                    .mapToDouble(Item::getCalories)
                    .sum());
            reportDto.setTotalProteinRemaining(profile.getFridgeInventory().getItems().stream()
                    .mapToDouble(Item::getProtein)
                    .sum());
            reportDto.setTotalFatRemaining(profile.getFridgeInventory().getItems().stream()
                    .mapToDouble(Item::getFat)
                    .sum());

            // Calculate total consumed items
            reportDto.setConsumedItems(weeklyConsumptionRecords.stream()
                    .map(cr -> cr.getItem().getName())
                    .distinct()
                    .toList());

            // Calculate total consumed calories, protein, and fat
            reportDto.setTotalCaloriesConsumed(weeklyConsumptionRecords.stream()
                    .mapToDouble(cr -> cr.getItem().getCalories())
                    .sum());
            reportDto.setTotalProteinConsumed(weeklyConsumptionRecords.stream()
                    .mapToDouble(cr -> cr.getItem().getProtein())
                    .sum());
            reportDto.setTotalFatConsumed(weeklyConsumptionRecords.stream()
                    .mapToDouble(cr -> cr.getItem().getFat())
                    .sum());

            reportDtos.add(reportDto);
        }

        return reportDtos;
    }
}
