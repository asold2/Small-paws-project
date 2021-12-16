using System.Collections.Generic;
using System.Threading.Tasks;
using Client.Model;
namespace Client.Data.AdoptionRequest

{
    /// <summary>
    /// An adoption request service interface
    /// </summary>
    public interface IAdoptionRequestService
    {
        /// <summary>
        /// Method used for making a post request for creating a new adoption.
        /// </summary>
        /// <param name="adoptionRequest">Created adoption request</param>
        Task MakeNewRequestAsync (Model.AdoptionRequest adoptionRequest);
        
        /// <summary>
        /// Method used for getting a list of adoption requests through a get request.
        /// </summary>
        /// <returns>A list of existing adoption requests</returns>
        Task<List<Model.AdoptionRequest>> GetAdoptionRequestsAsync();
        
        /// <summary>
        /// Method used for getting a specific pet owner with a given id through a get request.
        /// </summary>
        /// <param name="id">given id</param>
        /// <returns>pet owner with a given id</returns>
         Task<PetOwner> GetPetOwnerByIdAsync(int id);
        
        /// <summary>
        /// Method used for getting a specific veterinarian with a given id through a get request.
        /// </summary>
        /// <param name="id">given id</param>
        /// <returns>veterinarian with a given id</returns>
         Task<Veterinarian> GetVeterinarianByIdAsync(int id);
        
        /// <summary>
        /// Method used for updating information about the given adoption request. Specifically updating
        /// values of the veterinarian id and the status of the adoption request  
        /// </summary>
        /// <param name="adoptionRequest">given adoption request</param>
         Task UpdateAdoptionRequest(Model.AdoptionRequest adoptionRequest);
    }
}