package use_cases.arr;

public class ARRInputData {

    private String serialNum;
    private int quantity;

    //private String userName;

    /**
     * Instantiates a data object necessary to carry the data required for the Add, Return and Remove use cases
     *
     * @param serialNum the serial number of the object whose quantity is to be changed
     * @param quantity  the quantity to change
     */
    public ARRInputData(String serialNum, int quantity) {
        this.serialNum = serialNum;
        this.quantity = quantity;
    }

    /**
     * Gets the serial number of the Item to change
     *
     * @return the serial number
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * Gets the quantity to change the item's current quantity by
     *
     * @return the serial number
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter specifically for the remove use case
     * @param quantity the new quantity
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}



