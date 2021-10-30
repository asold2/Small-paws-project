using Microsoft.AspNetCore.Components;

namespace Client.Pages
{
    public abstract class CreateAccountDetailsRazor : ComponentBase
    {
        [Inject] protected NavigationManager navigationManager { get; set; }

        protected void LoadViewAnimals()
        {
            navigationManager.NavigateTo("ViewAnimals");
        }
        
        
        protected void LoadLogIn()
        {
            navigationManager.NavigateTo("");
        }
    }
}