using System.Threading.Tasks;
using Client.Data.Registration;
using Client.Model;
using Microsoft.AspNetCore.Components;

namespace Client.Pages
{
    public abstract class CreateAccountDetailsRazor : ComponentBase
    {
        [Inject] private NavigationManager NavigationManager { get; set; }
        private CreateAccountMain CreateAccountMain { get; set; }
        [Inject] private IUserCreateAccountServices UserCreateAccountServices { get; set; }

        protected string FirstName;
        protected string LastName;
        protected int Age;
        protected string Sex;
        protected string FamilyStatus;
        protected int AverageIncome;
        protected string Address;
        // protected int ZipCode;
        protected string JobTitle;
        protected int Id;

        protected async Task LoadViewAnimals()
        {
            var petOwner = new PetOwner
            {
/*                Email = CreateAccountMain.Email,
                Username = CreateAccountMain.Username,
                Password = CreateAccountMain.Password,
                */
                FirstName = FirstName,
                LastName = LastName,
                Age = Age,
                Sex = Sex,
                FamilyStatus = FamilyStatus,
                AvgIncome = AverageIncome,
                Address = Address,
               // ZipCode = ZipCode,
                JobTitle = JobTitle,
                Id = Id
            };
            if (await UserCreateAccountServices.CreateUserAsync(petOwner))
            {
                NavigationManager.NavigateTo("ViewAnimals");
            }
        }
        
        
        protected void LoadLogIn()
        {
            NavigationManager.NavigateTo("");
        }
    }
}