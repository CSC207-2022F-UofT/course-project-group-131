package use_cases.search_sort_use_case;

import entities.Item;
import entities.TempDataStorage;
import interface_adaptors.search_sort.SearchCategoryPresenter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import use_cases.search_sort.SearchCatOutputBoundary;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TestSearchCategory {

    @Test
    void categoryNotFound(){
        Date date = new Date(2023-01-22);
        List<String> category = Arrays.asList("Fruits");
        Item item = new Item("1","I", 30, 2, category,
                date, "second floor");
        Map<String, Item> map = new HashMap<String, Item>();
        map.put("10077", item);
        TempDataStorage.setTempDataStorage(map);
        ArrayList<Item> tempArray = new ArrayList<>();
        SearchCatOutputBoundary presenter = new SearchCategoryPresenter();
        Assertions.assertEquals(presenter.prepareSuccess(tempArray), "No Items found");


    }
}