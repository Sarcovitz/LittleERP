function validateRegistration()
{
    const email = document.getElementById("email").value;
    const login = document.getElementById("login").value;
    const pass = document.getElementById("password").value;
    const pass2 = document.getElementById("password2").value;

    const emailPattern = /[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}$/;
    const loginPattern = /[a-z0-9]{3,16}$/;
    const passwordPattern = /[A-Za-z0-9]{3,16}$/;

    if(!emailPattern.test(email)) {
        alert("Email is not valid");
        return false;
    }

    if(!loginPattern.test(login)) {
        alert("Login is not valid");
        return false;
    }

    if(!passwordPattern.test(pass)) {
        alert("Password is not valid");
        return false;
    }

    if(pass === pass2)return true;
    else
    {
        alert("Passwords are different.");
        return false;
    }
}

function validateCustomer()
{
    const name = document.getElementById("name").value;
    const code = document.getElementById("code").value;
    const nip = document.getElementById("nip").value;
    const address = document.getElementById("address").value;
    const mailOrPhone = document.getElementById("mailOrPhone").value;
    const city = document.getElementById("city").value;

    const nipRegex = /[0-9]{10}$/;

    if(code === null || code.length < 3 || code.length > 10)
    {
        alert("Code must be between 3 and 10 characters long!");
        return false;
    }
    else if(name === null || name.length < 3 || name.length > 50)
    {
        alert("Name must be between 3 and 50 characters long!");
        return false;
    }
    else if(nip === null || nip.length !== 10)
    {
        alert("NIP Number must have 10 digits.");
        return false;
    }
    else if(!nipRegex.test(nip))
    {
        alert("NIP Number can contain only digits 0-9!");
        return false;
    }
    else if(address===null || address.length < 3 || address.length > 50)
    {
        alert("Name must be between 3 and 50 characters long!");
        return false;
    }
    else if(mailOrPhone.length > 50)
    {
        alert("Contact field can have up to 50 characters.");
        return false;
    }
    else if(city === null || city.length === 0)
    {
        alert("You need to define city!");
        return false;
    }
    else return true;
}

function validateProduct()
{
    const name = document.getElementById("name").value;
    const code = document.getElementById("code").value;
    const ean = document.getElementById("ean").value;
    const price = document.getElementById("price").value;
    const quantity = document.getElementById("quantity").value;
    const vat = document.getElementById("vat").value;

    const eanRegex = /[0-9]{7,13}$/;

    if(code === null || code.length < 3 || code.length > 10)
    {
        alert("Code must be between 3 and 10 characters long!");
        return false;
    }
    else if(name === null || name.length < 3 || name.length > 50)
    {
        alert("Name must be between 3 and 50 characters long!");
        return false;
    }
    else if(!eanRegex.test(ean))
    {
        alert("Name must be between 7 and 13 DIGITS long!");
        return false;
    }
    else if(isNan(price))
    {
        alert("Price must be a number (float or integer)!");
        return false;
    }
    else if(!Number.isInteger(quantity) || Number.parseInt(quantity) <= 0)
    {
        alert("Quantity must be an integer number and higher than 0");
        return false;
    }
    else if(!Number.isInteger(vat))
    {
        alert("Vat Tax Rate must be an integer number");
        return false;
    }
    else return true;
}

function validateInvoice()
{
    const customer = document.getElementById("customer");

    var customerText = customer.options[customer.selectedIndex].text;

    if(customerText === "Please select customer...")
    {
        alert("You need to select a Customer");
        return false;
    }
    else return true;
}

function validatePosition()
{
    const product = document.getElementById("product");
    const quantity = document.getElementById("quantity");

    var productText = product.options[product.selectedIndex].text;

    if(productText === "Please select product..." || productText === "" )
    {
        alert("You need to select a Product");
        return false;
    }
    else if(!Number.isInteger(quantity) || Number.parseInt(quantity) <= 0)
    {
        alert("Quantity must be an integer number");
        return false;
    }
    else return true;
}

function validateNewOffer()
{
    const title = document.getElementById("title").value;
    const price = document.getElementById("price").value;
    const quantity = document.getElementById("quantity").value;
    const description = document.getElementById("description").value;


    if(title.length < 3)
    {
        alert(" Offer title is too short! ");

        return false;
    }

    if(isNaN(price))
    {
        alert("Price must be an floating point number!");
        return false;
    }

    if(!Number.isInteger(Number.parseInt(quantity)))
    {
        alert("Quantity must be an integer!");
        return false;
    }

    if(description.length < 3)
    {
        alert(" Offer description is too short! ");
        return false;
    }

    if(city.length === 0)
    {
        alert(" Offer description is too short! ");
        return false;
    }



    return true;
}