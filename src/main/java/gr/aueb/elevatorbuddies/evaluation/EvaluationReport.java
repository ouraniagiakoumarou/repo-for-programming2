package gr.aueb.elevatorbuddies.evaluation;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class EvaluationReport {
    private String scenarioName;
    private PerformanceMetrics metrics;
    public EvaluationReport(String scenarioName, PerformanceMetrics metrics) {
        if (scenarioName == null) {
            scenarioName = "Unknown Scenario";
        }
        this.scenarioName = scenarioName;
        if (metrics == null) {
            throw new IllegalArgumentException("PerformanceMetrics cannot be null");
        }
        this.metrics = metrics;
    }
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Evaluation Report for Scenario: ").append(scenarioName).append("\n");
        report.append("Average Waiting Time: ").append(String.format("%.2f", metrics.getAverageWaitingTime())).append("sec\n");
        report.append("Average Travel Time: ").append(String.format("%.2f", metrics.getAverageTravelTime())).append("sec\n");
        report.append("Reported Errors: ").append(metrics.getReportedErrors()).append("\n");
        report.append("Total Requests: ").append(metrics.getTotalRequests()).append("\n");
        report.append("Success Percentage: ").append(String.format("%.2f", metrics.getSuccessPercentage())).append("%\n");
        report.append("Successful Requests: ").append(metrics.getSuccessfulRequests()).append("\n");
        report.append("Failed Requests: ").append(metrics.getFailedRequests()).append("\n");
        report.append(formatFailure());
        return report.toString();
    }
    private String formatFailure() {
        StringBuilder report = new StringBuilder();
        int error = metrics.getFailedRequests();
        if (error == 0) {
            report.append("No failed requests were reported during the evaluation.\n");
        } else {
            report.append("Failed requests count: ").append(metrics.getFailedRequests()).append("\n");
            report.append("Detailed errors: ").append(metrics.getReportedErrors()).append("\n");
        }
        return report.toString();
    }
    public void printReport() {
        System.out.println(generateReport());
    }
    public void saveReportToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(generateReport());
            System.out.println("Report saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving report to file: " + e.getMessage());
        }
    }
    public static void compareReports(ArrayList<EvaluationReport> reports) {
        if (reports == null || reports.isEmpty()) {
System.out.println("No reports to compare.");
return;
}
            ArrayList<EvaluationReport> validReports = new ArrayList<>();
        for (EvaluationReport report : reports) {
            if (report != null) {
                validReports.add(report);
            }
        }
        if (validReports.size() < 2) {
            System.out.println("At least two valid reports are required for comparison.");
            return;
        }
        System.out.println("\n========== EVALUATION REPORTS COMPARISON ==========");
        for (EvaluationReport report : validReports) {
            System.out.println(report.generateReport());
            System.out.println("-----------------------------------------------");
        }
        EvaluationReport fastestWaiting = validReports.get(0);
        EvaluationReport fastestTravel = validReports.get(0);
        EvaluationReport highestSuccess = validReports.get(0);
        EvaluationReport mostErrors = validReports.get(0);
        EvaluationReport leastErrors = validReports.get(0);
        for (EvaluationReport report : validReports) {
            if (report.metrics.getAverageWaitingTime() < fastestWaiting.metrics.getAverageWaitingTime()) {
                fastestWaiting = report;
            }
            if (report.metrics.getAverageTravelTime() < fastestTravel.metrics.getAverageTravelTime()) {
                fastestTravel = report;
            }
            if (report.metrics.getSuccessPercentage() > highestSuccess.metrics.getSuccessPercentage()) {
                highestSuccess = report;
            }
            if (report.metrics.getReportedErrors() > mostErrors.metrics.getReportedErrors()) {
            mostErrors = report;
            }
            if (report.metrics.getReportedErrors() < leastErrors.metrics.getReportedErrors()) {
                leastErrors = report;
            }
       }
        System.out.println("\n============= COMPARISON RESULTS =============");
        System.out.println("Lowest Average Waiting Time: "
            + fastestWaiting.scenarioName
            + " (" + String.format("%.2f", fastestWaiting.metrics.getAverageWaitingTime()) + " sec)");
        System.out.println("Lowest Average Travel Time: "
            + fastestTravel.scenarioName
            + " (" + String.format("%.2f", fastestTravel.metrics.getAverageTravelTime()) + " sec)");
        System.out.println("Highest Success Percentage: "
            + highestSuccess.scenarioName
            + " (" + String.format("%.2f", highestSuccess.metrics.getSuccessPercentage()) + "%)");
        System.out.println("Most Reported Errors: "
            + mostErrors.scenarioName
            + " (" + mostErrors.metrics.getReportedErrors() + ")");
        System.out.println("Least Reported Errors: "
            + leastErrors.scenarioName
            + " (" + leastErrors.metrics.getReportedErrors() + ")");
        System.out.println("================================================");
    }
}