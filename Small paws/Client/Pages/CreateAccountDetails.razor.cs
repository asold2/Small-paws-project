using System;
using System.Diagnostics;
using System.Linq;
using System.Reflection;
using System.Threading.Tasks;
using Client.Data.Registration;
using Client.Model;
using Microsoft.AspNetCore.Components;

namespace Client.Pages
{
    public abstract class CreateAccountDetailsRazor : ComponentBase
    {
        [Inject] private NavigationManager NavigationManager { get; set; }
        [Inject] private EndUser EndUser { get; set; }
        [Inject] private IUserCreateAccountService UserCreateAccountService { get; set; }

        protected string FirstName;
        protected string LastName;
        protected int? Age;
        protected string Sex;
        protected string FamilyStatus;
        protected int? AverageIncome;
        protected string Address;
        protected int? ZipCode;
        protected string JobTitle;
        protected int? Id;

        protected string IdError ="";
        protected string AvgIncomeError="";
        protected string AgeError="";
        protected string SexError = "";
        protected string AddressError = "";
        // ReSharper disable once UnusedMember.Global
        protected string FirstNameError = "";
        // ReSharper disable once UnusedMember.Global
        protected string LastNameError = "";
        // ReSharper disable once UnusedMember.Global
        protected string FamilyStatusNameError = "";
        protected string ZipCodeError = "";
        protected string JobTitleError = "";
        protected string Error = "";

        protected async Task LoadViewAnimals()
        {
            var petOwner = GetPetOwnerAfterCheckingValues(FirstName, LastName, Age, Sex, FamilyStatus, AverageIncome,
                Address, ZipCode, JobTitle, Id);

            if (!IsAnyPropertyNullOrEmpty(petOwner))
            {
                if (await UserCreateAccountService.CreateUserAsync(petOwner) == 201)
                {
                    NavigationManager.NavigateTo("");
                }
                else
                {
                    Error = UserCreateAccountService.CreateUserAsync(petOwner).Result + " Error message";
                }   
            }
        }

        private static bool IsAnyPropertyNullOrEmpty(PetOwner petOwner)
        {
            return (from pi in petOwner.GetType().GetProperties() where pi.PropertyType == typeof(string) select
                (string) pi.GetValue(petOwner)).Any(string.IsNullOrEmpty);
        }
        private PetOwner GetPetOwnerAfterCheckingValues(string fName, string lName, int? age, string sex, string familyStatus, int? averageIncome, string address, int? zipCode,
            string jobTitle, int? id)
        {
            var petOwner = new PetOwner();
            if (string.IsNullOrEmpty(fName))
            {
                FirstNameError = "First name must be filled out";
            }
            else
            {
                petOwner.FirstName = fName;
            }

            if (string.IsNullOrEmpty(lName))
            {
                LastNameError = "Last name must be filled out";
            }
            else
            {
                petOwner.LastName = lName;
            }
            if (age == null || !age.ToString().All(char.IsDigit))
            {
                AgeError = "Age is not filled out, or is not a number";
                if (Age is < 18 or > 130)
                {
                    AgeError = "Age is either too low or too high";
                }
            }
            else
            {
                petOwner.Age = (int)age;
            }

            if (sex == null || !sex.All(char.IsLetter))
            {
                SexError = "Sex should be filled out";
            }
            else
            {
                petOwner.Sex = sex;
            }

            if (string.IsNullOrEmpty(familyStatus) || !familyStatus.All(char.IsLetter))
            {
                FamilyStatusNameError = "Family status must be filled out";
            }
            else
            {
                petOwner.FamilyStatus = familyStatus;
            }
            if (averageIncome == null || !averageIncome.ToString().All(char.IsDigit))
            {
                AvgIncomeError = "Average income is not filled out, or is not a number";
                if (averageIncome < 0)
                {
                    AvgIncomeError = "Average Income cannot be less than 0";
                }
            }
            else
            {
                petOwner.AvgIncome = (int)averageIncome;
            }

            if (string.IsNullOrEmpty(address))
            {
                AddressError = "Address must be filled out";
            }
            else
            {
                petOwner.Address = address;
            }

            if (zipCode == null || !zipCode.ToString().All(char.IsDigit))
            {
                ZipCodeError = "Zip code must be filled out";
            }
            else
            {
                petOwner.ZipCode = (int)zipCode;
            }

            if (string.IsNullOrEmpty(jobTitle))
            {
                JobTitleError = "Job title must be filled out";
            }
            else
            {
                petOwner.JobTitle = jobTitle;
            }
            if(id == null || !id.ToString().All(char.IsDigit))
            {
                IdError = "Id is not filled out, or is not a number";
                if (id.ToString().Length is < 4 or > 9 || id.ToString()[0] == 0)
                {
                    IdError = "Id cannot be less than 4 digits or bigger than 9 digits, and it cannot start with 0";
                }
            }
            else
            {
                petOwner.Id = (int)id;
            }
            return petOwner;
        }

        protected void LoadLogIn()
        {
            NavigationManager.NavigateTo("");
        }
    }
}