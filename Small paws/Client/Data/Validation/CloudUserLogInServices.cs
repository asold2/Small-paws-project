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
        
        public CloudUserLogInServices()
        {
            _httpClient = new HttpClient();
        }


        public async Task<bool> ValidateUserAsync(string username, string password, string role)
        {
            var AuthRequest = new AuthRequest()
            {
                Username = username,
                Password = password,
                Role = role
            };
            var userAsJson = JsonSerializer.Serialize(AuthRequest);
            HttpContent httpContent = new StringContent(
                userAsJson,
                Encoding.UTF8,
                "application/json");
            var responseMessage = await _httpClient.PostAsync(Uri + "/login", httpContent);
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