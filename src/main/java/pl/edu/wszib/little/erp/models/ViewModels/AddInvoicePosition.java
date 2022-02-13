package pl.edu.wszib.little.erp.models.ViewModels;

public class AddInvoicePosition
{
    private String productCode;
    private int quantity;

    public AddInvoicePosition(String productCode, int quantity)
    {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public AddInvoicePosition()
    {
    }

    public String getProductCode()
    {
        return productCode;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
