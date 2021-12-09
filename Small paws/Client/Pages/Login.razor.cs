using System;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Authorization;
using Client.Authentication;
using Microsoft.AspNetCore.Components.Web;


namespace Client.Pages
{
    public class LoginRazor : ComponentBase
    {
        [Inject] private NavigationManager NavigationManager { get; set; }
        [Inject] private AuthenticationStateProvider AuthenticationStateProvider{ get; set; }
    

        protected string Username;
        protected string Password;
        protected string ErrorMessage;

    protected async Task LoadMainPage()
    {
        ErrorMessage = "";

        try
        {
            await ((CustomAuthenticationStateProvider) AuthenticationStateProvider).ValidateLogin(Username, Password);
            NavigationManager.NavigateTo("ViewAnimals");
        }
        catch (Exception)
        {
            ErrorMessage = "Username or Password are incorrect. Please, Try again.";
        }

    }
    protected void LoadCreateAccount()
    {
        NavigationManager.NavigateTo("CreateAccountMain");
    }
    

    protected async Task Enter(KeyboardEventArgs e)
    {
        if (e.Code is "Enter" or "NumpadEnter")
        {
            await LoadMainPage();
        }
    }
    }
    

}