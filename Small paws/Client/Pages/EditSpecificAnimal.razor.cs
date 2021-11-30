using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Threading.Tasks;
using Client.Data;
using Client.Model;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Forms;
using Microsoft.JSInterop;

namespace Client.Pages
{
    public class EditSpecificAnimalRazor : ComponentBase
    {
        [Parameter]
        public string Value { get; set; }

        private IList<Animal> Animals { get; set; }
        [Inject] protected IAnimalService AnimalService { get; set; }
        [Inject] private IJSRuntime JsRuntime { get; set; }
        
        [Inject] private NavigationManager NavigationManager { get; set; }
        
        protected string ShownImage;
        protected string AnimalType;
        protected int? Age;
        protected int? Id;
        protected bool Washed;
        protected bool Fed;
        protected bool Vaccinated;
        private byte[] _picture;
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
                        Washed = animal.Washed;
                        if (Washed)
                        {
                            WashedIcon = "fas fa-check";
                        }
                        else
                        {
                            WashedIcon = "fas fa-times";
                        }
                
                        Fed = animal.Fed;
                
                        if (Fed)
                        {
                            FedIcon = "fas fa-check";
                        }
                        else
                        {
                            FedIcon = "fas fa-times";
                        }
                
                        Vaccinated = animal.Vaccinated;
                
                        if (Vaccinated)
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

        protected async Task UploadImage(InputFileChangeEventArgs eventArgs)
        {
            var sourceFile = eventArgs.File;
            _picture = new byte[sourceFile.Size];
            await sourceFile.OpenReadStream().ReadAsync(_picture);
            var imageType = sourceFile.ContentType;
            ShownImage = $"data:{imageType};base64,{Convert.ToBase64String(_picture)}";
        }
        
        protected void SetWashedToTrue()
        {
            Washed = true;
        }
        protected void SetWashedToFalse()
        {
            Washed = false;
        }    
        
        protected void SetFedToTrue()
        {
            Fed = true;
        }
        protected void SetFedToFalse()
        {
            Fed = false;
        }
        
        protected void SetVaccinatedToTrue()
        {
            Vaccinated = true;
        }
        
        protected void SetVaccinatedToFalse()
        {
            Vaccinated = false;
        }

        protected async Task SaveAnimal()
        {
            _picture ??= await File.ReadAllBytesAsync(Path.GetFullPath(@"wwwroot/photo_picture.png"));
            Debug.Assert(Age != null, nameof(Age) + " != null");


            var newAnimal = new Animal
            {
                Id = (int) Id,
                Description = Description,
                Picture = _picture,
                AnimalType = AnimalType,
                Age = (int) Age,
                Washed = Washed,
                Fed = Fed,
                Vaccinated = Vaccinated
            };
            await AnimalService.UpdateAnimal(newAnimal);
            NavigationManager.NavigateTo("/ViewAnimals");
        }
        
        // ReSharper disable once UnusedParameter.Local
        // ReSharper disable once RedundantAssignment
        private static void SetIcon(bool state, string icon)
        {
            // ReSharper disable once RedundantAssignment
            icon = state ? "fas fa-check" : "fas fa-times";
        }

        protected async Task Cancel()
        {
            if (!await JsRuntime.InvokeAsync<bool>("confirm",$"Are you sure you do not want to add a new animal?"))
            {
                return;
            }
            NavigationManager.NavigateTo("ViewAnimals");
        }
        
    }
}

