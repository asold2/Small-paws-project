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
    /// <summary>
    /// C# code for viewing animals page.
    /// </summary>
    public class ViewAnimalsRazor :ComponentBase
    {
        /// <summary>
        /// List of existing animals.
        /// </summary>
        protected IList<Animal> Animals { get; private set; }
        /// <summary>
        /// Injected animal service for getting all saved animals.
        /// </summary>
        [Inject] protected IAnimalService AnimalService { get; set; }
        /// <summary>
        /// Injected NavigationManager for navigating through pages.
        /// </summary>
        [Inject] private NavigationManager NavigationManager { get; set; }
        /// <summary>
        /// Bound shown image of type string array.
        /// </summary>
        protected string[] ShownImage;
        /// <summary>
        /// Bound shown description of type string array.
        /// </summary>
        protected string[] ShownDescription { get; set; }

        protected string NoAnimalsMessage;

        /// <summary>
        /// Method used for loading all animals when the page is loaded.
        /// </summary>
        protected override async Task OnInitializedAsync()
        {
            Animals = await AnimalService.GetAnimalsAsync();
            if (Animals.Count != 0)
            {
                ShownImage = new string[Animals.Max(a => a.Id)+1];
                ShownDescription = new string[Animals.Max(a => a.Id)+1];
                foreach (var animal in Animals)
                {
                    ShownImage[animal.Id] = $"data:image/jpg;base64,{Convert.ToBase64String(animal.Picture)}";
                    var stringSize = Encoding.ASCII.GetBytes(animal.Description);
                    if (stringSize.Length > 100)
                    {
                        ShownDescription[animal.Id] = animal.Description.Substring(0, 100) + "...";
                    }
                    else
                    {
                        ShownDescription[animal.Id] = animal.Description;
                    }
                }
                NoAnimalsMessage = "";
            }
            else
            {
                NoAnimalsMessage = "No animals in the system";
            }
                
        }

        /// <summary>
        /// Method used for opening specific animal.
        /// Used by pet owner.
        /// </summary>
        /// <param name="i">specific animal from a list of animals.</param>
        protected void OpenSpecificAnimal(int i)
        {
            NavigationManager.NavigateTo($"ViewSpecificAnimal/{i}");
        }

        /// <summary>
        /// Method used for opening specific animal with an option for editing animal information.
        /// Just for animal attendant and veterinarian.
        /// </summary>
        /// <param name="i"></param>
        protected void OpenEditSpecificAnimal(int i)
        {
            NavigationManager.NavigateTo($"EditSpecificAnimal/{i}");
        }
        
    
    }
}