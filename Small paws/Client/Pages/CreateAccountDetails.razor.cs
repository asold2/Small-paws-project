using System.Linq;
using System.Threading.Tasks;
using Client.Data.Registration;
using Client.Model;
using Microsoft.AspNetCore.Components;

namespace Client.Pages
{
    /// <summary>
    /// C# code for creating account with personal information page.
    /// </summary>
    public abstract class CreateAccountDetailsRazor : ComponentBase
    {
        /// <summary>
        /// Injected NavigationManager for navigating through pages.
        /// </summary>
        [Inject] private NavigationManager NavigationManager { get; set; }
        /// <summary>
        /// Injected EndUser for getting values from "CreateAccountMain" page.
        /// </summary>
        [Inject] private EndUser EndUser { get; set; }
        /// <summary>
        /// Injected create account service for creating an account.
        /// </summary>
        [Inject] private IUserCreateAccountService UserCreateAccountService { get; set; }

        /// <summary>
        /// Bound first name of type string.
        /// </summary>
        protected string FirstName;
        /// <summary>
        /// Bound last name of type string.
        /// </summary>
        protected string LastName;
        /// <summary>
        /// Bound age of type int with an option of being null.
        /// </summary>
        protected int? Age;
        /// <summary>
        /// Bound sex of type string.
        /// </summary>
        protected string Sex;
        /// <summary>
        /// Bound family status of type string.
        /// </summary>
        protected string FamilyStatus;
        /// <summary>
        /// Bound average income of type int with an option of being null.
        /// </summary>
        protected int? AverageIncome;
        /// <summary>
        /// Bound address of type string.
        /// </summary>
        protected string Address;
        /// <summary>
        /// Bound zip code of type int with an option of being null.
        /// </summary>
        protected int? ZipCode;
        /// <summary>
        /// Bound job title of type string.
        /// </summary>
        protected string JobTitle;
        /// <summary>
        /// Bound id of type int with an option of being null.
        /// </summary>
        protected int? Id;
        /// <summary>
        /// A field used to check if all the values in have been filled out.
        /// </summary>
        private bool _statement = true;
        
        /// <summary>
        /// Bound id error of type string shown when an id is blank or a wrong input is typed in.
        /// </summary>
        protected string IdError ="";
        /// <summary>
        /// Bound average income error in of type string when average income is blank or a wrong input is typed in.
        /// </summary>
        protected string AvgIncomeError="";
        /// <summary>
        /// Bound age error of type string when age is blank or a wrong input is typed in.
        /// </summary>
        protected string AgeError="";
        /// <summary>
        /// Bound sex error of type string when sex is blank or a wrong input is typed in.
        /// </summary>
        protected string SexError = "";
        /// <summary>
        /// Bound address error of type string when address is blank.
        /// </summary>
        protected string AddressError = "";
        /// <summary>
        /// Bound first name error of type string when first name is blank.
        /// </summary>
        protected string FirstNameError = "";
        /// <summary>
        /// Bound last name error of type string when last name is blank.
        /// </summary>
        protected string LastNameError = "";
        /// <summary>
        /// Bound family status error of type string when a family status is blank or a wrong input is typed in.
        /// </summary>
        protected string FamilyStatusNameError = "";
        /// <summary>
        /// Bound zip code error of type string when a zip code is blank or a wrong input is typed in.
        /// </summary>
        protected string ZipCodeError = "";
        /// <summary>
        /// Bound job title error of type string when a job title is blank.
        /// </summary>
        protected string JobTitleError = "";
        /// <summary>
        /// Bound error of type string when a different exception appeared.
        /// </summary>
        protected string Error = "";

