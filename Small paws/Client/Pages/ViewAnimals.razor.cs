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
        [Inject] protected IDataLoader DataLoader { get; set; }

        protected override async Task OnInitializedAsync()
        {
            try
            {
                Animals = await DataLoader.GetAnimalsAsync();
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
            
        }
    }
}