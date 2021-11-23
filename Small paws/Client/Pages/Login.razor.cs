using System;
using System.Threading.Tasks;
using Client.Data.Validation;
using Client.Model;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Authorization;
using Client.Authentication;


namespace Client.Pages
{
    public class LoginRazor : ComponentBase
    {
        [Inject] private NavigationManager NavigationManager { get; set; }
    [Inject] private AuthenticationStateProvider AuthenticationStateProvider{ get; set; }
    

    protected string Username;
    protected string Password;
    protected string errorMessage;

    protected async Task LoadMainPage()
    {
        errorMessage = "";

        try
        {
            await ((CustomAuthenticationStateProvider) AuthenticationStateProvider).ValidateLogin(Username, Password);
            NavigationManager.NavigateTo("ViewAnimals");
        }
        catch (Exception e)
        {
            errorMessage = "Username or Password are incorrect. Please, Try again.";
        }

    }
    protected void LoadCreateAccount()
    {
        NavigationManager.NavigateTo("CreateAccountMain");
    }
    

    }
    

}