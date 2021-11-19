using System;
using System.Diagnostics;
using System.IO;
using System.Threading.Tasks;
using Client.Data;
using Client.Model;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Forms;

namespace Client.Pages
{
    public abstract class AddAnimalRazor : ComponentBase
    {
        
        [Inject] private IAnimalService AnimalService { get; set; }
        
        protected string AnimalType;
        protected int? Age;
        protected int? Id;
        protected bool Washed;
        protected bool Fed;
        protected bool Vaccinated;
        protected byte[] Picture;
        protected string Description;
        protected string ShownImage = "photo_picture.png";
        protected async Task UploadImage(InputFileChangeEventArgs eventArgs)
        {
            var sourceFile = eventArgs.File;
            var buffers = new byte[sourceFile.Size];
            await sourceFile.OpenReadStream().ReadAsync(buffers);
            string imageType = sourceFile.ContentType;
            ShownImage = $"data:{imageType};base64,{Convert.ToBase64String(buffers)}";
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
            Debug.Assert(Id != null, nameof(Id) + " != null");
            Debug.Assert(Age != null, nameof(Age) + " != null");
            
            
            var newAnimal = new Animal
            {
                Description = Description,
                Picture = Picture,
                AnimalType = AnimalType,
                Id = (int) Id,
                Age = (int) Age,
                Washed = Washed,
                Fed = Fed,
                Vaccinated = Vaccinated
            };
            await AnimalService.AddAnimalAsync(newAnimal);
        }
    
    
    }
}