using Client.Model;
using Microsoft.AspNetCore.Components;

namespace Client.Pages
{
    public class CreateAccountMainRazor : ComponentBase
    {
        [Inject] private NavigationManager NavigationManager { get; set; }

        [Inject]
        private AuthRequest AuthRequest { get; set; }

        private string Password { get; set; }

        // public string Email { get; protected set; }
        protected string PasswordConfirmation { get; set; }

        protected void LoadLogIn()
        {
            NavigationManager.NavigateTo("");
        }
        protected void LoadCreateAccountDetails()
        {
            Password = AuthRequest.Password;
            if (Password.Equals(PasswordConfirmation))
            {
                NavigationManager.NavigateTo("CreateAccountDetails");
            }
        }
    }
}