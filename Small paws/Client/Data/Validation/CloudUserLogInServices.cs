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
        private const string Uri = "http://localhost:8090";
        private readonly HttpClient _httpClient;
        
        public CloudUserLogInServices()
        {
            _httpClient = new HttpClient();
        }


        public async Task<int> ValidateUserAsync(string username, string password)
        {
            var endUser = new EndUser()
            {
                userName = username,
                password = password,
            };
        
            var userAsJson = JsonSerializer.Serialize(endUser, new JsonSerializerOptions
            {
              PropertyNameCaseInsensitive  = true
            });
            HttpContent httpContent = new StringContent(
                userAsJson,
                Encoding.UTF8,
                "application/json");
            var responseMessage = await _httpClient.PostAsync(Uri + "/account", httpContent);
            if (!responseMessage.IsSuccessStatusCode)
            {
                throw new Exception($"Error, {responseMessage.StatusCode}, {responseMessage.ReasonPhrase}");
            }

            var message = await responseMessage.Content.ReadAsStringAsync();
            var result = JsonSerializer.Deserialize<int>(message);
            Console.WriteLine(result + "HERE!!!!!!!!!!!!!!!!!!!!");
            return result;
        }
    }
}