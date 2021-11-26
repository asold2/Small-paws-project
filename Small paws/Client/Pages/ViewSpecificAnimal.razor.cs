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
        // ReSharper disable once UnassignedField.Global
        protected string WashedIcon;
        // ReSharper disable once UnassignedField.Global
        protected string FedIcon;
        // ReSharper disable once UnassignedField.Global
        protected string VaccinatedIcon;

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
                SetIcon(_washed, icon: WashedIcon);
                _fed = Animals[valueInt].Fed;
                SetIcon(_fed, icon: FedIcon);
                _vaccinated = Animals[valueInt].Vaccinated;
                SetIcon(_vaccinated, icon: VaccinatedIcon);
                Description = Animals[valueInt].Description;

            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
            
        }

        // ReSharper disable once UnusedParameter.Local
        // ReSharper disable once RedundantAssignment
        private static void SetIcon(bool state, string icon)
        {
            // ReSharper disable once RedundantAssignment
            icon = state ? "fas fa-check" : "fas fa-times";
        }
    } 
}