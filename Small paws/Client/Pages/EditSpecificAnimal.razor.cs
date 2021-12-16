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
    /// <summary>
    /// C# code for editing specific animal page.
    /// </summary>
    public class EditSpecificAnimalRazor : ComponentBase
    {
        /// <summary>
        /// A gotten value from the "ViewAnimals" page, acting as an identification for a specific animal.
        /// </summary>
        [Parameter]
        public string Value { get; set; }

        /// <summary>
        /// A list of animals.
        /// </summary>
        private IList<Animal> Animals { get; set; }
        /// <summary>
        /// Injected animal service for updating specific animal's information.
        /// </summary>
        [Inject] protected IAnimalService AnimalService { get; set; }
        
        /// <summary>
        /// Injected IJSRuntime for invoking asynchronous JavaScript functions.
        /// </summary>
        [Inject] private IJSRuntime JsRuntime { get; set; }
        
        /// <summary>
        /// Injected NavigationManager for navigating through pages.
        /// </summary>
        [Inject] private NavigationManager NavigationManager { get; set; }
        
        /// <summary>
        /// Bound shown image of type string.
        /// </summary>
        protected string ShownImage;
        /// <summary>
        /// Bound animal type of type string.
        /// </summary>
        protected string AnimalType;
        /// <summary>
        /// Bound age of type int with an option of being null.
        /// </summary>
        protected int? Age;
        /// <summary>
        /// Bound sex of type string with an option of being null.
        /// </summary>
        protected string? Sex;
        /// <summary>
        /// Bound id of type int with an option of being null.
        /// </summary>
        protected int? Id;
        /// <summary>
        /// Bound washed status of type bool.
        /// </summary>
        protected bool Washed;
        /// <summary>
        /// Bound fed status of type bool.
        /// </summary>
        protected bool Fed;
        /// <summary>
        /// Bound vaccinated status of type bool.
        /// </summary>
        protected bool Vaccinated;
        /// <summary>
        /// Bound picture of type byte array.
        /// </summary>
        private byte[] _picture;
        /// <summary>
        /// Bound description of type string.
        /// </summary>
        protected string Description;
        /// <summary>
        /// Bound health notes of type string.
        /// </summary>
        protected string HealthNotes;
        // ReSharper disable once UnassignedField.Global
        /// <summary>
        /// Bound washed icon of type string showing washed status.
        /// </summary>
        protected string WashedIcon;
        // ReSharper disable once UnassignedField.Global
        /// <summary>
        /// Bound fed icon of type string showing fed status.
        /// </summary>
        protected string FedIcon;
        // ReSharper disable once UnassignedField.Global
        /// <summary>
        /// Bound vaccinated icon of type string showing vaccinated status.
        /// </summary>
        protected string VaccinatedIcon;

        /// <summary>
        /// Method used for loading information about the specific animal when the page is loaded.
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

        /// <summary>
        /// Method used for uploading an image.
        /// </summary>
        /// <param name="eventArgs"></param>
        protected async Task UploadImage(InputFileChangeEventArgs eventArgs)
        {
            var sourceFile = eventArgs.File;
            _picture = new byte[sourceFile.Size];
            await sourceFile.OpenReadStream().ReadAsync(_picture);
            var imageType = sourceFile.ContentType;
            ShownImage = $"data:{imageType};base64,{Convert.ToBase64String(_picture)}";
        }
        
        /// <summary>
        /// Method used to set washed status to true.
        /// </summary>
        protected void SetWashedToTrue()
        {
            Washed = true;
        }
        /// <summary>
        /// Method used to set washed status to false.
        /// </summary>
        protected void SetWashedToFalse()
        {
            Washed = false;
        }    
        /// <summary>
        /// Method used to set fed status to true.
        /// </summary>
        protected void SetFedToTrue()
        {
            Fed = true;
        }
        /// <summary>
        /// Method used to set fed status to false.
        /// </summary>
        protected void SetFedToFalse()
        {
            Fed = false;
        }
        /// <summary>
        /// Method used to set vaccinated status to true.
        /// </summary>
        protected void SetVaccinatedToTrue()
        {
            Vaccinated = true;
        }
        /// <summary>
        /// Method used to set vaccinated status to false.
        /// </summary>
        protected void SetVaccinatedToFalse()
        {
            Vaccinated = false;
        }
        /// <summary>
        /// Method used save animal with updated values.
        /// </summary>
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
        /// <summary>
        /// Method used for navigating back to "ViewAnimals" page.
        /// </summary>
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

