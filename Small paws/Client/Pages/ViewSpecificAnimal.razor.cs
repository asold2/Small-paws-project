using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Client.Data;
using Client.Model;
using Microsoft.AspNetCore.Components;

namespace Client.Pages
{
    public class ViewAnimalRazor : ComponentBase
    {
        [Parameter]
        public string Value { get; set; }

        private IList<Animal> Animals { get; set; }
        [Inject] protected IAnimalService AnimalService { get; set; }
        
        protected string ShownImage;
        protected string AnimalType;
        protected int? Age;
        protected int? Id;
        private bool _washed = true;
        private bool _fed = true;
        private bool _vaccinated = true;
        protected byte[] Picture;
        protected string Description;
        protected string WashedIcon = "fas fa-check";
        protected string FedIcon = "fas fa-times";
        protected string VaccinatedIcon = "fas fa-check";

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
                setIcon(_washed, icon: WashedIcon);
                _fed = Animals[valueInt].Fed;
                setIcon(_fed, icon: FedIcon);
                _vaccinated = Animals[valueInt].Vaccinated;
                setIcon(_vaccinated, icon: VaccinatedIcon);
                Description = Animals[valueInt].Description;

            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
            
        }

        private void setIcon(bool state, string icon)
        {
            icon = state ? "fas fa-check" : "fas fa-times";
        }
    } 
}