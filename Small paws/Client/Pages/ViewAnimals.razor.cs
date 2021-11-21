using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Client.Data;
using Client.Model;
using Microsoft.AspNetCore.Components;

namespace Client.Pages
{
    public class ViewAnimalsRazor :ComponentBase
    {
        protected IList<Animal> Animals { get; set; }
        [Inject] protected IAnimalService AnimalService { get; set; }

        protected string ShownImage;

        protected override async Task OnInitializedAsync()
        {
            try
            {
                if (await AnimalService.GetAnimalsAsync() != null)
                {
                    Animals = await AnimalService.GetAnimalsAsync();
                    ShownImage = $"data:image/jpg;base64,{Convert.ToBase64String(Animals[0].Picture)}";
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
            
        }
    }
}