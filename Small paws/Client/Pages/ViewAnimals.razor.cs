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

        protected override async Task OnInitializedAsync()
        {
            try
            {
               // Animals = await AnimalService.GetAnimalsAsync();
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
            
        }
    }
}