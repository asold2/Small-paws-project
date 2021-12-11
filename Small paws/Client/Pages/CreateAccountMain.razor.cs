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

        protected const string EmailError = "";

        // private IList<EndUser> users = null;

        protected void LoadLogIn()
        {
            NavigationManager.NavigateTo("");
        }
        protected void LoadCreateAccountDetails()
        {

            
            if (EndUser.Password.Equals(PasswordConfirmation) && EndUser.Email.Length!=0)
            {
                NavigationManager.NavigateTo("CreateAccountDetails");
            }
            else
            {
                PasswordError = "Repeated password was wrong. Try again.";
            }
        }

        protected async Task CheckUserName()
        {
            
            if (await UserCreateAccountService.CheckUserName(EndUser.UserName) ==403)
            {
                UserNameError = "Username already in use";
            }
            else
            {
                LoadCreateAccountDetails();
            }
        }
        protected void Enter(KeyboardEventArgs e)
        {
            if (e.Code is "Enter" or "NumpadEnter")
            {
                LoadCreateAccountDetails();
            }
        }
    }
}