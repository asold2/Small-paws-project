using System.Threading.Tasks;
using Client.Data.Validation;
using Client.Model;
using Microsoft.AspNetCore.Components;



namespace Client.Pages
{
    public class LoginRazor : ComponentBase
    {
    [Inject] private NavigationManager NavigationManager { get; set; }
    [Inject] private IUserLogInServices UserLogInServices { get; set; }

    protected string Username;
    protected string Password;
    protected string Role;

    protected async Task LoadMainPage()
    {
        
        if (await UserLogInServices.ValidateUserAsync(Username, Password, Role) == 202)
        {
            NavigationManager.NavigateTo("ViewAnimals");    
        }

    }
    protected void LoadCreateAccount()
    {
        NavigationManager.NavigateTo("CreateAccountMain");
    }
    
    protected void SetToEmployee()
    {
        Role = "Employee";
    }
    protected void SetToPetOwner()
    {
        Role = "PetOwner";
    }
    }
    

}