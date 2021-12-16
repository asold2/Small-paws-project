using System;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using Client.Model;

namespace Client.Data.Validation
{
    public class CloudUserLogInService : IUserLogInService
    {
        /// <summary>
        /// Uri of the 2nd tier server.
        /// </summary>
        private const string Uri = "http://localhost:8090";
        /// <summary>
        /// HttpClient used for making http requests to the 2nd tier server.
        /// </summary>
        private readonly HttpClient _httpClient;
        
        /// <summary>
        /// Constructor dependency injection.
        /// </summary>
        public CloudUserLogInService()
        {
            _httpClient = new HttpClient();
        }

        /// <inheritdoc />
        public async Task<EndUser> ValidateUserAsync(string username, string password)
        {
            var endUser = new EndUser()
            {
                UserName = username,
                Password = password,
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
            var result = JsonSerializer.Deserialize<EndUser>(message);
           
            return result;
        }
    }
}