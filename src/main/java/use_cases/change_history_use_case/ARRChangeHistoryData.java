package use_cases.change_history_use_case;

import entities.ItemInterface;
import use_cases.arr.ARRInputData;

public class ARRChangeHistoryData extends ChangeHistoryData{


    public ARRChangeHistoryData(String userName, String action, ARRInputData data, ItemInterface item) {
        super(userName, action, item);
        quantity = Double.toString(data.getQuantity());
    }
}
