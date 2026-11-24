package gr.aueb.elevatorbuddies.evaluation;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class EvaluationReport {
    private String scenarioName;
    private PerformanceMetrics metrics;
    public EvaluationReport(String scenarioName, PerformanceMetrics metrics) {
        this.metrics = metrics;
        if (scenarioName == null) {
            scenarioName = "Unknown Scenario";
        }
        this.scenarioName = scenarioName;
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
            report.append("There were no errors reported during the evaluation.\n");
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
        System.out.println("Comparing Evaluation Reports:");
        for (EvaluationReport report : reports) {
            System.out.println(report.generateReport());
            System.out.println("-----------------------------");
        }
    }

}