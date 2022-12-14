package interface_adaptors.export_inventory;

import use_cases.export_inventory.ExportOutputBoundary;

public class ExportPresenter implements ExportOutputBoundary {
    /**
     * Called if export is successful
     */
    ExportIView screen;

    public void setScreen(ExportIView screen){
        this.screen = screen;
    }
    public void prepareSuccess(String filePath){
        String message = "Inventory successfully exported to: " + filePath;
        screen.setMessage(message);
    }

}
