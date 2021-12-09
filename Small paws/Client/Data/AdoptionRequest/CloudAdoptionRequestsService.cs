using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using Client.Model;
using Microsoft.AspNetCore.Http;

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
        
            var requestAsJson = JsonSerializer.Serialize(adoptionRequest);
            HttpContent httpContent = new StringContent(
                requestAsJson,
                Encoding.UTF8,
                "application/json");
            Console.WriteLine(requestAsJson);
            var responseMessage = await _httpClient.PostAsync(Uri + "/newRequest", httpContent);
            Console.WriteLine(responseMessage);
            if (!responseMessage.IsSuccessStatusCode)
            {
                throw new Exception($"Error, {responseMessage.StatusCode}, {responseMessage.ReasonPhrase}");
            } 
        }

        public async Task<PetOwner> GetPetOwnerByIdAsync(int id)
        {
            string message;
            PetOwner result = null;
            var petOwnerAsJson = await _httpClient.GetAsync(Uri + "/user/" + id);
            if (!petOwnerAsJson.IsSuccessStatusCode)
            {
                throw new Exception("Ups something went wrong");
            }

            Console.WriteLine(petOwnerAsJson + " problem here");
            try
            {
                message = await petOwnerAsJson.Content.ReadAsStringAsync();
                Console.WriteLine(message + " Message here");
            
                result = JsonSerializer.Deserialize<PetOwner>(message);
                Console.WriteLine(result);
            }
            catch(Exception e)
            {
                Console.WriteLine(e.Message);
            }

            // message = await petOwnerAsJson.Content.ReadAsStringAsync();
            // Console.WriteLine(message + " Message here");
            //
            // result = JsonSerializer.Deserialize<PetOwner>(message);
            return result;
        }

        public async Task<Veterinarian> GetVeterinarianByIdAsync(int id)
        {
            var veterinarianAsJson = await _httpClient.GetAsync(Uri + "/veterinarian/" + id);
            if (!veterinarianAsJson.IsSuccessStatusCode)
            {
                throw new Exception("Ups something went wrong");
            }

            var message = await veterinarianAsJson.Content.ReadAsStringAsync();
            var result = JsonSerializer.Deserialize<Veterinarian>(message);
            return result;
        }

        public async Task UpdateAdoptionRequest(Model.AdoptionRequest adoptionRequest)
        {
            Console.WriteLine(adoptionRequest.VeterinarianId.Email + " here");
            
            Console.WriteLine(adoptionRequest.AnimalId.Id + "  Animal Id here!!!");
            var adoptionRequestAsJson = JsonSerializer.Serialize(adoptionRequest);
            HttpContent httpContent = new StringContent(
                adoptionRequestAsJson,
                Encoding.UTF8,
                "application/json");
            Console.WriteLine(adoptionRequestAsJson);    
            await _httpClient.PatchAsync(Uri + "/request_decision", httpContent);
          
        }

        public async Task<List<Model.AdoptionRequest>> GetAdoptionRequestsAsync()
        {
            Console.WriteLine("getting them from second tier");
            var responseMessage = await _httpClient.GetAsync(Uri + "/requests");
            if (!responseMessage.IsSuccessStatusCode)
            {
                throw new Exception("Ups something went wrong");
            }

            var message = await responseMessage.Content.ReadAsStringAsync();
            var result = JsonSerializer.Deserialize<List<Model.AdoptionRequest>>(message);
            // Console.WriteLine(result[0].RequestId + "Id of first request from list");
            return result;
        }
    }
}