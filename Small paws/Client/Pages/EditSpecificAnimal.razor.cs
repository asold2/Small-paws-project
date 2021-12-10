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
        protected string? Sex;
        protected int? Id;
        protected bool Washed;
        protected bool Fed;
        protected bool Vaccinated;
        private byte[] _picture;
        protected string Description;
        protected string HealthNotes;
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
                foreach (var animal in Animals)
                {
                    if (animal.Id != valueInt) continue;
                    ShownImage = $"data:image/jpg;base64,{Convert.ToBase64String(animal.Picture)}";
                    AnimalType = animal.AnimalType;
                    Age = animal.Age;
                    Sex = animal.Sex;
                    Id = animal.Id;
                    Washed = animal.Washed;
                    WashedIcon = Washed ? "fas fa-check" : "fas fa-times";
                
                    Fed = animal.Fed;
                
                    FedIcon = Fed ? "fas fa-check" : "fas fa-times";
                
                    Vaccinated = animal.Vaccinated;
                
                    VaccinatedIcon = Vaccinated ? "fas fa-check" : "fas fa-times";
                
                    Description = animal.Description;
                    HealthNotes = animal.HealthNotes;
                    _picture = animal.Picture;
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
                // ReSharper disable once PossibleInvalidOperationException
                Id = (int) Id,
                Description = Description,
                HealthNotes = HealthNotes,
                Picture = _picture,
                AnimalType = AnimalType,
                Age = (int) Age,
                Sex = Sex,
                Washed = Washed,
                Fed = Fed,
                Vaccinated = Vaccinated
            };
            await AnimalService.UpdateAnimal(newAnimal);
            NavigationManager.NavigateTo("/ViewAnimals");
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

