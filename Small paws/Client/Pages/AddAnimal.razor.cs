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
    public abstract class AddAnimalRazor : ComponentBase
    {
        
        [Inject] private IAnimalService AnimalService { get; set; }
        [Inject] private IJSRuntime JsRuntime { get; set; }
        [Inject] private NavigationManager NavigationManager { get; set; }
        
        protected string AnimalType;
        protected int? Age;
        protected string? Sex;
        protected bool? Washed;
        protected bool? Fed;
        protected bool? Vaccinated;
        private byte[] _picture;
        protected string Description;
        protected string ShownImage = "photo_picture.png";
        protected string CreationError = "";
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