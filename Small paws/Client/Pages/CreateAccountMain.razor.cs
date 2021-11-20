using Client.Model;
using Microsoft.AspNetCore.Components;

namespace Client.Pages
{
    public class CreateAccountMainRazor : ComponentBase
    {
        [Inject] private NavigationManager NavigationManager { get; set; }

        [Inject]
        private EndUser EndUser { get; set; }

        private string Password { get; set; }

        public static string Email { get; protected set; }
        protected string PasswordConfirmation { get; set; }

        protected string passwordError = "";

        protected void LoadLogIn()
        {
            NavigationManager.NavigateTo("");
        }
        protected void LoadCreateAccountDetails()
        {
            Password = EndUser.Password;
            if (Password.Equals(PasswordConfirmation))
            {
                NavigationManager.NavigateTo("CreateAccountDetails");
            }
            else
            {
                passwordError = "Repeated password was wrong. Try again.";
            }
        }
    }
}