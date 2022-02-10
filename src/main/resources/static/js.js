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

}

function validateNewOffer()
{
    const title = document.getElementById("title").value;
    const price = document.getElementById("price").value;
    const quantity = document.getElementById("quantity").value;
    const description = document.getElementById("description").value;

    if(title.length < 3)
    {
        alert(" Offer title is too short! ")
        return false;
    }

    if(isNaN(price))
    {
        alert("Price must be an floating point number!")
        return false;
    }

    if(!Number.isInteger(Number.parseInt(quantity)))
    {
        alert("Quantity must be an integer!")
        return false;
    }

    if(description.length < 3)
    {
        alert(" Offer description is too short! ")
        return false;
    }

    return true;
}