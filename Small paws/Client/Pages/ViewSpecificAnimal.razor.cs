using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Client.Data;
using Client.Model;
using Microsoft.AspNetCore.Components;
using Client.Authentication;
using Client.Data.AdoptionRequest;
using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.AspNetCore.Components.Web;

namespace Client.Pages
{
    /// <summary>
    /// C# code for viewing animals page.
    /// </summary>
    public class ViewSpecificAnimalRazor : ComponentBase
    {
        /// <summary>
        /// Injected AuthenticationStateProvider for getting logged in user's information for saving it into the adoption
        /// request.
        /// </summary>
        [Inject]private AuthenticationStateProvider AuthenticationStateProvider { get; set; }
        /// <summary>
        /// Injected adoption request service for creating a new adoption request.
        /// </summary>
        [Inject] private IAdoptionRequestService AdoptionRequestService { get; set; }


        /// <summary>
        /// A gotten value from the "ViewAnimals" page, acting as an identification for a specific animal.
        /// </summary>
        [Parameter]
        public string Value { get; set; }
        
        
        /// <summary>
        /// List of all existent animals.
        /// </summary>
        private IList<Animal> Animals { get; set; }
        
        /// <summary>
        /// Injected animal service for getting all animals.
        /// </summary>
        [Inject] protected IAnimalService AnimalService { get; set; }
        
        /// <summary>
        /// Bound shown image of type string.
        /// </summary>
        protected string ShownImage;
        /// <summary>
        /// Bound animal type of type string.
        /// </summary>
        protected string AnimalType;
        /// <summary>
        /// Bound sex of type string.
        /// </summary>
        protected string Sex;
        /// <summary>
        /// Bound age of type int.
        /// </summary>
        protected int Age;
        /// <summary>
        /// Bound id of type int.
        /// </summary>
        protected int Id;
        /// <summary>
        /// Bound washed status of type bool.
        /// </summary>
        private bool _washed;
        /// <summary>
        /// Bound fed status of type bool.
        /// </summary>
        private bool _fed;
        /// <summary>
        /// Bound vaccinated status of type bool.
        /// </summary>
        private bool _vaccinated;
        /// <summary>
        /// Bound description of type string.
        /// </summary>
        protected string Description;
        /// <summary>
        /// Bound health notes of type string.
        /// </summary>
        protected string HealthNotes;
        /// <summary>
        /// Bound washed icon of type string showing washed status.
        /// </summary>
        protected string WashedIcon = "fas fa-times";
        /// <summary>
        /// Bound fed icon of type string showing fed status.
        /// </summary>
        protected string FedIcon = "fas fa-times";
        /// <summary>
        /// Bound vaccinated icon of type string showing vaccinated status.
        /// </summary>
        protected string VaccinatedIcon = "fas fa-times";

        /// <summary>
        /// Bound animal name of type string.
        /// </summary>
        protected string AnimalName;
        /// <summary>
        /// Bound animal name error of type string showing when an animal name is blank.
        /// </summary>
        protected string AnimalNameError = "";
        /// <summary>
        /// Bound animal name success of type string showing when an adoption request has been successfully made with an animal name.
        /// </summary>
        protected string AnimalNameSuccess = "";

        /// <summary>
        /// Method used to load information about the specific animal.
        /// </summary>
        protected override async Task OnInitializedAsync()
        {
            try
            {
                var valueInt = Convert.ToInt32(Value);
                Animals = await AnimalService.GetAnimalsAsync();
                foreach (var animal in Animals)
                {
                    if (animal.Id != valueInt) continue;
                    ShownImage = $"data:image/jpg;base64,{Convert.ToBase64String(animal.Picture)}";
                    AnimalType = animal.AnimalType;
                    Sex = animal.Sex;
                    Age = animal.Age;
                    Id = animal.Id;
                    _washed = animal.Washed;
                    WashedIcon = _washed ? "fas fa-check" : "fas fa-times";
                
                    _fed = animal.Fed;
                
                    FedIcon = _fed ? "fas fa-check" : "fas fa-times";
                
                    _vaccinated = animal.Vaccinated;
                
                    VaccinatedIcon = _vaccinated ? "fas fa-check" : "fas fa-times";
                
                    Description = animal.Description;
                    HealthNotes = animal.HealthNotes;
                }

                

            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
            
        }

        /// <summary>
        /// Method used to make a new adoption request for the animal.
        /// </summary>
        protected async Task MakeAdoptionRequest()
        {
            
            var user = ((CustomAuthenticationStateProvider) AuthenticationStateProvider).GetCachedUser();
            
            var animalId = Convert.ToInt32(Value);
            var tempAnimal = new Animal();

            foreach (var animal in Animals)
            {
                if (animal.Id == animalId)
                {
                    tempAnimal = animal;
                }
            }
            var adoptRequest = new AdoptionRequest
            {
                DateTime = DateTime.Now,
                AnimalId = tempAnimal,
                UserId = await AdoptionRequestService.GetPetOwnerByIdAsync(user.UserId),
                Approve = false
            };
            if (string.IsNullOrEmpty(AnimalName))
            {
                AnimalNameError = "Fill out animal's name";
            }
            else
            {
                adoptRequest.AnimalName = AnimalName;
                
            }

            if (!IsAnyPropertyNullOrEmpty(adoptRequest))
            {
                await AdoptionRequestService.MakeNewRequestAsync(adoptRequest);
                AnimalNameSuccess = "Successfully sent adoption request, you can close the window";
            }
            
            
        }
        /// <summary>
        /// Method used to check if any property for the adoption request is null or empty.
        /// </summary>
        /// <param name="adoptionRequest">given adoption request</param>
        /// <returns>true if any property is null or empty</returns>
        private static bool IsAnyPropertyNullOrEmpty(AdoptionRequest adoptionRequest)
        {
            return (from pi in adoptionRequest.GetType().GetProperties() where pi.PropertyType == typeof(string) select
                (string) pi.GetValue(adoptionRequest)).Any(string.IsNullOrEmpty);
        }
        
        /// <summary>
        /// Method used to give functionality to "Enter" keyboard button.
        /// </summary>
        /// <param name="e"></param>
        protected async Task Enter(KeyboardEventArgs e)
        {
            if (e.Code is "Enter" or "NumpadEnter")
            {
                await MakeAdoptionRequest();
            }
        }
    } 
}