using System;
using System.Collections.Generic;
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
    public class ViewSpecificAnimalRazor : ComponentBase
    {
        [Inject]private AuthenticationStateProvider AuthenticationStateProvider { get; set; }
        [Inject] private IAdoptionRequestService AdoptionRequestService { get; set; }

        protected AdoptionRequest Request { get; set; }




        [Parameter]
        public string Value { get; set; }
        
        

        private IList<Animal> Animals { get; set; }
        [Inject] protected IAnimalService AnimalService { get; set; }
        
        protected string ShownImage;
        protected string AnimalType;
        protected string Sex;
        protected int Age;
        protected int Id;
        private bool _washed;
        private bool _fed;
        private bool _vaccinated;
        protected string Description;
        protected string HealthNotes;
        protected string WashedIcon = "fas fa-times";
        protected string FedIcon = "fas fa-times";
        protected string VaccinatedIcon = "fas fa-times";

        protected string AnimalName;

        protected override async Task OnInitializedAsync()
        {

            try
            {
                var valueInt = Convert.ToInt32(Value);
                Console.WriteLine(valueInt + "!!!!!!!!!!!!");
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
                Approve = false,
                AnimalName = AnimalName
                
            };
            Console.WriteLine(adoptRequest.UserId+ "!!!!!!!!!!") ;
            
           
            await AdoptionRequestService.MakeNewRequestAsync(adoptRequest);
            
        }
        
  
        
        protected async Task Enter(KeyboardEventArgs e)
        {
            if (e.Code is "Enter" or "NumpadEnter")
            {
                await MakeAdoptionRequest();
            }
        }
    } 
}