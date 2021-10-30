using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Mvc.RazorPages;


namespace Client.Pages
{
    public class LoginRazor : ComponentBase
    {
    [Inject] protected NavigationManager navigationManager { get; set; }

    protected void LoadMainPage()
    {
        navigationManager.NavigateTo("ViewAnimals");
    }
    protected void LoadCreateAccount()
    {
        navigationManager.NavigateTo("CreateAccountMain");
    }
    
    }
}