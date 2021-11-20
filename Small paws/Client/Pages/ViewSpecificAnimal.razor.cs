using System;
using Microsoft.AspNetCore.Components;

namespace Client.Pages
{
    public class ViewAnimalRazor : ComponentBase
    {
        protected string AnimalType;
        protected int? Age;
        protected int? Id;
        protected bool Washed = true;
        protected bool Fed = true;
        protected bool Vaccinated = true;
        protected byte[] Picture;
        protected string Description;
        protected string WashedIcon = "far fa-check-circle";
        protected string FedIcon = "fas fa-times";
        protected string VaccinatedIcon = "far   fa-times-circle";

        
        
    }
    

}