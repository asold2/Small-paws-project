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
            var requestAsJson = JsonSerializer.Serialize(adoptionRequest);
            HttpContent httpContent = new StringContent(
                requestAsJson,
                Encoding.UTF8,
                "application/json");
            var responseMessage = await _httpClient.PostAsync(Uri + "/newRequest", httpContent);
            Console.WriteLine(requestAsJson);
            if (!responseMessage.IsSuccessStatusCode)
            {
                throw new Exception($"Error, {responseMessage.StatusCode}, {responseMessage.ReasonPhrase}");
            } 
        }

        public Task<List<Model.AdoptionRequest>> GetAdoptionRequestsAsync()
        {
            throw new System.NotImplementedException();
        }
    }
}