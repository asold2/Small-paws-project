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
                var valueInt = Convert.ToInt32(Value) - 1;
                Animals = await AnimalService.GetAnimalsAsync();
                ShownImage = $"data:image/jpg;base64,{Convert.ToBase64String(Animals[valueInt].Picture)}";
                AnimalType = Animals[valueInt].AnimalType;
                Age = Animals[valueInt].Age;
                Id = Animals[valueInt].Id;
                _washed = Animals[valueInt].Washed;
                if (_washed)
                {
                    WashedIcon = "fas fa-check";
                }
                else
                {
                    WashedIcon = "fas fa-times";
                }
                
                _fed = Animals[valueInt].Fed;
                
                if (_fed)
                {
                    FedIcon = "fas fa-check";
                }
                else
                {
                    FedIcon = "fas fa-times";
                }
                
                _vaccinated = Animals[valueInt].Vaccinated;
                
                if (_vaccinated)
                {
                    VaccinatedIcon = "fas fa-check";
                }
                else
                {
                    VaccinatedIcon = "fas fa-times";
                }
                
                Description = Animals[valueInt].Description;

            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
            
        }
        
    } 
}