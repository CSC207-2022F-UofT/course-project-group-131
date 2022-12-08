package interface_adaptors.search_sort;

import entities.Item;
import entities.ItemInterface;
import use_cases.search_sort.SearchCatOutputBoundary;


import java.util.ArrayList;

public class SearchCategoryPresenter implements SearchCatOutputBoundary {
    SearchCategoryIView screen;

    /**
     * Sets the screen the SearchCategoryPresenter will update. The reason why it's not instantiated in the constructor
     * is due to the peculiar order the presenter, controller, use case and screen are made
     * @param screen the screen the presenter will change
     */
    @Override
    public void setScreen(SearchCategoryIView screen) {this.screen = screen;}

    @Override
    public String prepareSuccess(ArrayList<ItemInterface> data) {
        if (data.size() == 0){
            return "No Items found";}
        ArrayList<String> itemNames = new ArrayList<>();
        for (ItemInterface item: data)
        {itemNames.add(item.getName());}
        return "Items: " + itemNames;
    }
}
