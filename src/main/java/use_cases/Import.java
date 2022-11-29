package use_cases;

import java.io.IOException;
import java.util.List;
import entities.Item;
import entities.TempDataStorage;
import interface_adaptors.ImportPresenter;

public class Import implements ImportInputBoundary{
    private final ImportOutputBoundary presenter;
    public Import(ImportOutputBoundary presenter){
        this.presenter = presenter;
    }
    public String importDatabase(gatewayReaderInterface reader) throws IOException{
        ImportDS importData = new ImportDS(reader.getData(), reader.getFilePath());
        List<String[]> data = importData.getImportData();
        data.remove(0); //Remove the column titles from the data
        for (String[] lst: data) {
            AddDS itemInformation = new AddDS(lst[0], Integer.valueOf(lst[3]));
            if (!TempDataStorage.hasItem(itemInformation.getSerialNum())) {
                return presenter.prepareFailure(0, lst); // Check that item is in inventory
            } else if (itemInformation.getQuantity() < 0) {
                return presenter.prepareFailure(1, lst); // Check that quantity is not negative
            }
            Item item = TempDataStorage.getItem(itemInformation.getSerialNum());
            int newQuantity = item.getQuantity() + itemInformation.getQuantity();
            item.setQuantity(newQuantity);
        }
        return presenter.prepareSuccess(importData.getFilepath());
    }
}
