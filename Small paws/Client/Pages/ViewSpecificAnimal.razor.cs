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
        protected  Animal cachedAnimal;
        [Inject]private AuthenticationStateProvider AuthenticationStateProvider { get; set; }
        [Inject] private IAdoptionRequestService _adoptionRequestService { get; set; }

        protected EndUser cachedUser = new PetOwner();
        protected AdoptionRequest Request { get; set; }




        [Parameter]
        public string Value { get; set; }
        
        

        private IList<Animal> Animals { get; set; }
        [Inject] protected IAnimalService AnimalService { get; set; }
        
        protected string ShownImage;
        protected string AnimalType;
        protected int? Age;
        protected int? Id;
        private bool _washed;
        private bool _fed;
        private bool _vaccinated;
        protected string Description;
        protected string healthNotes;
        protected string WashedIcon = "fas fa-times";
        protected string FedIcon = "fas fa-times";
        protected string VaccinatedIcon = "fas fa-times";
        protected Animal animalTemp;

        protected string AnimalName;

        protected override async Task OnInitializedAsync()
        {

            try
            {
                var valueInt = Convert.ToInt32(Value);
                Console.WriteLine(valueInt + "!!!!!!!!!!!!");
                Animals = await AnimalService.GetAnimalsAsync();
                for (int i = 0; i < Animals.Count; i++)
                {
                    
                    Animal animal = Animals[i];
                    animalTemp = animal;
                    cachedAnimal = animal;
                    if (animal.Id == valueInt)
                    {
                        ShownImage = $"data:image/jpg;base64,{Convert.ToBase64String(animal.Picture)}";
                        AnimalType = animal.AnimalType;
                        Age = animal.Age;
                        Id = animal.Id;
                        _washed = animal.Washed;
                        if (_washed)
                        {
                            WashedIcon = "fas fa-check";
                        }
                        else
                        {
                            WashedIcon = "fas fa-times";
                        }
                
                        _fed = animal.Fed;
                
                        if (_fed)
                        {
                            FedIcon = "fas fa-check";
                        }
                        else
                        {
                            FedIcon = "fas fa-times";
                        }
                
                        _vaccinated = animal.Vaccinated;
                
                        if (_vaccinated)
                        {
                            VaccinatedIcon = "fas fa-check";
                        }
                        else
                        {
                            VaccinatedIcon = "fas fa-times";
                        }
                
                        Description = animal.Description;
                        healthNotes = animal.healthNotes;
                    }
                }

                

            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
            
        }
        public async Task MakeAdoptionRequest()
        {
            
            var user = ((CustomAuthenticationStateProvider) AuthenticationStateProvider).getCachedUser();
            
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
                AnimalId = animalTemp,
                UserId = await _adoptionRequestService.GetPetOwnerByIdAsync(user.userId),
                Approve = false,
                AnimalName = AnimalName
                
            };
            Console.WriteLine(adoptRequest.UserId+ "!!!!!!!!!!") ;
            
           
            await _adoptionRequestService.MakeNewRequestAsync(adoptRequest);
            
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