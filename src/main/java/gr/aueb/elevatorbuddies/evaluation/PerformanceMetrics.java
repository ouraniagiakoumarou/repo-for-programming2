public class PerformanceMetrics {
    private double averageWaitingTime;
    private double averageTravelTime;
    private int reportedErrors;
    private int totalRequests;
    private int successfulRequests;
    private int failedRequests;

    public PerformanceMetrics(double averageWaitingTime, double averageTravelTime, int reportedErrors, int totalRequests, int successfulRequests, int failedRequests) {
        this.averageWaitingTime = averageWaitingTime;
        this.averageTravelTime = averageTravelTime;
        this.reportedErrors = reportedErrors;
        this.totalRequests = totalRequests;
        this.successfulRequests = successfulRequests;
        this.failedRequests = failedRequests;
    }

    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public double getAverageTravelTime() {
        return averageTravelTime;
    }

    public int getReportedErrors() {
        return reportedErrors;
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public double getSuccessPercentage() {
        if (totalRequests == 0) {
            return 0.0; // Avoid division by zero
        }
        return ((double) successfulRequests / totalRequests) * 100.0;
    }

    public int getSuccessfulRequests() {
        return successfulRequests;
    }

    public int getFailedRequests() {
        return failedRequests;
    }
}