        /// <summary>
        /// Method used for creating an account and loading a page with animals.
        /// </summary>
        protected async Task LoadViewAnimals()
        {
            var petOwner = GetPetOwnerAfterCheckingValues(FirstName, LastName, Age, Sex, FamilyStatus, AverageIncome,
                Address, ZipCode, JobTitle, Id);
            petOwner.Email = EndUser.Email;
            petOwner.Password = EndUser.Password;
            petOwner.UserName = EndUser.UserName;
            if (_statement)
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

  
        /// <summary>
        /// Method used for checking typed in values and giving error messages.
        /// </summary>
        /// <param name="fName"></param>
        /// <param name="lName"></param>
        /// <param name="age"></param>
        /// <param name="sex"></param>
        /// <param name="familyStatus"></param>
        /// <param name="averageIncome"></param>
        /// <param name="address"></param>
        /// <param name="zipCode"></param>
        /// <param name="jobTitle"></param>
        /// <param name="id"></param>
        /// <returns>pet owner object after checking it's values</returns>
        private PetOwner GetPetOwnerAfterCheckingValues(string fName, string lName, int? age, string sex, string familyStatus, int? averageIncome, string address, int? zipCode,
            string jobTitle, int? id)
        {
            var petOwner = new PetOwner();
            if (string.IsNullOrEmpty(fName))
            {
                _statement = false;
                FirstNameError = "First name must be filled out";
            }
            else
            {
                _statement = true;

                petOwner.FirstName = fName;
            }

            if (string.IsNullOrEmpty(lName))
            {
                _statement = false;

                LastNameError = "Last name must be filled out";
            }
            else
            {
                _statement = true;

                petOwner.LastName = lName;
            }
            if (age == null || !age.ToString().All(char.IsDigit))
            {
                _statement = false;

                AgeError = "Age is not filled out, or is not a number";
                if (Age is < 18 or > 130)
                {
                    AgeError = "Age is either too low or too high";
                }
            }
            else
            {
                _statement = true;

                petOwner.Age = (int)age;
            }

            if (sex == null || !sex.All(char.IsLetter))
            {
                _statement = false;

                SexError = "Sex should be filled out";
            }
            else
            {
                _statement = true;

                petOwner.Sex = sex;
            }

            if (string.IsNullOrEmpty(familyStatus) || !familyStatus.All(char.IsLetter))
            {
                _statement = false;

                FamilyStatusNameError = "Family status must be filled out";
            }
            else
            {
                _statement = true;

                petOwner.FamilyStatus = familyStatus;
            }
            if (averageIncome == null || !averageIncome.ToString().All(char.IsDigit))
            {
                _statement = false;

                AvgIncomeError = "Average income is not filled out, or is not a number";
                if (averageIncome < 0)
                {
                    AvgIncomeError = "Average Income cannot be less than 0";
                }
            }
            else
            {
                _statement = true;

                petOwner.AvgIncome = (int)averageIncome;
            }

            if (string.IsNullOrEmpty(address))
            {
                _statement = false;

                AddressError = "Address must be filled out";
            }
            else
            {
                _statement = true;

                petOwner.Address = address;
            }

            if (zipCode == null || !zipCode.ToString().All(char.IsDigit))
            {
                _statement = false;

                ZipCodeError = "Zip code must be filled out";
            }
            else
            {
                _statement = true;

                petOwner.ZipCode = (int)zipCode;
            }

            if (string.IsNullOrEmpty(jobTitle))
            {
                _statement = false;

                JobTitleError = "Job title must be filled out";
            }
            else
            {
                _statement = true;

                petOwner.JobTitle = jobTitle;
            }
            if(id == null || !id.ToString().All(char.IsDigit))
            {
                _statement = false;

                IdError = "Id is not filled out, or is not a number";
                if (id.ToString().Length is < 4 or > 9 || id.ToString()[0] == 0)
                {
                    IdError = "Id cannot be less than 4 digits or bigger than 9 digits, and it cannot start with 0";
                }
            }
            else
            {
                _statement = true;

                petOwner.Id = (int)id;
            }
            return petOwner;
        }

        /// <summary>
        /// Method used for navigating to the login page.
        /// </summary>
        protected void LoadLogIn()
        {
            NavigationManager.NavigateTo("");
        }
    }
}