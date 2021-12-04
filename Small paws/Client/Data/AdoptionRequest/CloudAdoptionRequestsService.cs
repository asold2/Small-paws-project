using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using Client.Model;

namespace Client.Data.AdoptionRequest
{
    public class CloudAdoptionRequestsService : IAdoptionRequestService
    {
        private const string Uri = "http://localhost:8090";
        private readonly HttpClient _httpClient;

        public CloudAdoptionRequestsService()
        {
            _httpClient = new HttpClient();
        }


        public async Task MakeNewRequestAsync(Model.AdoptionRequest adoptionRequest)
        {
            Console.WriteLine("Making new Adoption request");
            Console.WriteLine(adoptionRequest.AnimalId.Id + " " + adoptionRequest.UserId + " " + adoptionRequest.RequestId);
            var requestAsJson = JsonSerializer.Serialize(adoptionRequest);
            HttpContent httpContent = new StringContent(
                requestAsJson,
                Encoding.UTF8,
                "application/json");
            var responseMessage = await _httpClient.PostAsync(Uri + "/newRequest", httpContent);
            Console.WriteLine();
            if (!responseMessage.IsSuccessStatusCode)
            {
                throw new Exception($"Error, {responseMessage.StatusCode}, {responseMessage.ReasonPhrase}");
            } 
        }

        public async Task<PetOwner> GetPetOwnerByIdAsync(int id)
        {
            var petOwnerAsJson = await _httpClient.GetAsync(Uri + "/user/" + id);
            if (!petOwnerAsJson.IsSuccessStatusCode)
            {
                throw new Exception("Ups something went wrong");
            }

            var message = await petOwnerAsJson.Content.ReadAsStringAsync();
            var result = JsonSerializer.Deserialize<PetOwner>(message);
            return result;
        }

        public Task<List<Model.AdoptionRequest>> GetAdoptionRequestsAsync()
        {
            throw new System.NotImplementedException();
        }
    }
}