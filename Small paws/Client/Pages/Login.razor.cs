using System;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Authorization;
using Client.Authentication;
using Microsoft.AspNetCore.Components.Web;


namespace Client.Pages
{
    /// <summary>
    /// C# code for login page.
    /// </summary>
    public class LoginRazor : ComponentBase
    {
        /// <summary>
        /// Injected NavigationManager for navigating through pages.
        /// </summary>
        [Inject] private NavigationManager NavigationManager { get; set; }
        /// <summary>
        /// Injected AuthenticationStateProvider for validating username and password.
        /// </summary>
        [Inject] private AuthenticationStateProvider AuthenticationStateProvider{ get; set; }
    
        /// <summary>
        /// Bound username of type string.
        /// </summary>
        protected string Username;
        /// <summary>
        /// Bound password of type string.
        /// </summary>
        protected string Password;
        /// <summary>
        /// Bound error message of type string shown when either password or username is blank, or username and password
        /// does not exists.
        /// </summary>
        protected string ErrorMessage;
    
        /// <summary>
        /// Method used for navigating to the main page, when the username and password has been verified.
        /// </summary>
    protected async Task LoadMainPage()
    {
        ErrorMessage = "";

        try
        {
            await ((CustomAuthenticationStateProvider) AuthenticationStateProvider).ValidateLogin(Username, Password);
            NavigationManager.NavigateTo("ViewAnimals");
        }
        catch (Exception)
        {
            ErrorMessage = "Username or Password are incorrect. Please, Try again.";
        }

    }
        /// <summary>
        /// Method used for navigating to the create account page
        /// </summary>
    protected void LoadCreateAccount()
    {
        NavigationManager.NavigateTo("CreateAccountMain");
    }
    
    /// <summary>
    /// Method for giving functionality to the "Enter" keyboard button.
    /// </summary>
    /// <param name="e"></param>
    protected async Task Enter(KeyboardEventArgs e)
    {
        if (e.Code is "Enter" or "NumpadEnter")
        {
            await LoadMainPage();
        }
    }
    }
    

}