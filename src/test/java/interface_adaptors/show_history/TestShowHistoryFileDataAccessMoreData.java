package interface_adaptors.show_history;

import interface_adaptors.gateway.*;

import interface_adaptors.show_history.ShowHistoryFileDataAccess;
import org.junit.jupiter.api.Test;
import use_cases.show_history_use_case.ShowHistoryStartInput;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class TestShowHistoryFileDataAccessMoreData {
    @Test
    void create() throws IOException {
        GatewayWriter clearwriter = new GatewayWriter("D:\\uoft\\2022 fall\\course-project-group-131\\src\\main\\java\\historydatabase\\history.csv");
        //clearwriter.rewriteFile(null);
        String[][] filecontent= new String[][]{{"2020-12-03 06:46:33,Daisy,Add,Apple,1,apple123"},{"2021-12-03 06:46:33,Daisy,Add,Apple,1,apple123"},{"2022-09-03 06:46:33,Emily,Add,pencil,5,pencil123"},{"2023-09-03 06:46:33,Emily,Add,pencil,5,pencil123"}};
        List<String[]> realfilecontent = Arrays.asList(filecontent);
        clearwriter.rewriteFile(realfilecontent);
        GatewayReader reader2 = new GatewayReader("D:\\uoft\\2022 fall\\course-project-group-131\\src\\main\\java\\historydatabase\\history.csv");
        ShowHistoryFileDataAccess shfda = new ShowHistoryFileDataAccess(reader2);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        assertTrue(shfda.StartDateValid(LocalDateTime.parse("2022-01-01 00:00:00",format)));
        assertTrue(shfda.EndDateValid(LocalDateTime.parse("2022-01-01 00:00:00",format)));
        ShowHistoryStartInput input = new ShowHistoryStartInput("2021-01-01 00:00:00","2022-01-01 00:00:00");
        String[][] test = new String[][]{{"2021-12-03 06:46:33","Daisy","Add","Apple","1","apple123"},{"2022-09-03 06:46:33","Emily","Add","pencil","5","pencil123"}};
        List<String[]> testdata= Arrays.asList(test);
        assertArrayEquals(testdata.get(0), shfda.readfile(input).get(0));



    }
}
