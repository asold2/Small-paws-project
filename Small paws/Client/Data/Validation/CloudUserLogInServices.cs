using System;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using Client.Model;

namespace Client.Data.Validation
{
    public class CloudUserLogInServices : IUserLogInServices
    {
        private const string Uri = "https://localhost:8090";
        private readonly HttpClient _httpClient;
        
        public CloudUserLogInServices(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }


        public async Task<bool> ValidateUserAsync(string username, string password)
        {
            var user = new User
            {
                Username = username,
                Password = password
            };
            var userAsJson = JsonSerializer.Serialize(user);
            HttpContent httpContent = new StringContent(
                userAsJson,
                Encoding.UTF8,
                "application/json");
            var responseMessage = await _httpClient.PostAsync(Uri + "/users", httpContent);
            if (!responseMessage.IsSuccessStatusCode)
            {
                throw new Exception($"Error, {responseMessage.StatusCode}, {responseMessage.ReasonPhrase}");
            }

            var message = await responseMessage.Content.ReadAsStringAsync();
            var result = JsonSerializer.Deserialize<bool>(message);
            return result;
        }
    }
}