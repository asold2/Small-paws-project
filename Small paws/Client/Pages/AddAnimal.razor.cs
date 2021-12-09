using System;
using System.Diagnostics;
using System.IO;
using System.Security.Permissions;
using System.Text;
using System.Threading.Tasks;
using Client.Data;
using Client.Model;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Forms;
using Microsoft.JSInterop;

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
        private bool _washed;
        private bool _fed;
        private bool _vaccinated;
        private byte[] _picture;
        protected string Description;
        protected string ShownImage = "photo_picture.png";
        protected string creationError = "";
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
            _washed = true;
        }
        protected void SetWashedToFalse()
        {
            _washed = false;
        }    
        
        protected void SetFedToTrue()
        {
            _fed = true;
        }
        protected void SetFedToFalse()
        {
            _fed = false;
        }
        
        protected void SetVaccinatedToTrue()
        {
            _vaccinated = true;
        }
        
        protected void SetVaccinatedToFalse()
        {
            _vaccinated = false;
        }

        protected async Task SaveAnimal()
        {
            _picture ??= await File.ReadAllBytesAsync(Path.GetFullPath(@"wwwroot/photo_picture.png"));
            if (Age.ToString().Length!=0 && AnimalType.Length!=0 && Description.Length!=0 && _picture.Length!=0)
            {
                var newAnimal = new Animal
                {
                    Description = Description,
                    Picture = _picture,
                    AnimalType = AnimalType,
                    Age = (int) Age,
                    Sex = Sex,
                    Washed = _washed,
                    Fed = _fed,
                    Vaccinated = _vaccinated
                };
        

                await AnimalService.AddAnimalAsync(newAnimal);
                NavigationManager.NavigateTo("/ViewAnimals");
            }
            else
            {
                creationError = "There is some data about the animal that has not been filled out!";
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