package use_cases.arr;

import entities.Item;
import entities.ItemInterface;
import entities.TempDataStorage;
import entities.User;
import use_cases.change_history_use_case.ARRChangeHistoryData;
import use_cases.change_history_use_case.ChangeHistory;
import use_cases.change_history_use_case.ChangeHistoryData;

public class Add implements ARRInputBoundary {

    private final ARROutputBoundary presenter;
    private final String ACTION = "ADD ITEM";

    public Add(ARROutputBoundary presenter){
        this.presenter = presenter;
    }

    /**
     * The Add Use case itself. Once done, tells the presenter to update information
     * @param data a Data Structure containing the serial number and quantity of the item to add
     */
    public void changeItemQuantity(ARRInputData data){


        if (data.getQuantity() < 0 && !(this instanceof RemoveUseCase) ||
            data.getQuantity() > 0 && (this instanceof RemoveUseCase))
            // To allow negative quantity values only when the item is being removed
        {
            presenter.prepareFailure(ARROutputBoundary.NEGATIVE_INT_ERROR);
        } // if the employee enters a negative quantity (unlikely, but you don't want this to happen at all)
        else if (!TempDataStorage.hasItem(data.getSerialNum())){
            presenter.prepareFailure(ARROutputBoundary.SERIAL_NUM_NOT_FOUND_ERROR);
        }
        else{
            ItemInterface item = TempDataStorage.getItem(data.getSerialNum());
            int quantity = item.getQuantity() + data.getQuantity();
            item.setQuantity(quantity);
            updateHistory(data, item);
            presenter.prepareSuccess(data);
        }
    }

    @Override
    public void updateHistory(ARRInputData data, ItemInterface item)
    {
        ChangeHistoryData historyData = new ARRChangeHistoryData(User.getUserName(),this.ACTION, data, item);
        new ChangeHistory(historyData).save_history_change();
    }
}
