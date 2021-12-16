using System.Threading.Tasks;
using Client.Model;
using Microsoft.AspNetCore.Components;
using Client.Data.Registration;

using Microsoft.AspNetCore.Components.Web;

namespace Client.Pages
{
    public class CreateAccountMainRazor : ComponentBase
    {
        [Inject] private IUserCreateAccountService UserCreateAccountService { get; set; }

        [Inject] private NavigationManager NavigationManager { get; set; }

        [Inject]
        protected EndUser EndUser { get; set; }
        
        protected string PasswordConfirmation { get; set; }

        protected string PasswordError = "";

        protected string UserNameError = "";

        protected string EmailError = "";

        protected void LoadLogIn()
        {
            NavigationManager.NavigateTo("");
        }

        private void LoadCreateAccountDetails()
        {
            if (EndUser.Password.Equals(PasswordConfirmation))
            {
                NavigationManager.NavigateTo("CreateAccountDetails");
                PasswordError = "";
            }
            else
            {
                PasswordError = "Repeated password was wrong. Try again.";
            }
        }

        protected async Task CheckUserName()
        {
            if (string.IsNullOrEmpty(EndUser.Email))
            {
                EmailError = "Email must be filled out";
                UserNameError = "";
            }
            else if (await UserCreateAccountService.CheckUserName(EndUser.UserName) ==403)
            {
                EmailError = "";
                UserNameError = "Username already in use";
            }
            else
            {
                EmailError = "";
                UserNameError = "";
                LoadCreateAccountDetails();
            }
        }
        protected async Task Enter(KeyboardEventArgs e)
        {
            if (e.Code is "Enter" or "NumpadEnter")
            {
                await CheckUserName();
            }
        }
    }
}