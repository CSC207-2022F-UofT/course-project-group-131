package use_cases.sales_reporter_use_case;

import entities.Item;
import entities.TempDataStorage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.sales_report.SalesReporterInputData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class TestSalesReporterInputData {
    private ArrayList<String[]> example_history;
    private Item example_item;
    private TempDataStorage example_temp;
    private Map<String, Item> example_map;
    @BeforeEach
    public void setUp(){
        example_item = new Item("4", "Cutting Board", 20.00, 10, null, null, "Shelf");
        example_map = new HashMap<>();
        example_map.put("4", example_item);
        /*example_temp = new TempDataStorage();
        example_temp.setTempDataStorage(example_map);*/

        example_history = new ArrayList<>();
        example_history.add(new String[]{"2022-10-31 10:00:00", "Lester", "ADD ITEM", "Ice Cream", "3", "1"});
        example_history.add(new String[]{"2022-10-31 12:00:00", "Lucas", "NEW ITEM", "iPad", "20", "2"});
        example_history.add(new String[]{"2022-10-31 14:00:00", "Daisy", "CHANGE PRICE", "Tomato", "-1.5", "3"});
        example_history.add(new String[]{"2022-10-31 16:00:00", "Robin", "SELL ITEM", "Cutting Board", "5", "4"});
        example_history.add(new String[]{"2022-10-31 20:00:00", "Jackson", "RETURN ITEM", "Cutting Board", "1", "4"});
    }
    @AfterEach
    public void teardown(){
        example_history.clear();
        example_item = null;
        example_map.clear();
        example_temp = null;
    }

    @Test
    public void stringToDateTimeTest() {
        LocalDateTime expected = LocalDateTime.of(2022, 12, 5, 10, 0, 0);
        LocalDateTime actual = SalesReporterInputData.stringToDateTime("2022-12-05 10:00:00");
        assertEquals(expected, actual);
    }
    @Test
    public void getStartTimeTest(){
        SalesReporterInputData test = new SalesReporterInputData(example_history, "2021-12-05 10:00:00", "2022-12-05 10:00:00" );
        LocalDateTime expected = LocalDateTime.of(2021, 12, 5, 10, 0);
        LocalDateTime actual = test.getStartTime();
        assertEquals(expected, actual);
    }
    @Test
    public void getEndTimeTest(){
        SalesReporterInputData test = new SalesReporterInputData(example_history, "2021-12-05 10:00:00", "2022-12-05 10:00:00" );
        LocalDateTime expected = LocalDateTime.of(2022, 12, 5, 10, 0);
        LocalDateTime actual = test.getEndTime();
        assertEquals(expected, actual);
    }
    @Test
    public void splitListTimeRangeTestAll(){
        SalesReporterInputData test = new SalesReporterInputData(example_history, "2021-12-05 10:00:00", "2022-12-05 10:00:00" );
        int expected = 5;
        ArrayList<String[]> actual_list = test.splitListTimeRange();
        int actual = actual_list.size();
        assertEquals(expected, actual);
    }

    @Test
    public void splitListTimeRangeTestMiddle(){
        SalesReporterInputData test = new SalesReporterInputData(example_history, "2021-12-05 10:00:00", "2022-10-31 15:00:00" );
        int expected = 3;
        ArrayList<String[]> actual_list = test.splitListTimeRange();
        int actual = actual_list.size();
        assertEquals(expected, actual);
    }

    @Test
    public void splitListTimeRangeTestEquals(){
        SalesReporterInputData test = new SalesReporterInputData(example_history, "2021-12-05 10:00:00", "2022-10-31 14:00:00" );
        int expected = 3;
        ArrayList<String[]> actual_list = test.splitListTimeRange();
        int actual = actual_list.size();
        assertEquals(expected, actual);
    }

    @Test
    public void serialNumListTest() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
        ArrayList<String> actual = SalesReporterInputData.serialNumList(example_history);
        assertEquals(expected, actual);
    }
    @Test
    public void itemSoldTest(){
        int expected = 5;
        int actual = SalesReporterInputData.getItemSold(example_history,"4");
        assertEquals(expected, actual);
    }
    @Test
    public void itemReturnedTest(){
        int expected = 1;
        int actual = SalesReporterInputData.getItemReturned(example_history,"4");
        assertEquals(expected, actual);
    }
    @Test
    public void itemPriceTest(){
        double expected = 5;
        double actual = SalesReporterInputData.getItemPrice("1");
        assertEquals(expected, actual);
    }
    @Test
    public void itemRevenueTest(){
        double expected = 80;
        double actual = SalesReporterInputData.getItemRevenue(example_history, "4");
        assertEquals(expected, actual);
    }

    @Test
    public void totalRevenueTest(){
        double expected = 80;
        double actual = SalesReporterInputData.getTotalRevenue(example_history);
        assertEquals(expected, actual);
    }

}
