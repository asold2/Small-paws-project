using Microsoft.AspNetCore.Components;

namespace Client.Pages
{
    public class CreateAccountMainRazor : ComponentBase
    {
        [Inject] private NavigationManager NavigationManager { get; set; }

        // public string Email { get; protected set; }
        public string Username { get; protected set; }
        public string Password { get; protected set; }
        protected string PasswordConfirmation { get; set; }

        protected void LoadLogIn()
        {
            NavigationManager.NavigateTo("");
        }
        protected void LoadCreateAccountDetails()
        {
            if (Password.Equals(PasswordConfirmation))
            {
                NavigationManager.NavigateTo("CreateAccountDetails");
            }
        }
    }
}