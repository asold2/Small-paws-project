using System.Threading.Tasks;
using Client.Data.Validation;
using Client.Model;
using Microsoft.AspNetCore.Components;



namespace Client.Pages
{
    public class LoginRazor : ComponentBase
    {
        [Inject] private NavigationManager NavigationManager { get; set; }
    [Inject] private IUserLogInService UserLogInService { get; set; }

    protected string Username;
    protected string Password;
    protected string errorMessage;

    protected async Task LoadMainPage()
    {
        errorMessage = "";
        
        if (await UserLogInService.ValidateUserAsync(Username, Password) == 202)
        {
            NavigationManager.NavigateTo("ViewAnimals");    
        }
        else
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