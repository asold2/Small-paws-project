using Client.Data.Validation;
using Microsoft.AspNetCore.Components;



namespace Client.Pages
{
    public class LoginRazor : ComponentBase
    {
    [Inject] private NavigationManager NavigationManager { get; set; }
    [Inject] private IUserLogInServices UserLogInServices { get; set; }

    protected string Username;
    protected string Password;

    protected async void LoadMainPage()
    {
        if (await UserLogInServices.ValidateUserAsync(Username, Password))
        {
            NavigationManager.NavigateTo("ViewAnimals");    
        }
    }
    protected void LoadCreateAccount()
    {
        NavigationManager.NavigateTo("CreateAccountMain");
    }
    
    }
}