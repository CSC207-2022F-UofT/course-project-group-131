package use_cases;

public class Return implements Returnable{
    private String employeeId;
    private String serialNo;
    private int increasedQuantity;

    Return(String employeeId, String serialNo, int increasedQuantity)
    {
        this.employeeId = employeeId;
        this.serialNo = serialNo;
        this.increasedQuantity = increasedQuantity;
    }
    /*
    public void returnItem()
    {
        super.addItem(employeeId, serialNo, increasedQuantity);
        updateHistory();
    }

    private void updateHistory(String serialNo, int increasedQuantity)
    {
        History obj = new History();
        obj.updateReturnHistory(employeeId, serialNo, increasedQuantity);
    }
*/
}
