using System;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Client.Data;
using Client.Model;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Forms;
using Microsoft.JSInterop;
#pragma warning disable 8632

namespace Client.Pages
{
    /// <summary>
    /// C# code for adding animal page.
    /// </summary>
    public abstract class AddAnimalRazor : ComponentBase
    {
        /// <summary>
        /// Injected animal service for adding new animals.
        /// </summary>
        [Inject] private IAnimalService AnimalService { get; set; }
        
        /// <summary>
        /// Injected IJsRuntime for calling a JavaScript functions asynchronously.
        /// </summary>
        [Inject] private IJSRuntime JsRuntime { get; set; }
        
        /// <summary>
        /// Injected NavigationManager for navigating through pages.
        /// </summary>
        [Inject] private NavigationManager NavigationManager { get; set; }
        
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
        /// Bound washed status of type bool with an option of being null.
        /// </summary>
        protected bool? Washed;
        /// <summary>
        /// Bound fed status of type bool with an option of being null.
        /// </summary>
        protected bool? Fed;
        /// <summary>
        /// Bound vaccinated status of type bool with an option of being null.
        /// </summary>
        protected bool? Vaccinated;
        /// <summary>
        /// Bound picture of type byte array.
        /// </summary>
        private byte[] _picture;
        /// <summary>
        /// Bound description of type string.
        /// </summary>
        protected string Description;
        /// <summary>
        /// Bound image that is being shown of type string.
        /// </summary>
        protected string ShownImage = "photo_picture.png";
        /// <summary>
        /// Bound error message of type string.
        /// </summary>
        protected string CreationError = "";
        
        /// <summary>
        /// Method used for uploading an image.
        /// </summary>
        protected async Task UploadImage(InputFileChangeEventArgs eventArgs)
        {
            var sourceFile = eventArgs.File;
            _picture = new byte[sourceFile.Size];
            await sourceFile.OpenReadStream().ReadAsync(_picture);
            var imageType = sourceFile.ContentType;
            ShownImage = $"data:{imageType};base64,{Convert.ToBase64String(_picture)}";
        }
        
        /// <summary>
        /// Method for setting washed status to true.
        /// </summary>
        protected void SetWashedToTrue()
        {
            Washed = true;
        }
        /// <summary>
        /// Method for setting washed status to false.
        /// </summary>
        protected void SetWashedToFalse()
        {
            Washed = false;
        }    
        
        /// <summary>
        /// Method for setting fed status to true.
        /// </summary>
        protected void SetFedToTrue()
        {
            Fed = true;
        }
        
        /// <summary>
        /// Method for setting fed status to false.
        /// </summary>
        protected void SetFedToFalse()
        {
            Fed = false;
        }
        
        /// <summary>
        /// Method for setting vaccinated status to true.
        /// </summary>
        protected void SetVaccinatedToTrue()
        {
            Vaccinated = true;
        }
        
        /// <summary>
        /// Method for setting vaccinated status to false.
        /// </summary>
        protected void SetVaccinatedToFalse()
        {
            Vaccinated = false;
        }
        
        /// <summary>
        /// Method for saving a specific animal.
        /// </summary>
        protected async Task SaveAnimal()
        {
            _picture ??= await File.ReadAllBytesAsync(Path.GetFullPath(@"wwwroot/photo_picture.png"));
            
            if (Age.ToString().All(char.IsDigit) && Age != null &&
                AnimalType != null && AnimalType.All(char.IsLetter) &&
                Sex != null && Sex.All(char.IsLetter) &&
                Washed != null && Fed != null && Vaccinated != null &&
                Description != null && _picture.Length!=0)
            {
                
                var newAnimal = new Animal
                {
                    Description = Description,
                    Picture = _picture,
                    AnimalType = AnimalType,
                    
                    Age = (int) Age,
                    Sex = Sex,
                    Washed = Washed != null && (bool) Washed,
                    Fed = Fed != null && (bool) Fed,
                    Vaccinated = Vaccinated != null && (bool) Vaccinated
                };
        

                await AnimalService.AddAnimalAsync(newAnimal);
                NavigationManager.NavigateTo("/ViewAnimals");
            }
            else
            {
                CreationError = "There is some data about the animal that has not been filled out," +
                                "or has not been filled correctly!";
            }
        }
        
        /// <summary>
        /// Method used for going back to the home page.
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