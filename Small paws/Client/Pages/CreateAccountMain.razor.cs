using Microsoft.AspNetCore.Components;

namespace Client.Pages
{
    public class CreateAccountMainRazor : ComponentBase
    {
        [Inject] protected NavigationManager navigationManager { get; set; }

        protected void LoadLogIn()
        {
            navigationManager.NavigateTo("");
        }
        protected void LoadCreateAccountDetails()
        {
            navigationManager.NavigateTo("CreateAccountDetails");
        }
    }
}