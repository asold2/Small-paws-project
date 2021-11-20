using System;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using Client.Model;

namespace Client.Data.Registration
{
    public class CloudUserCreateAccountService : IUserCreateAccountService
    {
        private readonly HttpClient _httpClient;
        private const string Uri = "http://localhost:8090";

        public CloudUserCreateAccountService()
        {
            _httpClient = new HttpClient();
        }


        public async Task<int> CreateUserAsync(PetOwner petOwner)
        { 
           
            var userAsJson = JsonSerializer.Serialize(petOwner);
            HttpContent httpContent = new StringContent(
                userAsJson,
                Encoding.UTF8,
                "application/json");
            var responseMessage = await _httpClient.PostAsync(Uri + "/newAccount", httpContent);
            
            if (!responseMessage.IsSuccessStatusCode)
            {
                throw new Exception($"Error, {responseMessage.StatusCode}, {responseMessage.ReasonPhrase}");
            }
            var message = await responseMessage.Content.ReadAsStringAsync();
            var result = JsonSerializer.Deserialize<int>(message);
            return result;
        }
    }
}