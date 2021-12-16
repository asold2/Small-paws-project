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
    /// <summary>
    /// C# code for viewing adoption requests page.
    /// </summary>
    public class AdoptRequestListRazor : ComponentBase
    {
        /// <summary>
        /// A list of adoption requests.
        /// </summary>
        protected IList<AdoptionRequest> AdoptRequests{ get; set; }
        
        /// <summary>
        /// Injected adoption request service for getting and updating adoption requests.
        /// </summary>
        [Inject] protected IAdoptionRequestService AdoptionRequestService{ get; set; }
        
        /// <summary>
        /// Injected NavigationManager for navigating through pages.
        /// </summary>
        [Inject] private NavigationManager NavigationManager { get; set; }
        
        /// <summary>
        /// Injected AuthenticationStateProvider for getting veterinarian's id.
        /// </summary>
        [Inject] private AuthenticationStateProvider AuthenticationStateProvider { get; set; }

        /// <summary>
        /// Bound status text of type string array.
        /// </summary>
        protected string [] StatusText;
        /// <summary>
        /// Bound first name of type string.
        /// </summary>
        protected string FirstName;
        /// <summary>
        /// Bound last name of type string.
        /// </summary>
        protected string LastName;
        /// <summary>
        /// Bound age of type int with an option of being null.
        /// </summary>
        protected int? Age;
        /// <summary>
        /// Bound sex of type string.
        /// </summary>
        protected string Sex;

        /// <summary>
        /// Bound family status of type string.
        /// </summary>
        protected string FamilyStatus;
        /// <summary>
        /// Bound average income of type int with an option of being null.
        /// </summary>
        protected int? AvgIncome;
        /// <summary>
        /// Bound address of type string.
        /// </summary>
        protected string Address;
        /// <summary>
        /// Bound zip code of type int.
        /// </summary>
        protected int Zipcode;
        /// <summary>
        /// Bound job title of type string.
        /// </summary>
        protected string JobTitle;
        
        /// <summary>
        /// Method used for getting all adopt requests and show them when the page is loaded.
        /// </summary>
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

        /// <summary>
        /// Method used when adoption request is accepted.
        /// </summary>
        /// <param name="adoptRequest">accepted adoption request</param>
        protected async Task AcceptRequest(AdoptionRequest adoptRequest)
        {
            adoptRequest.Approve = true;
            var user = ((CustomAuthenticationStateProvider) AuthenticationStateProvider).GetCachedUser();

            var vet = await AdoptionRequestService.GetVeterinarianByIdAsync(user.UserId);
            adoptRequest.VeterinarianId = vet;
            await AdoptionRequestService.UpdateAdoptionRequest(adoptRequest);
            
            NavigationManager.NavigateTo("AdoptRequestList");
        }
        
        /// <summary>
        /// Method used when adoption request is rejected.
        /// </summary>
        /// <param name="adoptRequest">rejected adoption request</param>
        protected async Task RejectRequest(AdoptionRequest adoptRequest)
        {
            adoptRequest.Approve = false;
            var user = ((CustomAuthenticationStateProvider) AuthenticationStateProvider).GetCachedUser();

            var vet = await AdoptionRequestService.GetVeterinarianByIdAsync(user.UserId);
            adoptRequest.VeterinarianId = vet;

            await AdoptionRequestService.UpdateAdoptionRequest(adoptRequest);
            NavigationManager.NavigateTo("AdoptRequestList");
        }
        
        /// <summary>
        /// Method used for showing pet owner information for a specific adoption request.
        /// </summary>
        /// <param name="adoptionRequest">specific adoption request</param>
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