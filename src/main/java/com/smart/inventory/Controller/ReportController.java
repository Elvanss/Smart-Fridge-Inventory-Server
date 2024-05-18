package com.smart.inventory.Controller;

import com.smart.inventory.DTO.ProfileDTO;
import com.smart.inventory.DTO.ReportDTO;
import com.smart.inventory.Service.ReportService;
import com.smart.inventory.System.Result;
import com.smart.inventory.System.StatusCode;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // Sequence Diagram 23
    // Requirement 1: Generate report for a profile
    @GetMapping("/{profileId}")
    public Result generateReport(@PathVariable Long profileId) {
        ReportDTO profileDTO = this.reportService.generateReport(profileId);
        return new Result(true, StatusCode.SUCCESS, "Report generated", profileDTO);
    }

    // Sequence Diagram 24
    // Requirement 3: Generate report for all profiles
    @GetMapping("/all/{userId}")
    public Result generateReportForAllProfiles(@PathVariable Long userId) {
        List<ReportDTO> report = this.reportService.generateWeeklyProfileReport(userId);
        return new Result(true, StatusCode.SUCCESS, "Report generated", report);
    }
}
