package app.shiniasse.query.dto;

public class OrderStatisticsDTO {
    private final int totalItems;
    private final int preparedCount;
    private final int unpreparedCount;

    public OrderStatisticsDTO(int total, int prepared, int unprepared) {
        this.totalItems = total;
        this.preparedCount = prepared;
        this.unpreparedCount = unprepared;
    }

    public int getTotalItems() { return totalItems; }
    public int getPreparedCount() { return preparedCount; }
    public int getUnpreparedCount() { return unpreparedCount; }
}
