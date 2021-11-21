using System.Threading.Tasks;
using Client.Data.Registration;
using Client.Model;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Web;


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
        protected int ZipCode;
        protected string JobTitle;
        protected int Id;

        protected async Task LoadViewAnimals()
        {
            var petOwner = new PetOwner
            {
                Email = EndUser.Email,
                UserName = EndUser.UserName,
                Password = EndUser.Password,
                FirstName = FirstName,
                LastName = LastName,
                Age = Age,
                Sex = Sex,
                FamilyStatus = FamilyStatus,
                AvgIncome = AverageIncome,
                Address = Address,
                ZipCode = ZipCode,
                JobTitle = JobTitle,
                Id = Id
            };

            if (await UserCreateAccountService.CreateUserAsync(petOwner) == 201)
            {
                NavigationManager.NavigateTo("ViewAnimals");
            }
        }
        
        
        protected void LoadLogIn()
        {
            NavigationManager.NavigateTo("");
        }
        
        protected async Task Enter(KeyboardEventArgs e)
        {
            if (e.Code is "Enter" or "NumpadEnter")
            {
                await LoadViewAnimals();
            }
        }
    }
}