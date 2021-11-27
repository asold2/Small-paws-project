using System;
using System.Collections.Generic;
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
                ShownDescription = new string[Animals.Count];
                ShownImage = new string[Animals.Count];
                    for (int i = 0; i < Animals.Count; i++)
                    {
                        ShownImage[i] = $"data:image/jpg;base64,{Convert.ToBase64String(Animals[i].Picture)}";
                        var stringSize = Encoding.ASCII.GetBytes(Animals[i].Description);
                        Console.WriteLine(stringSize.Length);
                        if (stringSize.Length > 100)
                        {
                            ShownDescription[i] = Animals[i].Description.Substring(0, 100) + "...";
                        }
                        else
                        {
                            ShownDescription[i] = Animals[i].Description;
                        }

                    }
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
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