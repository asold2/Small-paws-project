using System.Linq;
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
        private bool statement = true;

        protected string IdError ="";
        protected string AvgIncomeError="";
        protected string AgeError="";
        protected string SexError = "";
        protected string AddressError = "";
        protected string FirstNameError = "";
        protected string LastNameError = "";
        protected string FamilyStatusNameError = "";
        protected string ZipCodeError = "";
        protected string JobTitleError = "";
        protected string Error = "";

        protected async Task LoadViewAnimals()
        {
            var petOwner = GetPetOwnerAfterCheckingValues(FirstName, LastName, Age, Sex, FamilyStatus, AverageIncome,
                Address, ZipCode, JobTitle, Id);
            petOwner.Email = EndUser.Email;
            petOwner.Password = EndUser.Password;
            petOwner.UserName = EndUser.UserName;
            if (statement)
            {
                if (await UserCreateAccountService.CreateUserAsync(petOwner) == 201)
                {
                    NavigationManager.NavigateTo("/");
                }
                else
                {
                    Error = UserCreateAccountService.CreateUserAsync(petOwner).Result + " Error message";
                }   
            }

      
            
        }

  
        private PetOwner GetPetOwnerAfterCheckingValues(string fName, string lName, int? age, string sex, string familyStatus, int? averageIncome, string address, int? zipCode,
            string jobTitle, int? id)
        {
            var petOwner = new PetOwner();
            if (string.IsNullOrEmpty(fName))
            {
                statement = false;
                FirstNameError = "First name must be filled out";
            }
            else
            {
                statement = true;

                petOwner.FirstName = fName;
            }

            if (string.IsNullOrEmpty(lName))
            {
                statement = false;

                LastNameError = "Last name must be filled out";
            }
            else
            {
                statement = true;

                petOwner.LastName = lName;
            }
            if (age == null || !age.ToString().All(char.IsDigit))
            {
                statement = false;

                AgeError = "Age is not filled out, or is not a number";
                if (Age is < 18 or > 130)
                {
                    AgeError = "Age is either too low or too high";
                }
            }
            else
            {
                statement = true;

                petOwner.Age = (int)age;
            }

            if (sex == null || !sex.All(char.IsLetter))
            {
                statement = false;

                SexError = "Sex should be filled out";
            }
            else
            {
                statement = true;

                petOwner.Sex = sex;
            }

            if (string.IsNullOrEmpty(familyStatus) || !familyStatus.All(char.IsLetter))
            {
                statement = false;

                FamilyStatusNameError = "Family status must be filled out";
            }
            else
            {
                statement = true;

                petOwner.FamilyStatus = familyStatus;
            }
            if (averageIncome == null || !averageIncome.ToString().All(char.IsDigit))
            {
                statement = false;

                AvgIncomeError = "Average income is not filled out, or is not a number";
                if (averageIncome < 0)
                {
                    AvgIncomeError = "Average Income cannot be less than 0";
                }
            }
            else
            {
                statement = true;

                petOwner.AvgIncome = (int)averageIncome;
            }

            if (string.IsNullOrEmpty(address))
            {
                statement = false;

                AddressError = "Address must be filled out";
            }
            else
            {
                statement = true;

                petOwner.Address = address;
            }

            if (zipCode == null || !zipCode.ToString().All(char.IsDigit))
            {
                statement = false;

                ZipCodeError = "Zip code must be filled out";
            }
            else
            {
                statement = true;

                petOwner.ZipCode = (int)zipCode;
            }

            if (string.IsNullOrEmpty(jobTitle))
            {
                statement = false;

                JobTitleError = "Job title must be filled out";
            }
            else
            {
                statement = true;

                petOwner.JobTitle = jobTitle;
            }
            if(id == null || !id.ToString().All(char.IsDigit))
            {
                statement = false;

                IdError = "Id is not filled out, or is not a number";
                if (id.ToString().Length is < 4 or > 9 || id.ToString()[0] == 0)
                {
                    IdError = "Id cannot be less than 4 digits or bigger than 9 digits, and it cannot start with 0";
                }
            }
            else
            {
                statement = true;

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