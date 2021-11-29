using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Client.Data;
using Client.Model;
using Microsoft.AspNetCore.Components;

namespace Client.Pages
{
    public class ViewSpecificAnimalRazor : ComponentBase
    {
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
        protected string WashedIcon = "fas fa-times";
        protected string FedIcon = "fas fa-times";
        protected string VaccinatedIcon = "fas fa-times";

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
                    }
                }

                

            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
            
        }
        
    } 
}