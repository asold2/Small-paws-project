﻿using System.Collections;
using System.Collections.Generic;
using System.Threading.Tasks;
using Client.Model;
using Microsoft.AspNetCore.Components;
using Client.Data.Registration;


namespace Client.Pages
{
    public class CreateAccountMainRazor : ComponentBase
    {
        [Inject] private IUserCreateAccountService UserCreateAccountService { get; set; }

        [Inject] private NavigationManager NavigationManager { get; set; }

        [Inject]
        private EndUser EndUser { get; set; }

        private string Password { get; set; }

        public static string Email { get; protected set; }
        protected string PasswordConfirmation { get; set; }

        protected string passwordError = "";

        protected string userNameError = "";
        // private IList<EndUser> users = null;

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

        protected async Task CheckUserName()
        {
            
            if (await UserCreateAccountService.checkUserName(EndUser.UserName) ==403)
            {
                userNameError = "Username already in use";
            }
            else
            {
                LoadCreateAccountDetails();
            }
        }
    }
}