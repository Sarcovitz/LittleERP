package pl.edu.wszib.little.erp.models.ViewModels;

public class AddInvoice
{
    private String customerCode;
    private boolean salesInvoice;

    public AddInvoice(String customerCode, boolean salesInvoice)
    {
        this.customerCode = customerCode;
        this.salesInvoice = salesInvoice;
    }

    public AddInvoice()
    {
    }

    public String getCustomerCode()
    {
        return customerCode;
    }

    public void setCustomerCode(String customerCode)
    {
        this.customerCode = customerCode;
    }

    public boolean isSalesInvoice()
    {
        return salesInvoice;
    }

    public void setSalesInvoice(boolean salesInvoice)
    {
        this.salesInvoice = salesInvoice;
    }
}
