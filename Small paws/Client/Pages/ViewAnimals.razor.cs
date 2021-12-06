using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Client.Data;
using Client.Model;
using Microsoft.AspNetCore.Components;

namespace Client.Pages
{
    public class ViewAnimalsRazor :ComponentBase
    {
        protected IList<Animal> Animals { get; private set; }
        [Inject] protected IAnimalService AnimalService { get; set; }
        [Inject] private NavigationManager NavigationManager { get; set; }
        protected string[] ShownImage;
        protected string[] ShownDescription { get; set; }

        protected override async Task OnInitializedAsync()
        {
            
            try
            {
                Animals = await AnimalService.GetAnimalsAsync();
                ShownImage = new string[Animals.Max(a => a.Id)+1];
                ShownDescription = new string[Animals.Max(a => a.Id)+1];
                foreach (Animal animal in Animals)
                {
                    ShownImage[animal.Id] = $"data:image/jpg;base64,{Convert.ToBase64String(animal.Picture)}";
                    var stringSize = Encoding.ASCII.GetBytes(animal.Description);
                    // Console.WriteLine(stringSize.Length);
                    if (stringSize.Length > 100)
                    {
                        ShownDescription[animal.Id] = animal.Description.Substring(0, 100) + "...";
                    }
                    else
                    {
                        ShownDescription[animal.Id] = animal.Description;
                    }

                }
            }
            catch (Exception e)
            {
                // Console.WriteLine(e);
                // throw;
            }
            
        }

        protected void OpenSpecificAnimal(int i)
        {
            NavigationManager.NavigateTo($"ViewSpecificAnimal/{i}");
        }

        protected void OpenEditSpecificAnimal(int i)
        {
            NavigationManager.NavigateTo($"EditSpecificAnimal/{i}");
        }
        
    
    }
}