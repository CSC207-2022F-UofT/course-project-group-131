package use_cases.sales_report;
import entities.TempDataStorage;
import interface_adaptors.show_history.ShowHistoryFileDataAccess;
import use_cases.show_history_use_case.ShowHistoryDsGateway;

import java.time.LocalDateTime;
import java.util.ArrayList;



public class SalesReporter implements SalesReporterInputBoundary {
    private final SalesReporterOutputBoundary presenter;
    private final ShowHistoryDsGateway checker;


    public SalesReporter(SalesReporterOutputBoundary presenter, ShowHistoryDsGateway checker) {
        this.presenter = presenter;
        this.checker = checker;
    }

    // return a list of string arrays representing where each element in the list is a row in the sales report, and each
    // element in the array is a column of that row.
    public void generateReport(SalesReporterInputData data){
        LocalDateTime start = data.getStartTime();
        LocalDateTime end = data.getEndTime();
        if (!checker.StartDateValid(start)){
            presenter.prepareFailure(SalesReporterOutputBoundary.START_TIME_ERROR);
        }
        else if (!checker.EndDateValid(end)){
            presenter.prepareFailure(SalesReporterOutputBoundary.END_TIME_ERROR);
        }
        else {
            ArrayList<String[]> rows = data.getData();
            // generates the list of serial numbers in the data after splitting the history
            ArrayList<String> serials = SalesReporterInputData.serialNumList(rows);
            ArrayList<String[]> result = new ArrayList<String[]>();

            for (String serialNum : serials) {
                result.add(getRow(rows, serialNum));
            }
            String totalRevenue = String.format("Total Revenue: $%.2f", SalesReporterInputData.getTotalRevenue(rows));
            result.add(new String[]{"", "", "", "", "", totalRevenue});
            presenter.prepareSuccess(result);
        }
    }

    /**
     * Generates one row for the sales report for a specific item
     * @param rows the rows of history data
     * @param serialNum the serial number of the item
     * @return a string array representing the data for the item
     */

    public String[] getRow (ArrayList<String[]> rows, String serialNum){
        String name = SalesReporterInputData.getName(serialNum);
        String price = String.valueOf(SalesReporterInputData.getItemPrice(serialNum));
        String quantitySold = String.valueOf(SalesReporterInputData.getItemSold(rows, serialNum));
        String quantityReturned = String.valueOf(SalesReporterInputData.getItemReturned(rows, serialNum));
        String revenue = String.format("$%.2f", SalesReporterInputData.getItemRevenue(rows, serialNum));
        return new String[]{serialNum, name, price, quantitySold, quantityReturned, revenue};
    }
}
