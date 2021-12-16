using System.Threading.Tasks;
using Client.Model;
using Microsoft.AspNetCore.Components;
using Client.Data.Registration;

using Microsoft.AspNetCore.Components.Web;

namespace Client.Pages
{
    /// <summary>
    /// C# code for creating account with basic information page.
    /// </summary>
    public class CreateAccountMainRazor : ComponentBase
    {
        /// <summary>
        /// Injected create account service for checking username.
        /// </summary>
        [Inject] private IUserCreateAccountService UserCreateAccountService { get; set; }
        /// <summary>
        /// Injected NavigationManager for navigating through pages.
        /// </summary>
        [Inject] private NavigationManager NavigationManager { get; set; }
        /// <summary>
        /// Injected EndUser used for saving values typed in current page.
        /// Later used in "CreateAccountDetails" page.
        /// </summary>
        [Inject]
        protected EndUser EndUser { get; set; }
        
        /// <summary>
        /// Bound password confirmation of type string.
        /// </summary>
        protected string PasswordConfirmation { get; set; }
        
        /// <summary>
        /// Bound password error of type string shown when password is blank or does not matches the password confirmation.
        /// </summary>
        protected string PasswordError = "";
        
        /// <summary>
        /// Bound username error of type string shown when username is blank.
        /// </summary>
        protected string UserNameError = "";
        
        /// <summary>
        /// Bound email error of type string shown when email is blank.
        /// </summary>
        protected string EmailError = "";

        /// <summary>
        /// Method used for navigating to the login page.
        /// </summary>
        protected void LoadLogIn()
        {
            NavigationManager.NavigateTo("");
        }
        
        /// <summary>
        /// Method used for navigating to the "CreateAccountDetails" page, when all the values have been typed
        /// in correctly.
        /// </summary>
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
        
        /// <summary>
        /// Method used for checking email and username and handling error messages.
        /// </summary>
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
        
        /// <summary>
        /// Method for giving functionality to the "Enter" keyboard button.
        /// </summary>
        /// <param name="e"></param>
        protected async Task Enter(KeyboardEventArgs e)
        {
            if (e.Code is "Enter" or "NumpadEnter")
            {
                await CheckUserName();
            }
        }
    }
}