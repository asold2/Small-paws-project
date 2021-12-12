using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Client.Authentication;
using Client.Model;
using Client.Data.AdoptionRequest;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Authorization;

namespace Client.Pages
{
    public class AdoptRequestListRazor : ComponentBase
    {
        protected IList<AdoptionRequest> AdoptRequests{ get; set; }
        [Inject] protected IAdoptionRequestService AdoptionRequestService{ get; set; }
        [Inject] private NavigationManager NavigationManager { get; set; }
        [Inject] private AuthenticationStateProvider AuthenticationStateProvider { get; set; }

        protected string [] StatusText;

        protected string FirstName;
        protected string LastName;
        protected int? Age;
        protected string Sex;
        protected string FamilyStatus;
        protected int? AvgIncome;
        protected string Address;
        protected int Zipcode;
        protected string JobTitle;
        protected override async Task OnInitializedAsync()
        {
            AdoptRequests = await AdoptionRequestService.GetAdoptionRequestsAsync();
            StatusText = new string[AdoptRequests.Count];
            if (AdoptRequests.Any())
            {
                for (var i = 0; i < AdoptRequests.Count; i++)
                {
                    if (AdoptRequests[i].VeterinarianId == null)
                    {
                        StatusText[i] = "Awaiting";
                    }
                    else if (AdoptRequests[i].Approve)
                    {
                        StatusText[i] = "Approved";
                    }
                    else
                    {
                        StatusText[i] = "Rejected";
                    }

                }
            }
        }

        protected async Task AcceptRequest(AdoptionRequest adoptRequest)
        {
            adoptRequest.Approve = true;
            var user = ((CustomAuthenticationStateProvider) AuthenticationStateProvider).GetCachedUser();
            Console.WriteLine(user.UserId + "User's id here");

            var vet = await AdoptionRequestService.GetVeterinarianByIdAsync(user.UserId);
            adoptRequest.VeterinarianId = vet;
            Console.WriteLine(vet.Email + "HERE");
            await AdoptionRequestService.UpdateAdoptionRequest(adoptRequest);
            
            NavigationManager.NavigateTo("AdoptRequestList");
        }

        protected async Task RejectRequest(AdoptionRequest adoptRequest)
        {
            adoptRequest.Approve = false;
            var user = ((CustomAuthenticationStateProvider) AuthenticationStateProvider).GetCachedUser();

            var vet = await AdoptionRequestService.GetVeterinarianByIdAsync(user.UserId);
            adoptRequest.VeterinarianId = vet;

            await AdoptionRequestService.UpdateAdoptionRequest(adoptRequest);
            NavigationManager.NavigateTo("AdoptRequestList");
        }

        protected void OpenPopUpWindow(AdoptionRequest adoptionRequest)
        {
            FirstName = adoptionRequest.UserId.FirstName;
            LastName = adoptionRequest.UserId.LastName;
            Age = adoptionRequest.UserId.Age;
            Sex = adoptionRequest.UserId.Sex;
            FamilyStatus = adoptionRequest.UserId.FamilyStatus;
            AvgIncome = adoptionRequest.UserId.AvgIncome;
            Address = adoptionRequest.UserId.Address;
            Zipcode = adoptionRequest.UserId.ZipCode;
            JobTitle = adoptionRequest.UserId.JobTitle;
        }

    }
}