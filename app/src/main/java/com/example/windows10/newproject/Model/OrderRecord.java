package com.example.windows10.newproject.Model;

public class OrderRecord {
    private String ProductId ;
    private String ProductName ;
    //private String Quantity;
    private String Price ;
    private String Discount ;



    public OrderRecord(String productId, String productName, String price, String discount) {

        ProductId = productId;
        ProductName = productName;
        //Quantity = quantity;
        Price = price;
        Discount = discount;
    }

    public OrderRecord() {

    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

   /* public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }*/

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    @Override
    public String toString() {
        return "OrderRecord.COLUMN_ID" + ":" +this.getProductId() + ","
                + "OrderRecord.COLUMN_PRODUCTNAME" + ":" +this.getProductName() + ","
                + "OrderRecord.COLUMN_PRICE" + ":" +this.getPrice() + ","
                + "OrderRecord.COLUMN_DISCOUNT" + ":" +this.getDiscount() + ","

                ;
    }
}
