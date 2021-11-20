using System;
using System.ComponentModel;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using Client.Data.Registration;
using Client.Model;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Routing.Matching;
using Microsoft.VisualBasic.CompilerServices;


namespace Client.Pages
{
    public abstract class CreateAccountDetailsRazor : ComponentBase
    {
        [Inject] private NavigationManager NavigationManager { get; set; }
        [Inject] private EndUser EndUser { get; set; }
        [Inject] private IUserCreateAccountService UserCreateAccountService { get; set; }

        protected string FirstName;
        protected string LastName;
        protected int Age;
        protected string Sex;
        protected string FamilyStatus;
        protected int AverageIncome;
        protected string Address;
      //  protected int ZipCode;
        protected string JobTitle;
        protected int Id;

        protected string idError ="";
        protected string avgIncomeError="";
        protected string ageError="";
        protected string error = "";

        protected async Task LoadViewAnimals()
        {
            var petOwner = new PetOwner
            {
             //   Email = CreateAccountMain.Email,
                UserName = EndUser.UserName,
                Password = EndUser.Password,
                FirstName = FirstName,
                LastName = LastName,
                Age = Age,
                Sex = Sex,
                FamilyStatus = FamilyStatus,
                AvgIncome = AverageIncome,
                Address = Address,
              //  ZipCode = ZipCode,
                JobTitle = JobTitle,
                Id = Id
            };
            if (Age < 15 || Age > 130)
            {
                ageError = "Age is either too low or too high";
            }
            else if (AverageIncome < 0)
            {
                avgIncomeError = "Average Income cannot be less than 0";
            }
            else if (Id.ToString().Length < 4 || Id.ToString().Length > 9 || Id.ToString()[0] == 0)
            {
                idError = "Id cannot be less than 4 digits or bigger than 9 digits, and it cannot start with 0";
                
            }

            else if (await UserCreateAccountService.CreateUserAsync(petOwner) == 201)
            {
                NavigationManager.NavigateTo("ViewAnimals");
            }
            else
            {
                error = UserCreateAccountService.CreateUserAsync(petOwner).Result + " Error message";
            }

        }
        
        
        protected void LoadLogIn()
        {
            NavigationManager.NavigateTo("");
        }
    }
}